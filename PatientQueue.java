/**
 * @author adityagupta
 * 
 * CSC 210
 * 
 * P. Assignment 6
 * 
 * PatientQueue.Java
 */

class Heap
{
	private Patient[] heap;
	private int size;
	private int capacity;

	/**
	 * Constructor
	 * initialises new heap
	 * with array of size 10.
	 */

	public Heap()
	{
		capacity = 10;
		heap = new Patient[capacity];
		size = 0;
	}

	/**
	 * enqueue
	 * Adds data to queue as required, and as implemented
	 * calls bubble up to sort and resizes if necessary
	 * @param name
	 * @param priority
	 */
	public void enqueue(String name, int priority)
	{
		Patient patient = new Patient(name, priority);
		
		if (heap.length == size + 1)
		{
			capacity = capacity * 2;
			Patient[] temp = heap;
			heap = new Patient[capacity];

			for (int i = 1; i <= size; i++)
			{
				heap[i] = temp[i];
			}		
		} 

		heap[size + 1] = patient;
		size++;
		
		bubbleUp(size);
	}

	/**
	 * dequeue()
	 * removed object from the queue
	 * calls bubbleDown as required
	 * @return String leave (name)
	 * @throws Exception
	 */
	public String dequeue() throws Exception
	{
		if(size == 0)
			throw new Exception("Cannot Dequeue. Patient Queue is Empty.");

		String leave = heap[1].name;
		heap[1] = heap[size];
		size--;

        if (size > 0)
        {
            bubbleDown(1);
        }

		return leave;
	}

	/**
	 * Parent
	 * Helper function
	 * Gets parent
	 * @param i
	 * @return integer
	 */
	private int parent(int i)
	{
		return i / 2;
	} 

	/**
	 * left
	 * Helper function
	 * Gets left child
	 * @param i
	 * @return integer
	 */
	private int left(int i)
	{
		return i * 2;
	}

	/**
	 * right
	 * Helper function
	 * Gets right child
	 * @param i
	 * @return integer
	 */
	private int right(int i)
	{
		return (i * 2) + 1;
	}

	/**
	 * bubbleUp
	 * Helper function
	 * sorts the array as required when obect at
	 * position i is passed
	 * @param i
	 */
	private void bubbleUp(int i)
	{
		if (i > 1)
		{
			int parent = parent(i);
			
			Patient parentIndex = heap[parent];
			Patient index = heap[i];
			
			if (parentIndex.priority >= index.priority) 
			{
				if (parentIndex.priority == index.priority) 
				{
					if (parentIndex.name.compareTo(index.name) > 0) 
					{
						return;
					}
				}
				
				heap[parent] = index;
				heap[i] = parentIndex;
				bubbleUp(parent);
			}
		}
	}

	/**
	 * bubbledown
	 * Helper function
	 * sorts the array as required when obect at
	 * position i is passed
	 * @param i
	 */
	private void bubbleDown(int i)
	{
		if (i < size) 
		{
			Patient lefty = heap[left(i)];
			Patient righty = heap[right(i * 2 + 1)];
			Patient least = heap[i];
			
			if ((i * 2) <= size) 
			{
				if (lefty.priority < least.priority) 
				{
					least = lefty;
				}
				if (lefty.priority == least.priority && lefty.name.compareTo(least.name) < 0) 
				{
					least = lefty;
				}
			}
			
			if ((i * 2 + 1) <= size) 
			{
				if (righty.priority < least.priority) 
				{
					least = righty;
				}
				if (righty.priority == least.priority && righty.name.compareTo(least.name) < 0) 
				{
					least = righty;
				}

			}
			
			if (least != heap[i]) 
			{
				Patient temp = heap[i];
				
				if (least == lefty) 
				{
					heap[i] = lefty;
					heap[i * 2] = temp;
					bubbleDown(i * 2);
				} 
				else {
					heap[i] = righty;
					heap[i * 2 + 1] = temp;
					bubbleDown(i * 2 + 1);
				}
			}
		}
	}
	
	/**
	 * changePriority
	 * Changes priority as required
	 * implements bubble up and bubble down
	 * as required
	 * @param name
	 * @param newPriority
	 */
	public void changePriority(String name, int newPriority)
	{
		int temp = 0;

		for (int i = 1; i <= size; i++)
		{
			if (name.equals(heap[i].name))
			{
				temp = heap[i].priority;
				heap[i].priority = newPriority;

				if (temp > newPriority)
				{
					bubbleUp(i);
					break;
				}
				else if (temp < newPriority)
				{
					bubbleDown(i);
					break;
				}
				break;
			}
		}
	}

