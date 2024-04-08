package com.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.time.format.DateTimeParseException;

/**
 * * This class implements the HttpHandler interface to handle HTTP requests.
 * It provides functionality to handle POST requests by storing messages in
 * a database and serving them upon GET requests.
 */
public class MessageHandler implements HttpHandler {
    // instance of the messageDatabase used in the operations
    MessageDatabase db = MessageDatabase.getInstance();

    /**
     * Handles HTTP requests.
     * 
     * @param exchange The HTTP exchange object representing the request and
     *                 response.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            handlePost(exchange);

        } else if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            handleGetMessages(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }

    }

    /**
     * Handles POST requests for inserting a new message.
     * 
     * @param exchange The HTTP exchange object representing the request and
     *                 response.
     * @throws IOException If an I/O error occurs.
     */
    @SuppressWarnings("resource")
    public void handlePost(HttpExchange exchange) throws IOException {
        System.out.println("entering post!");
        InputStream stream = exchange.getRequestBody();
        String text = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        try {
            JSONObject obj = new JSONObject(text);
            db.insertMessage(obj);
            exchange.sendResponseHeaders(200, -1);
        } catch (SQLException e) {
            exchange.sendResponseHeaders(500, -1); // SQL exception
        } catch (JSONException je) {
            exchange.sendResponseHeaders(400, -1); // JSON exception
        } catch (DateTimeParseException dtpe) {
            exchange.sendResponseHeaders(400, -1); // error when parsing date
        }
    }

    /**
     * Handles GET requests for retrieving messages.
     * 
     * @param exchange The HTTP exchange object representing the request and
     *                 response.
     * @throws IOException If an I/O error occurs.
     */
    private void handleGetMessages(HttpExchange exchange) throws IOException {
        System.out.println("GET detected");
        String response = "";
        try {
            JSONArray responseMessage = new JSONArray();
            System.out.println("gettin the object");
            responseMessage = db.getDbMessages();
            System.out.println("got the object");
            if (responseMessage.isEmpty()) {
                exchange.sendResponseHeaders(204, -1); // no content
                return;
            }
            response = responseMessage.toString();
            byte[] bytes = response.getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length); // OK

            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (SQLException e) {
            exchange.sendResponseHeaders(500, -1); // SQL exception
        }

    }

}