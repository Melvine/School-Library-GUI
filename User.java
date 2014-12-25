package ProgAssignment4;

import java.text.ParseException;
import java.util.ArrayList;

public abstract class User {
	private String id, pin, idType;
	private String name, address; private String phoneNum;
	protected boolean access = true;	//block identifier when false

	User(String id, String pin){
		this.id = id;
		this.pin = pin;
	}
	
	User(String id, String pin, boolean access){
		this.id = id;
		this.pin = pin;
		this.access = access;
	}
	public String getidType(){ return idType;}
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id;}

	public String getPin() { return pin;}
	public void setPin(String pin) { this.pin = pin;}

	public String getName() { return name;}
	public void setName(String name) { this.name = name;}

	public String getAddress() { return address;}
	public void setAddress(String address) { this.address = address;}

	public String getPhoneNum() { return phoneNum;}
	public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum;}
	
	public boolean isAccess() { return access;}
	public boolean setAccess(boolean access) { return this.access = access;}
	
	public String getRental()  { return "this is a generic rental";}
	public void setRental(String rental) { /*this.rental = rental;*/ }

	public void setRental(String rental, Book catalog) {
		
	}

	public void addRental(String magicCode) throws ParseException {
		// TODO Auto-generated method stub
		
	}
	
	public boolean returnBook(String magicCode) throws ParseException{
		return false;
	}

	@Override
	public String toString(){
		return getId() + " " + getPin() + " " + getName() + " " + getAddress() + " " + getPhoneNum();
	}

	public void checkAccess() throws ParseException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getRentalArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getrentalDueDates() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeRental(String code) {
		// TODO Auto-generated method stub
		
	}
}
