package com.ds.trends;

public class WordRank {
	public static void main(String []args){
		try{
		Trends trend = new Trends();
		String word = args[0];
		String []file = new String[args.length-1];
		for(int i=1;i<args.length;i++)
			file[i-1] = args[i];
		int rank = trend.findRank(word, file);
		if(rank >= 0)
			System.out.println("Rank of "+word+" is "+rank+" by number of occurance");
		else
			System.out.println(word+" does not occur in the given input files");
		}
		catch(Exception e){
			System.out.println("Error:- "+e.getLocalizedMessage());
			System.out.println("Usage:- java -jar findwordrank.jar word inputfile1 [inputfile1 [inputfile3...]]");
		}
	}
}
