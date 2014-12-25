package ProgAssignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Student extends User{
	private String idType = "Student";
	ArrayList<String> rentals = new ArrayList<String>();
	ArrayList<String> rentalDates = new ArrayList<String>();

	
	Student(String id, String pin) {
		super(id, pin);
	}
	
	Student(String id, String pin, boolean access){
		super(id, pin, access);
	}
	
	//adds a book in an array of checkedoutBooks from UI
	public void addRental(String rentBook) throws ParseException{
		Date date = new Date();
		LibrarySystem y = new LibrarySystem();
		Database x = LibrarySystem.database;
		this.checkAccess();
		if(access == true && x.getBookAvail(rentBook)){
			String stime = y.getCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			System.out.println("here");
			Calendar cal = GregorianCalendar.getInstance();	//set calendar date to loginDate
			date = sdf.parse(stime);
			
			
			cal.setTime(date);	//cal time for login date used to compareTo rentalDate	
			cal.add(GregorianCalendar.DAY_OF_MONTH, 10);   //add 10 days of rent
			String tempRentalDate = sdf.format(cal.getTime());
			
			String tempRental = rentBook;
			System.out.println(tempRentalDate);
			x.changeAvail(rentBook);
			rentalDates.add(tempRentalDate);
			rentals.add(tempRental);
			System.out.println("rented");
		}
		else{
			System.out.println("can't rent");
			
		}
	}
	
	public void removeRental(String rentedBook){		
		//compare titles and remove title
		for(int i = 0; i < rentals.size(); i++){
			if((rentals.get(i)).compareTo(rentedBook) == 0){
				rentalDates.remove(i);
				rentals.remove(i);
				
				break;
			}
			
		}
	}
	
	
	public String getidType(){ return idType;}
	
	//adds into an arrayList of Strings from text files
	@Override
	public void setRental(String rental) {
		String[] tempString = rental.split(":|,");
		
		String temp;
		for(int i = 0; i < tempString.length; i++){
			temp = tempString[i];
			
			if((i+1)%2 == 0){
				rentals.add(temp);
			}
			else{
				rentalDates.add(temp);
			}
		}
		
	}
	
	//merges an array of rental books into a single string
	@Override
	public String getRental(){
		String tempString = "";
		for(int i = 0; i < rentals.size(); i++){
			if(i == rentals.size()-1)
				tempString = tempString.concat(rentalDates.get(i) + ":" + rentals.get(i));
			else
				tempString = tempString.concat(rentalDates.get(i) + ":" + rentals.get(i));
		}
		
		return tempString;
	}
	
	public boolean returnBook(String magicCode) throws ParseException{
		String code = magicCode;
		for(int i = 0; i < rentals.size(); i++){
			if(code.compareTo(rentals.get(i)) == 0){
				rentals.remove(i);
				rentalDates.remove(i);
				
				this.checkAccess();
				return true;
			}
		}
		
		return false;
	}
	
	public void checkAccess() throws ParseException{
		//checks student rentals for any overdue
		LibrarySystem x = new LibrarySystem();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
		boolean verified = true; //if verification is true the user did not have an overdue book
	    
	    Date loginDate = dateFormat.parse(x.getCurrentDate()); // conversion from String to Date
	    Date rentalDate;
	    Calendar cal = GregorianCalendar.getInstance();	//set calendar date to loginDate
	    Calendar rentCal = GregorianCalendar.getInstance();
	    cal.add(GregorianCalendar.DAY_OF_MONTH, 10);   //add 10 days of rent
	    cal.setTime(loginDate);	//cal time for login date used to compareTo rentalDate
	    
	    //String dates = dateFormat.format(cal.getTime());	//String conversion 
	    //System.out.println(dates);
	    
		for(int i = 0; i < rentalDates.size(); i++){
			rentalDate = dateFormat.parse(rentalDates.get(i));
			rentCal.setTime(rentalDate);
			
			if(cal.compareTo(rentCal) > 0) { //compare two dates if today's date is before the due date it is greater than zero
				verified = false; //overdue book; notifies method not to setAccess to true
				this.setAccess(false); //student has overdue books
			}
		}
		
		if(verified){
			this.setAccess(true);
		}
		
	}
	
	//returns the rental arrayList
	public ArrayList<String> getRentalArray() {
		return rentals;
	}
	
	public ArrayList<String> getrentalDueDates(){
		return rentalDates;
	}
	
	public String getBlock(){
		String isBlock = "Blocked";
		if(isAccess()){
			isBlock = "Unblocked";
		}
		
		return isBlock;
	}
	@Override
	public String toString(){
		String isBlock = "Blocked";
		if(isAccess()){
			isBlock = "Unblocked";
		}
		return idType + " " + isBlock + ":  " + " " + getId() + " \nName:" + getName() + " \nAddress:" + getAddress() + " \nPhone #:" + getPhoneNum() + " \n" + getRental();
	}
	
}
