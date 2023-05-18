/**
 * 
 * ListQueue implements QueueInterface
 * 
 * implementation of QueueInterface using LinkedList
 * 
 */
import java.util.Objects;

public class ListQueue implements QueueInterface
{
	private LinkedList queue;
	int size;
	
	public ListQueue()
	{
		queue = new LinkedList();
	}

	/**
	 * Copy Constructor
	 * Time Complexity in big-O notation : O(N)
	 * @param queueCopy
	 * @return nothing
	 */
	public ListQueue(ListQueue queueCopy)
	{
		this.queue = queueCopy.queue;
		this.size = queueCopy.size();
		
		LinkedList temp = new LinkedList();
				
		for(int i = 0; i < queueCopy.size(); i++)
		{
			temp.addLast(queueCopy.getID(i));
		}
		queue = temp;		
	}
	
	/**
	 * enqueue
	 * Time Complexity in big-O notation : O(1)
	 * adds element to the back of the queue
	 * @param value
	 * @return nothing
	 */
	@Override
	public void enqueue(int value) 
	{
		queue.addLast(value);
	}

	/**
	 * dequeue
	 * Time Complexity in big-O notation : O(1)
	 * removes and returns element from the front of the queue
	 * @return Front Element of List/Queue
	 */
	@Override
	public int dequeue() 
	{
		return queue.removeFirst();
	}

	
	/**
	 * peek
	 * Time Complexity in big-O notation : O(1)
	 * returns (does not remove) element from the front of the queue
	 * @return Front Element of List/Queue
	 */
	@Override
	public int peek() 
	{
		return queue.getFirst();
	}

	/**
	 * isEmpty
	 * Time Complexity in big-O notation : O(1)
	 * returns true if the List/queue is empty
	 * @return true or false
	 */
	@Override
	public boolean isEmpty() 
	{
		return queue.empty();
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
		return queue.getSize();
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
		queue.clearAll();
	}
	
	/**
	 * toString
	 * Time Complexity in big-O notation : O(N)
	 * creates a string of all elements in queue
	 * @return String str
	 */
	public String toString()
	{
		return queue.toString();
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
		
		ListQueue other = (ListQueue) obj;
		return Objects.equals(queue.toString(), other.queue.toString()) && queue.getSize() == other.size();
		
	}
	
	/**
	 * getID helper function
	 * Time Complexity in big-O notation : O(1)
	 * returns element at specific index
	 * @param i
	 * @return element at index
	 */
	private int getID(int i) 
	{
		return queue.GetNth(i);
	}


}
