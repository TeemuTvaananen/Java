package com.server;

import com.sun.net.httpserver.*;

import java.io.FileInputStream;

import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

/**
 * Represents the HTTP server for handling user messages and registrations over
 * HTTPS.
 */
public class Server {

    private Server() {
    }

    /**
     * Main method to start the server.
     * 
     * @param args Command line arguments containing the path to the keystore file
     *             and its password.
     * @throws Exception If an error occurs during server setup.
     */
    public static void main(String[] args) throws Exception {
        try {
            // creating a HttpsServer listening to port 8001
            HttpsServer server = HttpsServer.create(new InetSocketAddress(8001), 0);

            // getting the necessary sslContext
            SSLContext sslContext = myServerSSLContext(args[0], args[1]);
            // configuring the server witht he sslContext
            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    InetSocketAddress remote = params.getClientAddress();
                    SSLContext c = getSSLContext();
                    SSLParameters sslparams = c.getDefaultSSLParameters();
                    params.setSSLParameters(sslparams);
                }

            });
            // Initializing the database as a singleton from the class MessageDatabase, this
            // initialization creates a database called MessageDatabase
            MessageDatabase.getInstance().open("MessageDatabase");

            // Set up context and handlers
            /*
             * We need the handlers for the message and registration with the
             * userAuthenticator to only
             * make the operations available for authenticated users
             */
            UserAuthenticator userAuthenticator = new UserAuthenticator("/info");
            HttpContext context = server.createContext("/info", new MessageHandler());
            context.setAuthenticator(userAuthenticator);
            context = server.createContext("/registration", new RegistrationHandler(userAuthenticator));

            // Server start
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates SSLContext for the server using the provided keystore and password.
     * 
     * @param keyStoreLocation The location of the keystore file.
     * @param keyStorePassword The password for the keystore.
     * @return SSLContext for the server.
     * @throws Exception If an error occurs during SSLContext creation.
     */

    private static SSLContext myServerSSLContext(String keyStoreLocation, String keyStorePassword) throws Exception {
        char[] passphrase = keyStorePassword.toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(keyStoreLocation), passphrase);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, passphrase);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        SSLContext ssl = SSLContext.getInstance("TLS");
        ssl.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return ssl;
    }

}
