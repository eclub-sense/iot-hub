package cz.iot.remote;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cz.iot.local.Packet;
import cz.iot.main.Hub;
import cz.iot.messages.HubMessage;
import cz.iot.messages.HubMessageType;
import cz.iot.messages.MessageInstanceCreator;
import cz.iot.utils.Constants;
import cz.iot.utils.Identifier;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.util.logging.Level;

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

        HubMessage msg = MessageInstanceCreator.createMsgInstance(message);
        System.out.println(msg);
        if(msg != null) {
            switch (msg.getType()) {
                case NEW:
                    hub.registerDevice(new Identifier(msg.getUuid()));
                    break;
                case LOGIN_ACK:
                        System.out.println("!!!!!! UUID: "+msg.getUuid());
                        Constants.LOGGER.log(Level.INFO, "Logged into the cloud!");
                    break;

            }
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
