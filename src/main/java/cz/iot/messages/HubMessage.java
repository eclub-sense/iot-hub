package cz.iot.messages;

import com.google.gson.annotations.Expose;

public class HubMessage {

	@Expose protected HubMessageType type;
	@Expose protected String uuid;
	
	public HubMessage() {
		super();
	}

	public HubMessage(HubMessageType type, String UUID) {
		this.type = type;
		this.uuid = UUID;
	}
	
	public HubMessageType getType() {
		return type;
	}
	
	public void setType(HubMessageType type) {
		this.type = type;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public int getIntUuid() {
		return Integer.parseInt(uuid);
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
