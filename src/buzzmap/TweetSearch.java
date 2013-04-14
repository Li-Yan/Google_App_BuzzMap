package buzzmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class TweetSearch {
	private static final String Twitter_API_URL = "http://search.twitter.com/search.json?";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String tweetSearch(HashMap<String, String> Parameter) {
		String urlString = "";
		Iterator iter = Parameter.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Entry<String, String>) iter.next();
			try {
				urlString = urlString + "&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "utf8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		urlString = Twitter_API_URL + urlString.substring(1);
		
		String response = "";
		String line;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            response = response + line;
	         }
	         rd.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static ArrayList<Tweet> parseTweet(String jsonString, String searchString) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			JSONObject originalObject = new JSONObject(jsonString);
			JSONArray jsonArray = originalObject.getJSONArray("results");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Tweet tweet = new Tweet(jsonObject, searchString);
				tweets.add(tweet);
				DataStoreManager.AddTweet(tweet);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
	}
}
