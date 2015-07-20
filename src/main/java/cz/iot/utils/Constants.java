package cz.iot.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.logging.Logger;

/**
 * Created by Michal on 9. 7. 2015.
 */
public class Constants {

    public static int PORT                          = 2555;
    public static final Logger LOGGER               = Logger.getLogger("Server logger");
    public static final String SERVER_LINK          = "192.168.201.222:8080";
    public static final boolean CONNECT_ON_START    = true;
    //public static final String SERVER_LINK  = "localhost:2555";
    public static final boolean PRINT_STACKTRACE    = false;

    //LOGIN
    public static String HUB_UUID = "111";
    public static String USERNAME = "User";
    public static String PASSWORD = "123";

    public static void setTestData(String UUID, int port, String username, String password) {
        HUB_UUID = UUID;
        PORT = port;
        USERNAME = username;
        PASSWORD = password;
    }

}
