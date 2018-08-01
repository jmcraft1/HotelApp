package com.revature.model;

public class Messages {

	@Override
	public String toString() {
		return "Messages [messageTo=" + messageTo + ", messageFrom=" + messageFrom + ", message=" + message + "]";
	}
	String messageTo;
	String messageFrom;
	String message;
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Messages(String messageTo, String messageFrom, String message) {
		super();
		this.messageTo = messageTo;
		this.messageFrom = messageFrom;
		this.message = message;
	}
	public String getMessageTo() {
		return messageTo;
	}
	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}
	public String getMessageFrom() {
		return messageFrom;
	}
	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
