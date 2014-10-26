package com.ds.model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import com.ds.db.DBHelper;
import com.google.gson.stream.JsonWriter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DSObjectWriter {
	
	private JsonWriter jsonWriter = null;
	
	private DSObjectWriter(String file) throws IOException {
		jsonWriter = new JsonWriter(new FileWriter(file));
	}
	public void writeObject(Object obj) throws IOException {
		if (obj instanceof Review) {
			writeReviewObject((Review) obj);
		}
		else {
			throw new NotImplementedException();
		}
	}
	
	public void close() throws IOException {
		jsonWriter.close();
	}
	
	private void writeReviewObject(Review review) throws IOException {
		jsonWriter.setLenient(true);
		jsonWriter.beginObject();
		jsonWriter.name(Review.TYPE).value(review.getType());
		jsonWriter.name(Review.BUSINESS_ID).value(review.getBusinessId());
		jsonWriter.name(Review.USER_ID).value(review.getUserId());
		jsonWriter.name(Review.STARS).value(review.getStars());
		jsonWriter.name(Review.TEXT).value(review.getText());
		jsonWriter.name(Review.DATE).value(review.getDate());
		jsonWriter.name(Review.VOTES);
		jsonWriter.beginObject();
		jsonWriter.name(Votes.COOL).value(review.getVotes().getCool());
		jsonWriter.name(Votes.FUNNY).value(review.getVotes().getFunny());
		jsonWriter.name(Votes.USEFUL).value(review.getVotes().getUseful());
		jsonWriter.endObject();
		jsonWriter.endObject();
	}
	
	public static void main(String []args) {
		try {
			DSObjectReader objReader = new DSObjectReader("/home/biplap/Downloads/yelp/yelp_academic_dataset_review.json");
			DSObjectWriter objWriter = new DSObjectWriter("food_dataset_review.json");
			DBHelper dbHelper = new DBHelper();
			dbHelper.connect();
			Review review = null;
			boolean isFood = false;
			int countTotal = 0;
			int countFood = 0;
			
			while (objReader.hasNext()) {
				countTotal++;
				review = objReader.readObject(Review.class);
				try {
					isFood = dbHelper.getBoolean(Business.class, review.getBusinessId(), Business.ISCATEGORYFOOD);
				} catch (SQLException e) {
					continue;
				}
				if (isFood && review.getVotes().getUseful()>0) {
					countFood++;
					objWriter.writeObject(review);
				}
			}
			dbHelper.close();
			objReader.close();
			objWriter.close();
			System.out.println(countFood+" food reviews found out of "+countTotal);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}
