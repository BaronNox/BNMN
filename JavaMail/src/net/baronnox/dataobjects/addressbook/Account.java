package net.baronnox.dataobjects.addressbook;

public class Account {
	private String usrName;
	private String usrPw;
	
	public Account(String usrName, String usrPw) {
//		validateInput(usrName, usrPw);
		
		this.usrName = usrName;
		this.usrPw = usrPw;
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
	
	private String getUserName() {
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
