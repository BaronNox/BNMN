package net.baronnox.addressbook;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;

public class Contact {
	private InternetAddress iAddress;
	
	public Contact(String address) {
		this(address, null);
	}
	
	public Contact(String address, String personal) {
		try {
			this.iAddress = new InternetAddress(address, personal);
		} catch (UnsupportedEncodingException e) {
			System.err.println(address + " uses wrong charset.");
		}
	}
	
	public void setInternetAddress(InternetAddress iAddress) {
		this.iAddress = iAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.iAddress.setAddress(emailAddress);
	}
	
	public void setPersonal(String personal) {
		try {
			this.iAddress.setPersonal(personal);
		} catch (UnsupportedEncodingException e) {
			System.err.println(personal + " uses wrong charset.");
		}
	}
	
}
