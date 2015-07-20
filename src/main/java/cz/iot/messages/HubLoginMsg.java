package cz.iot.messages;

import com.google.gson.annotations.Expose;
import cz.iot.utils.Constants;

public class HubLoginMsg extends HubMessage{

	@Expose private String username;
	@Expose private String password;

	public HubLoginMsg(String username, String password) {
		super(HubMessageType.LOGIN, Constants.HUB_UUID);
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "HubLoginMsg [username=" + username + ", password=" + password + ", type=" + type + ", uuid=" + uuid
				+ "]";
	}
}
