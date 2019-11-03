package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class FindingCliques<V> {

	private Set<V> vertices;
	private Map<V,List<Edge<V>>> neighbors = new HashMap<>();
	private List<Edge<V>> edges;
	private List<Set<V>> sets;
	
	
	//Constructor for the graph
	public FindingCliques(Map<V, Set<V>> followers)  // changed
	//public FindingCliques(Map<String, List<String>> followers)
	{
		this.vertices = getVertices(followers);
		//this.vertices = makeVectorMap(followers);
		this.edges = findEdges2(followers);
		//this.neighbors = getListOfNeighbors(edges, this.vertices);
		this.sets = new ArrayList<>();
		//findGroups();
		this.neighbors = getListOfNeighbors(edges,vertices);	
		
		
	}
	
	public Set<V> getVertices(Map<V, Set<V>> followers) //good
	{
		Iterator<Entry<V, Set<V>>> myItr = followers.entrySet().iterator();
		Set<V> vertices = new HashSet<>();
		while(myItr.hasNext())
		{
			Entry<V,Set<V>> myEnt = myItr.next();
			vertices.add((V)myEnt.getKey());
			for(V Vinset: ((Set<V>) myEnt.getValue()))
			{
				vertices.add(Vinset);
			}	
		}
		
		return vertices;
	}
	
	
	/*
	 * This function will get all the edges for a map. It use the integers given to each 
	 * vertex my makeVectorMap, and gets integers for the edges
	 */
	
	public  List<Edge<V>> findEdges2(Map<V, Set<V>> followers)  //good
	//public  List<Edge> findEdges(Map<String,List<String>> followers)
	{
		
		List<Edge<V>> edges = new ArrayList<>();
		Iterator<V> myItr = followers.keySet().iterator();
		while(myItr.hasNext())
		{
			V currentUser = myItr.next();
			Set<V> userFollowers = followers.get(currentUser);
			for(V follower: userFollowers)
			{
				edges.add(new Edge<V>(follower , currentUser));
			}
		}
		
		return edges;
	}
	
	

	
	/*
	 * This function gets the neighbors for every vertex in the graph
	 */
	public Map<V, List<Edge<V>>> getListOfNeighbors(List<Edge<V>> edges, Set<V> vertices)
	{
		Map<V, List<Edge<V>>> listOfNeighbors = new HashMap<>();
		for(V vertex: vertices)
		{
			List<Edge<V>> neighbors = getNeighbors2(edges, vertex);
			if(!neighbors.isEmpty())
			{
				listOfNeighbors.put(vertex, neighbors);
			}
			
		}
		
		return listOfNeighbors;
	}
	
	
	
	
	public List<Edge<V>> getNeighbors2(List<Edge<V>> edges, V vertex)
	{
		List<Edge<V>> neighbors = new ArrayList<>();
		for(Edge<V> edge: edges)
		{
			if(edge.getU().equals(vertex))
			{
				neighbors.add(edge);
			}
		}
		return neighbors;
	}
	
	

	public List<Set<V>> DFS()
	{
		for(V vector: vertices)
		{
			MStack<V> mStk = new MStack<V>();
			recursion(vector,mStk );
		}
		return getRidOfSubsets2(sets);
	}
	
	
	
	public void recursion(V vector, MStack<V> visited)
	{
		visited.push(vector);
		List<Edge<V>> neighbor = this.neighbors.get(vector);
		try {
		for(Edge<V> nextVec: neighbor)
		{
			V next = nextVec.getV();
			if(!visited.contains(next) && checkIfFriends2(visited, next))
			{
				recursion(next, visited);
			}
				
		}

		List<V> visitedClone = (List<V>)visited.getStack().clone();
		Set<V> visitedSet = (Set<V>) visitedClone.stream().collect(Collectors.toSet());
		sets.add(visitedSet);
		visited.pop();
		}catch(NullPointerException e)
		{
		}
	}
	
	
	
	public boolean checkIfFriends2(MStack<V> myStrs, V friend)
	{
		boolean clique = true;
		if(!myStrs.isEmpty())
		{
			for(V person: myStrs.getStack())
			{
				if(edges.contains(new Edge<V>(person, friend)) && edges.contains(new Edge<V>(friend, person)))
				{
					clique &= true;
				}else
					clique &= false;
			}
		}
		return clique;
		
	}
	
	
	public List<Set<V>> getRidOfSubsets2(List<Set<V>> myNums)
	{
		List<Set<V>> removeThese = new ArrayList<>();
		for(Set<V> clique: myNums)
		{
			for(Set<V> otherClique: myNums)
			{
				if(clique.containsAll(otherClique) && !clique.equals(otherClique))
				{
				
					removeThese.add(otherClique);
				}else if(otherClique.size() <= 1)
				{
					removeThese.add(otherClique);
				}else if(otherClique.equals(clique))
				{
				}
			}
		}
		for(Set<V> rmList: removeThese)
		{
			myNums.remove(rmList);
			
		}
		
		Set<Set<V>> duplicateSet = new HashSet<>();
		List<Set<V>> finalProduct = new ArrayList<>();
		for(Set<V> dup: myNums){ duplicateSet.add(dup);}
		for(Set<V> fp: duplicateSet){ finalProduct.add(fp);}
		
		return finalProduct;
		
	}
	
	

	
}
