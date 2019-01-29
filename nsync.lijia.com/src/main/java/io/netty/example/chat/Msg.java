package io.netty.example.chat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Msg implements Serializable{
	private String username;
	private String message;
	private Date sendDate;
	private Date serverDate;
	private List<String> receiverList;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getServerDate() {
		return serverDate;
	}
	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}
	public List<String> getReceiverList() {
		return receiverList;
	}
	public void setReceiverList(List<String> receiverList) {
		this.receiverList = receiverList;
	}
	@Override
	public String toString() {
		return "Msg [username=" + username + ", message=" + message + ", sendDate=" + sendDate + ", serverDate="
				+ serverDate + ", receiverList=" + receiverList + "]";
	}
	public Msg(String username, String message, Date sendDate, Date serverDate, List<String> receiverList) {
		super();
		this.username = username;
		this.message = message;
		this.sendDate = sendDate;
		this.serverDate = serverDate;
		this.receiverList = receiverList;
	}
	
	
	
	
}
