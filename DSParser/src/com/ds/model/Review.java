package com.ds.model;
public class Review implements DSObject {
	
	public static final String TYPE = "type";
	public static final String BUSINESS_ID = "business_id";
	public static final String USER_ID = "user_id";
	public static final String STARS = "stars";
	public static final String TEXT = "text";
	public static final String DATE = "date";
	public static final String VOTES = "votes";
	
	private String type;
	private String businessId;
	private String userId;
	private double stars;
	private String text;
	private String date;
	private Votes votes;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getStars() {
		return stars;
	}
	public void setStars(double stars) {
		this.stars = stars;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Votes getVotes() {
		return votes;
	}
	public void setVotes(Votes votes) {
		this.votes = votes;
	}
	
	
}
