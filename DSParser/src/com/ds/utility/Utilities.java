package com.ds.utility;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import com.ds.db.DBHelper;
import com.ds.model.Business;
import com.ds.model.DSObjectReader;
import com.ds.model.DSObjectWriter;
import com.ds.model.Review;
import com.google.gson.stream.JsonWriter;

public class Utilities {

	public static void main (String []args) {
		try {
			filterFoodReviews("/home/biplap/Downloads/yelp/yelp_academic_dataset_review.json", "/home/biplap/Downloads/yelp/yelp_academic_dataset_food_review.json");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void exportReviewsToCSV(String inputFile, String outputFile) throws IOException {
		DSObjectReader dsObjectReader = new DSObjectReader(inputFile);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		Review review = null;
		StringBuilder sb = null;
		while (dsObjectReader.hasNext()) {
			sb = new StringBuilder("");
			review = dsObjectReader.readObject(Review.class);
			sb.append(review.getBusinessId());
			sb.append(",");
			sb.append(review.getUserId());
			sb.append(",");
			sb.append(review.getStars());
			sb.append(",");
			sb.append(review.getText());
			sb.append("\n");
			bw.write(sb.toString());
		}
		dsObjectReader.close();
		bw.close();
	}
	
	public static void filterFoodReviews(String inputFile, String outputFile) throws IOException, ClassNotFoundException, SQLException {
		
		DSObjectReader dsObjectReader = new DSObjectReader(inputFile);
		DSObjectWriter dsObjectWriter = new DSObjectWriter(outputFile);
		DBHelper dbHelper = new DBHelper();
		int totalCount = 0;
		int foodCount = 0;
		dbHelper.connect();
		Review review = null;
		Business business = null;
		
		while (dsObjectReader.hasNext()) {
			totalCount++;
			review = dsObjectReader.readObject(Review.class);
			try {
				business = dbHelper.get(Business.class, review.getBusinessId());
				if (business.isCategoryFood()) {
					foodCount++;
					dsObjectWriter.writeObject(review);
				}
			}  catch (SQLException e) {
				continue;
				//e.printStackTrace();
			}
			
		}
		
		dbHelper.close();
		dsObjectReader.close();
		dsObjectWriter.close();
		System.out.println(foodCount+" food reviews written out of "+totalCount+" reviews");
	}
}

