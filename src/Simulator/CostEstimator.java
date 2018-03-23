package Simulator;

import java.util.ArrayList;

public interface CostEstimator {
	
	public abstract int estimateCost(ArrayList<Location> locs);
	
}
