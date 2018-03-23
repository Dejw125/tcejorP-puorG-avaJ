package Simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Simulator {
	
	private int tickNum;
	private Grid grid;
	private List<Entity> entities;
	private Random gen;
	private List<Order> orders;
	
	public static void main(String[] args) {
		
		Simulator s = new Simulator(5, 5);
		s.sampleSetup();
		
		
		for (int i = 0; i < 10; i++) {
			s.generateOrder();
		}
		
		for (int i = 0; i < 100; i++) {
			s.simulateOneStep();
			
		}
		
	}
	
	private void sampleSetup() {
		addEntity(new Shelf("ss0", new Location(0,0)));
		addEntity(new Shelf("ss1", new Location(0,4)));
		
		
		Robot r = new Robot("r0", new Location(4,0), "c0", 50);
		addEntity(new ChargingPod("c0", new Location(4,0), 3, "r0"));
		
		
		
		PackingStation ps = new PackingStation("ps0", new Location(4,4));
		ps.addRobot(r);
		addEntity(ps);
		addEntity(r);
		
		
	}
	
	
	public Simulator(int w, int h) {
		grid = new Grid(w, h);
		tickNum = 0;
		entities = new ArrayList<Entity>();
		gen = new Random();
		orders = new ArrayList<Order>();
	}
	
	public void addEntity(Entity ent) {
		//add the entity to list
		entities.add(ent);
		//add to grid
		grid.placeEntity(ent, ent.getLocation());
	}
	
	public void simulateOneStep() {
		//iterate through list of entities
		for (Entity entity : entities) {
			///TODO work out better way of passing orders to PS
			entity.act(grid, orders);
			if (entity.getClass().getName().equals("Robot")){
				if (detectDeath((Robot) entity)){
					System.out.println("Robot out of power");
					//end simulation, robot has died
				}
			}
		}
		tickNum++;
		System.out.println(tickNum);
		grid.outputTextGrid();
	}
	
	private boolean detectDeath(Robot r) {
		return r.isAlive();
	}
	
	
	
	private void generateOrder() {
		
		ArrayList<String> shelfIDs = new ArrayList<String>();
		for (Entity entity : entities) {
			if (entity.getClass().getName().equals("Simulator.Shelf")){
				shelfIDs.add(entity.getUID());
			}
		}
		int numShelves = 1 + gen.nextInt(shelfIDs.size());
		int ticks = gen.nextInt(20);
		
		HashSet<String> shelves = new HashSet<String>();
		for (int i = 0; i < numShelves; i++) {
			int index = gen.nextInt(shelfIDs.size());
			shelves.add(shelfIDs.get(index));
			shelfIDs.remove(index);
		}
		
		orders.add(new Order(ticks, shelves));
		
	}
}
