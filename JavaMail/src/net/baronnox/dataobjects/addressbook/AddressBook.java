package net.baronnox.dataobjects.addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.baronnox.dataobjects.Notification;

public class AddressBook implements Serializable {
	private static final long serialVersionUID = Long.parseUnsignedLong("1");
	
	private ArrayList<Contact> contactList;
	private List<Notification> notificationList;
	
	public AddressBook() {
		this.contactList = new ArrayList<>();
		this.notificationList = new ArrayList<>();
	}
	
	public void addNotification(Notification notification) {
		notificationList.add(notification);
	}
	
	public void addContactToList(Contact contact) {
		this.contactList.add(contact);
	}
	
	public void setContactList(ArrayList<Contact> contactList) {
		this.contactList = contactList;
	}
	
	public List<Contact> getContactList() {
		return contactList;
	}
	
	public List<Notification> getNotifications() {
		return notificationList;
	}
	
	public Contact getContactByAddress(String address) {
		for(Contact c : contactList) {
			if(c.getAddressAsString().equals(address)) {
				return c;
			}
		}
		return null;
	}
	
	public List<Notification> getNotificationByContact(Contact contact) {
		List<Notification> list = new ArrayList<>();
		for(Notification e : notificationList) {
			for(Contact c : e.getRecipients()) {
				if(c.equals(contact)) {
					list.add(e);
				}
			}
		}
		
		return list;
	}
}
