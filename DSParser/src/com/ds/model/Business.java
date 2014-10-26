package com.ds.model;
import java.util.ArrayList;

	
public class Business implements DSObject {
	
	public static final String TYPE = "type";
	public static final String BUSINESS_ID = "business_id";
	public static final String NAME = "name";
	public static final String NEIGHBORHOODS = "neighborhoods";
	public static final String FULL_ADDRESS = "full_address";
	public static final String CITY = "city";
	public static final String STATE = "stage";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String STARS = "stars";
	public static final String REVIEW_COUNT = "review_count";
	public static final String CATEGORIES = "categories";
	public static final String OPEN = "open";
	public static final String ISCATEGORYFOOD = "iscategoryfood";
	
	private String type;
	private String id;
	private String name;
	private ArrayList<String> neighborhoodList;
	private String fullAddress;
	private String city;
	private String state;
	private String latitude;
	private String longitude;
	private float stars;
	private int reviewCount;
	private ArrayList<String> categories;
	private boolean open;
	private boolean categoryFood;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getNeighborhoodList() {
		return neighborhoodList;
	}
	public void setNeighborhoodList(ArrayList<String> neighborhoodList) {
		this.neighborhoodList = neighborhoodList;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isCategoryFood() {
		return categoryFood;
	}
	public void setCategoryFood(boolean categoryFood) {
		this.categoryFood = categoryFood;
	}
	
}
