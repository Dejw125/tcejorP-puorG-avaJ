package Simulator;

import java.util.List;

public abstract class Entity {
	protected String UID;
	protected Location location;
	
	
	public Entity(String ID, Location loc) {
		UID = ID;
		location = loc;
				
	}
	
	public abstract void act(Grid grid, List<Order> orders);
	
	public String getUID() {
		return UID;
	}
	
	public Location getLocation() {
		return location;
	}
	
	
	
}
