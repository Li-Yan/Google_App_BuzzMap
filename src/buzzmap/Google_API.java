package buzzmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Google_API {
	public static final String Google_Geocoding_API_URL = "http://maps.googleapis.com/maps/api/geocode/json?";
	
	private static Geocode parseGeocode(String jsonString) {
		Geocode geocode = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("results");
			JSONObject mainJsonObject = jsonArray.getJSONObject(0);
			JSONObject geometryJsonObject = mainJsonObject.getJSONObject("geometry");
			JSONObject locationJsonObject = geometryJsonObject.getJSONObject("location");
			double latitude = locationJsonObject.getDouble("lat");
			double longitude = locationJsonObject.getDouble("lng");
			geocode = new Geocode(latitude, longitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geocode;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Geocode getGeocode(HashMap<String, String> Parameter) {
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
		urlString = Google_Geocoding_API_URL + urlString.substring(1);
		
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
		return parseGeocode(response);
	}
}
