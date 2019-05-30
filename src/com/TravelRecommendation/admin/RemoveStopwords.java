package com.TravelRecommendation.admin;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.util.Scanner;

public class RemoveStopwords {
    private static Statement st;
    static int maxno;
    public String RemoveWords(String data) throws FileNotFoundException
    {
        boolean flag=false;
        Scanner sc;
        String str = null;
        StringBuilder sb = new StringBuilder();
    
         String [] tokens = data.split("\\P{Alpha}+");// \\p is use for space and {Alpha for (')eg it's}
        
   for(String s : tokens){
    sc=new Scanner(new File("E:\\stopwords.txt"));
       
    while(sc.hasNext())
    {   
        if(sc.next().equals(s)){
            flag = true;
            break;
        }
        else
        {
            flag=false;
        }
       
    }  
      if(flag==false)
        {
        
        sb.append(s+"\t");
        }
   }
    return sb.toString();
}
   public static void main(String args[]) throws FileNotFoundException
   {
       RemoveStopwords rm=new RemoveStopwords();
       String result=rm.RemoveWords("The data is to be updated");
       System.out.println("Data is "+result);
       
       
       Stemmer ss = new Stemmer();
		 String fname="E:\\StemmerInput.txt";
      
         Writer writer = null;
         try {
			writer = new BufferedWriter(new OutputStreamWriter(
			 new FileOutputStream(fname), "utf-8"));
		
         writer.append(result);
         writer.close();
         } catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
          String Stemmed=ss.GetData(fname);
         System.out.println("Final Data after Stemmed: - \t"+Stemmed);
       
   }

}

    
    

