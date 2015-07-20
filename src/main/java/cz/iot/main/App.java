package cz.iot.main;

import com.google.gson.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import cz.iot.local.Packet;
import cz.iot.local.SerialDataCollector;
import cz.iot.messages.*;
import cz.iot.remote.HubClient;
import cz.iot.remote.HubServer;
import cz.iot.utils.Constants;
import cz.iot.utils.DataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        Hub hub = new Hub("1111", 2556, "User", "123");

        //Test
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while(!(line = reader.readLine()).equalsIgnoreCase("kill")) {
                if (line.equalsIgnoreCase("send1")) {
                    hub.getClient().sendString(MessageInstanceCreator.createJsonRepresentation
                            (new HubLoginMsg(Constants.USERNAME, Constants.PASSWORD)));
                } else if (line.equalsIgnoreCase("reconnect1")) {
                    hub.getClient().setupSession();
                } else if (line.equalsIgnoreCase("close1")) {
                    hub.getClient().closeSession();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
