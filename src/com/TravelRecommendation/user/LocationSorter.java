package com.TravelRecommendation.user;
import java.util.ArrayList; 
import java.util.Collections;

  


public class LocationSorter {
	ArrayList<locationInfo> Location = new ArrayList<>();       
	  public LocationSorter(ArrayList<locationInfo> jobCandidate) {         
	    this.Location = jobCandidate;     
	  }       
	  public ArrayList<locationInfo> getSortedByPercentage() {         
	    Collections.sort(Location,Collections.reverseOrder());         
	    return Location;     
	  } 
}
