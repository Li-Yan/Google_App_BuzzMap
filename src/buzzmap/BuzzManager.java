package buzzmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("unused")
public class BuzzManager {
	private static final String NONE_BUZZ_WORD_STRINGS[] = {"a", "rt", "the", "at", "on", "in", "to", "by", 
		"with", "without", "for", "of", "not", "and", "or", "what", "which", "who", "how", "whether",
		"I", "me", "my", "mine", "you", "your", "yours", "he", "him", "his", "she", "her",
		"we", "our", "ours", "it", "its", "they", "them", "their",
		"is", "are", "was", "were", "can", "could", "cannot"};
	
	private static final String NONE_BUZZ_CONTAIN_STRINGS[] = {"http"};
	
	private static String onlyASCII(String word) {
		String newWord = "";
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if ((ch >= '0') && (ch <= '9') || (ch >= 'a') && (ch <= 'z')) {
				newWord = newWord + String.valueOf(ch);
			}
		}
		return newWord;
	}
	
	private static boolean buzzWord(String word) {
		if (word.length() <= 0) return false;
		for (String noneWord : NONE_BUZZ_WORD_STRINGS) {
			if (word.equalsIgnoreCase(noneWord)) return false;
		}
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if ((ch < 'a') || (ch > 'z')) return false;
		}
		return true;
	}
	
	private static ArrayList<Entry<String, Buzz>> mapValueSort(HashMap<String, Buzz> buzzMap) {
		ArrayList<Entry<String, Buzz>> list = new ArrayList<Entry<String, Buzz>>(buzzMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Buzz>>() {    
            public int compare(Map.Entry<String, Buzz> o1, Map.Entry<String, Buzz> o2) {    
                return (o2.getValue().count - o1.getValue().count);    
            }    
        });
		return list;
	}
	
	public static ArrayList<Entry<String, Buzz>> extractBuzz(ArrayList<Tweet> tweets) {
		HashMap<String, Buzz> buzzMap = new HashMap<String, Buzz>();
		
		for (Tweet tweet : tweets) {
			String words[] = tweet.text.toLowerCase().split(" ");
			for (String word : words) {
				word = word.toLowerCase();
				if (!buzzWord(word)) {
					continue;
				}
				if (!buzzMap.containsKey(word)) {
					Buzz buzz = new Buzz(word);
					buzz.addTweet(tweet);
					buzzMap.put(word, buzz);
				}
				else {
					Buzz buzz = buzzMap.get(word);
					buzz.addTweet(tweet);
					buzzMap.put(word, buzz);
				}
			}
		}
		ArrayList<Entry<String, Buzz>> sortedTweets = mapValueSort(buzzMap);
		return sortedTweets;
	}
}
