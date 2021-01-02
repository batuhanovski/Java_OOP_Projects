
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package boxes;

import java.util.LinkedList;
import java.util.Queue;
import elements.*;

public class Outbox extends Box{
	
	public Queue<Message> sent = new LinkedList<Message>();
	public Outbox(User owner){
		super(owner);
	}
	public void addToSent(Message x) {
		sent.offer(x);
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

