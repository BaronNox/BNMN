package net.baronnox.dataobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.baronnox.dataobjects.addressbook.Contact;
import net.baronnox.dataobjects.utility.NotificationDays;

public class Notification implements Serializable {
	private List<Contact> recipients;
	private Date due;
	private Date end;
	private List<NotificationDays> dueDays;
	
	private String msg;
}
