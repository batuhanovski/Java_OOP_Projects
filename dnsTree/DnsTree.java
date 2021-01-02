
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author Batuhan Tongarlak class of DNS trees
 */
public class DnsTree {
	DnsNode root;
	Map<String, Queue<String>> domainIpMap;
/** 
 * constructor of the DNS trees
 */
	public DnsTree() {

		this.root = new DnsNode();
		this.domainIpMap = new HashMap<String, Queue<String>>();

	}

	Stack<String> nodeNamesStack = new Stack<String>();
/** 
 * used for inserting nodes into the tree
 * @param domainName domain name of a node 
 * @param ipAddress ip address of a node
 */
	public void insertRecord(String domainName, String ipAddress) {
		if (this.domainIpMap.containsKey(domainName)) {
			this.domainIpMap.get(domainName).add(ipAddress);
		} else {
			Queue<String> queue = new LinkedList<>();
			queue.add(ipAddress);
			this.domainIpMap.put(domainName, queue);
		}
		this.nodeNamesStack.clear();
		String[] parts = domainName.split("\\.");
		for (String i : parts) {
			this.nodeNamesStack.push(i);
		}
		recursive1(this.root, this.nodeNamesStack, ipAddress, domainName);
	}
/** 
 * used to construct a recursive algorithm for inserting record
 * @param rootx given node 
 * @param stack the stack of Strings between every "." in the domain name
 * @param ipAddress given ip address of the domain name
 * @param domainName the full domain name
 */
	private void recursive1(DnsNode rootx, Stack<String> stack, String ipAddress, String domainName) {
		if (!stack.isEmpty()) {
			String a = stack.pop();
			if (rootx.childNodeList.containsKey(a)) {
				if (!stack.isEmpty()) {
					recursive1(rootx.childNodeList.get(a), stack, ipAddress, domainName);
				} else
					rootx.childNodeList.get(a).ipAddresses.add(ipAddress);
			} else {
				if (stack.isEmpty()) {
					DnsNode create = new DnsNode();
					create.ipAddresses.add(ipAddress);
					create.validDomain = true;
					rootx.childNodeList.put(a, create);
					recursive1(rootx.childNodeList.get(a), stack, ipAddress, domainName);
				} else {
					DnsNode create = new DnsNode();
					rootx.childNodeList.put(a, create);
					recursive1(rootx.childNodeList.get(a), stack, ipAddress, domainName);
				}
			}
		} else
			rootx.fullDomainName = domainName;
		;
	}
/** 
 * used to remove of change the validity of a domain of a node
 * @param domainName the full domain name of an address
 * @return whether the remove progress is successful or not
 */
	public boolean removeRecord(String domainName) {

		if (this.domainIpMap.containsKey(domainName)) {
			this.domainIpMap.remove(domainName);
		}
		this.nodeNamesStack.clear();
		String[] parts = domainName.split("\\.");
		for (String i : parts) {
			this.nodeNamesStack.push(i);
		}
		return recursive2(this.nodeNamesStack, this.root);

	}
/** 
 * used to construct a recursive algorithm for removing records
 * @param stack the stack of Strings between every "." in the domain name
 * @param rootx given node
 * @return whether the remove progress is successful or not
 */
	private boolean recursive2(Stack<String> stack, DnsNode rootx) {
		String a = stack.pop();
		if (rootx.childNodeList.containsKey(a)) {
			if (!stack.isEmpty()) {
				return false || recursive2(stack, rootx.childNodeList.get(a));
			} else {
				// buraya girmesi icin stack in son elemani olmasi lazim
				if (rootx.childNodeList.get(a).childNodeList.keySet().isEmpty()) {
					rootx.childNodeList.remove(a);
					return true;
				} else if (rootx.childNodeList.get(a).validDomain == false) {
					return false;
				} else {
					rootx.childNodeList.get(a).changeVDomain(-1);
					return true;
				}
			}
		} else
			return false;

	}
/** 
 * used for removing a specific ip address from a node
 * @param domainName the full domain name of an address
 * @param ipAddress the specific ip adddress of the domain
 * @return whether the remove progress is successful or not
 */
	public boolean removeRecord(String domainName, String ipAddress) {

		if (this.domainIpMap.containsKey(domainName)) {
			this.domainIpMap.get(domainName).remove(ipAddress);
		}
		this.nodeNamesStack.clear();
		String[] parts = domainName.split("\\.");
		for (String i : parts) {
			this.nodeNamesStack.push(i);
		}
		return recursive3(this.nodeNamesStack, this.root, ipAddress);

	}
/** 
 * used to construct a recursive algorithm for removing records
 * @param stack the stack of Strings between every "." in the domain name
 * @param rootx given node
 * @param ipAddress the specific ip address wanted to be removed
 * @return whether the remove progress is successful or not
 */
	private boolean recursive3(Stack<String> stack, DnsNode rootx, String ipAddress) {
		String a = stack.pop();
		if (rootx.childNodeList.containsKey(a)) {
			if (!stack.isEmpty()) {
				return false || recursive3(stack, rootx.childNodeList.get(a), ipAddress);
			} else {// stack in son elemani
				if (rootx.childNodeList.get(a).childNodeList.isEmpty()) {
					if (rootx.childNodeList.get(a).ipAddresses.size() > 1) {
						rootx.childNodeList.get(a).ipAddresses.remove(ipAddress);
						return true;
					} else {
						rootx.childNodeList.remove(a);
						return true;
					}
				} else {
					if (rootx.childNodeList.get(a).ipAddresses.size() > 1) {
						rootx.childNodeList.get(a).ipAddresses.remove(ipAddress);
						return true;
					} else {
						rootx.childNodeList.get(a).ipAddresses.remove(ipAddress);
						rootx.childNodeList.get(a).changeVDomain(-1);
						return true;
					}
				}
			}
		} else
			return false;
	}
/** 
 * it gets the next ip address of the domain
 * @param domainName the domain name of the address
 * @return the next ip address of the domain
 */
	public String queryDomain(String domainName) {
		this.nodeNamesStack.clear();
		String[] parts = domainName.split("\\.");
		for (String i : parts) {
			this.nodeNamesStack.push(i);
		}
		return recursive4(this.nodeNamesStack, this.root, domainName);
	}
/** 
 * used to construct a recursive algorithm for querying records
 * @param stack the stack of Strings between every "." in the domain name
 * @param rootx given node
 * @param domainName the domain name of the address
 * @return the next ip address of the domain
 */
	private String recursive4(Stack<String> stack, DnsNode rootx, String domainName) {
		String s = "";
		String a = stack.pop();
		if (rootx.childNodeList.containsKey(a)) {
			if (!stack.isEmpty()) {
				return s + recursive4(stack, rootx.childNodeList.get(a), domainName);
			} else {
				return s = rootx.childNodeList.get(a).getIp();
			}
		} else
			;
		if (s != "") {
			return s;
		} else
			return null;
	}
/** 
 * used to get all the valid records in the DNS tree
 * @return the map of the valid domain names and their ip addresses
 */
	public Map<String, Set<String>> getAllRecords() {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		recursive5(this.root, map);
		map.remove(null);
		return map;
	}
/** 
 * used to construct a recursive algorithm for getting valid records
 * @param rootx given node
 * @param map the map of the records
 */
	private void recursive5(DnsNode rootx, Map<String, Set<String>> map) {
		if (!rootx.childNodeList.isEmpty()) {
			for (DnsNode i : rootx.childNodeList.values()) {
				if (i.validDomain) {
					map.put(i.fullDomainName, i.ipAddresses);
				}
				recursive5(i, map);
			}
		} else
			;
	}
/** 
 * used in send request method to give next ip address of a domain according to round robin
 * @param domainName the domain name of the address
 * @return next ip address of a domain
 */
	public String getNextIp(String domainName) {
		String s = null;
		if (this.domainIpMap.containsKey(domainName)) {
			s = this.domainIpMap.get(domainName).poll();
			this.domainIpMap.get(domainName).add(s);
		}
		return s;
	}
/** 
 * getter for the root of the tree
 * @return root of the tree
 */
	public DnsNode getRoot() {
		return this.root;
	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
