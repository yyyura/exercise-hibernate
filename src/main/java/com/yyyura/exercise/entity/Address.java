package com.yyyura.exercise.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jurijs Cicelimovs
 */

@Entity
public class Address {

	@Id
	@GeneratedValue
	private int addressId;

	private String street;
	private String city;
	private String state;
	private String postalCode;

	public Address() {
	}

	public Address(String street, String city, String state, String postalCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public int getId() {
		return addressId;
	}

	public void setId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Adress ID: " + addressId + ", address: " + street + " street " + city + " " + state + " " + postalCode;
	}
}