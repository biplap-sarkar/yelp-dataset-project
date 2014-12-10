package com.ds.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.ds.model.Business;
import com.ds.model.ReviewSentiment;


public class DBHelper {
	private static final String CONFIG_FILE = "db.config";
	private Connection conn = null;
	private ResultSet rs = null;
	
	/*
	private static final int BUSINESS_ID = 1;
	private static final int BUSINESS_NAME = 2;
	private static final int BUSINESS_NEIGHBOURHOOTLIST = 3;
	private static final int BUSINESS_FULLADDRESS = 4;
	private static final int BUSINESS_CITY = 5;
	private static final int BUSINESS_STATE = 6;
	private static final int BUSINESS_LATITUDE = 7;
	private static final int BUSINESS_LONGITUDE = 8;
	private static final int BUSINESS_STARS = 9;
	private static final int BUSINESS_REVIEWCOUNT =10;
	private static final int BUSINESS_PHOTOURL = 11;
	private static final int BUSINESS_CATEGORIES = 12;
	private static final int BUSINESS_OPEN = 13;
	private static final int BUSINESS_SCHOOLS = 14;
	private static final int BUSINESS_URL = 15;
	*/
	Properties configProperties = new Properties();
	public void connect() throws IOException, ClassNotFoundException, SQLException {
		configProperties.load(getClass().getClassLoader().getResourceAsStream(CONFIG_FILE));
		String dbName = configProperties.getProperty("dbname");
		String ip = configProperties.getProperty("ip");
		String port = configProperties.getProperty("port");
		String user = configProperties.getProperty("user");
		String password = configProperties.getProperty("password");
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + ip + ":" + port+"/"+dbName;
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public void close() throws SQLException {
		conn.close();
	}
	
	public int save(Object obj) throws ClassNotFoundException, IOException, SQLException {
		int res = 0;
		if (obj instanceof Business) {
			res = saveBusiness((Business) obj);
		}
		else if (obj instanceof ReviewSentiment) {
			res = saveReviewSentiment((ReviewSentiment) obj);
		}
		else {
			throw new NotImplementedException();
		}
		return res;
	}
	
	public <T> T get(Class<T> type, String id) throws ClassNotFoundException, IOException, SQLException {
		if (type.equals(Business.class)) {
			return type.cast(getBusiness(id));
		}
		throw new NotImplementedException();
	}
	
	public <T> T get(Class<T> type, String[] fields, String[]vals) throws SQLException, ClassNotFoundException, IOException {
		if (type.equals(Business.class)) {
			return type.cast(getBusiness(fields, vals));
		}
		else if (type.equals(ReviewSentiment.class)) {
			return type.cast(getReviewSentiment(fields, vals));
		}
		throw new NotImplementedException();
	}
	
	public <T> ArrayList<T> getAll(Class<T> type, int limit) throws IOException, ClassNotFoundException, SQLException {
		if (type.equals(ReviewSentiment.class)) {
			return (ArrayList<T>) getAllReviewSentiment(limit);
		}
		throw new NotImplementedException();
	}
	
	public <T> T get(Class<T> type) throws IOException, ClassNotFoundException, SQLException {
		if (type.equals(ReviewSentiment.class)) {
			return type.cast(getReviewSentiment());
		}
		throw new NotImplementedException();
	}
	
	public <T> boolean next(Class<T> type) throws SQLException {
		if (type.equals(ReviewSentiment.class)) {
			return nextReviewSentiment();
		}
		throw new NotImplementedException();
	}
	/*
	public <T> ArrayList<T> getAll(Class<T> type) throws IOException, ClassNotFoundException, SQLException {
		if (type.equals(ReviewSentiment.class)) {
			return (ArrayList<T>) getAllReviewSentiment();
		}
		throw new NotImplementedException();
	}*/
	
	
	public <T> boolean getBoolean(Class<T> type, String id, String colName) throws SQLException {
		if (type.equals(Business.class)) {
			return getBooleanBusiness(id, colName);
		}
		throw new NotImplementedException();
	}
	
	public <T> String getString(Class<T> type, String id, String colName) throws SQLException {
		if (type.equals(Business.class)) {
			return getStringBusiness(id, colName);
		}
		throw new NotImplementedException();
	}
	
	public <T> int getInt(Class<T> type, String id, String colName) throws SQLException {
		if (type.equals(Business.class)) {
			return getIntBusiness(id, colName);
		}
		throw new NotImplementedException();
	}
	
	public <T> double getDouble(Class<T> type, String id, String colName) throws SQLException {
		if (type.equals(Business.class)) {
			return getDoubleBusiness(id, colName);
		}
		throw new NotImplementedException();
	}
	
	private int saveReviewSentiment(ReviewSentiment sentiment) throws SQLException {
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement("insert into review_sentiment (business_id, user_id, text, is_positive_food, is_positive_service, is_positive_ambience, is_positive_price, is_negative_food, is_negative_service, is_negative_ambience, is_negative_price, is_reviewed_manually)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) on duplicate key update"
				+ " text=?, is_positive_food=?, is_positive_service=?, is_positive_ambience=?, is_positive_price=?, is_negative_food=?, is_negative_service=?, is_negative_ambience=?, is_negative_price=?, is_reviewed_manually=?;");
		pstmt.setString(1, sentiment.getBusinessId());
		pstmt.setString(2, sentiment.getUserId());
		pstmt.setString(3, sentiment.getText());
		pstmt.setBoolean(4, sentiment.isPositiveFood());
		pstmt.setBoolean(5, sentiment.isPositiveService());
		pstmt.setBoolean(6, sentiment.isPositiveAmbience());
		pstmt.setBoolean(7, sentiment.isPositivePrice());
		pstmt.setBoolean(8, sentiment.isNegativeFood());
		pstmt.setBoolean(9, sentiment.isNegativeService());
		pstmt.setBoolean(10, sentiment.isNegativeAmbience());
		pstmt.setBoolean(11, sentiment.isNegativePrice());
		pstmt.setBoolean(12, sentiment.isReviewedManually());
		pstmt.setString(13, sentiment.getText());
		pstmt.setBoolean(14, sentiment.isPositiveFood());
		pstmt.setBoolean(15, sentiment.isPositiveService());
		pstmt.setBoolean(16, sentiment.isPositiveAmbience());
		pstmt.setBoolean(17, sentiment.isPositivePrice());
		pstmt.setBoolean(18, sentiment.isNegativeFood());
		pstmt.setBoolean(19, sentiment.isNegativeService());
		pstmt.setBoolean(20, sentiment.isNegativeAmbience());
		pstmt.setBoolean(21, sentiment.isNegativePrice());
		pstmt.setBoolean(22, sentiment.isReviewedManually());
		
		res = pstmt.executeUpdate();
		pstmt.close();
		return res;
	}
	
	private int saveBusiness(Business business) throws ClassNotFoundException, IOException, SQLException {
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement("insert into business (id, name, neighborhoodlist, fulladdress, city, state, latitude, longitude, stars, reviewcount, categories, open, iscategoryfood)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) on duplicate key update"
				+ " name=?, neighborhoodlist=?, fulladdress=?, city=?, state=?, latitude=?, longitude=?, stars=?, reviewcount=?, categories=?, open=?, iscategoryfood=?;");
		pstmt.setString(1, business.getId());
		pstmt.setString(2, business.getName());
		pstmt.setString(3, business.getNeighborhoodList().toString());
		pstmt.setString(4, business.getFullAddress());
		pstmt.setString(5, business.getCity());
		pstmt.setString(6, business.getState());
		pstmt.setString(7, business.getLatitude());
		pstmt.setString(8, business.getLongitude());
		pstmt.setFloat(9, business.getStars());
		pstmt.setInt(10, business.getReviewCount());
		pstmt.setString(11, business.getCategories().toString());
		pstmt.setBoolean(12, business.isOpen());
		pstmt.setBoolean(13, business.isCategoryFood());
		pstmt.setString(14, business.getName());
		pstmt.setString(15, business.getNeighborhoodList().toString());
		pstmt.setString(16, business.getFullAddress());
		pstmt.setString(17, business.getCity());
		pstmt.setString(18, business.getState());
		pstmt.setString(19, business.getLatitude());
		pstmt.setString(20, business.getLongitude());
		pstmt.setFloat(21, business.getStars());
		pstmt.setInt(22, business.getReviewCount());
		pstmt.setString(23, business.getCategories().toString());
		pstmt.setBoolean(24, business.isOpen());
		pstmt.setBoolean(25, business.isCategoryFood());
		
		res = pstmt.executeUpdate();
		pstmt.close();
		return res;
	}
	
	private boolean getBooleanBusiness(String id, String colName) throws SQLException {
		boolean result = false;
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=? and "+colName+"=?;");
		pstmt.setString(1, id);
		pstmt.setString(2, colName);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for the key "+id);
		}
		result = rs.getBoolean(1);
		pstmt.close();
		return result;
	}
	
