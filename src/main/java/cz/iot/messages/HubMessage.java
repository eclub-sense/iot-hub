package cz.iot.messages;

import com.google.gson.annotations.Expose;

public class HubMessage {

	@Expose protected MessageType type;
	@Expose protected String uuid;
	
	public HubMessage() {
		super();
	}

	public HubMessage(MessageType type, String UUID) {
		this.type = type;
		this.uuid = UUID;
	}
	
	public MessageType getType() {
		return type;
	}
	
	public void setType(MessageType type) {
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
