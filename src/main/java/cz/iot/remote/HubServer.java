package cz.iot.remote;

import cz.iot.utils.Constants;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;

import java.util.logging.Level;

/**
 * Created by Michal on 12. 7. 2015.
 */
public class HubServer implements Runnable{

    private int port;
    private Server server;
    private WebSocket webSocket;

    public HubServer(int port, WebSocket webSocket) {
        this.port = port;
        this.webSocket = webSocket;
    }

    public boolean initServer() {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        SocketHandler handler = new SocketHandler(webSocket);
        ContextHandler context = new ContextHandler();
        context.setContextPath("/events");
        context.setHandler(handler);
        server.setHandler(context);

        try {
            server.start();
            //server.dump(System.err);
        } catch (Exception e) {
            Constants.LOGGER.log(Level.INFO, "Could not start the server!");
            return false;
        }

        return true;
    }

    public void close() {
        if(server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                Constants.LOGGER.log(Level.INFO, "Could not stop the server!");
            }
            Constants.LOGGER.log(Level.INFO, "Server stopped succesfully!");
        }
    }

    public void run() {
        if(initServer()) {
            Constants.LOGGER.log(Level.INFO, "Server running at " + this.port);
            try {
                server.join();
            } catch (InterruptedException e) {
                Constants.LOGGER.log(Level.WARNING, "Server crashed!");
            }
        }
    }
}
