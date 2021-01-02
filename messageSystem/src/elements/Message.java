
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package elements;

public class Message{
	static int numOfMessages = 0;
	int id;
	String body;
	User sender;
	User receiver;
	int timeStampSent;
	int timeStampRead;
	int timeStampReceived;
	
	public Message(User sender,User receiver,String body,Server server,int time) {
		this.sender=sender;
		this.receiver=receiver;
		this.body=body.substring(1, body.length());
		this.id=numOfMessages;
		numOfMessages++;
	}

	public void setTimeStampSent(int timeStampSent) {
		this.timeStampSent = timeStampSent;
	}

	public void setTimeStampRead(int timeStampRead) {
		this.timeStampRead = timeStampRead;
	}

	public void setTimeStampReceived(int timeStampReceived) {
		this.timeStampReceived = timeStampReceived;
	}
	public int getId(){
		return this.id;
	}
	public String getBody() {
		return this.body;
	}
	public int compareTo(Message o) {
		if(this.body.length()>o.body.length()) {
			return 1;
		}
		if(this.body.length()<o.body.length()) {
			return -1;
		}
		else 
			return 0;
	}
	public boolean equals(Object o) {
		if(o instanceof Message) {
			Message newm = (Message) o;
			if(this.id==newm.id ) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	public int getSenderID() {
		return this.sender.id;
	}
	public User getSender() {
		return this.sender;
	}
	public int getReceiverID(){
		return this.receiver.id;
	}
	public String toString() {
		String s = "\tFrom:"+this.sender.id+" to:"+this.receiver.id+"\n \tReceived:"+this.timeStampReceived+" Read:"
				+this.timeStampRead+"\n\t"+this.body;
				
		return s;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

