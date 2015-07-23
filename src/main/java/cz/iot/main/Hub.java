package cz.iot.main;

import cz.iot.local.DataManager;
import cz.iot.local.FakeDataManager;
import cz.iot.local.SerialDataManager;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.remote.WebSocket;
import cz.iot.utils.Constants;
import cz.iot.utils.PacketManager;
import cz.iot.local.Identifier;
import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.Set;


/**
 * Class representing a HUB
 * Created by Michal on 13. 7. 2015.
 */
public class Hub {

    private HubClient hubClient;
    private ConcurrentHashSet<Identifier> devices = new ConcurrentHashSet<>();
    private DataManager manager;
    private WebSocket webSocket;
    private HubServer server;
    private PacketManager packetManager;

    public Hub(String UUID, int port, String username, String password) {

        Constants.setTestData(UUID, port, username, password);

        //Setup WebSocket and Server
        webSocket = new WebSocket(hubClient);
        server = new HubServer(Constants.PORT, webSocket);
        //Setup Client
        hubClient = new HubClient(webSocket);

        //Setup Data Manager
        packetManager = new PacketManager(hubClient);

        //Setup collector
        manager = new SerialDataManager(packetManager);

        new Thread(server).start();
        new Thread(hubClient).start();
        new Thread(manager).start();

    }

    public HubClient getClient() {
        return hubClient;
    }

}
