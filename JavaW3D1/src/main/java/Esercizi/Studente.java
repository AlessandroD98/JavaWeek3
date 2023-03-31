package Esercizi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Studente {

	private long id;
	private String name;
	private String surname;
	private String gender;
	//private Date birthdate;
	private String birthdate;
	private double avg;
	private int min_vote;
	private int max_vote;
	
	public Studente(long id, String name, String surname, String gender, String birthdate,double avg, int min_vote,int max_vote) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.avg = avg;
		this.min_vote = min_vote;
		this.max_vote = max_vote;
	
	}
	
	public Studente(String name, String surname, String gender, String birthdate, int min_vote,int max_vote) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		//this.setBirthdate(birthdate);
		this.birthdate = birthdate;
		this.setAvg(min_vote, max_vote);
		this.min_vote = min_vote;
		this.max_vote = max_vote;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	/*
	 * public void setBirthdate(String b) { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd/MM/yyyy"); try { this.birthdate = formatter.parse(b); }
	 * catch (ParseException e) { e.printStackTrace(); } }
	 */
	public void setBirthdate() {
		this.birthdate = birthdate;
	}
	
	
	public double getAvg() {
		return avg;
	}

	public void setAvg(int a, int b) {
		this.avg = (a + b)/2;
	}

	public int getMin_vote() {
		return min_vote;
	}

	public void setMin_vote(int min_vote) {
		this.min_vote = min_vote;
	}

	public int getMax_vote() {
		return max_vote;
	}

	public void setMax_vote(int max_vote) {
		this.max_vote = max_vote;
	}


	@Override
	public String toString() {
		return "Studente [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthdate="
				+ birthdate + ", avg=" + avg + ", min_vote=" + min_vote + ", max_vote=" + max_vote + "]";
	}

	
}
