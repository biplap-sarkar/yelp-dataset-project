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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.ds.model.Business;


public class DBHelper {
	private static final String CONFIG_FILE = "db.config";
	private Connection conn = null;
	
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
	
	private Business getBusiness(String id) throws ClassNotFoundException, IOException, SQLException {
		Business business = new Business();
		PreparedStatement pstmt = conn.prepareStatement("select * from business where id=?");
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.first() == false) {
			throw new SQLException("No record found for the key "+id);
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
