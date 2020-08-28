package com.homage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Document(collection = "member")
public @Data class Member implements Serializable{
	
//	private static final long serialVersionUID = -4012622784317283804L;
	
	@Transient
    public static final String SEQUENCE_NAME = "member_sequence";
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String photo;
	private String dob;					 //date of birth
	private String dod;                  //date of demise
	private long candle;
	private String lastWords;
	private String accomplishment;
	private String Street; 				// can be null
	private String city;					
	private String state;
	private String country;
	private String cause;
	private List<String> tribute;
	public Member() {
		
	}
	
	public Member( String firstName, String lastName, String photo, String dob, String dod, long candle, String lastWords, String accomplishment,
			String street, String city, String state, String country, String cause, List<String> tribute) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.dob = dob;
		this.dod = dod;
		this.candle = candle;
		this.lastWords = lastWords;
		this.accomplishment = accomplishment;
		Street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.cause = cause;
		this.tribute = tribute;
	}
	
	public List<String> getTribute() {
		return tribute;
	}

	public void setTribute(List<String> tribute) {
		this.tribute = tribute;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public long getCandle() {
		return candle;
	}

	public void setCandle(long candle) {
		this.candle = candle;
	}

	public String getLastWords() {
		return lastWords;
	}
	public void setLastWords(String lastWords) {
		this.lastWords = lastWords;
	}
	public String getAccomplishment() {
		return accomplishment;
	}
	public void setAccomplishment(String accomplishment) {
		this.accomplishment = accomplishment;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", photo=" + photo
				+ ", dob=" + dob + ", dod=" + dod + ", lastWords=" + lastWords + ", accomplishment=" + accomplishment
				+ ", Street=" + Street + ", city=" + city + ", state=" + state + ", country=" + country + ", cause=" + cause + "]";
	}

}
