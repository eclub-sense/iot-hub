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

import java.util.Scanner;
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

    public Hub() {

        //Setup WebSocket and Server
        webSocket = new WebSocket(this,hubClient);
        server = new HubServer(Constants.PORT, webSocket);
        //Setup Client
        hubClient = new HubClient(webSocket);

        //Setup Data Manager
        manager = new DataManager(hubClient, this);

        //Setup collector
        collector = new FakeDataCollector(manager);

        new Thread(server).start();
        new Thread(hubClient).start();
        new Thread(collector).start();


        //Test
        Scanner scan = new Scanner(System.in);
        while(true) {
            if(scan.next().equalsIgnoreCase("send"))
                manager.put(new Packet("00000001", "654564598797897896767545"));
        }
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

}
