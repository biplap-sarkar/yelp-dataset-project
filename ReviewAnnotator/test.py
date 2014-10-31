#!/usr/bin/python

# import required classes
from review_sentiment import ReviewSentiment
from dbhelper import DBHelper

# Create instance of DBHelper class
dbHelper = DBHelper();

# Create instance of ReviewSentiment class
sentiment = ReviewSentiment();

# Populate ReviewSentiment object
sentiment.business_id = "123"
sentiment.user_id = "434"
sentiment.text = "something is something"

# Connect DBHelper object
dbHelper.connect();

# Save ReviewSentiment object
dbHelper.save(sentiment);

# Close DBHelper object
dbHelper.close();

