package net.baronnox.dataobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import net.baronnox.dataobjects.addressbook.Contact;
import net.baronnox.dataobjects.utility.NotificationDays;

public class Notification implements Serializable {
	private static final long serialVersionUID = Long.parseUnsignedLong("1");
	
	private ArrayList<Contact> recipients;
	private Date due;
	private Date end;
	private ArrayList<NotificationDays> dueDays;
	
	private String subject;
	private String msg;
	
	public Notification(ArrayList<Contact> recipients, Date due, String subject, String msg) {
		this.recipients = recipients;
		this.due = due;
		this.subject = subject;
		this.msg = msg;
	}
	
	public ArrayList<Contact> getRecipients() {
		return recipients;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Date getDue() {
		return due;
	}
	
	public String getSubject() {
		return subject;
	}
}
