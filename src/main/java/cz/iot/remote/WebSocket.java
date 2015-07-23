package cz.iot.remote;

import cz.iot.main.Hub;
import cz.iot.messages.HubMessage;
import cz.iot.messages.MessageManager;
import cz.iot.utils.Constants;
import cz.iot.local.Identifier;
import cz.iot.utils.DeviceStorage;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.util.logging.Level;

/**
 * Created by Michal on 12. 7. 2015.
 */
public class WebSocket extends WebSocketAdapter {

    private HubClient client;

    public WebSocket(HubClient client) {
        this.client = client;
    }

    @Override
    public void onWebSocketConnect(Session sess) {
        super.onWebSocketConnect(sess);
        //System.out.println("Socket Connected: " + sess);
        Constants.LOGGER.log(Level.INFO, "Websocket created! Connection established");
    }

    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        //System.out.println("Received TEXT message: " + message);

        HubMessage msg = MessageManager.messageFromJSON(message);

        Constants.LOGGER.log(Level.INFO, "MSG:RCV: "+message);

        if(msg != null) {
            switch (msg.getType()) {
                case NEW:
                    //Register new device
                    DeviceStorage.getInstance().registerDevice(new Identifier(msg.getUuid()));
                    break;
                case LOGIN_ACK:
                        //Acknowledgement of the login
                        if(msg.getUuid().equalsIgnoreCase(Constants.HUB_UUID))
                            Constants.LOGGER.log(Level.INFO, "Logged into the cloud!");
                        else
                            Constants.LOGGER.log(Level.WARNING, "Hub UUID does not match! Ack did not pass!");
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
