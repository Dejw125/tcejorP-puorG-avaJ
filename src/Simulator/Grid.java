package Simulator;


public class Grid {
	private int width;
	private int height;
	//[width(x)][height(y)][0 for ground, 1 for robots]
	private Entity[][][] entities;
	
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		entities = new Entity[width][height][2];
	}
	
	/**
	 * 
	 * @param ent Entity being added to the grid
	 * @param loc location of the entity
	 */
	public void placeEntity(Entity ent, Location loc) {
		if (ent.getClass().getName().equals("Simulator.Robot")){
			entities[loc.getX()][loc.getY()][1] = ent;
		} else {
			entities[loc.getX()][loc.getY()][0] = ent;
		} 
	}
	
	/**
	 * 
	 * @param loc location being checked
	 * @return String array length 2, [0] has ground area, [1] has robot layer
	 */
	public String[] getEntityID(Location loc) {
		Entity[] ent = entities[loc.getX()][loc.getY()];
		String[] entIDs = new String[2];
		entIDs[0] = "";
		entIDs[1] = "";
		if (ent[0] != null){
			entIDs[0] = entities[loc.getX()][loc.getY()][0].getUID();
		}
		if (ent[1] != null){
			entIDs[1] = entities[loc.getX()][loc.getY()][1].getUID();
		}
				
		return entIDs;
	}
	
	public void updateRobotLocation(Entity ent, Location oldLoc) {
		Location newLoc = ent.getLocation();
		entities[oldLoc.getX()][oldLoc.getY()][1] = null;
		entities[newLoc.getX()][newLoc.getY()][1] = ent;
	}
	
	public void chargeRobot(Location loc, int power) {
		Robot r = (Robot) entities[loc.getX()][loc.getY()][1];
		r.charge(power);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Location getLocationOfEntity(String UID) {
		for(Entity[][] e : entities) {
			for(Entity[] f : e) {
				for(Entity entity : f) {
					if(entity != null) {
						if(entity.getUID().equals(UID)) {
							return entity.getLocation();
						}
					}
					
				}
			}
		}
		return null;
	}

	public boolean isLocationFree(Location loc) {
		if (entities[loc.getX()][loc.getY()][1] == null) {
			return true;
		}
		return false;
	}
	
	public Robot getRobot(Location loc) {
		return (Robot) entities[loc.getX()][loc.getY()][1];
	}
	
	public String outputTextGrid(){
		for(int i = 0; i < width; i++){//along each row
			for(int j = 0; j < height; j++){//for each square in the row
				int total = 0;
				if(entities[i][j][0] != null){
					if(entities[i][j][0].getClass().getName().equals("Simulator.PackingStation")){
						total += 1;
					} else if(entities[i][j][0].getClass().getName().equals("Simulator.Shelf")){
						total += 2;
					} else if(entities[i][j][0].getClass().getName().equals("Simulator.ChargingPod")){
						total += 3;
					}
				}
				if(entities[i][j][1] != null){
					total += 5;
				}
				System.out.print(total);
			}
			System.out.println("");
		}
		
		return "";
		
	}
}
