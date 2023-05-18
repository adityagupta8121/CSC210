/**
 * 
 * Dynamic Array
 * 
 * implementation of a DynamicArray
 * 
 */
import java.util.*;

public class DynamicArray 
{
	int size;
    int capacity = 10;
    int[] array;

	public DynamicArray()
	{
		this.array =  new int[capacity];
	}
	
	public DynamicArray(int capacity)
	{
		this.capacity = capacity;
        this.array = new int[capacity];
	}

	/**
	 * Copy Constructor
	 * @param ArrayCopy
	 */
	public DynamicArray(DynamicArray ArrayCopy)
	{
		this.size = ArrayCopy.size;
		this.capacity = ArrayCopy.capacity;
		this.array = ArrayCopy.array;
	}
	
	/**
	 * addLast
	 * add element to the last
	 * enqueue for Dynamic Array ArrayQueue
	 * push for Dynamic Array ArrayStack
	 * @param data
	 */
	public void addLast(int data)
	{
		if(size >= capacity)
		{
			expandArr();
		}
		array[size] = data;
		size++;
	}
	
	/**
	 * removeFront
	 * removes element from front
	 * dequeue for ArrayQueue
	 * @return retVal int
	 */
	public int removeFront()
	{
		int retVal;
		if(isEmpty())
			return -1;
		
		retVal = array[0];
		int[] tempArray = Arrays.copyOfRange(array, 1, array.length);
		array = tempArray;
		size--;
		shrinkSize();
		
		return retVal;
	}

	/**
	 * getFirst
	 * peek for ArrayQueue
	 * @return first element of array
	 */
	public int getFirst()
	{
		if(isEmpty())
			return -1;
		
        return array[0];
    }
	
	/**
	 * removeTop
	 * removes and returns Top/last element of array
	 * ArrayStack
	 * @return top element
	 */
	public int removeTop()   
	{   
		int retVal;
		if(isEmpty())
			return -1;

		retVal = array[size-1];
		if (size > 0)   
		{   
			array[size - 1] = 0;   
			size--;   
		}
		return retVal;
	}

	
	/**
	 * getLast
	 * returns Top/last element of array
	 * ArrayStack
	 * @return top element
	 */
	public int getLast()
	{
		if(isEmpty())
			return -1;
		
        return array[size - 1];
    }

	/**
	 * isEmpty
	 * used by ArrayQueue and ArrayStack
	 * used to check if array is empty
	 * @return true or false
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * getSize
	 * used by ArrayQueue and ArrayStack
	 * used to get size of array
	 * @return size
	 */
	public int getSize()
	{
		if(isEmpty())
			return 0;
		return size;
	}
	
	/**
	 * clearArr
	 * used by ArrayQueue and ArrayStack
	 * used to clear array
	 * @return nothing
	 */
	public void clearArr()
	{
		size = 0;
	}
	
	/**
	 * expandArr
	 * used to expand the capacity of dynamic array
	 * called when adding elements
	 */
	public void expandArr()
	{
		int newCapacity = capacity * 2;
		int[] newArray = new int[newCapacity];
		
		for(int i = 0; i < size; i++)
		{
			newArray[i] = array[i];
		}
		capacity = newCapacity;
		array = newArray;
	}
	
	/**
	 * shrinkSize
	 * used to reduce capacity of dynamic array
	 * called when removing elements
	 */
	public void shrinkSize()   
	{   
		
		if(size > (capacity/2)) {
			return;
		}
		
		this.capacity /=2;
		int[] newData = new int[size];

		for(int i = 0; i < size; i++) 
		{
			newData[i] = array[i];
		}

		array = newData;
	}   
	
	/**
	 * capacity
	 * returns capacity of element
	 * @return capacity elements array can hold
	 */
	public int capacity()
	{
		return capacity;
	}
	
	/**
	 * toString
	 * prints/returns array in string
	 * used by ArrayStack and ArrayQueue
	 * @return String string
	 */
	public String toString()
	{
        String string = "{";
        for(int i = 0; i < size; i++)
        {
        	if(i == size - 1)
        	{
        		string += array[i];
        	}
        	else
        	{
        		string += array[i] + ",";
        	}
        }
        string += "}";

        return string;
    }
	
	/**
	 * Helper Functon
	 * getFrom
	 * gets element on index mentioned
	 * @param idx
	 * @return element on index
	 */
	public int getFrom(int idx)
	{
		if(isEmpty())
			return -1;
		
        return array[idx];
    }
	
	/**
	 * checks if arrays/objects are equal in elements and size
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
		
		DynamicArray other = (DynamicArray) obj;
		
		return Arrays.equals(array, other.array) && size == other.size;
	}	
}