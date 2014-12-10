package com.ds.utility;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;

import com.ds.db.DBHelper;
import com.ds.model.ReviewSentiment;

public class ReviewSentimentToSeq {
	public static void main(String args[]) throws Exception {
		/*if (args.length != 1) {
			System.out.println("Arguments: [output sequence file]");
			return;
		}*/
		String posFoodDir = "food-pos";
		String posServiceDir = "service-pos";
		String posAmbienceDir = "ambience-pos";
		String posPriceDir = "price-pos";
		String negFoodDir = "food-neg";
		String negServiceDir = "service-neg";
		String negAmbienceDir = "ambience-neg";
		String negPriceDir = "price-neg";
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		//Writer writerFoodPos = new SequenceFile.Writer(fs, configuration, new Path(posFoodDir + "/chunk-0"),
		//		Text.class, Text.class);
		Writer writerServicePos = new SequenceFile.Writer(fs, configuration, new Path(posServiceDir + "/chunk-0"),
				Text.class, Text.class);
		/*
		Writer writerAmbiencePos = new SequenceFile.Writer(fs, configuration, new Path(posAmbienceDir + "/chunk-0"),
				Text.class, Text.class);
		Writer writerPricePos = new SequenceFile.Writer(fs, configuration, new Path(posPriceDir + "/chunk-0"),
				Text.class, Text.class);
		Writer writerFoodNeg = new SequenceFile.Writer(fs, configuration, new Path(negFoodDir + "/chunk-0"),
				Text.class, Text.class);
		Writer writerServiceNeg = new SequenceFile.Writer(fs, configuration, new Path(negServiceDir + "/chunk-0"),
				Text.class, Text.class);
		Writer writerAmbienceNeg = new SequenceFile.Writer(fs, configuration, new Path(negAmbienceDir + "/chunk-0"),
				Text.class, Text.class);
		Writer writerPriceNeg = new SequenceFile.Writer(fs, configuration, new Path(negPriceDir + "/chunk-0"),
				Text.class, Text.class);
		*/
		int count = 0;
		//BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
		DBHelper dbHelper = new DBHelper();
		ReviewSentiment sentiment = null;
		Text keyPosFood = new Text();
		Text keyPosService = new Text();
		Text keyPosAmbience = new Text();
		Text keyPosPrice = new Text();
		Text keyNegFood = new Text();
		Text keyNegService = new Text();
		Text keyNegAmbience = new Text();
		Text keyNegPrice = new Text();
		Text value = new Text();
		boolean nextRecord = true;
		String id = null;
		while (nextRecord) {
			sentiment = dbHelper.get(ReviewSentiment.class);
			String categoryFoodPos = "Not_Positive_Food";
			String categoryServicePos = "Not_Positive_Service";
			String categoryAmbiencePos = "Not_Positive_Ambience";
			String categoryPricePos = "Not_Positive_Price";
			
			String categoryFoodNeg = "Not_Negative_Food";
			String categoryServiceNeg = "Not_Negative_Service";
			String categoryAmbienceNeg = "Not_Negative_Ambience";
			String categoryPriceNeg = "Not_Negative_Price";
			
			/*
			if (sentiment.isPositiveFood()) {
				categoryFoodPos = "Positive_Food";
			}*/
			if (sentiment.isPositiveService()) {
				categoryServicePos = "Positive_Service";
			}
			/*
			if (sentiment.isPositiveAmbience()) {
				categoryAmbiencePos = "Positive_Ambience";
			}
			if (sentiment.isPositivePrice()) {
				categoryPricePos = "Positive_Price";
			}
			if (sentiment.isNegativeFood()) {
				categoryFoodNeg = "Negative_Food";
			}
			if (sentiment.isNegativeService()) {
				categoryServiceNeg = "Negative_Service";
			}
			if (sentiment.isNegativeAmbience()) {
				categoryAmbienceNeg = "Negative_Ambience";
			}
			if (sentiment.isNegativePrice()) {
				categoryPriceNeg = "Negative_Price";
			}
			*/
			value.set(sentiment.getText());
			id = sentiment.getBusinessId()+"|"+sentiment.getUserId();
			
			// Write to positive food file
			//keyPosFood.set("/" + categoryFoodPos + "/" + id);
			//writerFoodPos.append(keyPosFood, value);
			
			// Write to positive service file
			keyPosService.set("/" + categoryServicePos + "/" + id);
			writerServicePos.append(keyPosService, value);
			
			// Write to positive ambience file
			//keyPosAmbience.set("/" + categoryAmbiencePos + "/" +id);
			//writerAmbiencePos.append(keyPosAmbience, value);
			
			// Write to positive price file
			//keyPosPrice.set("/" + categoryPricePos + "/" + id);
			//writerPricePos.append(keyPosPrice, value);
			
			// Write to negative food file
			//keyNegFood.set("/" + categoryFoodNeg + "/" + id);
			//writerFoodNeg.append(keyNegFood, value);
			
			// Write to negative service file
			//keyNegService.set("/" + categoryServiceNeg + "/" + id);
			//writerServiceNeg.append(keyNegService, value);
			
			// Write to negative ambience file
			//keyNegAmbience.set("/" + categoryAmbienceNeg + "/" + id);
			//writerAmbienceNeg.append(keyNegAmbience, value);
			
			// Write to negative price file
			//keyNegPrice.set("/" + categoryPriceNeg + "/" + id);
			//writerPriceNeg.append(keyNegPrice, value);
			
			count++;
			nextRecord = dbHelper.next(ReviewSentiment.class);
		}
		
		//writerFoodPos.close();
		writerServicePos.close();
		/*
		writerAmbiencePos.close();
		writerPricePos.close();
		writerFoodNeg.close();
		writerServiceNeg.close();
		writerAmbienceNeg.close();
		writerPriceNeg.close();
		*/
		System.out.println("Wrote " + count + " entries.");
	}
}
