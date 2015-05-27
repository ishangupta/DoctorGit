package com.ons.doctor;

import java.io.Serializable;

public class Doctor implements Serializable{

	// Commited by ishan
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
String name;
String desc;
String speciality;
String place;
String number;
String degree;
String image;
String lat;
String lng;


public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
}
public String getPlace() {
	return place;
}
public void setPlace(String place) {
	this.place = place;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getLat() {
	return lat;
}
public void setLat(String lat) {
	this.lat = lat;
}
public String getLng() {
	return lng;
}
public void setLng(String lng) {
	this.lng = lng;
}

	
}
