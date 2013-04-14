package buzzmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TweetSearchServlet extends HttpServlet{
	private static final int Top_Buzz_Number = 10;
	private static final int User_Per_Buzz = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		/*
		 * Return url in format:
		 * 	index.jsp?buzz=buzz_name,sentiment@;@user1_name,user1_text,user1_image@,@user2_name...&buzz=
		 */
		
		String location = request.getParameter("location_textField").toLowerCase();
		String category = request.getParameter("category_textField").toLowerCase();
		String searchString = location + ";" + category;
		ArrayList<Tweet> tweets = null;
		
		boolean exist = DataStoreManager.checkSearchExist(searchString);
		if (!exist) {
			HashMap<String, String> parameter = new HashMap<String, String>();
			parameter.put("address", location);
			parameter.put("sensor", "false");
			Geocode geocode = Google_API.getGeocode(parameter);
			
			parameter.clear();
			parameter.put("q", category);
			parameter.put("rpp", "100");
			parameter.put("geocode", String.valueOf(geocode.latitude) + "," + 
					String.valueOf(geocode.longitude) + ",5mi");
			
			String jsonString = TweetSearch.tweetSearch(parameter);
			tweets = TweetSearch.parseTweet(jsonString, searchString);
		} else {
			tweets = DataStoreManager.getTweets(searchString);
		}

		Sentiment sentiment = new Sentiment();
		String buzzString = "";
		int buzzCount = 0;
		ArrayList<Entry<String, Buzz>> tweetList = BuzzManager.extractBuzz(tweets);
		for (Entry<String, Buzz> entry : tweetList) {
			Buzz buzz = entry.getValue();
			buzzString = buzzString + "&buzz" + "=" + buzz.buzzName + "," + 
					sentiment.checkSentiment(buzz.buzzName) + "@;@";
			
			String userString = "";
			int userCount = 0;
			for (Tweet tweet : buzz.tweets) {
				userString = userString + "@,@" + tweet.from_user_name + "," + tweet.text + "," + tweet.profile_image_url;
				if (++userCount == User_Per_Buzz) break;
			}
			buzzString = buzzString + userString.substring(3);
			
			if (++buzzCount == Top_Buzz_Number) break;
		}

		request.setAttribute("buzzString", buzzString.substring(1));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
