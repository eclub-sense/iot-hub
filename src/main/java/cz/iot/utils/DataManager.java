package cz.iot.utils;

import com.google.gson.Gson;
import cz.iot.local.Packet;
import cz.iot.main.Hub;

/**
 * Created by Michal on 16. 7. 2015.
 */
public class DataManager {

    private Hub hub;

    public DataManager(Hub hub) {
        this.hub = hub;
    }

    public void put(Packet packet) {

    }

    public byte[] serializePacket(Packet packet) {

        // TO DO

        return null;
    }

}
