package com.ds.utility;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import com.ds.db.DBHelper;
import com.ds.model.Business;
import com.ds.model.DSObjectReader;
import com.ds.model.Review;

public class Utilities {

	public static void filterFoodReviews(String inputFile, String outputFile) throws IOException, ClassNotFoundException {
		DSObjectReader dsObjectReader = new DSObjectReader(inputFile);
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		DBHelper dbHelper = new DBHelper();
		Review review = null;
		Business business = null;
		
		while (dsObjectReader.hasNext()) {
			review = dsObjectReader.readObject(Review.class);
			try {
				business = dbHelper.get(Business.class, review.getBusinessId());
				if (business.isCategoryFood()) {
					//bw.write();
				}
			}  catch (SQLException e) {
				continue;
				//e.printStackTrace();
			}
			
		}
	}
}

