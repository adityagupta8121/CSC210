/**
 * 
 * LinkedList
 * 
 * implementation of a singly linked list.
 * 
 */
import java.util.Objects;

public class LinkedList 
{
	private class Node
	{
		private int data;
		private Node next;
	
		
		public Node(int data)
		{
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;

	
	public LinkedList() 
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public LinkedList(LinkedList other) 
	{ 
		this.head = other.head;
		this.head.next = other.head.next;
		this.tail = other.tail;
		this.size = other.size;
	}    
		
	/**
	 * addLast
	 * enqueue for ListQueue
	 * Push for ListStack
	 * @param data
	 */
	public void addLast(int data)
	{
		Node newNode = new Node(data);
		size++;

		if(head == null)
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.next = newNode;
			tail = newNode;
		}
	}

	/**
	 * dequeue for ListQueue
	 * @return First Element
	 */
	public int removeFirst()
	{
		if (head == null)
			return -1;

		Node temp = head;
		head = head.next;
		size--;
		
		return temp.data;
	}
	
	/**
	 * pop for ListStack
	 * @return Last Element
	 */
	public int removeLast()
	{				
		if(getSize() == 0)
			return -1;
		
		if (head == null)
			return -1;

	    Node current = head;
	    Node temp = head;
	    
	    while (current.next != null) 
	    {
	        temp = current;
	        current = current.next;
	    }

	    int ret = current.data;
	    current = temp;
	    current.next = null;
	    size--;
	    
	    return ret;
	}

	/**
	 * peek for ListQueue
	 * @return First Element
	 */
	public int getFirst()
	{
		if (head == null)
			return -1;

		return head.data;
	}

	/**
	 * peek for ListStack
	 * @return Last Element
	 */
	public int getLast()
	{
		if(getSize() == 0)
			return -1;
		
		if (head == null)
			return -1;
		
	    Node current = head;
	    Node temp = head;
	    while (current.next != null) 
	    {
	        temp = current;
	        current = current.next;
	    }

	    int ret = current.data;
	    
	    return ret;
	}

	/**
	 * isEmpty for ListQueue and ListStack
	 * @return true or false
	 */
	public boolean empty() 
	{
		return size == 0;
	}
	
	/**
	 * Clear for ListQueue and ListStack
	 * @return nothing
	 */
	public void clearAll()
	{
		head = null;
		size = 0;
	}
		
	/**
	 * size for ListQueue and ListStack
	 * @return size
	 */
	public int getSize()
	{
		if(empty())
			return 0;
		return size;
	}
		
	/**
	 * toString
	 * @return String
	 */
	public String toString()
	{
		String string = "{";
		
		if(!empty()) 
		{
			Node current = head;

			for(int i = 0; i < size - 1; i++) 
			{
				string += current.data + ",";

				current = current.next;
			}
			string += current.data;
		}
		string += "}";
		
		return string;
	}
	
	@Override
	/**
	 * equals
	 * @param obj
	 * Checks if two objects are equal in elements and size
	 * return true or false
	 */
	public boolean equals(Object obj) 
	{		
		if (this == obj)
			return true;

		if (obj == null)
			return false;
		
		LinkedList other = (LinkedList) obj;
		
		return Objects.equals(head.data, other.head.data) && size == other.size && Objects.equals(tail.data, other.tail.data);
	}
	
	/**
	 * Helper Function
	 * Gets value of a specific Index
	 * @param index
	 * @return
	 */
	public int GetNth(int index)
	{
		Node current = head;
		int count = 0; 
		
		while (current != null)
		{
			if (count == index)
				return current.data;
			count++;
			current = current.next;
		}

		assert (false);
		return 0;
	}
}