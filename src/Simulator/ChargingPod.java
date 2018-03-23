package Simulator;

import java.util.List;

public class ChargingPod extends Entity {
	
	private int chargeSpeed;
	private String pairedRobotID;
	
	
	public ChargingPod(String ID, Location loc, int charge, String RID) {
		super(ID, loc);
		chargeSpeed = charge;
		pairedRobotID = RID;
	}

	@Override
	public void act(Grid grid, List<Order> orders) {
		if (grid.getEntityID(location)[1].equals(pairedRobotID)) {
			grid.chargeRobot(location, chargeSpeed);
		}
		// TODO Auto-generated method stub (charge robot)
		
	}

}
