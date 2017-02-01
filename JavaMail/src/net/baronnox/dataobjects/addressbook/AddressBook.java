package net.baronnox.dataobjects.addressbook;

import java.util.List;

public class AddressBook {
	private List<Contact> contactList;
	
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
