package ProgAssignment4;

import java.util.ArrayList;

public class Book {
	private String magicCode;
	private String title, author, publisher;
	private String year, subject;
	private Boolean checkedOut = false;
	
	Book(String magicNum, String title){
		magicCode = magicNum;
		this.title = title;
	}

	public String getMagicCode() {
		return magicCode;
	}

	public void setMagicCode(String magicCode) {
		this.magicCode = magicCode;
	}

	public String getMagicCodeNoCopyNum() {
		String result = "";
		String [] tempCode = magicCode.split("\\.");
		for(int i = 0; i < 3; i++){
			if(i == 2)
				result = result.concat(tempCode[i]);
			else
				result = result.concat(tempCode[i] + ".");
		}
		return result;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSubject() {
		return subject;
	}

	public void setCheckedOut(Boolean x) {
		this.checkedOut = x;
	}

	public Boolean isCheckedOut() {
		return checkedOut;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public ArrayList<String> getKeywords(){
		ArrayList<String> keywords = new ArrayList<String>();
		String [] title = getTitle().split("\\s+");
		String [] author = getAuthor().split(",|\\s+");
		String [] publisher = getPublisher().split("\\s+");
		String [] subject = getSubject().split("\\s+");
		
		for(String i: title)
			keywords.add(i.toLowerCase());
		for(String i: author)
			keywords.add(i.toLowerCase());
		for(String i: publisher)
			keywords.add(i.toLowerCase());
		for(String i: subject)
			keywords.add(i.toLowerCase());
		
		keywords.add(getYear());
		keywords.add(getMagicCodeNoCopyNum().toLowerCase());
		keywords.add(getMagicCode().toLowerCase());
		
		return keywords;
	}
	
	@Override
	public String toString(){
		return "Book: " + getTitle() + " " + getMagicCodeNoCopyNum();
	}
}
