/**
 * @author adityagupta
 * CS 210 PA5
 * a generic implementation of a map using a hash table.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * class Node
 * @param <K>
 * @param <V>
 */
class Node<K,V>
{
	public K key;
	public V value;
	Node<K,V> next;

	/**
	 * @param key
	 * @param value
	 */
	public Node(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	/**
	 * @return key
	 */
	public K getKey() 
	{
		return key;
	}

	/**
	 * @return value
	 */
	public V getValue()
	{
		return value;
	}
	
	/**
	 * @param newVal
	 * @return temp
	 */
	public V setValue(V newVal) 
	{
        V temp = value;
        value = newVal;
        return temp;
    }

	@Override
	public int hashCode() 
	{
		return Objects.hash(key, next, value);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		return Objects.equals(key, other.key) && Objects.equals(next, other.next) && Objects.equals(value, other.value);
	}
}

/**
 * MyHashMap
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K,V>
{
	private int size;
	final static int numBuckets = 8;
	ArrayList<LinkedList<Node<K,V>>> hashTable = new ArrayList<>(numBuckets);

	public MyHashMap()
	{
		for (int i = 0; i < numBuckets; i++)
			hashTable.add(new LinkedList<Node<K,V>>());
	}

	/**
	 * hash
	 * @param key
	 * @return index
	 */
	private int hash(K key)
	{
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}

	/**
	 * clear
	 * Removes all of the mappings from this map
	 */
	public void clear() 
	{
		for (LinkedList<Node<K, V>> list: hashTable)
			list.clear();
		size = 0;
	}

	/**
	 * containsKey
	 * Returns true if this map contains a
	 * mapping for the specified key.
	 * @param key
	 * @return true or false
	 */
	public boolean containsKey(K key)
	{
		LinkedList<Node<K, V>> list = getList((K) key);
		for (Node pair: list)
			if (pair.key.equals(key))
				return true;
		
		return false;
	}

	/**
	 * containsValue
	 * Returns true if this map maps one or
	 * more keys to the specified value
	 * @param val
	 * @return true or false
	 */
	public boolean containsValue(V val)
	{
		for (LinkedList<Node<K, V>> list: hashTable)
			for (Node pair: list)
				if (pair.getValue().equals(val))
					return true;
		return false;
	}

	/**
	 * get
	 * Returns the value to which the specified
	 * key is mapped, or null if this map contains
	 * no mapping for the key.
	 * @param key
	 * @return value
	 */
	public V get(K key)
	{
		LinkedList<Node<K, V>> list = getList((K) key);
		for (Node pair: list)
		{
			if (pair.getKey().equals(key))
				return (V) pair.value;
		}
		return null;
	}

	/**
	 * isEmpty
	 * Returns true if this map contains
	 * no key-value mappings
	 * @return true or false
	 */
	public boolean isEmpty() 
	{
		return size == 0;
	}

	/**
	 * keySet
	 * Returns a Set view of the keys 
	 * contained in this map
	 * @return set
	 */
	public Set<K> keySet() 
	{
		Set<K> set = new HashSet<>();

		for (LinkedList<Node<K, V>> list: hashTable)
		{
			for (Node<K, V> pair: list)
			{
				set.add((K) pair.key);
			}
		}
		return set;
			
	}

	/**
	 * printTable
	 * Outputs how many conflicts occur at each
	 * bucket and list the keys in that bucket
	 */
	public void printTable()
	{
		int total = 0;
		
		for (int i = 0; i < numBuckets; i++)
		{
			System.out.print( "Index " + i + ": " );

			var list = hashTable.get(i);
			int counterHelp = 0;
			int tempcount = 0;
			String s = "[";

			for (Node<K,V> node: list)
			{
				tempcount = counterHelp;
				counterHelp++;
				s = s + (node.key) + ", ";
			}
			total += tempcount;

			s = s + "]";
			System.out.print(" (" + tempcount + " conflicts), " + s);
			System.out.println();
		}
		System.out.println("Total # of conflicts: " + total);
	}

	/**
	 * put
	 * Associates the specified value with 
	 * the specified key in this map
	 * @param key
	 * @param val
	 * @return temp
	 */
	public V put(K key, V val)
	{    	
		LinkedList<Node<K, V>> list = getList(key);
		V temp = null;
		int index = hash(key);

		Node<K, V> node = new Node<>(key, val);
		

    	if (containsKey(key))
    	{
    		for (Node pair: list)
    		{
    			if (pair.getKey().equals(key)) 
    			{
    				temp = (V) pair.getValue();
    				pair.setValue(val);
    			}
    		}
    	} 
    	else 
    	{
    		list.addFirst(new Node<>(key, val));
    		size++;
    	}
    	return temp;

	}

	/**
	 * remove
	 * Removes the mapping for the specified
	 * key from this map if present
	 * @param key
	 * @return removedValue
	 */
	public V remove(K key)
	{
		V removedValue = null;

		if (containsKey(key))
		{
			LinkedList<Node<K, V>> list = getList(key);
			int index = list.indexOf(new Node<>(key, get(key)));
			removedValue = list.get(index).getValue();
			list.remove(index);
			size--;
		}
		return removedValue;
	}

	/**
	 * size
	 * Returns the number of key-value
	 * mappings in this map
	 * @return size
	 */
	public int size() 
	{
		if (isEmpty())
			return size = 0;
		else
			return size;	
	}

	private LinkedList<Node<K, V>> getList(K key) 
	{
		int index = Math.abs(key.hashCode() % hashTable.size());
		return hashTable.get(index);
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(hashTable, size);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof MyHashMap))
			return false;
		MyHashMap other = (MyHashMap) obj;
		return Objects.equals(hashTable, other.hashTable) && size == other.size;
	}
}