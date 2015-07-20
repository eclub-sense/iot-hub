package cz.iot.remote;

import cz.iot.local.Packet;
import cz.iot.local.SerialDataCollector;
import cz.iot.utils.Constants;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Set;
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

            if(session == null)
                setupSession();

            if(session.isOpen())
                session.getRemote().sendString(message);

            } catch (IOException e) {
            Constants.LOGGER.log(Level.INFO, "Could not connect to URI!");
        }
    }

    public synchronized void sendBytes(byte[] bytes) {
        try {
            if(session == null)
                setupSession();

            if(session.isOpen())
                session.getRemote().sendBytes(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            Constants.LOGGER.log(Level.INFO, "Could not connect to URI!");
        }
    }

    public synchronized void setupSession(){

        try {
            Future<Session> got = client.connect(webSocket, uriToServer);
            session = got.get();
        } catch (InterruptedException e) {
            Constants.LOGGER.log(Level.INFO, "Connection interrupted!");
        } catch (ExecutionException e) {
            Constants.LOGGER.log(Level.INFO, "Connection lost!");
        } catch (IOException e) {
            Constants.LOGGER.log(Level.INFO, "Could not conenct");
        }
    }

    public synchronized void closeSession() {
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

