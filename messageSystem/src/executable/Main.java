
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import elements.*;
import boxes.*;
		
public class Main{
	public static PrintStream printer;
	public static void main(String[] args) throws FileNotFoundException {
		int time=0;
		Scanner console = new Scanner(new File("input5.txt"));
		printer = new PrintStream(new File("output5.txt"));
		int A = console.nextInt();
		int B = console.nextInt();
		long C = console.nextLong();
			Server server = new Server(C);
			User person[] = new User[A];
			for(int i=0;i<A;i++) {
				person[i]= new User(i);
			}
		
		for(int k=0;k<B;k++) {	
			int operation = console.nextInt();
		if(operation==0) {
			int sid =console.nextInt();
			int rid =console.nextInt();
			String body =console.nextLine();
				person[sid].sendMessage(person[rid],body,time,server);
				server.checkServerLoad(printer);
				time++;
		}
		if(operation==1) {
			int rid =console.nextInt();
				person[rid].inbox.receiveMessages(server, time);
				server.checkServerLoad(printer);
				time++;
		}
		if(operation==2) {
			int rid =console.nextInt();
			int msgnum =console.nextInt();
					if(msgnum==0) {		
			time+=person[rid].inbox.readMessages(person[rid].inbox.getMessageNum(), time);
						}
					else
			time+=person[rid].inbox.readMessages(msgnum, time);
					}
		if(operation==21) {
			int rid =console.nextInt();
			int sid =console.nextInt();
				time+=person[rid].inbox.readMessages(person[sid], time);				
		}
		if(operation==22) {
			int rid =console.nextInt();
			int msgid =console.nextInt();
			person[rid].inbox.readMessage(msgid, time);
			time++;
		}
		if(3==operation) {
			int id1 =console.nextInt();
			int id2 =console.nextInt();
					person[id1].addFriend(person[id2]);		
					time++;
		}
		if(4==operation) {
			int id1 =console.nextInt();
			int id2 =console.nextInt();
					person[id1].removeFriend(person[id2]);
					time++;
		}
		if(5==operation) {
			server.flush();
			time++;
		}
		if(6==operation) {
			printer.println("Current load of the server is "+server.getCurrentSize()+" characters.");
			time++;
		}
		if(61==operation) {
			int id =console.nextInt();		
			if(person[id].inbox.getLastRead()!=null) {
				printer.println(person[id].inbox.getLastRead());
				time++;
			}
		
		}
		}
		//for(int i=0;i<A;i++) {
		System.out.println(person[1].inbox.getReadQueue());
	//}
	}
}
	


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

