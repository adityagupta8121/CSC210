/**
 * 
 * ArrayQueue implements QueueInterface
 * 
 * implementation of QueueInterface using DynamicArray
 * 
 */
import java.util.Objects;

public class ArrayQueue implements QueueInterface
{

	private DynamicArray array;
	int size;
	
	public ArrayQueue()
	{
		array = new DynamicArray();
	}
	
	/**
	 * Copy Constructor
	 * Time Complexity in big-O notation : O(N)
	 * @param queueCopy
	 * @return nothing
	 */
	public ArrayQueue(ArrayQueue queueCopy)
	{
		this.size = queueCopy.size();
		this.array = queueCopy.array;
		
		DynamicArray temp = new DynamicArray();
		
		for(int i = 0; i < queueCopy.size(); i++)
		{
			temp.addLast(queueCopy.getID(i));
		}
		
		array = temp;
	}

	/**
	 * enqueue
	 * Time Complexity in big-O notation : O(N)
	 * adds element to the back of the queue
	 * @param value
	 * @return nothing
	 */
	@Override
	public void enqueue(int value) 
	{
		array.addLast(value);				
	}

	/**
	 * dequeue
	 * Time Complexity in big-O notation : O(N)
	 * removes and returns element from the front of the queue
	 * @return Front Element of array
	 */
	@Override
	public int dequeue() 
	{
		return array.removeFront();
	}

	/**
	 * peek
	 * Time Complexity in big-O notation : O(1)
	 * returns (does not remove) element from the front of the queue
	 * @return Front Element of array
	 */
	@Override
	public int peek() 
	{
		return array.getFirst();
	}

	/**
	 * isEmpty
	 * Time Complexity in big-O notation : O(1)
	 * returns true if the array/queue is empty
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
	 * returns size or number of elements in the queue/array
	 * @return size in integer
	 */
	@Override
	public int size() 
	{
		return array.getSize();
	}

	/**
	 * clear
	 * Time Complexity in big-O notation : O(1)
	 * clears all elements in the queue/array
	 * @return nothing
	 */
	@Override
	public void clear() 
	{
		array.clearArr();
	}
	
	/**
	 * toString
	 * Time Complexity in big-O notation : O(N)
	 * creates a string of all elements in queue
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
	 * returns true if the objects are equal and size is same
	 * @param obj
	 * @return true or false
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		ArrayQueue other = (ArrayQueue) obj;
		return Objects.equals(array, other.array) && array.getSize() == other.size();
	}
	
	/**
	 * getID helper function
	 * Time Complexity in big-O notation : O(1)
	 * returns element at specific index
	 * @param ID
	 * @return element at index
	 */
	
	public int getID(int ID)
	{
		return array.getFrom(ID);
	}
}
