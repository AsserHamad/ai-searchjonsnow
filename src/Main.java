import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
	
	public static action[] actions;
	private static ArrayList<state> previousStates;
	public static String strategy = "DFS";
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException, ClassNotFoundException {
		Map map = new Map();
		actions = action.populateActions();
		previousStates = new ArrayList<state>();
		state initialstate = new state(map);
		
		//As to include the root in the states
		previousStates.add(initialstate);
		node root = new node(initialstate, "", 0, 0, -1);

		ArrayList<node> list= new ArrayList<node>();
		list.add(root);
		System.out.println(generateNodes(list, strategy));
		
	}
	
	//COST: 1
	public static void move(Map map, String direction) {
		map.moveJonSnow(direction);
	}
	
	//COST: 10
	public static void refill(Map map) {
		map.refill();
	}
	
	public static ArrayList<node> generateNodes(ArrayList<node> list, String strategy) throws CloneNotSupportedException, ClassNotFoundException, IOException{
		if(list.isEmpty())
			return null;
		
		final node node = list.get(0);
		System.out.println(node.state.map);
		list.remove(0);
		int count = 0;
		System.out.println("///////////NEW NODE/////////////");
		System.out.println(node);
		for(action action: actions) {
			switch(action.operator) {
				case "F":{
					Map map = (Map)clone(node.state.map);
					map.moveJonSnow("F");
					state _state = new state(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "F", node.depth+1, node.cost + 1, node.id);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "B":{
					Map map = (Map)clone(node.state.map);
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
					Map map = (Map)clone(node.state.map);
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
					Map map = (Map)clone(node.state.map);
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
					Map map = (Map)clone(node.state.map);
					map.attack();
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
					Map map = (Map)clone(node.state.map);
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
		System.out.println("POSSIBLE ACTIONS");
		System.out.println(list);
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
	

	public static Object clone(Map obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}
	
	//Helper function to check if goal state
	public static boolean didIWin(Map map, node node) {
		return map.ww == 0;
	}
}
