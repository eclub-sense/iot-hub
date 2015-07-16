package cz.iot.main;

import cz.iot.local.DataCollector;
import cz.iot.local.FakeDataCollector;
import cz.iot.local.Packet;
import cz.iot.local.SerialDataCollector;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.remote.WebSocket;
import cz.iot.utils.Constants;
import cz.iot.utils.DataManager;
import cz.iot.utils.Identifier;
import org.eclipse.jetty.util.ConcurrentHashSet;


/**
 * Class representing a HUB
 * Created by Michal on 13. 7. 2015.
 */
public class Hub {

    private HubClient hubClient;
    private ConcurrentHashSet<Identifier> devices = new ConcurrentHashSet<>();
    private DataCollector collector;
    private WebSocket webSocket;
    private HubServer server;
    private DataManager manager;

    public Hub() {

        //Setup WebSocket and Server
        webSocket = new WebSocket(this,hubClient);
        server = new HubServer(Constants.PORT, webSocket);
        //Setup Client
        hubClient = new HubClient(webSocket);

        //Setup Data Manager
        manager = new DataManager(this);

        //Setup collector
        collector = new FakeDataCollector(manager);

        new Thread(server).start();
        new Thread(hubClient).start();
        new Thread(collector).start();
    }

    public void registerDevice(Identifier UUID) {
        this.devices.add(UUID);
    }

    public boolean deviceExists(Identifier UUID) {
        return devices.contains(UUID);
    }

    public void sendData(byte[] data) {
        hubClient.sendBytes(data);
    }
}
