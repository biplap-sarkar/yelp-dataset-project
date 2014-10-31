package com.ds.annotation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ds.db.DBHelper;
import com.ds.model.DSObjectReader;
import com.ds.model.Review;
import com.ds.model.ReviewSentiment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Annotation {
	
	private final int FOOD = 0;
	private final int SERVICE = 1;
	private final int AMBIENCE = 2;
	private final int PRICE = 3;
	
	private static String[] food = new String[] {"food","menu","delicious","breakfast","lunch","dinner","meal","salad","buffet","order",
			"hot","cold","eat","favorite","drinks","fresh","chicken","pizza","cheese","mexican"};
	private static String[] service = new String[] {"service","friendly","staff"};
	private static String[] ambience = new String[] {"ambience","atmosphere","clean","dirty"};
	private static String[] price = new String[] {"price","cost","prices","worth","cheap"};
	ArrayList<String[]> match = new ArrayList<String[]>();
	HashMap<Integer,Boolean> category = new HashMap<Integer,Boolean>();
	
	public static void main (String []args)
	{
		String []text = new String[2];
		text[0] = "food was bad";
		text[1] = "food was good";
		int i = 0;
		ArrayList<String> list = new ArrayList<>();
		
		while(i < text.length)
		{
			StringBuffer sb = new StringBuffer("\"");
			sb.append(text[i++]);
			sb.append("\"");
			list.add(sb.toString());
		}
		System.out.println(list.toString());
		try {
			new Annotation().annotateReviews("/home/satya/GitRepo/PdP_Project/test_review.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void annotateReviews(String inputFile) throws IOException, ClassNotFoundException, SQLException {
		match.add(food);
		match.add(service);
		match.add(ambience);
		match.add(price);
		
		DSObjectReader dsObjectReader = new DSObjectReader(inputFile);
		Review review = new Review();
		DBHelper dbHelper = new DBHelper();
		dbHelper.connect();
		
		while (dsObjectReader.hasNext()) {
			category.put(FOOD, false);
			category.put(SERVICE, false);
			category.put(AMBIENCE, false);
			category.put(PRICE, false);
			
			review = dsObjectReader.readObject(Review.class);
			String lines[] = review.getText().split("\\.");
			int i = 0;
			ArrayList<String> list = new ArrayList<>();
			
			while(i < lines.length)
			{
				StringBuffer sb = new StringBuffer("\"");
				sb.append(lines[i++]);
				sb.append("\"");
				list.add(sb.toString());
			}
			ArrayList<Dataset> arrObj = post("http://sentiment.vivekn.com/api/batch/",list.toString());
			i = 0;
			Classifier classifierObj = new Classifier();
			while(i < lines.length)
			{
				findClass(classifierObj,lines[i],arrObj.get(i));
				i++;
			}
			
			//updateDB(dbHelper,review,classifierObj);
		}
	}
	
	private void updateDB(DBHelper dbHelper, Review review, Classifier classifierObj) throws ClassNotFoundException, IOException, SQLException
	{
		ReviewSentiment rs = new ReviewSentiment();
		rs.setText(review.getText());
		rs.setBusinessId(review.getBusinessId());
		rs.setUserId(review.getUserId());
		rs.setPositiveFood(classifierObj.isFoodPositive());
		rs.setNegativeFood(classifierObj.isFoodNegative());
		rs.setPositiveAmbience(classifierObj.isAmbPositive());
		rs.setNegativeAmbience(classifierObj.isAmbNegative());
		rs.setPositiveService(classifierObj.isServicePositive());
		rs.setNegativeService(classifierObj.isServiceNegative());
		rs.setPositivePrice(classifierObj.isPricePositive());
		rs.setNegativePrice(classifierObj.isPriceNegative());
		dbHelper.save(rs);
	}
	
	private void findClass(Classifier classifierObj, String line, Dataset datasetObj)
	{
		int index = 0;
		for(String []itr:match)
		{
			if(!category.get(index))
				if(findMatch(itr,classifierObj,line,datasetObj, index) == true)
					category.put(index, true);
			index++;
		}
	}
	
	private boolean findMatch(String []match,Classifier classifierObj, String line, Dataset datasetObj, int index)
	{
		for (String s : match)
		{
		  if (line.contains(s))
		  {
			  switch (index) {
			  case FOOD:
				  if(datasetObj.result.equalsIgnoreCase("positive"))
					  classifierObj.SetPositiveFood(true);
				  else if(datasetObj.result.equalsIgnoreCase("negative"))
					  classifierObj.SetNegativeFood(true);
				  return true;
				
			  case SERVICE:
				  if(datasetObj.result.equalsIgnoreCase("positive"))
					  classifierObj.SetPositiveService(true);
				  else if(datasetObj.result.equalsIgnoreCase("negative"))
					  classifierObj.SetNegativeService(true);
				  return true;
					
			  case AMBIENCE:
				  if(datasetObj.result.equalsIgnoreCase("positive"))
					  classifierObj.SetPositiveAmbience(true);
				  else if(datasetObj.result.equalsIgnoreCase("negative"))
					  classifierObj.SetNegativeAmbience(true);
				  return true;
					
			  case PRICE:
				  if(datasetObj.result.equalsIgnoreCase("positive"))
					  classifierObj.SetPositivePrice(true);
				  else if(datasetObj.result.equalsIgnoreCase("negative"))
					  classifierObj.SetNegativePrice(true);
				  return true;

			default:
				break;
			}
		  }
		}
		return false;
	}	
	
	private static ArrayList<Dataset> post(String targetUrl, String urlParameters)
	{
		URL url = null;
	    HttpURLConnection connection = null;
	    try
	    {
	    	url = new URL(targetUrl);
	    	connection = (HttpURLConnection)url.openConnection();
	    	connection.setRequestMethod("POST");
	    	connection.setRequestProperty("Content-Type", "application/json");
	    	
	    	connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	    	connection.setRequestProperty("Content-Language", "en-US");
	    	
	    	//Send request
	    	connection.setDoOutput(true);
	    	connection.setDoInput(true);
	        DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
	        wr.writeBytes (urlParameters);
	        wr.flush ();
	        wr.close ();
	        
	      //Get Response	
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuffer response = new StringBuffer();
	        JsonParser parser = new JsonParser();
	        ArrayList<Dataset> arrayObj = new ArrayList<Dataset>();
	        while((line = rd.readLine()) != null) {
	        	JsonArray jArray = parser.parse(line).getAsJsonArray();
	        	System.out.println(line);
	        	for(JsonElement obj : jArray )
	        	{
	        		Gson gson = new Gson();
	        		arrayObj.add(gson.fromJson(obj, Dataset.class));
	        	}
	        	response.append(line);
	        	response.append('\r');
	        }
	        rd.close();
	        return arrayObj;
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	private class Dataset {
	    public String confidence;
	    public String result;
	}
	
	private class Classifier{
		public Classifier()
		{
			isFoodPositive = false;
			isFoodNegative = false;
			isServicePositive = false;
			isServiceNegative = false;
			isAmbiancePositive = false;
			isAmbianceNegative = false;
			isPricePositive = false;
			isPriceNegative = false;
		}
		private boolean isFoodPositive;
		private boolean isFoodNegative;
		private boolean isServicePositive;
		private boolean isServiceNegative;
		private boolean isAmbiancePositive;
		private boolean isAmbianceNegative;
		private boolean isPricePositive;
		private boolean isPriceNegative;
		
		public void SetPositiveFood(boolean value)
		{
			isFoodPositive = value;
		}
		
		public void SetNegativeFood(boolean value)
		{
			isFoodNegative = value;
		}
		
		public void SetPositiveService(boolean value)
		{
			isServicePositive = value;
		}
		
		public void SetNegativeService(boolean value)
		{
			isServiceNegative = value;
		}
		
		public void SetPositiveAmbience(boolean value)
		{
			isAmbiancePositive = value;
		}
		
		public void SetNegativeAmbience(boolean value)
		{
			isAmbianceNegative = value;
		}
		
		public void SetPositivePrice(boolean value)
		{
			isPricePositive = value;
		}
		
		public void SetNegativePrice(boolean value)
		{
			isPriceNegative = value;
		}
		
		public boolean isFoodPositive(){return isFoodPositive;}
		public boolean isFoodNegative(){return isFoodNegative;}
		public boolean isServicePositive(){return isServicePositive;}
		public boolean isServiceNegative(){return isServiceNegative;}
		public boolean isAmbPositive(){return isAmbiancePositive;}
		public boolean isAmbNegative(){return isAmbianceNegative;}
		public boolean isPricePositive(){return isPricePositive;}
		public boolean isPriceNegative(){return isPriceNegative;}
		
	}
}
