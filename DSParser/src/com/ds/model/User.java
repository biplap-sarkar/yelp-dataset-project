package com.ds.model;
import java.util.ArrayList;


public class User implements DSObject {
	private String type;
	private int userId;
	private String name;
	private int reviewCount;
	private float averageStars;
	private ArrayList<Votes> votesList;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public float getAverageStars() {
		return averageStars;
	}
	public void setAverageStars(float averageStars) {
		this.averageStars = averageStars;
	}
	public ArrayList<Votes> getVotesList() {
		return votesList;
	}
	public void setVotesList(ArrayList<Votes> votesList) {
		this.votesList = votesList;
	}
	
	
}
