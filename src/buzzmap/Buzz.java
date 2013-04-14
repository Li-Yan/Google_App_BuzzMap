package buzzmap;

import java.util.ArrayList;

public class Buzz {
	public String buzzName;
	public int count;
	public String sentiment;
	public ArrayList<Tweet> tweets;
	
	public Buzz(String Name) {
		buzzName = Name;
		count = 0;
		sentiment = "";
		tweets = new ArrayList<Tweet>();
	}
	
	public void addTweet(Tweet tweet) {
		count++;
		tweets.add(tweet);
	}
	
	public void setSentiment(String Sentiment) {
		sentiment = Sentiment;
	}
}