	/**
	 * peek
	 * peeks/returns the name of frontmost patient
	 * @return String name
	 * @throws Exception
	 */
	public String peek() throws Exception
	{
		if (isEmpty())
		{
			throw new Exception("Cannot Peek. Patient Queue is Empty");
		}
		return heap[1].name;
	}

	/**
	 * peekPriority
	 * peeks/returns priority of frontmost patient
	 * @return Integer priority
	 * @throws Exception
	 */
	public int peekPriority() throws Exception 
	{
		if (isEmpty())
		{
			throw new Exception("Cannot Peek. Patient Queue is Empty");
		}
		return heap[1].priority;
	}

	/**
	 * isEmpty
	 * checks if heap is empty
	 * @return true if empty
	 */
	public boolean isEmpty()
	{
		if (size == 0) 
		{
			return true;
		}
		return false;
	}

	/**
	 * size
	 * checks size of heap and returns
	 * @return size
	 */
	public int size()
	{
		return size;
	}

	/**
	 * clear
	 * clears the heap by initialising a new array
	 * same as constructor
	 */
	public void clear()
	{
		capacity = 10;
		heap = new Patient[capacity];
		size = 0;
	}

	/**
	 * toString
	 * string implementation
	 * @return String str
	 */
	@Override
	public String toString()
	{
		String str = "";
		str += "{";

		for(int i = 1; i <= size; i++)
		{
			if(i < size)
			{
				str += heap[i];
				str += ", ";				
			}
			else
			{
				str += heap[i];
			}
		}

		return str + "}";
	}

}

public class PatientQueue
{

	private Heap pQueue;

	/**
	 * Constructor
	 * initialises new empty queue using Heap
	 */
	public PatientQueue()
	{
		pQueue = new Heap();
	}

	/**
	 * enqueue
	 * add to the queue with given priority
	 * call resize/bubble up to double size if required
	 * (uses enqueue of Heap)
	 * @param name
	 * @param priority
	 * @return NA
	 */
	public void enqueue(String name, int priority) 
	{
		pQueue.enqueue(name, priority);
	}

	/**
	 * enqueue
	 * add the queue with a patient object
	 * instead of name & priority
	 * (uses enqueue of Heap)
	 * @param patient
	 * @return NA
	 */
	public void enqueue(Patient patient) 
	{
		pQueue.enqueue(patient.name, patient.priority);
	}

	/**
	 * dequeue
	 * removes and returns name
	 * of frontmost patient
	 * (uses dequeue of Heap)
	 * @param NA
	 * @return string name
	 * @throws Exception
	 */
	public String dequeue() throws Exception
	{
		return pQueue.dequeue();
	}

	/**
	 * changePriority
	 * modify priority of already existing
	 * patient in the queue and resizing 
	 * as required
	 * (uses changePriority functionality of Heap)
	 * @param name
	 * @param newPriority
	 * @return NA
	 */
	public void changePriority(String name, int newPriority)
	{
		pQueue.changePriority(name, newPriority);
	}

	/**
	 * peek
	 * name of frontmost patient
	 * (uses peek functionality of Heap)
	 * @param NA
	 * @return string name
	 * @throws Exception
	 */
	public String peek() throws Exception
	{
		return pQueue.peek();
	}

	/**
	 * peekPriority
	 * int priority of frontmost patient
	 * (uses peekPriority functionality of Heap)
	 * @param NA
	 * @return int priority
	 * @throws Exception
	 */
	public int peekPriority() throws Exception 
	{
		return pQueue.peekPriority();
	}

	/**
	 * isEmpty
	 * checks if Patient Queue is empty
	 * (uses isEmpty of Heap)
	 * @param NA
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return pQueue.isEmpty();
	}

	/**
	 * size
	 * returns the size of the Patient Queue
	 * (uses size functionality of Heap)
	 * @param NA
	 * @return int size
	 */
	public int size()
	{
		return pQueue.size();
	}

	/**
	 * clear
	 * clears the Patient Queue 
	 * by initialising the queue
	 * (uses clear functionality of Heap)
	 * @param NA
	 * @return NA
	 */
	public void clear()
	{
		pQueue.clear();
	}

	/**
	 * toString
	 * represents Patient Queue in String
	 * (uses toString functionality of Heap)
	 * @param NA
	 * @return String str
	 */
	@Override
	public String toString()
	{
		return pQueue.toString();
	}
}