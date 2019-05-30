package com.TravelRecommendation.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.TravelRecommendation.Dbconn;

public class SearchDemo extends HttpServlet {

		
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
		getInforation(request.getParameter("skeyword"), request.getParameter("poi"));
			
			request.setAttribute("skeyword", request.getParameter("skeyword"));
		PrintWriter out1 = null;
		response.setContentType("text/html;charset=UTF-8");
		out1 = response.getWriter();
		out1.println("<html><script>alert('Store in db');</script><body>");
		out1.println("");
		out1.println("</body></html>");
		RequestDispatcher rd = request.getRequestDispatcher("/AddLatLong.jsp");
		rd.include(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void getInforation(String SKeyword,String POI) {

		try{
		System.out.println("======0 "+SKeyword);
		System.out.println("======0 "+POI);
	//	TripadvisorData.displaydata1(request.getParameter("skeyword"),request.getParameter("poi"));
		
		String SearchingKeyword = POI+" in "+SKeyword;
		String newDesignTripadvisor="";
		String url = SearchDemo.get_googleurls(SearchingKeyword);
		System.out.println("urllllllllll " + url);
		String inputlines = SearchDemo.save_tripadvisor_page(url, SearchingKeyword);

		File input = new File("F:\\HotelPages\\" + SearchingKeyword + ".html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		
		System.out.println("Title=" + doc.title());
		
		Elements PropertyDiv = doc.select("div.attraction_element").attr("attraction_element", "class2");

		for (Element div : PropertyDiv) {
			Document docc = Jsoup.parse(div.html());
			Elements PropertyDiv1 = docc.select("div.listing_title").attr("listing_title", "class2");
			for (Element div1 : PropertyDiv1) {
				newDesignTripadvisor="--";
				AddInforation(div1, docc, SKeyword, POI);
			}
		}
		if(!newDesignTripadvisor.equals("--"))
		{
			Elements PropertyDivNew = doc.select("div.attractions-attraction-overview-main-TopPOIs__info--1X5nA").attr("attractions-attraction-overview-main-TopPOIs__info--1X5nA", "class2");
			for (Element div : PropertyDivNew) {
				Document docc = Jsoup.parse(div.html());
				Elements PropertyDiv1 = docc.select("a.attractions-attraction-overview-main-TopPOIs__name--GndbY").attr("attractions-attraction-overview-main-TopPOIs__name--GndbY", "class2");
				for (Element div1 : PropertyDiv1) {
					
					AddInforation(div1, docc, SKeyword, POI);
				}
			}
		}
		
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	public static void AddInforation(Element div1,Document docc,String SKeyword,String POI) {
		try {
			

			//newDesignTripadvisor="--";
			Element link = docc.select("a").last();
		System.out.println("+++++++Link+++++++" + link.attr("href"));

		System.out.println("+++++++Link Location Name+++++++" + link.text());
		/*System.out.println("------"+div1.text());*/
		
		Elements links = docc.select("span");
		String City = links.text().replaceAll("\\(", "").replaceAll("\\)", "");
		
		String Review=getReviews(link.attr("href"),div1.text()).replaceAll("'", "");
		String[] score=scorecalculation(Review);
		String Seasons=getseasons(Review);
		
		try {
			String latLongs[] = LatLng.getLatLongPositions(div1.text() + " " + SKeyword);

			if(latLongs[0]!= null && latLongs[0].isEmpty() )
			{
			
			Dbconn.insertUpdateFromSqlQuery("insert into travellogue values('"+link.attr("href")+"','"+div1.text()+"','"+SKeyword+"','"+Review+"','"+score[0]+"','"+score[1]+"','"+POI+"','"+Seasons+"','"+latLongs[0]+"','"+latLongs[1]+"')");
			}
			else
			{
				System.err.println("The Lat Long are null");
			}
		} catch (Exception e) {
			Dbconn.insertUpdateFromSqlQuery("insert into travellogue values('"+link.attr("href")+"','"+div1.text()+"','"+SKeyword+"','"+Review+"','"+score[0]+"','"+score[1]+"','"+POI+"','"+Seasons+"','1','1')");
			e.printStackTrace();
		}
		
		//Dbconn.insertUpdateFromSqlQuery("insert into travellogue values('"+link.text()+"','"+div1.text()+"','"+request.getParameter("skeyword")+"','"+request.getParameter("poi")+"')");
		
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String get_googleurls(String searchword)
	{
		String google = "http://www.google.com/search?q=";
		String search ="10 best "+searchword+" tripadvisor";
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
		String url="";
		int pp=0;
		Elements links;
		try {
			links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
		

		for (Element link : links) {
		    String title = link.text();
		    url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

		    if (!url.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
		    break;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	
	public static void main(String[] args) {
		save_tripadvisor_page("http://getbootstrap.com/examples/jumbotron-narrow/","kishor");
	}
	
	public static String save_tripadvisor_page(String url,String SearchingKeyword)
	{
		StringBuilder inputlines = new StringBuilder();
		try{
		PrintWriter writer = new PrintWriter("F:\\HotelPages\\"+SearchingKeyword+".html", "UTF-8");
        // writer.println("The first line");
         URL google = new URL(url);
         BufferedReader in = new BufferedReader(new InputStreamReader(google.openStream()));
         String inputLine; 

         while ((inputLine = in.readLine()) != null) {
        	 inputlines.append(inputLine);
             // Process each line.
           //  System.out.println(inputLine);
             writer.println(inputLine);
         }
         writer.close();
         in.close(); 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return inputlines.toString();
	}
	
	public static String tripadvisor1(String url,String Filename)
	{
		StringBuilder inputlines = new StringBuilder();
		PrintWriter writer=null;
		BufferedReader in=null;
		try{
			 writer = new PrintWriter("F:\\TripFiles\\"+Filename+".html", "UTF-8");
	        // writer.println("The first line");
	         URL google = new URL(url);
	          in = new BufferedReader(new InputStreamReader(google.openStream()));
         String inputLine; 

         while ((inputLine = in.readLine()) != null) {
        	 inputlines.append(inputLine);
             // Process each line.
           //  System.out.println(inputLine);
             writer.println(inputLine);
         }
         in.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		writer.close();
        
		return inputlines.toString();
	}
	
	public static String getReviews(String url,String Filename) {
		String Review="";
		try {

			
			url = "https://www.tripadvisor.in"+url;
			System.out.println("urllllllllll " + url);
			String inputlines = SearchDemo.tripadvisor1(url,Filename);

			// https://www.tripadvisor.in/Attractions-g297648-Activities-c20-t99-Maharashtra.html

			File input = new File("F:\\TripFiles\\"+Filename+".html");
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

			Elements masthead = doc.getElementsByClass("rev_wrap");  //reviewWrap
			
			
			 for (Element div : masthead) {
				 
				 Document docc = Jsoup.parse(div.html());
		            System.out.println("---|||||--------"+div.html());  
		            
		            Elements titleofreview = docc.getElementsByClass("noQuotes");
		            for (Element div1 : titleofreview) {
		            	//System.out.println("======title=======================================");
		            	System.out.println("-----------"+div1.html());
		            	Elements images = docc.getElementsByClass("partial_entry");
			            for (Element div2 : images) {
			            	//System.out.println("=============================================");
			            	//System.out.println("-----------"+div1.html());
			            	 Document docc1 = Jsoup.parse(div2.html());
			            	 Review+=div1.text()+" "+ div2.text()+"##";
					         System.out.println("===== "+div1.text()+" **** "+ div2.text());
			            }	
		            }    
		        }
			 
			 
			 Elements masthead1 = doc.getElementsByClass("imgWrap");
				//Elements masthead =doc.getElementsByTag("img");
				 int i=0;
				 for (Element div : masthead1) {
					 System.out.println("-----------"+div.html());
					 Document docc = Jsoup.parse(div.html());
					 Elements link = docc.getElementsByTag("img");
					 Elements media = docc.select("[src]");
					
					 for (Element src : media) {
				            if (src.tagName().equals("img"))
				            {
				            	if(i==1)
				            	{
				            	System.out.println(" * %s: <%s> %sx%s (%s)"+
				                        src.tagName()+"----"+ src.attr("abs:src"));
				            	saveImage(src.attr("abs:src"),Filename);
				            	}
				            	i++;
				            }
				        }
				 }
			 Elements masthead2 = doc.getElementsByClass("oh");
			 for (Element div : masthead2) {
		            System.out.println("-----WRAP------"+div.text());        
		        }	
			 input.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====Review========="+Review);
		return Review;

	}
	
	public static String[] scorecalculation(String review)
	{
		String Stemmed="";
		try{
		
		RemoveStopwords rmst=new RemoveStopwords();
		String stopwordless=rmst.RemoveWords(review);
		
		
		
		Stemmer ss = new Stemmer();
		 String fname="E:\\StemmerInput.txt";
        
           Writer writer = null;
           writer = new BufferedWriter(new OutputStreamWriter(
           new FileOutputStream(fname), "utf-8"));
           writer.append(stopwordless);
           writer.close();
             Stemmed=ss.GetData(fname);
         //  System.out.println("Final Data after Stemmed: - \t"+Stemmed);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String arr=calculateScore.Calcscore(Stemmed);
		//System.out.println("Score in scorecal ----- "+arr);
		String[] score=arr.split("\t");
		return score;
		
	}

	
	public static String getseasons(String Review)
	{
		String season="";

		 
		if(Review.contains("February") || Review.contains("March") || Review.contains("April") || Review.contains("May"))
		{
			season+="Summer";
		}
		 if(Review.contains("June") || Review.contains("July") || Review.contains("August") || Review.contains("September"))
		{
			season+="Rainy";
		}
		 if(Review.contains("October") || Review.contains("November") || Review.contains("December") || Review.contains("January"))
		{
			season+="Winter";
		}
		 if(season.equals("SummerRainyWinter"))
		 {
			 season="All";
		 }
			
		if(season.equals(""))
		{
			season="All";
		}
		return season;
	}

	public static void saveImage(String imageUrl,String Filename) throws IOException  {
		URL url = new URL(imageUrl);
		String fileName = url.getFile();
		String destName = Dbconn.Projectpath + Filename+".jpg";
		System.out.println(destName);
	 
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destName);
	 
		byte[] b = new byte[2048];
		int length;
	 
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
	 
		is.close();
		os.close();
	}
	

}


