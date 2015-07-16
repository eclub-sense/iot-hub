package cz.iot.remote;

import cz.iot.local.Packet;
import cz.iot.main.Hub;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

/**
 * Created by Michal on 12. 7. 2015.
 */
public class WebSocket extends WebSocketAdapter {

    private Hub hub;
    private HubClient client;

    public WebSocket(Hub hub, HubClient client) {
        this.hub = hub;
        this.client = client;
    }

    @Override
    public void onWebSocketConnect(Session sess) {
        super.onWebSocketConnect(sess);
        System.out.println("Socket Connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        System.out.println("Received TEXT message: " + message);

        //Device registration basics
        System.out.println("register1");
        if(message.substring(message.lastIndexOf(':')+2).equalsIgnoreCase("register")) {
            //Register device

        }
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);

    }

    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }

    @Override
    public String toString() {
        return "Event socket!";
    }
}
