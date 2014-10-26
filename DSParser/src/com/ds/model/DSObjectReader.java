package com.ds.model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ds.db.DBHelper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Class to parse objects from Yelp Dataset Json files in streaming fashion.
 * @author biplap
 *
 */
public class DSObjectReader {
	
	private JsonReader jsonReader = null;
	
	/**
	 * Initializes the Object reader with a file.
	 * @param fileName: name of the file which needs to be parsed
	 * 
	 * @throws FileNotFoundException
	 */
	public DSObjectReader(String fileName) throws FileNotFoundException {
		jsonReader = new JsonReader(new FileReader(fileName));
	}
	
	/**
	 * 
	 * @param type of the object which needs to be parsed.
	 * 
	 * @return parsed object of type T
	 * 
	 * @throws IOException
	 */
	public <T> T readObject(Class<T> type) throws IOException {
		if (jsonReader == null) {
			throw new IllegalStateException("File information not found");
		}
		if (type.equals(Business.class)) {
			return type.cast(readBusinessObject());
		}
		else if (type.equals(Review.class)) {
			return type.cast(readReviewObject());
		}
		else if (type.equals(Votes.class)) {
			return type.cast(readVotesObject());
		}
		else if (type.equals(User.class)) {
			return type.cast(readUserObject());
		}
		throw new ClassCastException("Could not find object of any supported Class");
	}
	
	/**
	 * 
	 * @return boolean value indicating if more records exist in file
	 * 
	 * @throws IOException
	 */
	public boolean hasNext() throws IOException {
		return !jsonReader.peek().equals(JsonToken.END_DOCUMENT);
	}
	
	/**
	 * Closes the object reader associated file
	 * @throws IOException
	 */
	public void close() throws IOException {
		jsonReader.close();
	}
	
	private Business readBusinessObject() throws IOException {
		Business business = new Business();
		business.setCategoryFood(false);
		jsonReader.setLenient(true);
		jsonReader.beginObject();
		String key = "";
		String nextCategory;
		while (jsonReader.hasNext()) {
			key = jsonReader.nextName();
			if (key.equalsIgnoreCase(Business.TYPE)) {
				business.setType(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.BUSINESS_ID)) {
				business.setId(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.NAME)) {
				business.setName(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.NEIGHBORHOODS)) {
				business.setNeighborhoodList(new ArrayList<String>());
				jsonReader.beginArray();
				while (jsonReader.hasNext()) {
					business.getNeighborhoodList().add(jsonReader.nextString());
				}
				jsonReader.endArray();
			}
			else if (key.equalsIgnoreCase(Business.FULL_ADDRESS)) {
				business.setFullAddress(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.CITY)) {
				business.setCity(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.STATE)) {
				business.setState(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.LATITUDE)) {
				business.setLatitude(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.LONGITUDE)) {
				business.setLongitude(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Business.STARS)) {
				business.setStars((float) jsonReader.nextDouble());
			}
			else if (key.equalsIgnoreCase(Business.REVIEW_COUNT)) {
				business.setReviewCount(jsonReader.nextInt());
			}
			else if (key.equalsIgnoreCase(Business.CATEGORIES)) {
				business.setCategories(new ArrayList<String>());
				jsonReader.beginArray();
				while (jsonReader.hasNext()) {
					nextCategory = jsonReader.nextString();
					if (nextCategory.toLowerCase().contains("food") || nextCategory.toLowerCase().contains("restaurant")) {
						business.setCategoryFood(true);
					}
					business.getCategories().add(nextCategory);
				}
				jsonReader.endArray();
			}
			else if (key.equalsIgnoreCase(Business.OPEN)) {
				business.setOpen(jsonReader.nextBoolean());
			}
			else {
				jsonReader.skipValue();
			}
		}
		jsonReader.endObject();
		return business;
	}
	
	
	/**
	 * Parses a Review object from the JSON stream
	 * 
	 * @return parsed Review object
	 * @throws IOException
	 */
	private Review readReviewObject() throws IOException {
		Review review = new Review();
		jsonReader.setLenient(true);
		jsonReader.beginObject();
		String key = "";
		while(jsonReader.hasNext()) {
			key = jsonReader.nextName();
			if (key.equalsIgnoreCase(Review.TYPE)) {
				review.setType(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Review.BUSINESS_ID)) {
				review.setBusinessId(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Review.USER_ID)) {
				review.setUserId(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Review.STARS)) {
				review.setStars(jsonReader.nextDouble());
			}
			else if (key.equalsIgnoreCase(Review.TEXT)) {
				review.setText(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Review.DATE)) {
				review.setText(jsonReader.nextString());
			}
			else if (key.equalsIgnoreCase(Review.VOTES)) {
				review.setVotes(readVotesObject());
			}
			else {
				jsonReader.skipValue();
			}
		}
		jsonReader.endObject();
		
		return review;
	}
	
	private User readUserObject() {
		return null;
	}
	
	/**
	 * Parses a Votes object from the JSON stream
	 * 
	 * @return parsed Votes object
	 * @throws IOException
	 */
	private Votes readVotesObject() throws IOException {
		Votes votes = new Votes();
		jsonReader.beginObject();
		String key = "";
		while(jsonReader.hasNext()) {
			key = jsonReader.nextName();
			if (key.equalsIgnoreCase(Votes.USEFUL)) {
				votes.setUseful(jsonReader.nextInt());
			}
			else if (key.equalsIgnoreCase(Votes.FUNNY)) {
				votes.setFunny(jsonReader.nextInt());
			}
			else if (key.equalsIgnoreCase(Votes.COOL)) {
				votes.setCool(jsonReader.nextInt());
			}
			else {
				jsonReader.skipValue();
			}
		}
		jsonReader.endObject();
		return votes;
	}
	
	public static void main(String []args) {
		try {
			DBHelper dbHelper = new DBHelper();
			DSObjectReader dsObjectReader = new DSObjectReader("/home/biplap/Downloads/yelp/yelp_academic_dataset_business.json");
			Business business = null;
			Gson gson = new Gson();
			//String jsonStr = null;
			while (dsObjectReader.hasNext()) {
				business = dsObjectReader.readObject(Business.class);
				dbHelper.save(business);
				//System.out.println(gson.toJson(business));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	public static void main(String []args) {
		try {
			DSObjectReader dsObjectReader = new DSObjectReader("/home/biplap/Downloads/yelp/yelp_academic_dataset_review.json");
			BufferedWriter bw = new BufferedWriter(new FileWriter("/home/biplap/Downloads/yelp/reviews.txt"));
			Review review = null;
			
			while (dsObjectReader.hasNext()) {
				review = dsObjectReader.readObject(Review.class);
				bw.write(review.getText());
			}
			bw.close();
			
			dsObjectReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
