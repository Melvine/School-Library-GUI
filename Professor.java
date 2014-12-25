package ProgAssignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Professor extends Student{
	String idType = "Professor";
	ArrayList<String> rentals = new ArrayList<String>();
	ArrayList<String> rentalDates = new ArrayList<String>();
		
	public Professor(String id, String pin, boolean access) {
		super(id, pin, access);
		// TODO Auto-generated constructor stub
	}

	public Professor(String id, String pin) {
		super(id, pin);
		// TODO Auto-generated constructor stub
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
				cal.add(GregorianCalendar.DAY_OF_MONTH, 20);   //add 20 days of rent
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

	public String getidType(){ return idType;}
	
	//adds into an arrayList of Strings from text files
	
	@Override
	public String toString(){
		return idType + ": " + isAccess() + " " + getId() + " "  + getPin() + " " + getName() + " " + getAddress() + " " + getPhoneNum() + " " + getRental();
	}
	

}
