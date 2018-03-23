package Simulator;

import java.util.List;

public class Shelf extends Entity {

	
	
	public Shelf(String ID, Location loc) {
		super(ID, loc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act(Grid grid,  List<Order> orders) {
		Robot robot = grid.getRobot(location);
		if (robot != null) {
			robot.pickUpItem(UID);
		}
	}

	
	
}
