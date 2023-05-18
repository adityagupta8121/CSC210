/**
 * 
 * ArrayStack implements StackInterface
 * 
 * implementation of StackInterface using DynamicArray
 * 
 */
import java.util.Objects;

public class ArrayStack implements StackInterface
{
	private DynamicArray array;
	int size;

	public ArrayStack()
	{
		this.array = new DynamicArray();
	}

	/**
	 * copy constructor
	 * Time Complexity in big-O notation : O(N)
	 * @param arrayCopy
	 */
	public ArrayStack(ArrayStack arrayCopy)
	{
		this.size = arrayCopy.size();
		this.array = arrayCopy.array;
		
		DynamicArray temp = new DynamicArray();
		
		for(int i = 0; i < arrayCopy.size(); i++)
		{
			temp.addLast(arrayCopy.getID(i));
		}
		
		array = temp;		
	}

	/**
	 * push
	 * Time Complexity in big-O notation : O(N)
	 * Adds element to the top of the stack
	 * @param value
	 * @return nothing
	 */
	@Override
	public void push(int value) 
	{
		array.addLast(value);
	}

	/**
	 * pop
	 * Time Complexity in big-O notation : O(N)
	 * Removes and returns the element at the top of the stack
	 * @return Top element of stack
	 */
	@Override
	public int pop() 
	{
		return array.removeTop();
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
		return array.getLast();
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
		return array.isEmpty();
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
		return array.getSize();
	}

	/**
	 * clear
	 * Time Complexity in big-O notation : O(1)
	 * clears all elements of the stack
	 */
	@Override
	public void clear() 
	{
		array.clearArr();
	}

	/**
	 * toString
	 * Time Complexity in big-O notation : O(N)
	 * returns elements of stack in string
	 * @return String str
	 */
	public String toString()
	{
		String str = array.toString();
		return str;
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
		
		ArrayStack other = (ArrayStack) obj;
		return Objects.equals(array, other.array) && array.getSize() == other.size();
	}
	
	/**
	 * Helper Function
	 * getID
	 * Time Complexity in big-O notation : O(1)
	 * returns element at specified index
	 * @return element
	 */
	public int getID(int ID)
	{
		return array.getFrom(ID);
	}
}