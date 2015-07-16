package cz.iot.main;

import com.google.gson.*;
import cz.iot.local.Packet;
import cz.iot.local.SerialDataCollector;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.utils.Constants;
import cz.iot.utils.DataManager;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Michal on 9. 7. 2015.
 * Basic APP main class
 */
public class App {
    public static void main(String[] args) {
        new Hub();
    }
}
