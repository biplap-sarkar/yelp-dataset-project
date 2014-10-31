import MySQLdb as db
import dbconfig
from review_sentiment import ReviewSentiment

class DBHelper:
	con = None
	
	def connect(self):
		self.con = db.connect(dbconfig.host, dbconfig.user, dbconfig.password, dbconfig.dbname);

	def save(self, obj):
		if isinstance(obj, ReviewSentiment):
			if self.con is None:
				raise Exception("Not connected to DB");
			cur = self.con.cursor();
			cur.execute("insert into review_sentiment (business_id, user_id, text, is_positive_food, is_positive_service, is_positive_ambience, is_positive_price, is_negative_food, is_negative_service, is_negative_ambience, is_negative_price, is_reviewed_manually)"+
			" values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) on duplicate key update "+
			" text=%s, is_positive_food=%s, is_positive_service=%s, is_positive_ambience=%s, is_positive_price=%s, is_negative_food=%s, is_negative_service=%s, is_negative_ambience=%s, is_negative_price=%s, is_reviewed_manually=%s;",
			(obj.business_id, obj.user_id, obj.text, obj.is_positive_food, obj.is_positive_service, obj.is_positive_ambience, obj.is_positive_price,
			obj.is_negative_food, obj.is_negative_service, obj.is_negative_ambience, obj.is_negative_price, obj.is_reviewed_manually, 
			obj.text, obj.is_positive_food, obj.is_positive_service, obj.is_positive_ambience, obj.is_positive_price,
			obj.is_negative_food, obj.is_negative_service, obj.is_negative_ambience, obj.is_negative_price, obj.is_reviewed_manually));
		else:
			raise ValueError("Class type not supported");
	
	def commit(self):
		if self.con is None:
			raise Exception("Not connected to DB");
		self.con.commit();
		
		
	def close(self):
		if self.con is None:
			raise Exception("Not connected to DB");
		self.commit();
		self.con.close();
			
