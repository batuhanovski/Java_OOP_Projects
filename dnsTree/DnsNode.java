
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/** 
 * class of DNS nodes
 * @author Batuhan Tongarlak
 *
 */
public class DnsNode {

	Map<String, DnsNode> childNodeList;
	boolean validDomain;
	Set<String> ipAddresses;
	String fullDomainName;
	DnsNode motherNode;
	String singleDomainName;
	Iterator<String> ipItr;
/** 
 * constructor of DNS nodes
 */
	public DnsNode() {
		this.childNodeList = new HashMap<String, DnsNode>();
		this.ipAddresses = new HashSet<String>();
		this.validDomain = false;
		this.fullDomainName = null;
		ipItr = this.ipAddresses.iterator();
	}
/** 
 * it changes node's validity
 * @param i negative of positive number using the specifying the new validity
 */
	public void changeVDomain(int i) {
		if (i > 0) {
			this.validDomain = true;
		}
		if (i < 0) {
			this.validDomain = false;
		}
	}
/** 
 * used for getting the child nodes of a node
 * @return the map of child nodes list
 */
	public Map<String, DnsNode> getchildNodeList() {
		return this.childNodeList;
	}
/** 
 * it used in query domain method, it provides the process of round robin
 * @return the next ip address
 */
	public String getIp() {
		if(!this.ipAddresses.isEmpty()) {
		if (this.ipItr.hasNext()) {	 
		} else {
			this.ipItr= this.ipAddresses.iterator();
		}
		return this.ipItr.next();
		}
		else return null;
	}
/** 
 * it gets the full name of domain of a node
 * @return full domain name of a node
 */
	public String getFulldomain() {
		return this.fullDomainName;
	}
/** 
 * getter method for full domain names
 * @return full domain name of a node
 */
	public String getFullDomainName() {
		return fullDomainName;
	}

}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
