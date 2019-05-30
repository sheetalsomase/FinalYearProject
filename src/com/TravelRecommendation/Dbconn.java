package com.TravelRecommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbconn {
	public static String ProjectName="Travel";
	public static String Projectpath="F:\\BE 2018\\JSPM\\Workspace\\Travelrecommendation\\WebContent\\images\\";
	
	static Connection conn;
	public Dbconn() throws SQLException {
		// initComponents();
		// Connection con;
	}
	
	public static void main(String[] args) {
		System.out.println("----");
	}

	public static Connection getConnection(){
		try{
		Class.forName("com.mysql.jdbc.Driver");    
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/travel", "root", "admin");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return (conn);
	}
	
	public static void CloseConnection()
    {
        if(conn!=null)
        {
            try
            {
                conn.close();
                conn = null;
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }            
        }
    }

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		System.out.println("in funcation getResultFromSqlQuery "+SqlQueryString);
		ResultSet rs = null;
	//	Connection conn = null;
		try {
			if (conn == null) {
				getConnection();
			}
			rs = conn.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	/*Initializer.initializeResources(Projectpath,
			"com.mysql.jdbc.Driver",
			"jdbc:mysql://localhost/smartcrawler", "root", "admin");
	conn = Initializer.getConnection();
	System.out.println("Connection success...");*/
	
	public static void insertUpdateFromSqlQuery(String SqlQueryString) {
		System.out.println("in function insertUpdateFromSqlQuery "+SqlQueryString);
		try {
			if (conn == null) {
				getConnection();
			}
			conn.createStatement().executeUpdate(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}
