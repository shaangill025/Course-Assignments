import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterator<T>{
	private QueueNode<T> front, rear, currentNode;
	private int numItems;
	public LinkedQueue() {
		this.front = this.rear = null;
		this.numItems = 0;
	}
	
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
	
	public int getNumItems() {
		return numItems;
	}

	
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
	
	public boolean isEmpty(){
		return numItems == 0; // return front == null;
	}
	
	public String toString(){
		String str = "";
		QueueNode<T> cursor = front;
		while(cursor != null){
			str += cursor.getInfo().toString() + "  ";
			cursor = cursor.getLink();
		}
		return str;
	}

	public Iterator<T> getIterator(){
		currentNode = front;
		return this;
	}
	
	@Override
	public boolean hasNext() {
		return currentNode != null;
	}

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
