package buzzmap;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class DataStoreManager {
	private static final String datastoreName = "BuzzMap";
	
	public static boolean AddTweet(Tweet tweet) {
		Entity tweetEntity = new Entity(datastoreName);
		tweetEntity.setProperty("search", tweet.search);
		tweetEntity.setProperty("created_at", tweet.created_at);
		tweetEntity.setProperty("from_user", tweet.from_user);
		tweetEntity.setProperty("from_user_id_str", tweet.from_user_id_str);
		tweetEntity.setProperty("from_user_name", tweet.from_user_name);
		tweetEntity.setProperty("id_str", tweet.id_str);
		tweetEntity.setProperty("profile_image_url", tweet.profile_image_url);
		tweetEntity.setProperty("source", tweet.source);
		tweetEntity.setProperty("text", tweet.text);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(tweetEntity);
		
		return true;
	}
	
	public static boolean ClearTweets() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(datastoreName);
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			datastore.delete(result.getKey());
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean checkSearchExist(String searchString) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(datastoreName);
		q.addFilter("search", Query.FilterOperator.EQUAL, searchString);
		PreparedQuery pq = datastore.prepare(q);
		return pq.countEntities() > 0;
	}
	
	@SuppressWarnings("deprecation")
	public static ArrayList<Tweet> getTweets(String searchString) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(datastoreName);
		q.addFilter("search", Query.FilterOperator.EQUAL, searchString);
		PreparedQuery pq = datastore.prepare(q);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		for (Entity result : pq.asIterable()) {
			Tweet tweet = new Tweet(result);
			tweets.add(tweet);
		}
		return tweets;
	}
}
