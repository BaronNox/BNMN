package net.baronnox.dataobjects.utility;

public enum NotificationDays {
	Monday(0),
	Tuesday(1),
	Wednesday(2),
	Thurday(3),
	Friday(4),
	Saturday(5),
	Sunday(6);
	
	private final int id;
	
	private NotificationDays(int id) {
		this.id = id;
	}
	
	public int getDayID() {
		return this.id;
	}
}
