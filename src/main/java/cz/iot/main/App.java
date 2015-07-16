package cz.iot.main;

import cz.iot.local.SerialDataCollector;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.utils.Constants;

import java.util.Scanner;

/**
 * Created by Michal on 9. 7. 2015.
 * Basic APP main class
 */
public class App {
    public static void main(String[] args) {
        new Hub();
    }
}
