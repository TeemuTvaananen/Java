package com.server;

import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Authenticates users and manages user-related operations.
 */

public class UserAuthenticator extends com.sun.net.httpserver.BasicAuthenticator {
    // instance of the messagedatabase
    private MessageDatabase db = null;

    /**
     * Constructor for UserAuthenticator.
     * 
     * @param realm The realm for which authentication is required.
     */
    public UserAuthenticator(String realm) {
        super(realm);
        this.db = MessageDatabase.getInstance();
    }

    /**
     * Checks the credentials of a user.
     * 
     * @param username The username to authenticate.
     * @param password The password associated with the username.
     * @return True if the user is authenticated, false otherwise.
     */
    @Override
    public boolean checkCredentials(String username, String password) {
        boolean isValidUser;
        try {
            isValidUser = db.authenticateUser(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return isValidUser;
    }

    /**
     * Adds a new user to the database.
     * 
     * @param userName     The username of the new user.
     * @param password     The password of the new user.
     * @param email        The email address of the new user.
     * @param userNickname The nickname of the new user.
     * @return True if the user is successfully added, false otherwise.
     * @throws JSONException If there is an error creating the JSON object.
     * @throws SQLException  If there is an error executing SQL queries.
     */
    public boolean addUser(String userName, String password, String email, String userNickname)
            throws JSONException, SQLException {
        boolean result = db.setUser(new JSONObject().put("username", userName).put("password", password)
                .put("email", email).put("userNickname", userNickname));
       
        return result;
    }
}
