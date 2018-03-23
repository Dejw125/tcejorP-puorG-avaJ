package Simulator;

import java.util.HashSet;

public class Order {
	
	private int packingTime;
	private HashSet<String> shelfUIDs;
	private String status;
	
	public Order(int pt, HashSet<String> SUIDs) {
		packingTime = pt;
		shelfUIDs = SUIDs;
		status = "unassigned";
	}
	
	public String getStatus() {
		return status;
	}
	
	/**
	 * Updates the status of the order based on if:
	 * 1) a packing station has the order
	 * 2) the packing station has all of the items
	 * 3) the packing station has dispatched the order
	 */
	
	public void assignOrder() {
		status = "assigned";
	}
	
	public HashSet<String> requiredItems(){
		return shelfUIDs;
	}
	
	/**
	 * 
	 * @return true if item has finished, false if it still needs packing time
	 */
	public boolean progress() {
		packingTime--;
		if (packingTime == 0) {
			status = "dispatched";
			return true;
		}
		return false;
	}
	
	public void itemGained(String collectedItem) {
		shelfUIDs.remove(collectedItem);
	}
}
