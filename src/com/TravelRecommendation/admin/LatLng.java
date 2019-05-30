package com.TravelRecommendation.admin;

import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.xml.crypto.Data;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;

import com.TravelRecommendation.Dbconn;



/**
 * This class will get the lat long values.
 * 
 * @author SANTHOSH REDDY MANDADI
 * @version 1.0
 * @since 20-Sep-2012
 */
public class LatLng {
	public static void main(String[] args)  {
		//displayLatlongDb();
		//displayLatlongDb1();
	diplaylocation("Nashik");
	//getLatLongPositions("Season mall pune");
	}
	
	
	
	
	
	public static void diplaylocation(String location)
	{
		String latLongs[] = getLatLongPositions(location);
		
		System.out.println("Location :"+location+"  Latitude: " + latLongs[0] + " and Longitude: "
				+ latLongs[1]);
		System.out.println("---------------------------------");
	}
	
	public static void displayLatlongDb1()
	{
		try{
			ResultSet rs = Dbconn
					.getResultFromSqlQuery("select * from travellogue");
			while (rs.next()) {
				try{
					Thread.sleep(2400);
				System.out.println("---------------------------------"+rs.getString("locationname"));
				/*if(rs.getString("locationname").length()<20)
				{*/
				
				String latLongs[] = getLatLongPositions(rs
						.getString("locationname")+","+rs.getString("City"));
				
				System.out.println("Location :"+rs.getString("locationname")+"  Latitude: " + latLongs[0] + " and Longitude: "
						+ latLongs[1]);
				//Dbconn.insertUpdateFromSqlQuery("insert into latlong values('"+rs.getString("locationname")+"','"+latLongs[0]+"','"+latLongs[1]+"')");
				
				System.out.println("---------------------------------");
				//}
				}catch(Exception e)
				{
				//	Dbconn.insertUpdateFromSqlQuery("delete from travellogue where locationname='"+rs.getString("locationname")+"'");
				//	System.out.println("deleted");
				e.printStackTrace();	
				}
			}

	}catch(Exception e)
	{
	e.printStackTrace();	
	}

	}

	public static String[] getLatLongPositions(String address)  {
		String data="";
		try{
			
			System.out.println("address ---------- "+address);
		int responseCode = 0;
		
		String api = "https://maps.googleapis.com/maps/api/geocode/xml?address="
				+ URLEncoder.encode(address, "UTF-8") + "&key=AIzaSyDWeJHq9HGts4LK2jTAfi12DFIipFI7Qos";
		System.out.println("API -- "+api);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url
				.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			;
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document,
					XPathConstants.STRING);
			System.out.println("--- Status ------ "+status);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location/lat");
				String latitude = (String) expr.evaluate(document,
						XPathConstants.STRING);
				expr = xpath.compile("//geometry/location/lng");
				String longitude = (String) expr.evaluate(document,
						XPathConstants.STRING);
				data=latitude+" "+longitude;
				return new String[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: "
						+ status);
			}
		}
		}catch(Exception e)
		{
			System.err.println(e.toString());
			//e.printStackTrace();
		}
		finally
		{/*
			if(data.equals(""))
			{
				System.out.println("Finally Block Called"+address);
				try{
					Thread.sleep(2400);		
					System.out.println("address ---------- "+address);
				int responseCode = 0;
				String api = "http://maps.googleapis.com/maps/api/geocode/xml?address="
						+ URLEncoder.encode(address, "UTF-8") + "&sensor=true";
				URL url = new URL(api);
				HttpURLConnection httpConnection = (HttpURLConnection) url
						.openConnection();
				httpConnection.connect();
				responseCode = httpConnection.getResponseCode();
				
				if (responseCode == 200) {
					DocumentBuilder builder = DocumentBuilderFactory.newInstance()
							.newDocumentBuilder();
					;
					Document document = builder.parse(httpConnection.getInputStream());
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					XPathExpression expr = xpath.compile("/GeocodeResponse/status");
					String status = (String) expr.evaluate(document,
							XPathConstants.STRING);
					if (status.equals("OK")) {
						expr = xpath.compile("//geometry/location/lat");
						String latitude = (String) expr.evaluate(document,
								XPathConstants.STRING);
						expr = xpath.compile("//geometry/location/lng");
						String longitude = (String) expr.evaluate(document,
								XPathConstants.STRING);
						data=latitude+" "+longitude;
						return new String[] { latitude, longitude };
					} else {
						throw new Exception("Error from the API - response status: "
								+ status);
					}
				}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			
			}
		*/}
		return null;
	}
}