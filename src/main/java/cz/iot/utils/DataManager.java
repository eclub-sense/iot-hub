package cz.iot.utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import cz.iot.local.Packet;
import cz.iot.main.Hub;
import cz.iot.remote.HubClient;

/**
 * Created by Michal on 16. 7. 2015.
 */
public class DataManager {

    private HubClient client;
    private Hub hub;

    public DataManager(HubClient client, Hub hub) {
        this.client = client;
        this.hub = hub;
    }

    public synchronized void put(Packet packet) {
        if(hub.deviceExists(packet.getUUID())) {

            String data = serializePacket(packet);
            send(data);
        }
    }

    public String serializePacket(Packet packet) {

        JsonObject jsonPacket = new JsonObject();
        jsonPacket.addProperty("type", "DATA");
        jsonPacket.addProperty("uuid", packet.getUUID().getID());
        jsonPacket.addProperty("data", packet.getPayload());

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        return gson.toJson(jsonPacket);
    }

    public synchronized void send(String data) {
        client.sendString(data);
    }

}
