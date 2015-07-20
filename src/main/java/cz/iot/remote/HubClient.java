package cz.iot.remote;

import cz.iot.utils.Constants;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

/**
 * Created by Michal on 12. 7. 2015.
 */
public class HubClient implements Runnable {

    private WebSocketClient client;
    private URI uriToServer;
    private Session session;
    private WebSocket webSocket;

    public HubClient(WebSocket webSocket) {
        //Target for the client websocket
        uriToServer = URI.create("ws://"+Constants.SERVER_LINK+"/events/");
        this.webSocket = webSocket;
    }

    public boolean initClient() {

        client =  new WebSocketClient();
        try {
            client.start();
        } catch (Exception e) {
            Constants.LOGGER.log(Level.INFO, "Could not start the client!");
            return false;
        }
        return true;
    }

    public void close() {
        if(client != null) {
            try {
                client.stop();
            } catch (Exception e) {
                Constants.LOGGER.log(Level.INFO, "Could not stop the client!");
            }
            Constants.LOGGER.log(Level.INFO, "Client stopped successfully");
        }
    }

    public synchronized void sendString(String message) {
        try {

            //If we are not connected setup new session
            if(session == null)
                setupSession();

            //Check if the session is open
            if(session.isOpen())
                session.getRemote().sendString(message);
            else
                Constants.LOGGER.log(Level.WARNING, "No session opened. Could not send data!");

            } catch (IOException e) {
            Constants.LOGGER.log(Level.WARNING, "Could not connect to URI!");
        }
    }

    public void setupSession(){

        try {
            //Setup the session
            Future<Session> got = client.connect(webSocket, uriToServer);
            session = got.get();
        } catch (Throwable t) {
            Constants.LOGGER.log(Level.WARNING, "Could not Set up session!");
            if(Constants.PRINT_STACKTRACE)
                t.printStackTrace();
        }
    }

    public void closeSession() {
        if(session != null)
            session.close();
    }

    public void run() {
        if(initClient()) {
            Constants.LOGGER.log(Level.INFO, "Client successfully initialized!");

            //Connect websocket with cloud
            if(Constants.CONNECT_ON_START)
                setupSession();
        }
    }
}

