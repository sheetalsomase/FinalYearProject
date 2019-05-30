package com.TravelRecommendation.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class calculateScore {
	static int maxno;
	static int posve = 0;
	static int negve = 0, finalChecking = 0;
	String neutral;
	static Boolean positivevalue = false, negativevalue = false;

	public static String Calcscore(String data){
		boolean flag = false;
		Scanner scposve, scnegve, prrp;
		String finalCheck = null;
		String str = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();

		String[] tokens = data.split("\t");// \\p is use for space and {Alpha
		posve = 0;								// for (')eg it's}
		negve = 0;
		for (String s : tokens) {
		//	System.out.println("Input Words:- " + s);
			try {
			scposve = new Scanner(new File("E:\\positive.txt"));
			scnegve = new Scanner(new File("E:\\Negative.txt"));
			
			while (scposve.hasNext()) {
				String value=scposve.next();
				//System.out.println("----- "+value+" ----- "+s);
				if (value.equals(s)) {
					//System.out.println("scposve words:- " + value+"---"+s);
					if (negativevalue) {
						negativevalue = false;
						//negve++;
						break;
					} else {
						positivevalue = true;
						posve++;
						break;
					}
				} else {

					positivevalue = false;

				}

			}
			while (scnegve.hasNext()) {
				if (scnegve.next().equals(s)) {
					if (negativevalue) {
						negativevalue = false;
						 posve++;
						 negve--;
						break;
					} else {
						negve++;
						finalChecking++;
						negativevalue = true;
						sb.append(s + "\t");
						break;
					}
				} else {
					negativevalue = false;
				}
			}

			/*if (finalChecking == 2) {
				negve = negve - 2;
				posve++;
			}*/
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return "" + posve + "\t" + negve;
	}

	public static void main(String args[]) throws FileNotFoundException {
		calculateScore calculateScore = new calculateScore();
		String score = calculateScore.Calcscore("iphone camera not good :*	");

		String[] parts = score.split("\t");
		int pos = Integer.parseInt(parts[0]);
		int neg = Integer.parseInt(parts[1]);

		System.out.println("Postive words:- " + pos);
		System.out.println("Negetive words:- " + neg);
	}

	
	/*
	
	
	

    private static Statement st;
    static int maxno;
    int posve=0;
    int negve=0,finalChecking=0;
    String neutral;
   
    public String Calcscore(String data) throws FileNotFoundException
    {
        boolean flag=false;
        Scanner scposve,scnegve,prrp;
        String finalCheck=null;
        String str = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
    
        String [] tokens = data.split("\t");// \\p is use for space and {Alpha for (')eg it's}
        
   for(String s : tokens){
	   scposve=new Scanner(new File("D:\\+ve.txt"));
	   scnegve = new Scanner(new File("D:\\-ve.txt"));
	   
       
    while(scposve.hasNext())
    {   
        if(scposve.next().equals(s)){
         
        	posve++;
            break;
        }
        else 
        {
        	
        	 while(scnegve.hasNext())
        	    {   
        	        if(scnegve.next().equals(s)){
        	          
        	        	negve++;
        	        	finalChecking++;
        	        	
        	        	sb.append(s+"\t");
        	            break;
        	        }
        	       
        	    }
        	   

        	
        }
       
    }
   
   
    
     
   }
    return ""+posve+"\t"+negve;
}
   public static void main(String args[]) throws FileNotFoundException
   {
       
   }



*/}

