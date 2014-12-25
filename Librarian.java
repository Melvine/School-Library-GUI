package ProgAssignment4;

public class Librarian extends User{
	String idType = "Librarian";
	
	public Librarian(String id, String pin) {
		super(id, pin, false);
	}
	
	public void changeUserAccess(User user, boolean x){
		user.setAccess(x);
	}
	
	public String getidType(){ return idType;}
	
	
	@Override
	public String toString(){
		return idType + ": " + getId() + " " + getPin() + " " + getName() + " " + getAddress() + " " + getPhoneNum();
	}
}
