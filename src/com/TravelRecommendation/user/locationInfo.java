package com.TravelRecommendation.user;

import java.util.Comparator;

public class locationInfo implements Comparable<locationInfo> {
	int percentage;
	String Locationname,lat,lng;
	
	public locationInfo(int percentage,String Locationname,String lat,String lng)
	{
		this.percentage=percentage;
		this.Locationname=Locationname;
		this.lat=lat;
		this.lng=lng;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String getLocationname() {
		return Locationname;
	}

	public void setLocationname(String locationname) {
		Locationname = locationname;
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

	@Override
	public int compareTo(locationInfo locationInfo) {
		// TODO Auto-generated method stub
		return (this.getPercentage() < locationInfo.getPercentage() ? -1 : 
            (this.getPercentage() == locationInfo.getPercentage() ? 0 : 1));   
		
	}
	 @Override     
	  public String toString() {         
	    return " Percentage: " + this.percentage + ", Locationname: " + this.Locationname + ", Lat:" + this.lat;     
	  } 
	
}


