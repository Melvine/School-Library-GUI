package ProgAssignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	ArrayList<User> users = new ArrayList<User>(); //database of Users
	ArrayList<Book> catalog = new ArrayList<Book>(); //database of Catalog
	LibrarySystem system = new LibrarySystem();
	
	Database(){
		try {
			//file must be in student -> professor -> librarian order
			Scanner input = new Scanner(new File("Library.data"));
			
			while(input.hasNextLine()){
					String phrase = input.nextLine();
					
					if(phrase.compareTo("Books") == 0){
						input.nextLine(); //ignores the next "::::::::::"
						
						while(input.hasNextLine()){
							Book x;
							String magicCode = input.nextLine();
							
							
							if(magicCode.compareTo("::::::::::") == 0)
								break;
							
							String title = input.nextLine();
											
							x = new Book(magicCode, title);
						
							
							String author = input.nextLine();
							String publisher = input.nextLine();
							String year = input.nextLine();
							String subject = input.nextLine();
							
							x.setAuthor(author);
							x.setPublisher(publisher);
							x.setYear(year);
							x.setSubject(subject);

							catalog.add(x);
						}	
					}
					
					//search for students
					if(phrase.compareTo("Students") == 0){
						input.nextLine(); //ignores the next "::::::::::"
						
						while(input.hasNextLine()){
							Student x;
							String id = input.nextLine();
							
							
							if(id.compareTo("::::::::::") == 0)
								break;
							
							if(id.compareTo("[Blocked]") == 0 ){
								id = input.nextLine();
								(users.get(users.size()-1)).setAccess(false);
							}
							
							String pin = input.nextLine();
							
							x = new Student(id, pin);
							
							
							String name = input.nextLine();
							String address = input.nextLine();
							String phoneNum = input.nextLine();
							String rental = input.nextLine();
							
							x.setName(name);
							x.setAddress(address);
							x.setPhoneNum(phoneNum);
							x.setRental(rental);

							//mark books unavailable
							for(String i: x.getRentalArray()){
								for(Book j: catalog){
									if(i.compareTo(j.getMagicCode()) == 0){
										j.setCheckedOut(true);
										break;
									}	
								}
							}
							users.add(x);
						}	
					}
					
					//search for professors
					if(phrase.compareTo("Professors") == 0){
						input.nextLine(); //ignores the next "::::::::::"
						
						while(input.hasNextLine()){
							Professor x;
							String id = input.nextLine();
							
							
							if(id.compareTo("::::::::::") == 0)
								break;
							
							if(id.compareTo("[Blocked]") == 0 ){
								id = input.nextLine();
								(users.get(users.size()-1)).setAccess(false);
							}
							
							String pin = input.nextLine();
							
							x = new Professor(id, pin);
							
							
							String name = input.nextLine();
							String address = input.nextLine();
							String phoneNum = input.nextLine();
							String rental = input.nextLine();
							
							x.setName(name);
							x.setAddress(address);
							x.setPhoneNum(phoneNum);
							x.setRental(rental);
							
							//mark books unavailable
							for(String i: x.getRentalArray()){
								for(Book j: catalog){
									if(i.compareTo(j.getMagicCode()) == 0){
										j.setCheckedOut(true);
										break;
									}	
								}
							}
							users.add(x);
						}
					}
					
					//search for librarians
					if(phrase.compareTo("Librarians") == 0){
						input.nextLine(); //ignores the next "::::::::::"
						
						while(input.hasNextLine()){
							Librarian x;
							String id = input.nextLine();
							String pin = input.nextLine();
							
							if(id.compareTo("::::::::::") == 0 )
								break;
							
							x = new Librarian(id, pin);
							
							String name = input.nextLine();
							String address = input.nextLine();
							String phoneNum = input.nextLine();
							
							x.setName(name);
							x.setAddress(address);
							x.setPhoneNum(phoneNum);
							
							users.add(x);
						}
					}	
			}
			printAll();
			
			input.close();	
		} catch(FileNotFoundException e) {
		    System.out.println("File not found.");
		    System.exit(1);
		}		
	}
	
	public void changeAvail(String magicCode){
		for(Book i: catalog){
			if(magicCode.compareTo(i.getMagicCode()) == 0){
				if(i.isCheckedOut())
					i.setCheckedOut(false);
				else
					i.setCheckedOut(true);
			}
		}
	}
	
	public boolean getBookAvail(String magicCode){
		for(Book i: catalog){
			if(magicCode.compareTo(i.getMagicCode()) == 0){
				if(!(i.isCheckedOut()) )
					return true;	//best scenario; can rent out the book.
				else
					return false;	
			}
		}
		return false;	
	}
	
	public ArrayList<User> getUserbase(){
		return users;
	}
	
	public ArrayList<Book> getBooks(){
		return catalog;
	}
	
	public void printAll(){
		//print out all
		for(Book i: catalog)
			System.out.println(i.toString());
		for(User i: users)
			System.out.println(i.toString());
	}
}
