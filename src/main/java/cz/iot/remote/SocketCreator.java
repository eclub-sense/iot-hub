package cz.iot.remote;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Michal on 16. 7. 2015.
 */
public class SocketCreator implements WebSocketCreator {

    private WebSocket socket;

    public SocketCreator(WebSocket socket) {
        this.socket = socket;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        return socket;
    }
}
