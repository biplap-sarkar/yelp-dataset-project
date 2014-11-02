package com.ds.annotation;

import java.io.IOException;
import java.sql.SQLException;

import com.ds.model.DSObjectReader;
import com.ds.model.Review;

public class Driver {
	public static void main(String []args) throws ClassNotFoundException, IOException, SQLException {
		
		int threadCount = 5;
		int count = 0;
		String inputFile = "/home/satya/GitRepo/PdP_Project/yelp/yelp_academic_dataset_food_review.json";
		DSObjectReader dsObjectReader = new DSObjectReader(inputFile);
		
		while (dsObjectReader.hasNext())
		{
			dsObjectReader.readObject(Review.class);
			count++;
		}
		System.out.println("total records "+count);
		int startIndex = 0;
		int endIndex = 0;
		for (int i=0;i<threadCount;i++) {
			startIndex = i*(count/threadCount);
			endIndex = (i+1)*(count/threadCount);
			if(i == threadCount-1)
				endIndex = count;
			AnnotatorTask task = new AnnotatorTask(inputFile,startIndex , endIndex);
			Thread th = new Thread(task);
			th.start();
		}
	}
}
