package assignment4;



import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import okhttp3.Response;



public class JSON_to_Tweets {
	
	
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static List<Tweets> getTweets(String url) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
		String response = OkHttp.sendGet(url);
	

			List<Tweets> tweets = Arrays.asList(mapper.readValue(response, Tweets[].class));
			return filterBadData(tweets);
       
			
	
	}
	public static List<Tweets> filterBadData(List<Tweets> tweets)
	{
		List<Tweets> filteredTweets = new ArrayList<>();
		JSON_to_Tweets jtt = new JSON_to_Tweets();
		for (Tweets tweet : tweets)
		{
			if(jtt.isName(tweet) && jtt.isSize(tweet) && jtt.isDate(tweet) && jtt.isID(tweet))
			{
				filteredTweets.add(tweet);
			}
		}
		
		return filteredTweets;
		
	}
	
	
	public boolean isName(Tweets tweet)
	{
		try {
		   return tweet.getName().matches("[a-zA-Z0-9_]+");
		} catch(NullPointerException e)
		{
			return false;
		}
	}
	
	public boolean isSize(Tweets tweet)
	{
		try {
			return tweet.getText().length() <= 140;
		} catch(NullPointerException e)
		{
			return false;
		}
	}
	
	public boolean isDate(Tweets tweet)
	{
		try {
		String regex = "\\d{4}-\\d{2}-\\d{2}(T\\d{2}:\\d{2}:\\d{2}Z)?(T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z)?";
		String regex2 = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?(\\.\\d+)?Z";
		Instant thing = Instant.parse(tweet.getDate());
		return true;
		//return tweet.getDate().matches(regex2);
		}catch(DateTimeParseException e)
		{
			return false;
		}catch(NullPointerException e)
		{
			return false;
		}
	}
	
	public boolean isID(Tweets tweet)
	{
		try {
			int id = tweet.getId();
			return (id > 0)? true : false;
			
		}catch(NumberFormatException e)
		{
			return false;
		}
	}
	


}