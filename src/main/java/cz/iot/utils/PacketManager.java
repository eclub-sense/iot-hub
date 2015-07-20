package cz.iot.utils;

import cz.iot.local.Packet;
import cz.iot.main.Hub;
import cz.iot.messages.HubDataMsg;
import cz.iot.messages.MessageManager;
import cz.iot.remote.HubClient;

/**
 * Created by Michal on 16. 7. 2015.
 */
public class PacketManager {

    private HubClient client;

    public PacketManager(HubClient client) {
        this.client = client;
    }

    public synchronized void put(Packet packet) {
        if(DeviceStorage.getInstance().deviceExists(packet.getUUID())) {
            String data = serializePacket(packet);
            send(data);
        }
    }

    public String serializePacket(Packet packet) {
        return MessageManager.JSONFromMessage(new HubDataMsg(packet.getUUID().getID(), packet.getPayload()));
    }

    public synchronized void send(String data) {
        client.sendString(data);
    }

}
