package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element The element to add
	 */
	public boolean add(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> newNode = new LLNode<>(element);
		newNode.prev = tail.prev;
		newNode.next = tail;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = getNode(index);
		return currentNode.data;
	}

	private LLNode<E> getNode(int index) {
		LLNode<E> currentNode;
		if (index < size / 2) {
			currentNode = head.next;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.next;
			}
		} else {
			currentNode = tail.prev;
			for (int i = size - 1; i > index; i--) {
				currentNode = currentNode.prev;
			}
		}
		return currentNode;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The     index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Null elements are not allowed.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (index == size) {
			add(element); // Call the add(element) method to append the element
		} else {
			LLNode<E> newNode = new LLNode<>(element);
			LLNode<E> currentNode = getNode(index);
			newNode.prev = currentNode.prev;
			newNode.next = currentNode;
			currentNode.prev.next = newNode;
			currentNode.prev = newNode;
			size++;
		}
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = getNode(index);
		currentNode.prev.next = currentNode.next;
		currentNode.next.prev = currentNode.prev;
		size--;
		return currentNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index   The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currentNode = getNode(index);
		E oldValue = currentNode.data;
		currentNode.data = element;
		return oldValue;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
