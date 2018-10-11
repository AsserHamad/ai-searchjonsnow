import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	
	public static action[] actions;
	private static ArrayList<state> previousStates;
	
	@SuppressWarnings("unchecked")
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
		System.out.println(generateDFSNodes(root, map, list));
		
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
	
	public static ArrayList<node> generateDFSNodes(node node, map map, ArrayList<node> list){
		int count = 0;
		for(action action: actions) {
			switch(action.operator) {
				case "F":{
					map = move(map, "F");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "F", node.depth+1, node.cost + 1, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				case "B":{
					map = move(map, "B");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "B", node.depth+1, node.cost + 1, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				case "L":{
					map = move(map, "L");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "L", node.depth+1, node.cost + 1, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				case "R":{
					map = move(map, "R");
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "R", node.depth+1, node.cost + 1, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				case "ATTACK":{
					attack(map);
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "ATTACK", node.depth+1, node.cost + 3, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				case "REFILL":{
					refill(map);
					state _state = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
					if(!previousStates.contains(_state)) {
						node _node = new node(_state, "REFILL", node.depth+1, node.cost + 10, node.id);
						list.add(count, _node);
						count++;
					}
				}
					break;
				}
		}
		return list;
	}
	
	public static boolean didIWin(map map, node node) {
		return map.ww == 0;
	}
}
