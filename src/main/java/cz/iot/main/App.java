package cz.iot.main;

import cz.iot.messages.*;
import cz.iot.utils.Constants;
import cz.iot.utils.DeviceStorage;

import javax.activation.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michal on 9. 7. 2015.
 * Basic APP main class
 */
public class App {
    public static void main(String[] args) {
        Hub hub = new Hub("00001111", 2556, "User", "123");

        //Test
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while(!(line = reader.readLine()).equalsIgnoreCase("kill")) {
                if (line.equalsIgnoreCase("login")) {
                    hub.getClient().sendString(MessageManager.JSONFromMessage
                            (new HubLoginMsg(Constants.USERNAME, Constants.PASSWORD)));
                } else if (line.equalsIgnoreCase("connect")) {
                    hub.getClient().setupSession();
                } else if (line.equalsIgnoreCase("close")) {
                    hub.getClient().closeSession();
                } else if (line.equalsIgnoreCase("list")) {
                    System.out.println(DeviceStorage.getInstance().getDevices());
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        hub.getClient().closeSession();
        hub.getClient().close();
    }
}
