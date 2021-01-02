//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

	public class Main {
		public static void main(String[] args) {
				
				DnsTree tree = new DnsTree();
				Client client1 = new Client("1",tree);
				Client client2 = new Client("2",tree);
				
				System.out.println(tree.getRoot().getchildNodeList());
				
				
				client1.sendRequest("google.com");	
				System.out.println(client1.sendRequest("google.com"));
				client2.sendRequest("mail.google.com");
				tree.insertRecord("google.com", "1.1.1.1");
				tree.insertRecord("google.com", "1.1.1.2");
				client1.sendRequest("google.com");	
				tree.insertRecord("google.com", "1.1.1.1");
				tree.insertRecord("mail.google.com", "2.2.2.2");
				tree.insertRecord("mail.google.com", "3.3.3.3");
				tree.insertRecord("google.com", "1.1.1.1");
				System.out.println(client1.sendRequest("google.com"));
				tree.queryDomain("mail.google.com");
				System.out.println(tree.getAllRecords());
				tree.insertRecord("bbc.co.uk","7.7.7.7");
				tree.insertRecord("cambridge.ac.uk","8.8.8.8");
				tree.insertRecord("bbc.co.uk","7.7.7.0");
				tree.insertRecord("bbc.co.uk","7.7.7.1");
				tree.insertRecord("bbc.co.uk","7.7.7.2");
				client1.sendRequest("bbc.co.uk");
				client1.sendRequest("bbc.co.uk");
				client2.sendRequest("bbc.co.uk");
				tree.getAllRecords();
				tree.queryDomain("bbc.co.uk");
				
				tree.removeRecord("bbc.co.uk.", "7.7.7.7");
				tree.getAllRecords();
				System.out.println(client1.sendRequest("bbc.co.uk"));
				System.out.println(tree.getAllRecords());
				tree.insertRecord("twitter.com", "5.5.5.5");
				tree.insertRecord("developer.twitter.com", "6.6.6.6");
				System.out.println(tree.removeRecord("twitter.com"));
				System.out.println(tree.removeRecord("twitter.com"));
				tree.insertRecord("boun.edu.tr", "1.1.1.1");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("bbc.co.uk");
				client1.sendRequest("bbc.co.uk");
				client1.sendRequest("cmpe.boun.edu.tr");
				tree.insertRecord("cmpe.boun.edu.tr","2.2.2.2");
				client1.sendRequest("boun.edu.tr");
				System.out.println(tree.removeRecord("com"));
				System.out.println(tree.removeRecord("twitter.com"));
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("cmpe.boun.edu.tr");
				client1.sendRequest("cmpe.boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("cmpe.boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("boun.edu.tr");
				client1.sendRequest("twitter.com");
				client1.sendRequest("developer.twitter.com");
				client1.sendRequest("cmpe.boun.edu.tr");
				client1.sendRequest("x");
				client1.sendRequest("y");
				client1.sendRequest("z");
				tree.insertRecord("x", "0.0.0.0");
				tree.insertRecord("y", "1.0.0.0");
				tree.insertRecord("y", "2.0.0.0");
				tree.insertRecord("y", "3.0.0.0");
				tree.insertRecord("y", "4.0.0.0");
				tree.insertRecord("x", "0.0.0.1");
				tree.insertRecord("z", "0.8.0.0");
				tree.insertRecord("z", "0.0.8.0");
				client1.sendRequest("x");
				client1.sendRequest("y");
				client1.sendRequest("z");
				client1.sendRequest("x");
				client1.sendRequest("y");
				client1.sendRequest("z");
				client1.sendRequest("mail.google.com");
				tree.removeRecord("twitter.com","6.6.6.6");
				tree.removeRecord("twitter.com","5.5.5.5");
				client1.sendRequest("cambridge.ac.uk");
				client1.sendRequest("cambridge.ac.uk");
				client1.sendRequest("cambridge.ac.uk");
				client1.sendRequest("x");
				tree.removeRecord("co.uk");
				tree.removeRecord("co.uk","1");
				tree.insertRecord("new.com", "9999.999.99.hamzaningotu");
				client1.sendRequest("new.com");
			
		}
	}



	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE