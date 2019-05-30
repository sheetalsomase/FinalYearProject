package com.TravelRecommendation.admin;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.TravelRecommendation.Dbconn;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getinformation("Temple and mandir in pune");
		//nullornot();
		//SearchDemo.getReviews("/Attraction_Review-g303883-d2009090-Reviews-Ramkund-Nashik_Nashik_District_Maharashtra.html", "Ramkund");
	}

	public static void getinformation(String SearchingKeyword)
	{
		try {

		File input = new File("E:\\HotelPages\\" + SearchingKeyword + ".html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		System.out.println("Title=" + doc.title());
		Elements PropertyDiv = doc.select("div.attractions-attraction-overview-main-TopPOIs__info--1X5nA").attr("attractions-attraction-overview-main-TopPOIs__info--1X5nA", "class2");
		//Elements PropertyDiv = doc.select("div.attraction_element").attr("attraction_element", "class2");

		for (Element div : PropertyDiv) {
			//System.out.println("---- "+div.html());
			Document docc = Jsoup.parse(div.html());
			//Elements PropertyDiv1 = docc.select("div.listing_title").attr("listing_title", "class2");
			Elements PropertyDiv1 = docc.select("a.attractions-attraction-overview-main-TopPOIs__name--GndbY").attr("attractions-attraction-overview-main-TopPOIs__name--GndbY", "class2");
			for (Element div1 : PropertyDiv1) {
			Element link = docc.select("a").last();
			System.out.println("+++++++Link+++++++" + link.attr("href"));

			System.out.println("+++++++Link Location Name+++++++" + link.text());
			System.out.println("+++++++Link Location Name+++++++"+div1.text());
			}
		}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void nullornot() {
		String latLongs=null;
		if(latLongs!=null&& latLongs.isEmpty() )
		{
			System.err.println("The Lat Long are not null");
		//Dbconn.insertUpdateFromSqlQuery("insert into travellogue values('"+link.attr("href")+"','"+div1.text()+"','"+request.getParameter("skeyword")+"','"+Review+"','"+score[0]+"','"+score[1]+"','"+request.getParameter("poi")+"','"+Seasons+"','"+latLongs[0]+"','"+latLongs[1]+"')");
		}
		else
		{
			System.err.println("The Lat Long are null");
		}
	}
	
}
