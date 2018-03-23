package Simulator;

public class Location {
	
	private int x;
	private int y;
	
	Location(int x, int y){
		updateLocation(x, y);	
	}
	
	public void updateLocation(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean sameLocation(Location loc) {
		if (loc == null) {
			return false;
		} else if (loc.getX() == x && loc.getY() == y) {
			return true;
		}
		return false;
	}
}
