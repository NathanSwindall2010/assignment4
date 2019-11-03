package assignment4;

import java.util.ArrayList;

public class MStack<T> implements Cloneable, Comparable<MStack<T>> {
	
	private ArrayList<T> stack;
	
	public MStack()
	{
		this.stack = new ArrayList<>();	
	}
	
	public MStack(MStack<T> mstack)
	{
		this.stack = mstack.getStack();
	}
	
	
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	public T pop()
	{
		if(!isEmpty())
		{
			T top = (T)stack.get(stack.size()-1);
			stack.remove(stack.size() -1);
			return top;
		}else
			return null;
	}
	
	public T peek()
	{
		if(!isEmpty())
		{
			T top = (T)stack.get(stack.size()-1);
			return top;
		}else
		 return null ;
	}
	
	public void push(T t)
	{
		stack.add(t);
		
	}
	
	public boolean contains(Object object)
	{
		return stack.contains(object);
	}
	
	public ArrayList<T> getStack()
	{
		return stack;
	}
	
	public String toString()
	{
		return stack.toString();
		
	}
	
	public MStack<T> clone() throws CloneNotSupportedException
	{
		return (MStack<T>)super.clone();
		
	}
	
	

	@Override
	public int compareTo(MStack<T> o) {
		
		if(equals(o))
			return 0;
		else if(!stack.equals(o) && stack.size() > o.getStack().size())
			return 1;
		else
			return -1;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stack == null) ? 0 : stack.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MStack<T> other = (MStack<T>) obj;
		if (stack == null) {
			if (other.stack != null)
				return false;
		} else if (!stack.equals(other.stack))
			return false;
		return true;
	}	
	
}
