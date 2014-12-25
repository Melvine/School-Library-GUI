package ProgAssignment4;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class graphicInterface extends JFrame{
	//login Interface
	private String first,second;
	
	private JTextField loginField;
	private JTextField passField;
	private JButton buttonField;
	private JTextField dateField;
	Date date = new Date();
	User user;
	Database data;
	
	//User Interface
	private JButton buttonField1;
	private JButton buttonField2;
	private JButton buttonField3;
	private JButton buttonField4;
	int maxU1Win = 1;
	int maxU2Win = 1;
	int maxU3Win = 1;
	int maxU4Win = 1;
	int maxL1Win = 1;
	int maxL2Win = 1;
	int maxL3Win = 1;
	
	//extra
	private JTextField response1;
	private JTextField response2;
	private JTextField response3;
	private JButton buttonUserInfo;
	
	//login interface
	graphicInterface(Database x){
		super("Library OS");
		setLayout(new FlowLayout());
		data = x;
		
		loginField = new JTextField("ID", 10);
		add(loginField);
		
		passField = new JTextField("PIN", 10);
		add(passField);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String loginDate = sdf.format(date);
		
		dateField = new JTextField(loginDate, 10);
		add(dateField);
		
		buttonField = new JButton("Login");
		add(buttonField);
		
		loginHandler event = new loginHandler();
		loginField.addActionListener(event);
		passField.addActionListener(event);
		buttonField.addActionListener(event);
	}
	
	
	
	//user interface
	graphicInterface(User x){
		super("Library OS");
		setLayout(new FlowLayout());
		System.out.println(x.getidType());
		
		if((x.getidType()).compareTo("Librarian") == 0){
			System.out.println("in");
			LibrarianInterface enter = new LibrarianInterface();
			buttonField1 = new JButton("L1");
			add(buttonField1);
			buttonField2 = new JButton("L2");
			add(buttonField2);
			buttonField3 = new JButton("L3");
			add(buttonField3);
			
			
			buttonField1.addActionListener(enter);
			buttonField2.addActionListener(enter);
			buttonField3.addActionListener(enter);

		}
		else{ //student or professor
			basicUserInterface enter = new basicUserInterface();
			buttonField1 = new JButton("U1");
			add(buttonField1);
			buttonField2 = new JButton("U2");
			add(buttonField2);
			buttonField3 = new JButton("U3");
			add(buttonField3);
			buttonField4 = new JButton("U4");
			add(buttonField4);
			
			
			buttonField1.addActionListener(enter);
			buttonField2.addActionListener(enter);
			buttonField3.addActionListener(enter);
			buttonField4.addActionListener(enter);
		}
	}
	
	public JTextField interfaceU1(){
		setLayout(new FlowLayout());
		JLabel label = new JLabel("Search books by keywords: ");
		response1 = new JTextField(20);
		if(maxU1Win == 1){
			add(label);
			add(response1);
			maxU1Win--;
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return response1;
	}
	
	public JTextField interfaceU2(){
		JLabel label = new JLabel("Enter magic number of book to check out:");
		response2 = new JTextField(20);
		if(maxU2Win == 1){
			add(label);
			add(response2);
			maxU2Win--;
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return response2;
	}
	
	public JTextField interfaceU3(){
		JLabel label = new JLabel("Enter a magic code of a book to return:");
		response3 = new JTextField(20);
		if(maxU3Win == 1){
			add(label);
			add(response3);
			maxU3Win--;
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return response3;
	}
	
	public JButton interfaceU4(){
		JLabel label = new JLabel("Click for an update on user account info");
		buttonUserInfo = new JButton("info");
		if(maxU4Win == 1){
			add(label);
			add(buttonUserInfo);
			maxU4Win--;	
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return buttonUserInfo;
	}
	public JTextField interfaceL1(){
		JLabel label = new JLabel("Enter a user ID for information:");
		response1 = new JTextField(20);
		
		if(maxL1Win == 1){
			add(label);
			add(response1);
			maxL1Win--;
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return response1;
	}
	
	public JTextField interfaceL2(){
		JLabel label = new JLabel("Enter both block type & User ID to set access:");
		response2 = new JTextField(20);
		
		if(maxL2Win == 1){
			add(label);
			add(response2);
			maxL2Win--;
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return response2;
	}
	
	public JButton interfaceL3(){
		JLabel label = new JLabel("Click for overdue users in the system");
		buttonUserInfo = new JButton("info");
		if(maxL3Win == 1){
			add(label);
			add(buttonUserInfo);
			maxL3Win--;	
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,500);
		setVisible(true);
		
		return buttonUserInfo;
	}
	
	//handles ID and PIN
	private class loginHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean access = false;
			if(event.getSource() == loginField || event.getSource() == passField || event.getSource() == buttonField){
				first = loginField.getText();
				second = passField.getText();
				
				for(User i: data.getUserbase()){
					if(i.getId().compareTo(first) == 0 && i.getPin().compareTo(second) == 0){
						user = i;
						LibrarySystem x = new LibrarySystem();
						x.setCurrentUser(user);
						x.setCurrentDate(dateField.getText());
						
						JOptionPane.showMessageDialog(null, "You are now logged in, " + i.getName());
						access = true;
						setVisible(false);
						graphicInterface test = new graphicInterface(i);
						test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						test.setSize(350,100);
						test.setVisible(true);
						
						break;
					}
				}
				if(access == false)
					JOptionPane.showMessageDialog(null, "Login failed");
			}
		}
	}

	//handles Student and professor buttons
	private class basicUserInterface implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			LibrarySystem x = new LibrarySystem();
			if(event.getSource() == buttonField1){
				basicUserInterface event1 = new basicUserInterface();
				interfaceU1().addActionListener(event1);
			}
			if(event.getSource() == buttonField2){
				basicUserInterface event2 = new basicUserInterface();
				interfaceU2().addActionListener(event2);
			}
			if(event.getSource() == buttonField3){
				basicUserInterface event3 = new basicUserInterface();
				interfaceU3().addActionListener(event3);
			}
			if(event.getSource() == buttonField4){
				basicUserInterface event4 = new basicUserInterface();
				interfaceU4().addActionListener(event4);
			}
			
			if(event.getSource() == response1){
				String output = "";
				ArrayList<String> temp = x.U1(response1.getText());		//checks database for given keyword and returns array of searches
				for(String i: temp){
					output = output.concat(i + "\n");
				}
				JOptionPane.showMessageDialog(null, output);
			}
			
			if(event.getSource() == response2){
				String temp = response2.getText();		//checks database for given magic code and returns if checked out or not
				if(x.getCurrentUser().isAccess() == true){
					try {
						if(x.U2(temp,x.getCurrentUser()))
							JOptionPane.showMessageDialog(null, "The book" + temp + " was successfully rented");
						else
							JOptionPane.showMessageDialog(null, "You can't rent the book: " + temp + ". It's unavailable or not an associated magic number");
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "You can't rent the book: You are blocked");
			}	
			
			if(event.getSource() == response3){
				String temp = response3.getText();		//checks database for given magic code and returns if checked out or not

				try {
					if(x.U3(temp))
						JOptionPane.showMessageDialog(null, "The book" + temp + " was successfully removed from your rentals");
					else
						JOptionPane.showMessageDialog(null, "Error: " + temp + ". Book was not found in your account or mistyped magic code. Try again.");
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("parse error in graphic interface " + e);
					e.printStackTrace();
				}
			}	
			
			if(event.getSource() == buttonUserInfo){
				//checks database for given magic code and returns if checked out or not
				String idType = x.getCurrentUser().getidType();
				String name = x.getCurrentUser().getName();
				String phone = x.getCurrentUser().getPhoneNum();
				String address = x.getCurrentUser().getAddress();
				String blockage = x.getCurrentUser().getBlock();
				String id = x.getCurrentUser().getId();
				String pass = x.getCurrentUser().getPin();
				ArrayList<String> rentals = x.getCurrentUser().getRentalArray();
				ArrayList<String> dueDates = x.getCurrentUser().getrentalDueDates();
				
				try {
					if(x.U4()){
						String rentString = "";
						for(int i = 0; i < rentals.size(); i++){
							if(i == rentals.size()-1)
								rentString = rentString.concat(dueDates.get(i) + ":" + rentals.get(i));
							else
								rentString = rentString.concat(dueDates.get(i) + ":" + rentals.get(i) + "\n");
						}
						JOptionPane.showMessageDialog(null, idType + " " + blockage +  "- Name:" + name + "\nLogin: " + id + "\nPass:" + pass + "\nPhone #:" + phone + "\nAddress:" + address + "\nrentals:\n" + rentString  );
					}
					
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("parse error in graphic interface " + e);
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	//handles librarian buttons
	private class LibrarianInterface implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			LibrarySystem x = new LibrarySystem();
			
			if(event.getSource() == buttonField1){
				LibrarianInterface event1 = new LibrarianInterface();
				interfaceL1().addActionListener(event1);
			}
			if(event.getSource() == buttonField2){
				LibrarianInterface event2 = new LibrarianInterface();
				interfaceL2().addActionListener(event2);
			}
			if(event.getSource() == buttonField3){
				LibrarianInterface event3 = new LibrarianInterface();
				interfaceL3().addActionListener(event3);
			}
			
			if(event.getSource() == response1){
				String temp = response1.getText();		//checks database for given magic code and returns if checked out or not
				for(User i: LibrarySystem.database.getUserbase()){
					if((i.getId()).compareTo(temp) ==0){
						String idType = i.getidType();
						String name = i.getName();
						String phone = i.getPhoneNum();
						String address = i.getAddress();
						String blockage = i.getBlock();
						String id = i.getId();
						String pass = i.getPin();
						ArrayList<String> rentals = i.getRentalArray();
						ArrayList<String> dueDates = i.getrentalDueDates();
						
						try {
							if(x.U4()){
								String rentString = "";
								for(int j = 0; j < rentals.size(); j++){
									if(j == rentals.size()-1)
										rentString = rentString.concat(dueDates.get(j) + ":" + rentals.get(j));
									else
										rentString = rentString.concat(dueDates.get(j) + ":" + rentals.get(j) + "\n");
								}
								JOptionPane.showMessageDialog(null, idType + " " + blockage +  "- Name:" + name + "\nLogin: " + id + "\nPass:" + pass + "\nPhone #:" + phone + "\nAddress:" + address + "\nrentals:\n" + rentString  );
							}
							else
								JOptionPane.showMessageDialog(null, "No record found.");
							
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (ParseException e) {
							System.out.println("parse error in graphic interface " + e);
							e.printStackTrace();
						}
					}
				}
			}	
				
			if(event.getSource() == response2){
				String[] temp = response2.getText().split("\\s+");		//checks database for given magic code and returns if checked out or not
				boolean tempBoo = true;
				boolean inside = false;
				if(temp[0].compareTo("block") == 0){
					tempBoo = false;
				}
				
				for(User i: LibrarySystem.database.getUserbase()){
					if(i.getId().compareTo(temp[1]) == 0){
						i.setAccess(tempBoo);
						System.out.println(i.isAccess());
						inside = true;
						JOptionPane.showMessageDialog(null, "User is now " + temp[0] + "ed");
						break;
					}
				}
				if(!inside)
					JOptionPane.showMessageDialog(null, "No changes were made. Try again.");
			}	
			
			if(event.getSource() == buttonUserInfo){
				String names = "";
				
					try {
						for(User i: LibrarySystem.database.getUserbase()){
							i.checkAccess();
							if(!i.isAccess() && ((i.getidType()).compareTo("Student") == 0|| (i.getidType()).compareTo("Professor") == 0)){
								names = names.concat(i.getName() + "\n"); 
							}
						}
						JOptionPane.showMessageDialog(null, names);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				
			}	
			
			
			
			
			
			
		}
	}
}