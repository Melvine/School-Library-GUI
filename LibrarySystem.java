package ProgAssignment4;
//Melvin Nguyen 7126782 mnguyen00@umail.ucsb.edu

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class LibrarySystem{
	static Database database;
	static User currentUser;
	static String currentDate;
	
	public static void main(String[] args){
		database = new Database();
		graphicInterface test = new graphicInterface(database);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(350,100);
		test.setVisible(true);
	}
	
	public void setCurrentUser(User x){
		currentUser = x;
	}
	public User getCurrentUser(){
		return currentUser;
	}
	public void setCurrentDate(String x){
		currentDate = x;
	}
	public String getCurrentDate(){
		return currentDate;
	}
	
	//search books
	public ArrayList<String> U1(String input){
		ArrayList<Book> catalog = database.getBooks();
		ArrayList<Book> results = new ArrayList<Book>();
		ArrayList<String> result = new ArrayList<String>();
		
		String [] tempWord = input.split("\\s+");
		for(int i = 0; i < tempWord.length; i++){
			tempWord[i] = tempWord[i].toLowerCase();
		}
		
		for(Book i: catalog){
			for(int j = 0; j < (i.getKeywords()).size(); j++){
				boolean match = false;
				for(int k = 0; k < tempWord.length; k++){
					if( ((i.getKeywords()).get(j)).compareTo(tempWord[k]) == 0){
						results.add(i);
						match = true;
						break;
					}
				}
				if(match == true)
					break;
			}
		}		
		System.out.println("Results:");
		System.out.println(results);
		for(int i = 0; i < results.size(); i++){
			String avail = "is";
			if((results.get(i)).isCheckedOut())
				avail = "not";
			System.out.println((results.get(i)).getMagicCode() + ": " + (results.get(i)).getTitle() + ",by " + (results.get(i)).getAuthor() + " is "  + avail + " available.");
			result.add((results.get(i)).getMagicCode() + ": " + (results.get(i)).getTitle() + " - " + avail + " available.");
		}
		return result;
	}
	
	public User matchUser(String login, String pass){
		User tempUser = null;
		System.out.println("hi: " + login);
		
		for(User i: database.getUserbase()){
			if(login.compareTo(i.getId()) == 0 && pass.compareTo(i.getPin()) == 0)
				tempUser = i;	
		}
		return tempUser;
	}
	
	//check out the book
	public boolean U2(String book, User user) throws ParseException{
		ArrayList<Book> catalog = database.getBooks();
		User tempUser = user;
		System.out.println("hi," + tempUser.getName());
		
		System.out.println("the book: " + book);	
		for(Book i: catalog){
			if(book.compareTo(i.getMagicCode()) == 0){
				if(i.isCheckedOut() == false){
					tempUser.addRental(i.getMagicCode()); //book is in user rental
					i.setCheckedOut(true);  //book is taken out
					System.out.println("check out successfully");
					return true;
				}		
			}
		}
		return false;
	}
		
	//return a book
	public boolean U3(String code) throws ParseException{
		ArrayList<Book> catalog = database.getBooks();
		for(Book i: catalog){
			if(code.compareTo(i.getMagicCode()) == 0){
					if(currentUser.returnBook(code)){
						currentUser.removeRental(code);
						i.setCheckedOut(false); //makes the book available for the public again
						return true;
					}
			}
		}
		return false;

	}
		
	//reflect user information
	public boolean U4() throws ParseException{
 			for(User i: database.getUserbase()){
				i.checkAccess();
			}
 			return true;
	}
	
	
	
	//list a specific user's information
	public void L1(){
			
	}
		
	//change user status
	public void L2(){
			
	}
		
	//list users for any overdue item
	public void L3(){
			
	}
	
	
	
	
	
	
	
	
	
	

}
