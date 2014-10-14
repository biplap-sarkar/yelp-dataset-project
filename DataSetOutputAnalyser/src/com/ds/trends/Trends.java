package com.ds.trends;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class to find word and wordpair trends
 * @author satya
 *
 */
public class Trends {

	public ArrayList<Word> findMaxOccuringWord(int k, String []file) throws NumberFormatException, IOException{
		PriorityQueue<Word> minHeap = new PriorityQueue<Word>();
		ArrayList<Word> kMaxWords = new ArrayList<>();
		for(int n=0;n<file.length;n++){

			BufferedReader br = new BufferedReader(new FileReader(file[n]));
			String line = "";
			String []str = null;
			while((line=br.readLine())!=null){
				str = line.split("\\s+");
				Word word = new Word(str[0],Integer.parseInt(str[1]));
				minHeap.add(word);
			}
			br.close();
		}
		for(int i=0;i<k;i++){
			kMaxWords.add(minHeap.poll());
		}
		return kMaxWords;
	}
	
	public ArrayList<Word> findMaxOccuringWordPair(int k, String []file) throws NumberFormatException, IOException{
		PriorityQueue<Word> minHeap = new PriorityQueue<Word>();
		ArrayList<Word> kMaxWordPair = new ArrayList<>();
		for(int n=0;n<file.length;n++){

			BufferedReader br = new BufferedReader(new FileReader(file[n]));
			String line = "";
			String []str = null;
			while((line=br.readLine())!=null){
				str = line.split("\\s+");
				Word word = new Word(str[0]+" "+str[1],Integer.parseInt(str[2]));
				minHeap.add(word);
			}
			br.close();
		}
		for(int i=0;i<k;i++){
			kMaxWordPair.add(minHeap.poll());
		}
		return kMaxWordPair;
	}

	public int findRank(String word, String[] file) throws NumberFormatException, IOException {
		int rank = 0;
		PriorityQueue<Word> rankHeap = new PriorityQueue<>();

		for (int i = 0; i < file.length; i++) {

			BufferedReader br = new BufferedReader(new FileReader(file[i]));
			String line = "";
			String[] str = null;
			while ((line = br.readLine()) != null) {
				str = line.split("\\s+");
				Word wordCnt = new Word(str[0], Integer.parseInt(str[1]));
				rankHeap.add(wordCnt);
			}
			br.close();
		}
		Word wordToBeSearched = null;
		do {
			rank = rank + 1;
			wordToBeSearched = rankHeap.poll();
			if (wordToBeSearched.getWord().equals(word))
				return rank;
		} while (rankHeap.isEmpty() == false);
		rank = -1;

		return rank;
	}

	public static void main(String []args){
		Trends trend = new Trends();
		if(args.length==0){
			System.out.println("Please specify the value of k for finding top k words");
			System.out.println("Usage:- java -jar findtrends.jar K input_file1 [input_file2 [input_file3...]]");
		}
		if(args.length==1){
			System.out.println("Please specify an input file");
			System.out.println("Usage:- java -jar findtrends.jar K input_file1 [input_file2 [input_file3...]]");
			System.exit(1);
		}
		
		try{
			int k = Integer.parseInt(args[0]);
			String []file = new String[args.length-1];
			for(int i=1;i<args.length;i++)
				file[i-1] = args[i];
			ArrayList<Word> words = trend.findMaxOccuringWord(k, file);
			for(int i=0;i<words.size();i++)
				System.out.println(words.get(i).getWord()+ " Count:- "+words.get(i).getCount());
		}
		catch(Exception e){
			System.out.println("Error:- "+e.getLocalizedMessage());
			System.out.println("Usage:- java -jar findtrends.jar K input_file1 [input_file2 [input_file3...]]");
		}
	}
}
