package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * This class implements the HttpHandler interface to handle HTTP requests.
 * It provides functionality to handle POST requests by storing messages in
 * memory
 * and serving them upon GET requests.
 */
public class MyHandler implements HttpHandler {
    /** A list to store messages received via POST requests. */

    private List<String> messages = new ArrayList<>();

    /**
     * Overrides the handle method of the HttpHandler interface.
     * Handles incoming HTTP requests and delegates them to specific methods based
     * on request method.
     * If the request method is not supported, it sends a "Not supported" response
     * with status code 400.
     *
     * @param exchange The HttpExchange object representing the HTTP request and
     *                 response.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            handlePost(exchange);
        } else if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            handleGet(exchange);
        } else {
            String response = "Not supported";
            byte[] bytes = response.getBytes("UTF-8");
            exchange.sendResponseHeaders(400, bytes.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }

            exchange.getResponseBody().close();
        }
    }

    /**
     * Handles POST requests by reading the request body, collecting the incoming
     * text,
     * adding it to the messages list, and sending a 200 OK response.
     *
     * @param exchange The HttpExchange object representing the HTTP request and
     *                 response.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    private void handlePost(HttpExchange exchange) throws IOException {
        InputStream stream = exchange.getRequestBody();
        String text = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        messages.add(text);
        stream.close();
        exchange.sendResponseHeaders(200, -1);
    }

    /**
     * Handles POST requests by reading the request body, collecting the incoming
     * text,
     * adding it to the messages list, and sending a 200 OK response.
     *
     * @param exchange The HttpExchange object representing the HTTP request and
     *                 response.
     * @throws IOException If an I/O error occurs while handling the request.
     */
    private void handleGet(HttpExchange exchange) throws IOException {
        StringBuilder responseStringBuilder = new StringBuilder();

        if (messages.isEmpty()) {
            responseStringBuilder.append("No messages\n");
        } else {
            for (String message : messages) {
                responseStringBuilder.append(message).append("\n");
            }
        }
        String response = responseStringBuilder.toString();
        byte[] bytes = response.getBytes("UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
        exchange.getResponseBody().close();
    }

}
