
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import elements.*;

public class Inbox extends Box {

	public Stack<Message> unread; 
	Queue<Message> read = new LinkedList<Message>();
	Queue<Message> dirt;

	public Inbox(User owner) {
		super(owner);
		unread = new Stack<>();
		dirt = new LinkedList<Message>();
	}

	public void receiveMessages(Server server, int time) {
		int m = server.msgs.size();
		for (int i = 0; i < m; i++) {
			Message e = server.msgs.poll();
			if (e.getSender().isFriendsWith(this.owner) && e.getReceiverID() == this.owner.getID()) {
				e.setTimeStampReceived(time);
				this.unread.push(e);
				server.subtractCurrentSize(e.getBody().length());
			} else {
				server.msgs.offer(e);
			}
		}
	}

	public int readMessages(int num, int time) { // Checked
		int check = 0;
		if (num != 0) {
			for (int i = 0; i < num; i++) {
				if (!this.unread.empty()) {
					Message x = this.unread.pop();
					x.setTimeStampRead(time + check);
					this.read.add(x);
					check++;
				}
			}
			return check;
		} else {
			for (int i = 0; i < this.unread.size(); i++) {
				Message x = this.unread.pop();
				x.setTimeStampRead(time + check);
				this.read.add(x);
				check++;
			}

			return check;
		}
	}

	public int readMessages(User sender, int time) {
		int check = 0;
		int m = this.unread.size();
		for (int i = 0; i < m; i++) {	
				Message x = this.unread.pop();
				if(x.getSenderID()==sender.getID()) {
					x.setTimeStampRead(time + check);
					this.read.add(x);
					check++;
				}
				else
					this.dirt.add(x);
		}
			while(!this.dirt.isEmpty()) {
				this.unread.push(getLastDirt());
			}
		return check;
	}

	public void readMessage(int msgId, int time) {
		for (int i = 0; i < this.unread.size(); i++) {
			if (this.unread.get(i).getId() == msgId) {
				Message x = this.unread.pop();
				x.setTimeStampRead(time);
				this.read.add(x);
			}
		}
	}

	public int getMessageNum() {
		return this.unread.capacity();
	}

	public Queue<Message> getReadQueue() {
		return this.read;
	}

	public Message getLastRead() {
		for (int i = 0; i < this.read.size() - 1; i++) {
			Message e = read.poll();
			read.add(e);
		}
		Message temp = read.poll();
		read.add(temp);
		return temp;
	}
		private Message getLastDirt() {
			for (int i = 0; i < this.dirt.size() - 1; i++) {
				Message e = dirt.poll();
				dirt.add(e);
		}
		Message temp = dirt.poll();
		dirt.add(temp);
		return temp;
	}
	
		
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
