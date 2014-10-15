package com.ds.trends;

import java.util.ArrayList;

public class TopWordPairs {
	public static void main(String []args){
		Trends trend = new Trends();
		if(args.length==0){
			System.out.println("Please specify the value of k for finding top k words");
			System.out.println("Usage:- java -jar findwordpairtrends.jar K input_file1 [input_file2 [input_file3...]]");
		}
		if(args.length==1){
			System.out.println("Please specify an input file");
			System.out.println("Usage:- java -jar findwordpairtrends.jar K input_file1 [input_file2 [input_file3...]]");
			System.exit(1);
		}
		
		try{
			int k = Integer.parseInt(args[0]);
			String []file = new String[args.length-1];
			for(int i=1;i<args.length;i++)
				file[i-1] = args[i];
			ArrayList<Word> words = trend.findMaxOccuringWordPair(k, file);
			for(int i=0;i<words.size();i++)
				System.out.println(words.get(i).getWord()+ " "+words.get(i).getCount());
		}
		catch(Exception e){
			System.out.println("Error:- "+e.getLocalizedMessage());
			System.out.println("Usage:- java -jar findwordpairtrends.jar K input_file1 [input_file2 [input_file3...]]");
		}
	}
}
