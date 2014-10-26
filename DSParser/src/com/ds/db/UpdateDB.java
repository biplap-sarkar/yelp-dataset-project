package com.ds.db;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import com.ds.model.Business;
import com.ds.model.DSObjectReader;
import com.ds.model.Review;

public class UpdateDB {
	public static void main (String []args) {
		
		// Upserts all the business records form JSON file to DB
		/*
		try {
			DSObjectReader dsObjectReader = new DSObjectReader("/home/biplap/Downloads/yelp/yelp_academic_dataset_business.json");
			Review review = null;
			Business business = null;
			DBHelper dbHelper = new DBHelper();
			
			while (dsObjectReader.hasNext()) {
				business = dsObjectReader.readObject(Business.class);
				dbHelper.save(business);
			}	
			dsObjectReader.close();
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
		}*/
	}
}
