package com.javainuse.model;

import org.springframework.stereotype.Component;

@Component("holidayBO")
public class HolidayBO {
String date;
String day;
String description;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

@Override
public String toString() {
	return "HolidayBO [holidayDate=" + date + ", day=" + day + ", description=" + description + "]";
}


}
