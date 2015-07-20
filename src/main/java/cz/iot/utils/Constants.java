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

    public static final int PORT                    = 2555;
    public static final Logger LOGGER               = Logger.getLogger("Server logger");
    public static final String SERVER_LINK          = "192.168.201.222:8080";
    public static final boolean CONNECT_ON_START    = true;
    //public static final String SERVER_LINK  = "localhost:2555";

    //LOGIN
    public static final String HUB_UUID = "111";
    public static final String USERNAME = "User";
    public static final String PASSWORD = "123";
    public static String loginMessage = "";

    public static void config() {
        JsonObject jsonPacket = new JsonObject();
        jsonPacket.addProperty("type", "LOGIN");
        jsonPacket.addProperty("uuid", HUB_UUID);
        jsonPacket.addProperty("username", USERNAME);
        jsonPacket.addProperty("password", PASSWORD);

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        loginMessage = gson.toJson(jsonPacket);
    }
}
