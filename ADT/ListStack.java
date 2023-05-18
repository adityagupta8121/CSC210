/**
 * 
 * ListStack implements StackInterface
 * 
 * implementation of StackInterface using LinkedList
 * 
 */
import java.util.Objects;

public class ListStack implements StackInterface 
{
	private LinkedList stack;
	int size;
	
	public ListStack()
	{
		stack = new LinkedList();
	}

	/**
	 * copy constructor
	 * Time Complexity in big-O notation : O(N)
	 * @param stackCopy
	 */
	public ListStack(ListStack stackCopy)
	{
		this.stack = stackCopy.stack;
		this.size = stackCopy.size();
		
		LinkedList temp = new LinkedList();
				
		for(int i = 0; i < stackCopy.size(); i++)
		{
			temp.addLast(stackCopy.getID(i));
		}
		
		stack = temp;		
	}
	
	/**
	 * push
	 * Time Complexity in big-O notation : O(1)
	 * Adds element to the top of the stack
	 * @param value
	 * @return nothing
	 */
	@Override
	public void push(int value) 
	{
		stack.addLast(value);
	}

	/**
	 * pop
	 * Time Complexity in big-O notation : O(1)
	 * Removes and returns the element at the top of the stack
	 * @return Top element of stack
	 */
	@Override
	public int pop() 
	{
		return stack.removeLast();
	}

	/**
	 * peek
	 * Time Complexity in big-O notation : O(1)
	 * returns the element at the top of the stack
	 * @return Top element of stack
	 */
	@Override
	public int peek() 
	{
		return stack.getLast();
	}

	/**
	 * isEmpty
	 * Time Complexity in big-O notation : O(1)
	 * returns true if stack is empty
	 * @return true or false
	 */
	@Override
	public boolean isEmpty() 
	{
		return stack.empty();
	}

	/**
	 * size
	 * Time Complexity in big-O notation : O(1)
	 * returns size of the stack
	 * @return number of elements in stack
	 */
	@Override
	public int size() 
	{		
		return stack.getSize();
	}

	/**
	 * clear
	 * Time Complexity in big-O notation : O(1)
	 * clears all elements of the stack
	 */
	@Override
	public void clear() 
	{
		stack.clearAll();
	}
	
	/**
	 * toString
	 * Time Complexity in big-O notation : O(N)
	 * returns elements of stack in string
	 * @return String str
	 */
	public String toString()
	{
		return stack.toString();
	}
	
	/**
	 * equals
	 * Time Complexity in big-O notation : O(1)
	 * returns true if two stacks are equal
	 * @return true or false
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		ListStack other = (ListStack) obj;
		return Objects.equals(stack.toString(), other.stack.toString()) && stack.getSize() == other.size();
	}

	/**
	 * Helper Function
	 * getID
	 * Time Complexity in big-O notation : O(1)
	 * returns element at specified index
	 * @return element
	 */
	private int getID(int i) 
	{
		return stack.GetNth(i);
	}

}