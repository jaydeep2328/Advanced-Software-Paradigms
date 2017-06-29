package com.inventory.model;

public class MainForm {

	UserRegistration registration = new UserRegistration();

	public UserRegistration getRegistration() {
		return registration;
	}

	public void setRegistration(UserRegistration registration) {
		this.registration = registration;
	}
	
	QuotationMaster master = new QuotationMaster();

	public QuotationMaster getMaster() {
		return master;
	}

	public void setMaster(QuotationMaster master) {
		this.master = master;
	}
	
	ItemLocation location = new ItemLocation();

	public ItemLocation getLocation() {
		return location;
	}

	public void setLocation(ItemLocation location) {
		this.location = location;
	}
	
	
	
	
	
}
