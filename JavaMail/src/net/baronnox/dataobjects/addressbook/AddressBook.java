package net.baronnox.dataobjects.addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressBook implements Serializable {
	private List<Contact> contactList;
	
	public AddressBook() {
		this.contactList = new ArrayList<>();
	}
	
	public void addContactToList(Contact contact) {
		this.contactList.add(contact);
	}
	
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
	public List<Contact> getContactList() {
		return contactList;
	}
}
