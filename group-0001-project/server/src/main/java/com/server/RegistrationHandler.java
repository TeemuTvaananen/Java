package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

/**
 * Handles user registration requests.
 */
public class RegistrationHandler implements HttpHandler {

    private UserAuthenticator auth;

    /**
     * Constructor for RegistrationHandler.
     * 
     * @param newAuth The UserAuthenticator instance to use for user authentication
     *                and registration.
     */
    public RegistrationHandler(UserAuthenticator newAuth) {
        auth = newAuth;
    }

    /**
     * Handles HTTP requests for user registration.
     * @param exchange The HTTP exchange object representing the request and response.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Headers headers = exchange.getRequestHeaders();
        String contentType = "";
        String response = "";
        int code = 200;
        JSONObject obj = null;
        System.out.println("Entering registration handle");

        try {
            System.out.println("enterint try");
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                if (headers.containsKey("Content-Type")) {
                    contentType = headers.get("Content-Type").get(0);

                } else {
                    code = 411;
                    response = "No content type in request";
                }
                if (contentType.equalsIgnoreCase("application/json")) {
                    InputStream stream = exchange.getRequestBody();

                    @SuppressWarnings("resource")
                    String newUser = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                            .collect(Collectors.joining("\n"));
                    stream.close();

                    if (newUser == null || newUser.length() == 0) {

                        code = 412;
                        response = "no user credentials";

                    } else {
                        try {
                            obj = new JSONObject(newUser);
                        } catch (JSONException e) {
                            System.out.println("json parse error, faulty user json");
                        }

                        if (obj.getString("username").length() == 0 || obj.getString("password").length() == 0) {
                            code = 413;
                            response = "no proper user credentials";
                        } else {
                            Boolean result = auth.addUser(obj.getString("username"), obj.getString("password"),
                                    obj.getString("email"), obj.getString("userNickname"));
                            if (result == false) {
                                code = 405;
                                response = "user already exist";
                            } else {
                                code = 200;
                                response = "User registered";
                            }
                        }
                    }
                    byte[] bytes = response.getBytes("UTF-8");
                    exchange.sendResponseHeaders(code, bytes.length);
                    OutputStream s = exchange.getResponseBody();
                    s.write(bytes);
                    s.flush();
                    s.close();
                } else {
                    code = 407;
                    response = "content type is not application/json";
                }
            } else {
                System.out.println("Other than POST detected");
                code = 401;
                response = "only POST is accepted";
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            code = 500;
            response = "Internal server error";

        }
        if (code >= 400) {
            byte[] bytes = response.getBytes("UTF-8");
            exchange.sendResponseHeaders(code, bytes.length);
            OutputStream stream = exchange.getResponseBody();
            stream.write(bytes);
            stream.close();
        }
    }
}
