package cz.iot.utils;

import cz.iot.local.Identifier;
import org.eclipse.jetty.util.ConcurrentHashSet;

/**
 * Created by Michal on 20. 7. 2015.
 */
public class DeviceStorage {
    private static DeviceStorage ourInstance = new DeviceStorage();
    private static ConcurrentHashSet<Identifier> devices;

    public static DeviceStorage getInstance() {
        return ourInstance;
    }

    private DeviceStorage() {
        devices = new ConcurrentHashSet<>();
    }

    public static void registerDevice(Identifier id) {
        System.out.println("Registering device with uuid: "+ id.getID());
        devices.add(id);
    }

    public static boolean deviceExists(Identifier id) {
        return devices.contains(id);
    }

    public static String getDevices() {
        return devices.toString();
    }
}
