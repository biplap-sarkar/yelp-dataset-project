package com.ds.model;

public class ReviewSentiment {
	
	public static final String BUSINESS_ID = "business_id";
	public static final String USER_ID = "user_id";
	public static final String TEXT = "text";
	public static final String IS_POSITIVE_FOOD = "is_positive_food";
	public static final String IS_POSITIVE_SERVICE = "is_positive_service";
	public static final String IS_POSITIVE_AMBIENCE = "is_positive_ambience";
	public static final String IS_POSITIVE_PRICE = "is_positive_price";
	public static final String IS_NEGATIVE_FOOD = "is_negative_food";
	public static final String IS_NEGATIVE_SERVICE = "is_negative_service";
	public static final String IS_NEGATIVE_AMBIENCE = "is_negative_ambience";
	public static final String IS_NEGATIVE_PRICE = "is_negative_price";
	public static final String IS_REVIEWED_MANUALLY = "is_reviewed_manually";
	
	private String businessId;
	private String userId;
	private String text;
	private boolean isPositiveFood = false;
	private boolean isPositiveService = false;
	private boolean isPositiveAmbience = false;
	private boolean isPositivePrice = false;
	private boolean isNegativeFood = false;
	private boolean isNegativeService = false;
	private boolean isNegativeAmbience = false;
	private boolean isNegativePrice = false;
	private boolean reviewedManually = false;
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isPositiveFood() {
		return isPositiveFood;
	}
	public void setPositiveFood(boolean isPositiveFood) {
		this.isPositiveFood = isPositiveFood;
	}
	public boolean isPositiveService() {
		return isPositiveService;
	}
	public void setPositiveService(boolean isPositiveService) {
		this.isPositiveService = isPositiveService;
	}
	public boolean isPositiveAmbience() {
		return isPositiveAmbience;
	}
	public void setPositiveAmbience(boolean isPositiveAmbience) {
		this.isPositiveAmbience = isPositiveAmbience;
	}
	public boolean isPositivePrice() {
		return isPositivePrice;
	}
	public void setPositivePrice(boolean isPositivePrice) {
		this.isPositivePrice = isPositivePrice;
	}
	public boolean isNegativeFood() {
		return isNegativeFood;
	}
	public void setNegativeFood(boolean isNegativeFood) {
		this.isNegativeFood = isNegativeFood;
	}
	public boolean isNegativeService() {
		return isNegativeService;
	}
	public void setNegativeService(boolean isNegativeService) {
		this.isNegativeService = isNegativeService;
	}
	public boolean isNegativeAmbience() {
		return isNegativeAmbience;
	}
	public void setNegativeAmbience(boolean isNegativeAmbience) {
		this.isNegativeAmbience = isNegativeAmbience;
	}
	public boolean isNegativePrice() {
		return isNegativePrice;
	}
	public void setNegativePrice(boolean isNegativePrice) {
		this.isNegativePrice = isNegativePrice;
	}
	public boolean isReviewedManually() {
		return reviewedManually;
	}
	public void setReviewedManually(boolean reviewedManually) {
		this.reviewedManually = reviewedManually;
	}
	
	
}
