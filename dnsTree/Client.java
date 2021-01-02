
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/** 
 * 
 * @author Batuhan Tongarlak
 * Class of Client
 */
public class Client {
/** 
 * Nested class for cached contents
 * @author lenovo
 *
 */
	private class CachedContent {
		String domainName;
		String ipAddress;
		int hitNo;
		/**
		 * Constructor of cachedcontent
		 * @param d name of the address
		 * @param ip ip of the address
		 */
		private CachedContent(String d, String ip) {
			this.domainName = d;
			this.ipAddress = ip;
			this.hitNo = 0;
		}
	}
/** 
 * getter method for this client's cache list
 * @return this client's cache list
 */
	public CachedContent[] getCache() {
		return this.cacheList;
	}

	DnsTree root;
	String ipAddress;
	public CachedContent[] cacheList;
	Queue<String> stringqueue = new LinkedList<String>();

	/** 
	 * Constructor of Client  
	 * @param ipAddress unique address of a client
	 * @param root tree of DNS
	 */
	public Client(String ipAddress, DnsTree root) {
		this.cacheList = new CachedContent[10];
		this.ipAddress = ipAddress;
		this.root = root;
	}
/** 
 * by this method, client can reach the ip address of a domain name 
 * @param domainName domain name ip address wanted from
 * @return the ip address of the given domain name
 */
	public String sendRequest(String domainName) {
		int c = 0;
		String result = null;
		// part of cache having the domain name
		for (CachedContent i : this.cacheList) {
			if (i != null) {
				if (i.domainName.equals(domainName)) {
					result = i.ipAddress;
					i.hitNo++;
					c = 1;
				}
			}
		}
		// part of cache does not have the domain name
		if (c == 0) {
			if (this.root.getAllRecords().containsKey(domainName)) {
				result = this.root.getNextIp(domainName);
				this.addToCache(domainName, result);
			}

		}
		return result;

	}
/** 
 * it adds the domain name and the ip address to cache, if it is full, it replaces the new content with the one has minimum hit number
 * @param domainName domain name of the address wanted to be added
 * @param ipAddress ip address of the address wanted to be added
 */
	public void addToCache(String domainName, String ipAddress) {
		int take = 0;
		int x = 0;
		for (CachedContent i : this.cacheList) {
			if (i == null) {
				x = 1;
			}
		}
		if (x == 1) {
			//finding the index of empty cache
			for (int i = 0; i < this.cacheList.length; i++) {
				if (this.cacheList[i] == null) {
						take=i;
					break;
				}
			}
				if (this.root.getAllRecords().containsKey(domainName)) {
					CachedContent newc = new CachedContent(domainName, ipAddress);
					newc.hitNo++;
					this.cacheList[take] = newc;
				}
		} else {
			//the case of not having empty cache
			for (int i = 0; i < this.cacheList.length; i++) {
				if (this.cacheList[i].domainName.equals(this.findMinHit().domainName)) {
					CachedContent newc = new CachedContent(domainName, ipAddress);
					newc.hitNo++;
					this.cacheList[i] = newc;
					break;
				}
			}

		}

	}
/** 
 * it finds the content which has the least number of hit
 * @return content has the minimum hit number
 */
	private CachedContent findMinHit() {
		CachedContent temp = this.cacheList[0];
		for (int i = 0; i < this.cacheList.length; i++) {
			if (this.cacheList[i].hitNo < temp.hitNo) {
				temp = this.cacheList[i];
			}
		}
		return temp;
	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
