package buzzmap;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Tweet {
	public String search;
	public String created_at;
	public String from_user;
	public String from_user_id_str;
	public String from_user_name;
	public String id_str;
	public String profile_image_url;
	public String source;
	public String text;
	
	public Tweet(JSONObject tweetObject, String searchString) {
		try {
			search = searchString;
			created_at = tweetObject.getString("created_at");
			from_user = tweetObject.getString("from_user");
			from_user_id_str = tweetObject.getString("from_user_id_str");
			from_user_name = tweetObject.getString("from_user_name");
			id_str = tweetObject.getString("id_str");
			profile_image_url = tweetObject.getString("profile_image_url");
			source = tweetObject.getString("source");
			text = tweetObject.getString("text");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Tweet(Entity entity) {
		search = entity.getProperty("search").toString();
		created_at = entity.getProperty("created_at").toString();
		from_user = entity.getProperty("from_user").toString();
		from_user_id_str = entity.getProperty("from_user_id_str").toString();
		from_user_name = entity.getProperty("from_user_name").toString();
		id_str = entity.getProperty("id_str").toString();
		profile_image_url = entity.getProperty("profile_image_url").toString();
		source = entity.getProperty("source").toString();
		text = entity.getProperty("text").toString();
	}
}
