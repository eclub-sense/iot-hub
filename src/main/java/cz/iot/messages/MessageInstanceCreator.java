package cz.iot.messages;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageInstanceCreator {

	public static HubMessage createMsgInstance(String json) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		JSONObject jsonObject;
		Object type = null;
		try {
			jsonObject = new JSONObject(json);
			type = jsonObject.get("type");
			} catch (JSONException e) {
			e.printStackTrace();
		}
		switch ((String)type) {
			case "NEW" : return gson.fromJson(json, HubMessage.class);
			case "LOGIN_ACK" :  return gson.fromJson(json, HubMessage.class);
			default : return null;
		}
	}

	public static String jsonRepresentation(HubMessage hubMessage) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(hubMessage);
		return json;
	}
}
