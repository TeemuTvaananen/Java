package com.server;

import java.io.File;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import org.apache.commons.codec.digest.Crypt;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Manages the SQLite database for storing messages and user information.
 */

public class MessageDatabase {
    // the needed attributes for the database to function
    private Connection dbConnection = null;
    private static MessageDatabase dbInstance = null;
    private static SecureRandom secureRandom;
    private WeatherService service = new WeatherService();

    /**
     * Gets the singleton instance of MessageDatabase
     * 
     * @return The singleton MessageDatabase instance.
     */
    public static synchronized MessageDatabase getInstance() {

        if (dbInstance == null) {
            dbInstance = new MessageDatabase();
            secureRandom = new SecureRandom();
        }
        return dbInstance;

    }

    /**
     * Private constructor to enforce singleton pattern.
     */
    private MessageDatabase() {
    }

    /**
     * Opens a connection to the SQLite database.
     * 
     * @param dbName The name of the database file.
     * @throws SQLException If an SQL error occurs.
     */
    public void open(String dbName) throws SQLException {
        File file = new File(dbName);

        boolean fileExists = !file.exists() && !file.isDirectory();
        String connectionString = "jdbc:sqlite:" + dbName;
        try {
            dbConnection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to the database.");
            if (fileExists) {
                initializeUserTable();
                initializeMessageTable();
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Initializes the user table in the database if it does not exist.
     * 
     * @throws SQLException If an SQL error occurs.
     */
    private void initializeUserTable() throws SQLException {
        String dataBaseName = "MessageDatabase";
        String database = "jdbc:sqlite:" + dataBaseName;
        dbConnection = DriverManager.getConnection(database);

        if (null != dbConnection) {
            String createUserTable = "CREATE TABLE IF NOT EXISTS USERS (USERNAME VARCHAR(50) NOT NULL, PASSWORD VARCHAR(50) NOT NULL, EMAIL VARCHAR(50) NOT NULL, "
                    + "NICKNAME VARCHAR(50), PRIMARY KEY(USERNAME))";
            Statement createStatement = dbConnection.createStatement();
            createStatement.execute(createUserTable);
            createStatement.close();
            System.out.println("User table created");
        }
    }

    /**
     * Initializes the message table in the database if it does not exist.
     * 
     * @throws SQLException If an SQL error occurs.
     */
    private void initializeMessageTable() throws SQLException {
        String dataBaseName = "MessageDatabase";
        String database = "jdbc:sqlite:" + dataBaseName;
        dbConnection = DriverManager.getConnection(database);

        if (dbConnection != null) {
            String messagesTable = "CREATE TABLE IF NOT EXISTS MESSAGES (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "LOCATION_NAME VARCHAR(50) NOT NULL," +
                    "LOCATION_DESCRIPTION VARCHAR(255) NOT NULL," +
                    "LOCATION_CITY VARCHAR(50) NOT NULL," +
                    "LOCATION_COUNTRY VARCHAR(50) NOT NULL," +
                    "LOCATION_STREET_ADDRESS VARCHAR(255) NOT NULL," +
                    "ORIGINAL_POSTER VARCHAR(50)," +
                    "ORIGINAL_POSTING_TIME INTEGER NOT NULL," +
                    "LATITUDE VARCHAR(50)," +
                    "LONGITUDE VARCHAR(50)," +
                    "WEATHER VARCHAR(50))";

            Statement createStatement = dbConnection.createStatement();
            createStatement.execute(messagesTable);
            createStatement.close();
            System.out.println("Messages table created");
        }
    }

    /**
     * Inserts a message into the database.
     * 
     * @param message The message to insert. Json object got from the
     *                handlePost-method in MessageHandler.java
     * @throws SQLException If an SQL error occurs.
     */
    public void insertMessage(JSONObject message) throws SQLException {
        String location = message.getString("locationName");
        String description = message.getString("locationDescription");
        String city = message.getString("locationCity");
        String country = message.getString("locationCountry");
        String address = message.getString("locationStreetAddress");
        String originalPoster = message.getString("originalPoster");
        String postingTime = message.getString("originalPostingTime");
        String latitude = message.optString("latitude");
        String longitude = message.optString("longitude");
        boolean hasWeather = message.has("weather");

        // Convert posting time to Unix timestamp
        UserMessage messageTime = new UserMessage(location, description, city, country, address, originalPoster,
                postingTime, latitude, longitude);
        long unixLong = messageTime.dateAsInt();

        String insertMessageQuery = "INSERT INTO MESSAGES (LOCATION_NAME, LOCATION_DESCRIPTION, LOCATION_CITY, LOCATION_COUNTRY, LOCATION_STREET_ADDRESS, ORIGINAL_POSTER, ORIGINAL_POSTING_TIME, LATITUDE, LONGITUDE, WEATHER) VALUES ("
                + "'" + location + "',"
                + "'" + description + "',"
                + "'" + city + "',"
                + "'" + country + "',"
                + "'" + address + "',"
                + "'" + originalPoster + "',"
                + "'" + unixLong + "',"
                + "'" + latitude + "',"
                + "'" + longitude + "',";
        if (hasWeather) {
            insertMessageQuery += "'" + message.getString("weather") + "')";
        } else {
            insertMessageQuery += "'" + " - " + "')";
        }

        Statement insertStatement = dbConnection.createStatement();
        insertStatement.executeUpdate(insertMessageQuery);
        insertStatement.close();
        System.out.println("Message inserted into the database");
    }

    /**
     * Adds a new user to the database.
     * 
     * @param user The user to add.
     * @return True if the user is successfully added, false otherwise.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean setUser(JSONObject user) throws SQLException {
        if (checkIfUserExists(user.getString("username"))) {
            return false;
        }
        String hashedPassword = getHashedPassword(user);
        String setUserString = "INSERT INTO USERS " +
                "VALUES('" + user.getString("username") + "','" + hashedPassword + "','"
                + user.getString("email") + "','" + user.getString("userNickname") + "')";
        Statement createStatement;
        createStatement = dbConnection.createStatement();
        createStatement.executeUpdate(setUserString);
        createStatement.close();
        return true;
    }

    /**
     * Checks if a user with the given username already exists in the database.
     * 
     * @param username The username to check.
     * @return True if the user exists, false otherwise.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean checkIfUserExists(String test) throws SQLException {
        Statement queryStatement = null;
        ResultSet rs;
        String checkUser = "SELECT USERNAME FROM USERS WHERE USERNAME = '" + test + "'";
        queryStatement = dbConnection.createStatement();
        rs = queryStatement.executeQuery(checkUser);
        if (rs.next()) {
            System.out.println("user exists");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Authenticates a user by checking the username and password against the
     * database.
     * 
     * @param testUsername The username to authenticate.
     * @param testPassword The password to authenticate.
     * @return True if the authentication is successful, false otherwise.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean authenticateUser(String testUsername, String testPassword) throws SQLException {
        Statement queryStatement = null;
        ResultSet rs;

        String getMessagesString = "SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = '" + testUsername + "'";

        queryStatement = dbConnection.createStatement();
        rs = queryStatement.executeQuery(getMessagesString);

        if (rs.next() == false) {
            System.out.println("User not found!");
            return false;
        } else {
            String storedPassword = rs.getString("PASSWORD");
            // compare the plain password (the password to be tested) with the stored,
            // hashed password in the database(storedPassword)
            if (isSamePassword(testPassword, storedPassword)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Retrieves messages from the database.
     * 
     * @return A JSON array containing the retrieved messages.
     * @throws SQLException If an SQL error occurs.
     */
    public JSONArray getDbMessages() throws SQLException {
        Statement quaryStatement = null;
        JSONArray dbMessages = new JSONArray();

        String selectMessagesQuery = "SELECT " +
                "LOCATION_NAME, " +
                "LOCATION_DESCRIPTION, " +
                "LOCATION_CITY, " +
                "LOCATION_COUNTRY, " +
                "LOCATION_STREET_ADDRESS, " +
                "ORIGINAL_POSTER, " +
                "ORIGINAL_POSTING_TIME, " +
                "LATITUDE, " +
                "LONGITUDE, " +
                "WEATHER " +
                "FROM " +
                "MESSAGES";
        quaryStatement = dbConnection.createStatement();
        ResultSet rs = quaryStatement.executeQuery(selectMessagesQuery);

        while (rs.next()) {
            JSONObject obj = new JSONObject();
            ZonedDateTime time = ZonedDateTime.ofInstant(Instant.ofEpochMilli(rs.getLong("ORIGINAL_POSTING_TIME")),
                    ZoneOffset.UTC);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

            obj.put("locationName", rs.getString("LOCATION_NAME"));
            obj.put("locationDescription", rs.getString("LOCATION_DESCRIPTION"));
            obj.put("locationCity", rs.getString("LOCATION_CITY"));
            obj.put("locationCountry", rs.getString("LOCATION_COUNTRY"));
            obj.put("locationStreetAddress", rs.getString("LOCATION_STREET_ADDRESS"));
            obj.put("originalPoster", rs.getString("ORIGINAL_POSTER"));
            obj.put("originalPostingTime", time.format(formatter));

            System.out.println("moving to fetch the weather info!");

            // if the message contained the key weather we first check to see if it can be
            // fetched and put to the object and then place the coordinates to the object
            if (!rs.getString("LATITUDE").isEmpty() && !rs.getString("LONGITUDE").isEmpty()) {
                if (!rs.getString("WEATHER").equals(" - ")) {
                    obj.put("weather", getWeatherInfo(rs.getDouble("LATITUDE"), rs.getDouble("LONGITUDE")));
                }
                obj.put("latitude", rs.getDouble("LATITUDE"));
                obj.put("longitude", rs.getDouble("LONGITUDE"));
            }
            System.out.println(obj);
            dbMessages.put(obj);
        }

        return dbMessages;
    }

    /**
     * Retrieves weather information for a given latitude and longitude.
     * 
     * @param latitude  The latitude.
     * @param longitude The longitude.
     * @return The weather information.
     */
    private String getWeatherInfo(double latitude, double longitude) {
        return service.getWeatherInfo(latitude, longitude);

    }

    /**
     * Closes the database connection.
     * 
     * @throws SQLException If an SQL error occurs.
     */
    public void close() throws SQLException {
        if (null != dbConnection) {
            dbConnection.close();
            System.out.println("closing db connection");
            dbConnection = null;
        }
    }
     /**
     * Generates a hashed password for a user.
     * 
     * @param user The user for whom to generate the hashed password.
     * @return The hashed password.
     */
    private String getHashedPassword(JSONObject user) {
        byte[] bytes = new byte[13];
        secureRandom.nextBytes(bytes);
        String saltBytes = new String(Base64.getEncoder().encode(bytes));
        String salt = "$6$" + saltBytes;
        String hashedPassword = Crypt.crypt(user.getString("password"), salt);
        return hashedPassword;
    }

    /**
     * Compares a plain password with a salted hashed password.
     * 
     * @param plain        The plain password.
     * @param saltedHashed The salted hashed password.
     * @return True if the passwords match, false otherwise.
     */
    private boolean isSamePassword(String plain, String saltedHashed) {
        if (saltedHashed.equals(Crypt.crypt(plain, saltedHashed))) {
            return true;
        }
        return false;
    }

}
