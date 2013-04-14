package buzzmap;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TweetSearchServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, String> parameter = new HashMap<String, String>();
		String location = request.getParameter("location_textField");
		parameter.put("address", location);
		parameter.put("sensor", "false");
		Geocode geocode = Google_API.getGeocode(parameter);
		System.out.println(geocode.latitude + "," + geocode.longitude);
		
		parameter.clear();
		String category = request.getParameter("category_textField");
		parameter.put("q", category);
		parameter.put("rpp", "20");
		
		String jsonString = TweetSearch.tweetSearch(parameter);
		TweetSearch.parseTweet(jsonString);
		
		response.sendRedirect("index.jsp");
	}
}
