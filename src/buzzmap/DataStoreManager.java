package buzzmap;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DataStoreManager {
	private static final String datastoreName = "BuzzMap";
	
	public static boolean AddTweet(Tweet tweet) {
		Key tweetKey = KeyFactory.createKey(datastoreName, tweet.id_str);
		Entity tweetEntity = new Entity(datastoreName, tweetKey);
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
}
