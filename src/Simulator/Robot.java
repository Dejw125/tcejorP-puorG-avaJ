package Simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Robot extends Entity {
	
	private int battery;
	private int maxBattery;
	//assigned, tired, ready
	private String status;
	private String chargingPodID;
	private PathFinder pf;
	private CostEstimator ce;
	private List<Location> currentPath;
	private String targetItem;
	private boolean carryingItem;
	private Location assignedPSLocation;
	
	public Robot(String ID, Location loc, String CID, int bat) {
		super(ID, loc);
		chargingPodID = CID;
		pf = new PathFinder1();
		ce = new CostEstimator1();
		battery = bat;
		maxBattery = bat;
	}

	@Override
	public void act(Grid grid, List<Order> orders) {
		
		
		if (targetItem != null ) {
			status = "assigned";
		} 
		if (currentPath == null) {
			currentPath = new ArrayList<Location>();
		}
		
		
		
		if (!currentPath.isEmpty() ) {
			//Has a path, attempt to move
			move(grid);
		} else {
			//work out whether to rest or to deliver the item
			if (carryingItem) {
				currentPath = pf.getOptimalPath(location, assignedPSLocation, grid);
			} else { //not carrying an Item and unassigned, rest
				//attempt to rest
				currentPath = pf.getOptimalPath(location, grid.getLocationOfEntity(chargingPodID), grid);
			}
			
		}
		if (battery < maxBattery/2 ) {
			status = "tired";
		} 

	}

	public void charge(int power) {
		battery += power;
		if ((battery / maxBattery) == 1) {
			battery = maxBattery;
		}
		if (battery > maxBattery / 2) {
			status = "ready";
		}
	}
	
	

	private void move(Grid grid) {
		Location next = currentPath.get(0);
		if (grid.isLocationFree(next)) {
			//move to location
			Location old = location;
			location = next;
			grid.updateRobotLocation(this, old);
			updateBattery();
			currentPath.remove(0);
		} else {
			//wait/move elsewhere
		}
	}
	
	private void updateBattery() {
		if (carryingItem == true) {
			battery -= 2;
		} else {
			battery--;
		}
	}
	
	public String getStatus() {
		return status;
	}
	
	public boolean takeJob(String SUID, Grid grid, Location pLoc) {
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(location);
		locs.add(grid.getLocationOfEntity(SUID));
		locs.add(pLoc);
		locs.add(grid.getLocationOfEntity(chargingPodID));
		assignedPSLocation = pLoc;
		if (ce.estimateCost(locs) < battery) {
			status = "assigned";
			currentPath = pf.getOptimalPath(location, locs.get(1), grid);
			targetItem = SUID;
			return true;
		}
		return false;
	}
	
	public void pickUpItem(String SID) {
		if (SID.equals(targetItem)) { 
			carryingItem = true;
		}
	}
	
	public String getChargingPodID() {
		return chargingPodID;
	}
	
	public boolean isAlive() {
		if (battery > 0) {
			return true;
		}
		return false;
	}
	
	public boolean collectCarriedItem(String SUID) {
		return SUID.equals(targetItem);
	}
	
	public boolean hasItem(HashSet<String> SUIDs) {
		if (SUIDs.contains(targetItem) && carryingItem) {
			return true;
		}
		return false; 
	}
	
	public String carriedItem() {
		carryingItem = false;
		String item = targetItem;
		targetItem = null;
		status = "ready";
		return item;
	}
	
}







