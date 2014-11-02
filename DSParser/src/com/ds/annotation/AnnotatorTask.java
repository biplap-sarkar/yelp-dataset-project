package com.ds.annotation;

import java.io.IOException;
import java.sql.SQLException;

public class AnnotatorTask implements Runnable{
	private String fileName;
	private int startRecord;
	private int endRecord;
	
	public AnnotatorTask (String fileName, int startRecord, int endRecord) {
		this.fileName = fileName;
		this.startRecord = startRecord;
		this.endRecord = endRecord;
	}
	@Override
	public void run() {
		Annotation annotation = new Annotation();
		try {
			System.out.println("current thread id "+Thread.currentThread().getId()+" startindex "+startRecord+" endindex "+endRecord);
			annotation.annotateReviews(fileName, startRecord, endRecord);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
