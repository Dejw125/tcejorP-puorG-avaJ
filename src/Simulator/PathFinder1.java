package Simulator;

import java.util.ArrayList;
import java.util.List;


/**
 * Looks left, right, up then down
 * 
 * @author lewisna
 *
 */
public class PathFinder1 implements PathFinder {
	
	private Location rLoc;
	
	public PathFinder1() {
		
	}
	
	@Override
	public List<Location> getOptimalPath(List<Location> targetLocations, Grid grid) {
		List<Location> path = new ArrayList<Location>();
		return path;
	}
	
	public List<Location> getOptimalPath(Location robotLoc, Location dLoc, Grid grid){
		List<Location> path = new ArrayList<Location>();
		this.rLoc = new Location(robotLoc.getX(), robotLoc.getY()); 
		while(!reachedEnd(rLoc, dLoc)){
			if (rLoc.getX() < dLoc.getX()){
				//move right
				rLoc.updateLocation(rLoc.getX() + 1, rLoc.getY());
				path.add(new Location(rLoc.getX(), rLoc.getY()));
			} else if(rLoc.getX() > dLoc.getX()){
				//move left
				rLoc.updateLocation(rLoc.getX() - 1, rLoc.getY());
				path.add(new Location(rLoc.getX() , rLoc.getY()));
			} else if(rLoc.getY() < dLoc.getY()){
				//move down
				rLoc.updateLocation(rLoc.getX(), rLoc.getY() + 1);
				path.add(new Location(rLoc.getX(), rLoc.getY() ));
			} else {
				//move up
				rLoc.updateLocation(rLoc.getX(), rLoc.getY() - 1);
				path.add(new Location(rLoc.getX(), rLoc.getY()));
			}
		}
		return path;
		
	}
	
	private boolean reachedEnd(Location loc1, Location loc2){
		if (loc1.sameLocation(loc2)){
			return true;
		}
		return false;
	}
	
	

}
