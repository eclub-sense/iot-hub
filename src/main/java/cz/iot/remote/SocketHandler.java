package cz.iot.remote;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by Michal on 14. 7. 2015.
 */
public class SocketHandler extends WebSocketHandler {

    private WebSocket socket;

    public SocketHandler(WebSocket socket) {
        this.socket = socket;
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.setCreator(new SocketCreator(socket));
    }
}
