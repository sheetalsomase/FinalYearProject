package com.TravelRecommendation.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TravelRecommendation.Dbconn;



public class GetLocation extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		response.getWriter().write(getLocationInfo().toString());
	}


	static ArrayList<String> getLocationInfo(){
		ArrayList<String> al = new ArrayList<String>();
		try{
			/*ResultSet rs=Dbconn.getResultFromSqlQuery("select * from latlong1");
			while(rs.next()){
				al.add(rs.getString(2)+"#"+rs.getString(3)+"#"+rs.getString(1));
				System.out.println(rs.getString(2)+"#"+rs.getString(3));
			}*/
			
			
			
		
			int i=1;
			String locationname="";
			String lat="";
			String lng="";
			double distance=0.0;
			ResultSet rs=Dbconn.getResultFromSqlQuery("select * from latlong1");
			while(rs.next())
			{
				
			//System.out.println("  "+rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));	

			ResultSet rs2=Dbconn.getResultFromSqlQuery("select * from travellogue where locationname='"+rs.getString(1)+"' and city='"+displaymap.city+"'");
			if(rs2.next())
			{
				/*if(i!=1)
				{
					Location loc1 = new Location(locationname,Float.valueOf((String) lat) , Float.valueOf((String) lng));
			        Location loc2 = new Location(rs2.getString("locationname"),Float.valueOf((String) rs2.getString("lat")) , Float.valueOf((String) rs2.getString("lng")));  
			         distance = loc1.distanceTo(loc2);
				}*/
				//System.out.println("  "+rs2.getString("Seasons")+"  "+rs2.getString("locationname")+"  "+rs2.getString("lat")+"  "+rs2.getString("lng"));
				//System.out.println("dis "+distance);
				 locationname=rs2.getString("locationname");
				 lat=rs2.getString("lat");
				 lng=rs2.getString("lng");
				
			
				 al.add(lat+"#"+lng+"#"+locationname+"#"+rs2.getString("Seasons"));
				System.out.println(lat+"#"+lng+"#"+locationname+"#"+rs2.getString("Seasons"));
				
				i++;
			}


			}



			

			
			
			
			
			
			
			
			
			/*al.add();
			"lat:"+ rs.getString(2),
		      lng: -8.745,
		      name: "Camping Praia da Barra",
		      address1:"Rua Diogo Cão, 125",
		      address2: "Praia da Barra",
		      postalCode: "3830-772 Gafanha da Nazaré"*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return al;
	}
}

