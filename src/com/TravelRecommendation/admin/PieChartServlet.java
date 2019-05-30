package com.TravelRecommendation.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;


import com.TravelRecommendation.Dbconn;






public class PieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection dbConnection = null;

	public PieChartServlet() {
		dbConnection = Dbconn.getConnection();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    JDBCCategoryDataset dataset = new JDBCCategoryDataset(dbConnection);
    
    try {
    	Dbconn.insertUpdateFromSqlQuery("truncate analysis");
    	ResultSet rs=Dbconn.getResultFromSqlQuery("select count(*) from adminnotification");
    	ResultSet rs2=Dbconn.getResultFromSqlQuery("select count(*) from userlog");
    	rs.next();
    	rs2.next();
    	
    	
    	Dbconn.insertUpdateFromSqlQuery("insert into analysis values('1','"+((rs2.getDouble(1)-rs.getDouble(1))/rs2.getDouble(1))+"','"+(rs.getDouble(1)/rs2.getDouble(1))+"')");
    	
    	
    	
    	
    dataset.executeQuery("SELECT id,precison,recall FROM analysis");
    
    JFreeChart chart = ChartFactory.createBarChart(
           "Precesion and Recall", "", "Values",
           dataset, PlotOrientation.VERTICAL, true, true, true);           
           chart.setBorderVisible(true);
    
    if (chart != null) {
            int width = 600;
            int height = 400;
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();
            ChartUtilities.writeChartAsJPEG(out, chart, width, height);
    }
    }
    catch (SQLException e) {
            System.err.println(e.getMessage());
    }
	}
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}