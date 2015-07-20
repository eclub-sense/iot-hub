package cz.iot.messages;

import com.google.gson.annotations.Expose;
import cz.iot.utils.Constants;

public class HubDataMsg extends HubMessage {
	
	@Expose private String data;

	public HubDataMsg(String data) {
		super(HubMessageType.DATA, Constants.HUB_UUID);
		this.data = data;
	}
	
	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return "HubDataMsg [data=" + data + ", type=" + type + ", uuid=" + uuid + "]";
	}
}