	private String getStringBusiness(String id, String colName) throws SQLException {
		String result = "";
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=? and "+colName+"=?;");
		pstmt.setString(1, id);
		pstmt.setString(2, colName);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for the key "+id);
		}
		result = rs.getString(1);
		pstmt.close();
	
		return result;
	}
	
	private int getIntBusiness(String id, String colName) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=? and "+colName+"=?;");
		pstmt.setString(1, id);
		pstmt.setString(2, colName);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for the key "+id);
		}
		result = rs.getInt(1);
		pstmt.close();
		return result;
	}
	
	private double getDoubleBusiness(String id, String colName) throws SQLException {
		double result = 0.0;
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=? and "+colName+"=?;");
		pstmt.setString(1, id);
		pstmt.setString(2, colName);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for the key "+id);
		}
		result = rs.getDouble(1);
		
		return result;
	}
	
	private ArrayList<ReviewSentiment> getAllReviewSentiment(int limit) throws IOException, ClassNotFoundException, SQLException {
		ArrayList<ReviewSentiment> sentimentList = new ArrayList<ReviewSentiment>();
		if (conn == null) {
			connect();
		}
		PreparedStatement pstmt = conn.prepareStatement("select * from review_sentiment limit ?");
		pstmt.setInt(1, limit);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ReviewSentiment sentiment = new ReviewSentiment();
			sentiment.setBusinessId(rs.getString(1));
			sentiment.setUserId(rs.getString(2));
			sentiment.setText(rs.getString(3));
			sentiment.setPositiveFood(rs.getBoolean(4));
			sentiment.setPositiveService(rs.getBoolean(5));
			sentiment.setPositiveAmbience(rs.getBoolean(6));
			sentiment.setPositivePrice(rs.getBoolean(7));
			sentiment.setNegativeFood(rs.getBoolean(8));
			sentiment.setNegativeService(rs.getBoolean(9));
			sentiment.setNegativeAmbience(rs.getBoolean(10));
			sentiment.setNegativePrice(rs.getBoolean(11));
			sentiment.setReviewedManually(rs.getBoolean(12));
			sentimentList.add(sentiment);
		}
		return sentimentList;
	}
	
	private ReviewSentiment getReviewSentiment() throws IOException, ClassNotFoundException, SQLException {
		ReviewSentiment sentiment = new ReviewSentiment();
		if (rs == null) {
			if (conn == null) {
				connect();
			}
			PreparedStatement pstmt = conn.prepareStatement("select * from review_sentiment");
			rs = pstmt.executeQuery();
			rs.first();
		}
		sentiment.setBusinessId(rs.getString(1));
		sentiment.setUserId(rs.getString(2));
		sentiment.setText(rs.getString(3));
		sentiment.setPositiveFood(rs.getBoolean(4));
		sentiment.setPositiveService(rs.getBoolean(5));
		sentiment.setPositiveAmbience(rs.getBoolean(6));
		sentiment.setPositivePrice(rs.getBoolean(7));
		sentiment.setNegativeFood(rs.getBoolean(8));
		sentiment.setNegativeService(rs.getBoolean(9));
		sentiment.setNegativeAmbience(rs.getBoolean(10));
		sentiment.setNegativePrice(rs.getBoolean(11));
		sentiment.setReviewedManually(rs.getBoolean(12));
		return sentiment;
	}
	
	private boolean nextReviewSentiment() throws SQLException {
		boolean res = false;
		if (rs != null) {
			res = rs.next();
			if (res == false) {
				rs = null;
			}
		}
		return res;
	}
	
	
	
	private ArrayList<ReviewSentiment> getAllReviewSentiment() throws IOException, ClassNotFoundException, SQLException {
		ArrayList<ReviewSentiment> sentimentList = new ArrayList<ReviewSentiment>();
		if (conn == null) {
			connect();
		}
		PreparedStatement pstmt = conn.prepareStatement("select * from review_sentiment");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ReviewSentiment sentiment = new ReviewSentiment();
			sentiment.setBusinessId(rs.getString(1));
			sentiment.setUserId(rs.getString(2));
			sentiment.setText(rs.getString(3));
			sentiment.setPositiveFood(rs.getBoolean(4));
			sentiment.setPositiveService(rs.getBoolean(5));
			sentiment.setPositiveAmbience(rs.getBoolean(6));
			sentiment.setPositivePrice(rs.getBoolean(7));
			sentiment.setNegativeFood(rs.getBoolean(8));
			sentiment.setNegativeService(rs.getBoolean(9));
			sentiment.setNegativeAmbience(rs.getBoolean(10));
			sentiment.setNegativePrice(rs.getBoolean(11));
			sentiment.setReviewedManually(rs.getBoolean(12));
			sentimentList.add(sentiment);
		}
		return sentimentList;
	}
	
	private Business getBusiness(String[] fields, String[] vals) throws SQLException, ClassNotFoundException, IOException {
		Business business = new Business();
		PreparedStatement pstmt = buildStatement("select * from business", fields, vals);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for given fields");
		}
		business.setId(rs.getString(1));
		business.setName(rs.getString(2));
		business.setNeighborhoodList(new ArrayList<String>(Arrays.asList(rs.getString(3).replaceAll("\\]|\\[|\\ ", "").split(","))));
		business.setFullAddress(rs.getString(4));
		business.setCity(rs.getString(5));
		business.setState(rs.getString(6));
		business.setLatitude(rs.getString(7));
		business.setLongitude(rs.getString(8));
		business.setStars(rs.getFloat(9));
		business.setReviewCount(rs.getInt(10));
		business.setCategories(new ArrayList<String>(Arrays.asList(rs.getString(11).replaceAll("\\]|\\[|\\ ", "").split(","))));
		business.setOpen(rs.getBoolean(12));
		business.setCategoryFood(rs.getBoolean(13));
		
		pstmt.close();
		return business;
	}
	
	private Business getBusiness(String id) throws ClassNotFoundException, IOException, SQLException {
		Business business = new Business();
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=?");
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			return null;
		}
		business.setId(rs.getString(1));
		business.setName(rs.getString(2));
		business.setNeighborhoodList(new ArrayList<String>(Arrays.asList(rs.getString(3).replaceAll("\\]|\\[|\\ ", "").split(","))));
		business.setFullAddress(rs.getString(4));
		business.setCity(rs.getString(5));
		business.setState(rs.getString(6));
		business.setLatitude(rs.getString(7));
		business.setLongitude(rs.getString(8));
		business.setStars(rs.getFloat(9));
		business.setReviewCount(rs.getInt(10));
		business.setCategories(new ArrayList<String>(Arrays.asList(rs.getString(11).replaceAll("\\]|\\[|\\ ", "").split(","))));
		business.setOpen(rs.getBoolean(12));
		business.setCategoryFood(rs.getBoolean(13));
		
		pstmt.close();
		
		return business;
	}
	
	private ReviewSentiment getReviewSentiment(String[] fields, String[] vals) throws SQLException, ClassNotFoundException, IOException {
		ReviewSentiment sentiment = new ReviewSentiment();
		PreparedStatement pstmt = buildStatement("select * from review_sentiment", fields, vals);
		ResultSet rs = pstmt.executeQuery();
		if (rs.first()==false) {
			return null;
		}
		sentiment.setBusinessId(rs.getString(1));
		sentiment.setUserId(rs.getString(2));
		sentiment.setText(rs.getString(3));
		sentiment.setPositiveFood(rs.getBoolean(4));
		sentiment.setPositiveService(rs.getBoolean(5));
		sentiment.setPositiveAmbience(rs.getBoolean(6));
		sentiment.setPositivePrice(rs.getBoolean(7));
		sentiment.setNegativeFood(rs.getBoolean(8));
		sentiment.setNegativeService(rs.getBoolean(9));
		sentiment.setNegativeAmbience(rs.getBoolean(10));
		sentiment.setNegativePrice(rs.getBoolean(11));
		sentiment.setReviewedManually(rs.getBoolean(12));
		
		pstmt.close();
		return sentiment;
	}
	
	private PreparedStatement buildStatement(String query, String[] fields, String[] vals) throws SQLException, ClassNotFoundException, IOException {
		if (fields == null || fields.length == 0)
			throw new IllegalArgumentException("Expected atleast one field, found none");
		if (fields.length != vals.length)
			throw new IllegalArgumentException("Mismatch between number of fields and number of values");
		PreparedStatement pstmt = null;
		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(" where ");
		for (int i=0;i<fields.length;i++) {
			queryBuilder.append(fields[i]+" = "+"?");
			if (i < fields.length-1) {
				queryBuilder.append(" and ");
			}
		}
		if (conn == null) {
			connect();
		}
		pstmt = conn.prepareStatement(queryBuilder.toString());
		for (int i=1;i<=vals.length;i++) {
			pstmt.setString(i, vals[i-1]);
		}
		return pstmt;
	}
	/*
	public static void main (String []args) {
		DBHelper dbHelper = new DBHelper();
		try {
			ArrayList<ReviewSentiment> sentimentList = dbHelper.getAll(ReviewSentiment.class, 20);
			for (int i=0;i<sentimentList.size();i++) {
				System.out.println(sentimentList.get(i).getText());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	/*
	public static void main(String[]args){
		ArrayList<String> str = new ArrayList<String>();
		str.add("str1");
		str.add("str2");
		str.add("str3");
		System.out.println(str.toString());
		ArrayList<String> str1 = new ArrayList<String>(Arrays.asList(str.toString().replaceAll("\\[|\\]|\\ ", "").split(",")));
		System.out.println(str1.toString());
	}
	*/
	
}
