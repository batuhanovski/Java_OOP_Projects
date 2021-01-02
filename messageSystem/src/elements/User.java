
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;

import java.util.ArrayList;
import java.util.Iterator;
import boxes.*;
import elements.*;
import executable.*;

public class User {

	int id;
	public Inbox inbox;
	public Outbox outbox;
	public ArrayList<User> friends = new ArrayList<User>();
	public Iterator<User> itr = friends.iterator();

	public User(int id) {
		this.id = id;
		this.outbox = new Outbox(this);
		this.inbox = new Inbox(this);
	}

	public void addFriend(User other) {

		if (this.isFriendsWith(other)) {
		} else
			this.friends.add(other);
		other.friends.add(this); // þunu deðiþtridin dikkat et ayný kiþi olmsaýna dikkat et

	}

	public void removeFriend(User other) {
		if (this.isFriendsWith(other)) {
			this.friends.remove(other);
			other.friends.remove(this);
		} else
			;
	}

	public boolean isFriendsWith(User other) {
		if (this.friends.contains(other)) {
			return true;
		} else
			return false;
	}

	public int getID() {
		return this.id;
	}

	public void sendMessage(User receiver, String body, int time, Server server) {

		Message newm = new Message(this, receiver, body, server, time);
		newm.setTimeStampSent(time);
		server.msgs.offer(newm);
		this.outbox.addToSent(newm);
		server.currentSize += newm.body.length();
	}

	public boolean equals(User u) {
		if (this.id == u.id) {
			return true;
		} else
			return false;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
