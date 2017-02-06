package net.baronnox.dataobjects;

import java.io.Serializable;

import net.baronnox.dataobjects.addressbook.AddressBook;

public class Account implements Serializable {
	private static final long serialVersionUID = Long.parseUnsignedLong("1");
	
	private String accName;
	private String usrName;
	private String usrPw;
	private AddressBook addressBook;
	
	public Account(String accName, String usrName, String usrPw) {
//		validateInput(usrName, usrPw);
		
		this.accName = accName;
		this.usrName = usrName;
		this.usrPw = usrPw;
		this.addressBook = new AddressBook();
	}
	
	public Account(String usrName, String usrPw) {
		this(null, usrName, usrPw);
	}
	
	private void validateInput(String usrName, String usrPw) {
		try{
			if(usrName == null || usrPw == null || !usrName.contains("@gmail.com") || usrName.length() == 0 || usrPw.length() == 0) {
				throw new Exception("-1");
			}
		} catch (Exception e) {
			System.err.println("User name and/or password are not valid.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void setUserName(String usrName) {
		this.usrName = usrName;
	}
	
	public void setUserPw(String usrPw) {
		this.usrPw = usrPw;
	}
	
	public AddressBook getAddressBook() {
		return addressBook;
	}
	
	public String getAccName() {
		return accName;
	}
	
	public String getUserName() {
		return usrName;
	}
	
	private String getUserPw() {
		return usrPw;
	}
	
	public boolean isEqual(Account account) {
		if(account.getUserName().equals(getUserName())) return true;
		return false;
	}
}
