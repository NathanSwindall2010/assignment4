package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<Tweets> tweets, int k) {
        List<String> mostFollowers = new ArrayList<>();
        Map<String, Set<String>> followers = SocialNetwork.getFollowers2(tweets);
        Map<String,Integer> followerCounts = getFollowerCounts(followers);
        Map<String,Integer> sorted = sortByValue(followerCounts);
        Iterator<Entry<String, Integer>> myitr = sorted.entrySet().iterator();
        int count = k;
        while(myitr.hasNext() && count > 0)
        {
        	mostFollowers.add(myitr.next().getKey());
        	count--;
        }
        
        return mostFollowers;
    }


    
    // if A mentions B, then A follows B. 
    // add to set then you can see everything
    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    public static List<Set<String>> findCliques(List<Tweets> tweets) {
        List<Set<String>> result = new ArrayList<Set<String>>();
        Map<String, Set<String>> followers = getFollowers2(tweets);
        FindingCliques<String> FC = new FindingCliques<>(followers);
        
        return FC.DFS();
    }
    
    
   
    public static Map<String, Set<String>> getFollowers2(List<Tweets> tweets)
    {
    	Map<String,Set<String>> followers = new HashMap<>();
    	for(Tweets tweet: tweets)
    	{
    		String[] splits = tweet.getText().toLowerCase().split(" ");
  
    	
    		for(String sp: splits)
    		{
    			if(sp.matches("@[a-zA-Z0-9]+"))
    			{
    				String followed = sp.split("@")[1];
    				if(!followers.containsKey(followed))
    				{
    					followers.put(followed, new HashSet<String>());
    				}
    				followers.get(followed).add(tweet.getName());
    				
    			}
    		}
    	
    	}
    	
    	return followers;
    }
    
    
    
    
    public static Map<String, Integer> getFollowerCounts(Map<String, Set<String>> goods)
	{
		Map<String,Integer> myMap = new HashMap<>();
		
		Iterator<Entry<String, Set<String>>> myItr = goods.entrySet().iterator();
		
		while( myItr.hasNext())
		{
			Entry<String,Set<String>> entry = myItr.next();
			myMap.put(entry.getKey(), entry.getValue().size());
			
		}
		
		
		return myMap;
	}
    
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o2,
                               Map.Entry<String, Integer> o1) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /*
    public static Map<String, Set<String>> getFollowers(List<Tweets> tweets)
    {
    	Map<String,Set<String>> followers = new HashMap<>();
    	for (Tweets tweet: tweets)
    	{
    		String regex = "([^a-zA-Z0-9_]|^)@[a-zA-Z0-9_]+";
    		//String regex = "[\\s]@[a-zA-Z0-9_]+|^@[a-zA-Z0-9_]+";
    		Pattern pattern = Pattern.compile(regex);
    		Matcher match = pattern.matcher(tweet.getText());
    		while(match.find())
    		{
    			String[] spl = match.group().split("@");
    			if(!followers.containsKey(spl[1].toLowerCase()))
    			{
    				followers.put(spl[1].toLowerCase(), new HashSet<String>());
    			}
    			
    			Set<String> newSet = followers.get(spl[1].toLowerCase()); // the new set with update value
    			newSet.add(tweet.getName().toLowerCase()); // add updated value
    			
    			
    			followers.replace(spl[1], newSet); // now at to the follower
    		}
    		
    		
    	}
		return followers;
    }
    */
    
	
}


