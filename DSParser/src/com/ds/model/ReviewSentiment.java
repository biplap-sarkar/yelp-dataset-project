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
	/**
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the isPositiveFood
	 */
	public boolean isPositiveFood() {
		return isPositiveFood;
	}
	/**
	 * @param isPositiveFood the isPositiveFood to set
	 */
	public void setPositiveFood(boolean isPositiveFood) {
		this.isPositiveFood = isPositiveFood;
	}
	/**
	 * @return the isPositiveService
	 */
	public boolean isPositiveService() {
		return isPositiveService;
	}
	/**
	 * @param isPositiveService the isPositiveService to set
	 */
	public void setPositiveService(boolean isPositiveService) {
		this.isPositiveService = isPositiveService;
	}
	/**
	 * @return the isPositiveAmbience
	 */
	public boolean isPositiveAmbience() {
		return isPositiveAmbience;
	}
	/**
	 * @param isPositiveAmbience the isPositiveAmbience to set
	 */
	public void setPositiveAmbience(boolean isPositiveAmbience) {
		this.isPositiveAmbience = isPositiveAmbience;
	}
	/**
	 * @return the isPositivePrice
	 */
	public boolean isPositivePrice() {
		return isPositivePrice;
	}
	/**
	 * @param isPositivePrice the isPositivePrice to set
	 */
	public void setPositivePrice(boolean isPositivePrice) {
		this.isPositivePrice = isPositivePrice;
	}
	/**
	 * @return the isNegativeFood
	 */
	public boolean isNegativeFood() {
		return isNegativeFood;
	}
	/**
	 * @param isNegativeFood the isNegativeFood to set
	 */
	public void setNegativeFood(boolean isNegativeFood) {
		this.isNegativeFood = isNegativeFood;
	}
	/**
	 * @return the isNegativeService
	 */
	public boolean isNegativeService() {
		return isNegativeService;
	}
	/**
	 * @param isNegativeService the isNegativeService to set
	 */
	public void setNegativeService(boolean isNegativeService) {
		this.isNegativeService = isNegativeService;
	}
	/**
	 * @return the isNegativeAmbience
	 */
	public boolean isNegativeAmbience() {
		return isNegativeAmbience;
	}
	/**
	 * @param isNegativeAmbience the isNegativeAmbience to set
	 */
	public void setNegativeAmbience(boolean isNegativeAmbience) {
		this.isNegativeAmbience = isNegativeAmbience;
	}
	/**
	 * @return the isNegativePrice
	 */
	public boolean isNegativePrice() {
		return isNegativePrice;
	}
	/**
	 * @param isNegativePrice the isNegativePrice to set
	 */
	public void setNegativePrice(boolean isNegativePrice) {
		this.isNegativePrice = isNegativePrice;
	}
	/**
	 * @return the reviewedManually
	 */
	public boolean isReviewedManually() {
		return reviewedManually;
	}
	/**
	 * @param reviewedManually the reviewedManually to set
	 */
	public void setReviewedManually(boolean reviewedManually) {
		this.reviewedManually = reviewedManually;
	}
	
	
}
