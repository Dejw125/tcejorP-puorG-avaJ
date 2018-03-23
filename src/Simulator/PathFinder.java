package Simulator;

import java.util.List;

public interface PathFinder {
	
	public abstract List<Location> getOptimalPath(List<Location> targets, Grid grid);
	public abstract List<Location> getOptimalPath(Location rLoc, Location dLoc, Grid grid);
	
}
