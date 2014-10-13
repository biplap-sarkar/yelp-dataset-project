package com.ds.model;

public class Votes implements DSObject {
	
	public static final String USEFUL = "useful";
	public static final String FUNNY = "funny";
	public static final String COOL = "cool";
	
    private int useful;	
	private int funny;
	private int cool;
	public int getUseful() {
		return useful;
	}
	public void setUseful(int useful) {
		this.useful = useful;
	}
	public int getFunny() {
		return funny;
	}
	public void setFunny(int funny) {
		this.funny = funny;
	}
	public int getCool() {
		return cool;
	}
	public void setCool(int cool) {
		this.cool = cool;
	}
}
