import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static action[] actions;
	private static ArrayList<state> previousStates;
	public static String strategy = "DFS";
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		Map map = new Map();
		actions = action.populateActions();
		previousStates = new ArrayList<state>();
		state initialstate = new state(map);
		
		//As to include the root in the states
		previousStates.add(initialstate);
		node root = new node(initialstate, "", 0, 0, -1);
		
		System.out.println(map);
		
		System.out.println(root+"\n");
		ArrayList<node> list= new ArrayList<node>();
		list.add(root);
		System.out.println(generateNodes(list, strategy));
		
	}
	
	//COST: 1
	public static void move(Map map, String direction) {
		map.moveJonSnow(direction);
	}
	
	//COST: 3
	public static Map attack(Map map) {
		return map.attack();
	}
	
	//COST: 10
	public static void refill(Map map) {
		map.refill();
	}
	
	public static ArrayList<node> generateNodes(ArrayList<node> list, String strategy) throws CloneNotSupportedException{
		if(list.isEmpty())
			return null;
		
		int count = 0;
		final node node = list.get(0);
		list.remove(0);
		System.out.println("///////////NEW NODE/////////////");
		System.out.println(node);
		System.out.println("-----------LIST AFTER REMOVING NODE----------");
		System.out.println(list);
		for(action action: actions) {
			switch(action.operator) {
				case "F":{
					System.out.println("OLD MAP WAS ::::");System.out.println(node.state.map);
					Map map = (Map)node.state.map.clone();
					map.moveJonSnow("F");
					System.out.println("NEW MAP IS ::::");System.out.println(map);
					System.out.println("NEW OLD MAP THO IS :::::");System.out.println(node.state.map);
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						System.out.println("F eligible woohoo!");
						previousStates.add(_state);
						node _node = new node(_state, "F", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					} else {
						System.out.println("Previous state does contain this state!");
						System.out.println("STATE:"); System.out.println(_state);
						System.out.println("PREVIOUS STATES:"); System.out.println(previousStates);
					}
				}
					break;
				case "B":{
					Map map = node.state.map;
					move(map, "B");
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "B", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "L":{
					Map map = node.state.map;
					move(map, "L");
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "L", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "R":{
					Map map = node.state.map;
					move(map, "R");
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "R", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "ATTACK":{
					Map map = node.state.map;
					map = attack(node.state.map);
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "ATTACK", node.depth+1, node.cost + 3, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "REFILL":{
					Map map = node.state.map;
					refill(map);
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "REFILL", node.depth+1, node.cost + 10, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				}
		}
		System.out.println("..............................................................");
		return generateNodes(list, strategy);
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
	public static boolean didIWin(Map map, node node) {
		return map.ww == 0;
	}
}
