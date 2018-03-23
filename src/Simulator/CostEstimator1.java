package Simulator;

import java.util.ArrayList;

public class CostEstimator1 implements CostEstimator {

	public CostEstimator1() {
		
	}
	
	@Override
	public int estimateCost(ArrayList<Location> locs) {
		int total = 0;
		Location previousLoc = locs.get(0);
		Location firstLoc = locs.get(0);
		locs.remove(0);
		for(Location loc : locs) {
			total += Math.abs(previousLoc.getX() - loc.getX());
			total += Math.abs(previousLoc.getY() - loc.getY());
			previousLoc = loc;
		}
		total *= 1.2;
		locs.add(0, firstLoc);
		return total;
	}

}
