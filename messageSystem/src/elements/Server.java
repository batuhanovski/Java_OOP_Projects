
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
	long capacity;
	long currentSize = 0;
	public Queue<Message> msgs = new LinkedList<Message>();
	public Iterator<Message> serveritr = msgs.iterator();
	static int check;
	static int temp;

	public Server(long capacity) {
		this.capacity = capacity;
		check = 0;
	}

	public void checkServerLoad(PrintStream printer) {
		temp = check;
		if (0.5 * this.capacity > this.currentSize) {
			check = 0;
		}
		if (0.8 * this.capacity > this.currentSize && this.currentSize >= this.capacity * 0.5) {
			check = 1;
		}
		if (0.8 * this.capacity <= this.currentSize && this.capacity > this.currentSize) {
			check = 2;
		}
		if (this.capacity <= this.currentSize) {
			check = 3;
		} else
			;
		if (check != temp) {
			if (check == 1) {
			printer.println("Warning! Server is 50% full.");
			}
			if (check == 2) {
			printer.println("Warning! Server is 80% full.");
			}
			if (check == 3) {	
			printer.println("Server is full. Deleting all messages...");
			this.flush();
			} else;
		}
			else;	
		

	}

	public long getCurrentSize() {
		return this.currentSize;
	}

	public void subtractCurrentSize(int x) {
		this.currentSize = this.currentSize - x;
	}

	public void flush() {
		this.msgs.clear();
		this.currentSize = 0;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
