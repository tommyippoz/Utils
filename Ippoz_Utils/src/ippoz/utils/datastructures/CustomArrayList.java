/**
 * 
 */
package ippoz.utils.datastructures;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Tommy
 *
 */
public class CustomArrayList<T> implements java.lang.Iterable<T> {
	
	private T[] list;
	
	private int head;
	private int tail;
	
	private int currentSize;
	private int size;
	
	@SuppressWarnings("unchecked")
	public CustomArrayList(int size){
		this.size = size;
		list = (T[]) new Object[size];
		currentSize = 0;
		head = -1;
		tail = -1;
	}
	
	public CustomArrayList(double doubleSize) {
		this((int)doubleSize);
	}

	public int size(){
		return currentSize;
	}
	
	public T get(int index){
		if(!isEmpty() && index >= 0 && index < size()){
			return list[(head + index) % size];
		} else return null;
	}
	
	public boolean isFull(){
		return currentSize == size;
	}
	
	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	public void add(T item){
		if(!isFull()){
			tail = (tail + 1) % size;
			list[tail] = item;
			if(head == -1)
				head = 0;
			currentSize++;
		}
	}
	
	public T getLast(){
		T tailElement = null;
		if(!isEmpty()){
			tailElement = list[tail];
		}
		return tailElement;
	}
	
	public T removeLast(){
		T tailElement = null;
		if(!isEmpty()){
			tailElement = list[tail];
			tail = (tail - 1) % size;
			currentSize--;
		}
		return tailElement;
	}
	
	public T getFirst(){
		T headElement = null;
		if(!isEmpty()){
			headElement = list[head];
		}
		return headElement;
	}
	
	public T removeFirst(){
		T headElement = null;
		if(!isEmpty()){
			headElement = list[head];
			head = (head + 1) % size;
			currentSize--;
		}
		return headElement;
	}

	@Override
	public Iterator<T> iterator() {
		return Arrays.asList(list).iterator();
	}

}
