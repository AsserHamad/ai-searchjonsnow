package problems;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import actions.action;
import grids.Map;
import nodes.node;
import states.*;

public class saveWesteros extends problem {
	private static ArrayList<SWstate> previousStates;
	public static ArrayList<node> goalnodes;

	public saveWesteros(String strategy, boolean visualize) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		search(genGrid(), strategy, visualize);	
	}
	
	//Generates an n*m grid
	public static Map genGrid() {
		return new Map();
	}
	
	public node search(Map map, String strategy, boolean visualize) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		this.actions = action.populateActions();
		previousStates = new ArrayList<SWstate>();
		goalnodes = new ArrayList<node>();
		SWstate initialstate = new SWstate(map);
		
		//As to include the root in the states
		previousStates.add(initialstate);
		root = new node(initialstate, "", 0, 0, null);

		ArrayList<node> list= new ArrayList<node>();
		list.add(root);
		System.out.println(((SWstate)root.state).map);
		System.out.println(generateNodes(list, strategy, visualize));
		Collections.sort(goalnodes);
		if(!goalnodes.isEmpty()) {
			printHeritage(goalnodes);
			return goalnodes.get(0);
		} else {
			return null;
		}
		
	}
	
	private void printHeritage(ArrayList<node> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			node node = nodes.get(i);
			System.out.println("COST: "+pathCost(node));
			while(node != null && !node.operator.equals("")) {
				System.out.print(node.operator+" > ");
				node = node.parent;
			}
			System.out.println();
		}
	}

	//COST: 1
	public static void move(Map map, String direction) {
		map.moveJonSnow(direction);
	}
	
	//COST: 10
	public static void refill(Map map) {
		map.refill();
	}
	
	public ArrayList<node> generateNodes(ArrayList<node> list, String strategy, boolean visualize) throws CloneNotSupportedException, ClassNotFoundException, IOException{
		if(list.isEmpty())
			return null;
		
		final node node = list.get(0);
		if(goalTest(node)) {
			print("You won!!!", visualize);
			print("****************************\n****************************", visualize);
			print(((SWstate)node.state).map.toString(), visualize);
			print("COST:   ", visualize);print(node.cost+"", visualize);
			print("****************************\n****************************", visualize);
			
			goalnodes.add(node);
			list.remove(0);
			Collections.sort(goalnodes);
			return null;
			
			//Uncomment this for optimality
			//return generateNodes(list, strategy);
			
		}
		print(((SWstate)node.state).map.toString(), visualize);
		list.remove(0);
		int count = 0;
		print("///////////NEW NODE/////////////", visualize);
		print(node.toString(), visualize);
		for(action action: this.actions) {
			switch(action.operator) {
				case "F":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.moveJonSnow("F");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "F", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "B":{
					Map map = (Map)clone(((SWstate)node.state).map);
					move(map, "B");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "B", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "L":{
					Map map = (Map)clone(((SWstate)node.state).map);
					move(map, "L");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "L", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "R":{
					Map map = (Map)clone(((SWstate)node.state).map);
					move(map, "R");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "R", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "ATTACK":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.attack();
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "ATTACK", node.depth+1, pathCost(node) + 3, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "REFILL":{
					Map map = (Map)clone(((SWstate)node.state).map);
					refill(map);
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "REFILL", node.depth+1, pathCost(node) + 10, node);
						/* Add Heuristic */
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				}
		}
		print("POSSIBLE ACTIONS", visualize);
		print(list.toString(), visualize);
		print("..............................................................", visualize);
		return generateNodes(list, strategy, visualize);
	}
	
	//Adding the nodes to the list (tree) in the appropriate order
	public ArrayList<node> addToList(String strategy, int count, ArrayList<node> list, node node){
		switch(strategy) {
			case "DFS":{
				list.add(count, node);
			}
			break;
			case "BFS":{
				list.add(node);
			}
			break;
			case "UC":{
				list.add(node);
				Collections.sort(list);
			}
			break;
			case "ID":{
				node _node = node;
				while(!list.contains(_node.parent) && _node != null) {
					list.add(_node.parent);
					_node = _node.parent;
				}
				list.add(list.indexOf(node.parent) + count ,node);
			}
			break;
			case "GREEDY":{
				node.cost = 0;
				list.add(node);
				Collections.sort(list);
			}
			case "A*":{
				list.add(node);
				Collections.sort(list);
			}
		}
		return list;
	}
	

	public Object clone(Map obj) throws IOException, ClassNotFoundException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}

	@Override
	public boolean goalTest(node node) {
		return ((SWstate)node.state).map.ww == 0;
	}

	@Override
	public int pathCost(node node) {
		return node.cost;
	}
	
	public void print(String text, boolean condition) {
		if(condition)
			System.out.println(text);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		new saveWesteros("UC", false);
	}

}
