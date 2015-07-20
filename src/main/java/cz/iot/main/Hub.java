package cz.iot.main;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import cz.iot.local.DataCollector;
import cz.iot.local.FakeDataCollector;
import cz.iot.local.SerialDataCollector;
import cz.iot.messages.HubLoginMsg;
import cz.iot.messages.MessageInstanceCreator;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.remote.WebSocket;
import cz.iot.utils.Constants;
import cz.iot.utils.DataManager;
import cz.iot.utils.Identifier;
import org.eclipse.jetty.util.ConcurrentHashSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;


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

    public Hub(String UUID, int port, String username, String password) {

        Constants.setTestData(UUID, port, username, password);

        //Setup WebSocket and Server
        webSocket = new WebSocket(this,hubClient);
        server = new HubServer(Constants.PORT, webSocket);
        //Setup Client
        hubClient = new HubClient(webSocket);

        //Setup Data Manager
        manager = new DataManager(hubClient, this);

        //Setup collector
        collector = new SerialDataCollector(manager);

        new Thread(server).start();
        new Thread(hubClient).start();
        new Thread(collector).start();
        System.out.println("Running hub: "+Constants.HUB_UUID);

    }

    public void registerDevice(Identifier UUID) {
        System.out.println("Registering device with uuid: "+ UUID.getID());
        this.devices.add(UUID);
    }

    public boolean deviceExists(Identifier UUID) {
        return devices.contains(UUID);
    }

    public Set<Identifier> getDevices() {
        return this.devices;
    }

    public HubClient getClient() {
        return hubClient;
    }

}
