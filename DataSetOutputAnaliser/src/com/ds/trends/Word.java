package com.ds.trends;


public class Word implements Comparable<Word>{
	String word;
	int count;
	public Word(String word, int count){
		this.word = word;
		this.count = count;
	}
	public String getWord() {
		return word;
	}
	public int getCount() {
		return count;
	}
	@Override
	public int compareTo(Word o) {
		return o.getCount()-this.count;
	}
	
}
