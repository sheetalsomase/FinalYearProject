package com.TravelRecommendation.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

import com.TravelRecommendation.Dbconn;



 

public class TSPNearestNeighbour

{

    private int numberOfNodes;

    private Stack<Integer> stack;

 

    public TSPNearestNeighbour()

    {

        stack = new Stack<Integer>();

    }

 

    public void tsp(float adjacencyMatrix[][])

    {

        numberOfNodes = adjacencyMatrix[1].length - 1;

        int[] visited = new int[numberOfNodes + 1];

        visited[1] = 1;

        stack.push(1);

        int element, dst = 0, i;

        float min = Integer.MAX_VALUE;

        boolean minFlag = false;
        System.out.print(arr.get(0).toString() + "\t");
        System.out.print(1 + "\t");
        Dbconn.insertUpdateFromSqlQuery("insert into latlong1 values('"+arr.get(0).toString()+"','"+arrlat.get(0).toString()+"','"+arrlng.get(0).toString()+"')");
 

        while (!stack.isEmpty())

        {

            element = stack.peek();

            i = 1;

            min = Integer.MAX_VALUE;

            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] >= 0 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }

            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(arr.get(dst-1).toString() + "\t");
                System.out.print(dst + "\t");
                Dbconn.insertUpdateFromSqlQuery("insert into latlong1 values('"+arr.get(dst-1).toString()+"','"+arrlat.get(dst-1).toString()+"','"+arrlng.get(dst-1).toString()+"')");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }
    public static ArrayList<locationInfo> ar = new ArrayList<locationInfo>();
    public static ArrayList arr=new ArrayList();
    public static ArrayList arrlat=new ArrayList();
    public static ArrayList arrlng=new ArrayList();
    public static int count=0;

    public static void main(String... arg)
    {
    	TSPProblem("pune","Temple and mandir");
    }
    public static void TSPProblem(String Searchword,String poi)
    {
    	/*String Searchword="Kashmir";
		String poi="Mountains";*/	
		int number_of_nodes=0;
        Scanner scanner = null;
        try
        {
        	ar = new ArrayList<locationInfo>();
        	arr=new ArrayList();
        	arrlat=new ArrayList();
        	arrlng=new ArrayList();
        	Dbconn.insertUpdateFromSqlQuery("truncate latlong1");
//        	ArrayList<locationInfo> ar = new ArrayList<locationInfo>();
            System.out.println("Enter the number of nodes in the graph");   
            ResultSet rsCount = Dbconn
    				.getResultFromSqlQuery("select count(*) from travellogue where City like '%"+ Searchword + "%' and  poi='"+poi+"'"); //City like '"+ Searchword + "%' and
            if(rsCount.next())
            {
            	count=rsCount.getInt(1);
            }
            double percentage=0.0,total=0.0,poscount=0.0;
            ResultSet rs = Dbconn
    				.getResultFromSqlQuery("select DISTINCT locationname,city,poi,lat,lng,pos,neg from travellogue where City like '%"+ Searchword + "%' and  poi='"+poi+"'"); //City like '"+ Searchword + "%' and
    		while (rs.next()) {
    			percentage=0.0;
    			if(count>4)
    			{
    				percentage=((rs.getDouble("pos"))/(rs.getDouble("pos")+rs.getDouble("neg")));
    				System.out.println("Percentage "+percentage);
    				if(percentage>0.50)
    				{
    				ar.add(new locationInfo((int)(percentage*100), rs.getString("locationname"), rs.getString("lat"), rs.getString("lng")));
    				//arr.add(rs.getString("locationname"));
        			//arrlat.add(rs.getString("lat"));
        			//arrlng.add(rs.getString("lng"));
    				}
    			}
    			else
    			{
    				ar.add(new locationInfo((int)(percentage*100), rs.getString("locationname"), rs.getString("lat"), rs.getString("lng")));
    			//arr.add(rs.getString("locationname"));
    			//arrlat.add(rs.getString("lat"));
    			//arrlng.add(rs.getString("lng"));
    			}
    		}
            
    		
    		LocationSorter locationSorter = new LocationSorter(ar);
    		ArrayList<locationInfo> sortedLocation = locationSorter.getSortedByPercentage();
    		System.out.println("-----Sorted JobCandidate by age: Ascending-----");
    		int only10=0;
    		for (locationInfo locationInfo : sortedLocation) {
    			if(only10<10)
    			{
    				arr.add(locationInfo.getLocationname());
        			arrlat.add(locationInfo.getLat());
        			arrlng.add(locationInfo.getLng());
    				System.out.println(locationInfo);
    			}
    			only10++;
    			
    		}
    		
            
            //scanner = new Scanner(System.in);

            number_of_nodes = arr.size();

            float adjacency_matrix[][] = new float[number_of_nodes +1][number_of_nodes +1 ];

            System.out.println("Enter the adjacency matrix");

            for (int i = 1; i <= number_of_nodes; i++)

            {

                for (int j = 1; j <= number_of_nodes; j++)

                {
                	double distance=0.0;
    				if(i!=j)
    				{
    				//Location loc1 = new Location(arr.get(i-1).toString(),Float.valueOf((String) arrlat.get(i-1)) , Float.valueOf((String) arrlng.get(i-1)));
    		        //Location loc2 = new Location(arr.get(j-1).toString(),    Float.valueOf((String) arrlat.get(j-1)), Float.valueOf((String) arrlng.get(j-1)));  
    		         //distance = loc1.distanceTo(loc2);
    		         distance= distFrom(Double.parseDouble((String) arrlat.get(i-1)), Double.parseDouble((String) arrlng.get(i-1)), Double.parseDouble((String) arrlat.get(j-1)), Double.parseDouble((String) arrlng.get(j-1)));
    		         System.out.println("Location "+arr.get(i-1).toString()+" --- "+arr.get(j-1).toString()+" -- "+distance);
    				}

                    adjacency_matrix[i][j] = (float) distance;

                }

            }

            for (int i = 1; i <= number_of_nodes; i++)

            {

                for (int j = 1; j <= number_of_nodes; j++)

                {
                	System.out.print("--"+adjacency_matrix[i][j]);

                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)

                    {

                        adjacency_matrix[j][i] = 1;

                    }

                }
                System.out.println("--");

            }

            System.out.println("the citys are visited as follows");

            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();

            tspNearestNeighbour.tsp(adjacency_matrix);

        } catch (Exception e)

         {
e.printStackTrace();
             System.out.println("Wrong Input format");

         }

 //       scanner.close();

    
    }
    
    public static float distFrom(double d, double e, double f, double g) {
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(f-d);
	    double dLng = Math.toRadians(g-e);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(f)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);
//System.out.println("---- "+dist);
double kilometers = dist /1000;
//System.out.println("---- "+(int) kilometers);
	    return (float) kilometers;
	    }
    

}