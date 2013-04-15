package buzzmap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Sentiment {

	public static HashMap<String, Integer> sentimentMap;

	public Sentiment() {
		sentimentMap = new HashMap<String, Integer>();
		BufferedReader reader = null;
		String lineString = null;
		try {
			reader = new BufferedReader(new FileReader("AFINN-111.txt"));
			while ((lineString = reader.readLine()) != null) {
				String ss[] = lineString.split("\t");
				sentimentMap.put(ss[0], Integer.parseInt(ss[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String checkSentiment(String word) {
		int rate = 0;
		if (sentimentMap.containsKey(word)) {
			rate = sentimentMap.get(word);
		}
		if (rate < -3) return "very negative";
		else if (rate < 0) return "negative";
		else if (rate == 0) return "neutral";
		else if (rate <= 3) return "positive";
		else return "very positive";
	}
}
