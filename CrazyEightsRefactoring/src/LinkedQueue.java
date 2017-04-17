import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedQueue.
 *
 * @param <T> the generic type
 */
public class LinkedQueue<T> implements Iterator<T>{
	
	/** The current node. */
	private QueueNode<T> front, rear, currentNode;
	
	/** The num items. */
	private int numItems;
	
	/**
	 * Instantiates a new linked queue.
	 */
	public LinkedQueue() {
		this.front = this.rear = null;
		this.numItems = 0;
	}
	
	/**
	 * Enqueue.
	 *
	 * @param item the item
	 */
	public void enqueue(T item){
		QueueNode<T> newNode = new QueueNode<T>(item);
		if(rear == null){
			front = newNode;
		}
		else{
			rear.setLink(newNode);			
		}
		rear = newNode;
		numItems++;
	}
	
	/**
	 * Gets the num items.
	 *
	 * @return the num items
	 */
	public int getNumItems() {
		return numItems;
	}

	
	/**
	 * Dequeue.
	 *
	 * @return the t
	 */
	public T dequeue(){
		T toReturn = null;
		if(front != null){
			toReturn = front.getInfo();
			front = front.getLink();
			numItems--;
			if(front == null) rear = null;
		}
		return toReturn;
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty(){
		return numItems == 0; // return front == null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = "";
		QueueNode<T> cursor = front;
		while(cursor != null){
			str += cursor.getInfo().toString() + "  ";
			cursor = cursor.getLink();
		}
		return str;
	}

	/**
	 * Gets the iterator.
	 *
	 * @return the iterator
	 */
	public Iterator<T> getIterator(){
		currentNode = front;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return currentNode != null;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		T savedInfo = null;
		if(!hasNext()) throw new NoSuchElementException();
		if(currentNode != null){
			savedInfo =  currentNode.getInfo();
			currentNode = currentNode.getLink();
		}		
		return savedInfo;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		//dequeue();	
		throw new UnsupportedOperationException(); 
	}
}

class QueueNode<T>{
	private T info;
	private QueueNode<T> link;
	
	public QueueNode(T item){
		info = item;
		link = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public QueueNode<T> getLink() {
		return link;
	}

	public void setLink(QueueNode<T> link) {
		this.link = link;
	}	
}
