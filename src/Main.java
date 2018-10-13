import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static action[] actions;
	private static ArrayList<state> previousStates;
	public static String strategy = "DFS";
	
	public static void main(String[] args) throws IOException {
		map map = new map();
		actions = action.populateActions();
		previousStates = new ArrayList<state>();
		state initialstate = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
		
		//As to include the root in the states
		previousStates.add(initialstate);
		node root = new node(initialstate, "", 0, 0, -1);
		
		System.out.println(map);
		
		System.out.println(root+"\n");
		ArrayList<node> list= new ArrayList<node>();
		System.out.println(generateNodes(root, map, list, strategy));
		
	}
	
	//COST: 1
	public static map move(map map, String direction) {
		return map.moveJonSnow(direction);
	}
	
	//COST: 3
	public static void attack(map map) {
		map.attack();
	}
	
	//COST: 10
	public static void refill(map map) {
		map.refill();
	}
	
	public static ArrayList<node> generateNodes(node node, map map, ArrayList<node> list, String strategy){
		int count = 0;
		for(action action: actions) {
			switch(action.operator) {
				case "F":{
					map = move(map, "F");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "F", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "B":{
					map = move(map, "B");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "B", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "L":{
					map = move(map, "L");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "L", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "R":{
					map = move(map, "R");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "R", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "ATTACK":{
					attack(map);
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "ATTACK", node.depth+1, node.cost + 3, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "REFILL":{
					refill(map);
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "REFILL", node.depth+1, node.cost + 10, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				}
		}
		return list;
	}
	
	//Adding the nodes to the list (tree) in the appropriate order
	public static ArrayList<node> addToList(String strategy, int count, ArrayList<node> list, node node){
		switch(strategy) {
			case "DFS":{
				list.add(count, node);
			}
			break;
		}
		return list;
	}
	
	//Helper function to check if goal state
	public static boolean didIWin(map map, node node) {
		return map.ww == 0;
	}
}
