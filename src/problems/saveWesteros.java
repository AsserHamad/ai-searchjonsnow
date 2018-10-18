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
	
	// Constructor
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
		SWstate initialstate = new SWstate(map);
		
		//As to include the root in the states
		previousStates.add(initialstate);
		root = new node(initialstate, "", 0, 0, null);

		ArrayList<node> list= new ArrayList<node>();
		list.add(root);
		System.out.println(((SWstate)root.state).map);
		node goal = generateNodes(list, strategy, visualize, 1);
		System.out.println("SWORDS: " + ((SWstate)root.state).map.maxswords);
		if(goal != null) {
			printHeritage(goal);
			return goal;
		} else {
			System.out.println("FAIL");
			return null;
		}
		
	}
	
	/* Function to print all the actions leading to the goal
	 */
	private void printHeritage(node goal) {
			System.out.println("COST: "+pathCost(goal));
			ArrayList<String> n = new ArrayList<String>();
			while(goal != null && !goal.operator.equals("")) {
				n.add(goal.operator);
				goal = goal.parent;
			}
			Collections.reverse(n);
			System.out.print(n);
			System.out.println();
	}

	/* The main bread and butter of this class, it loops on each node
	 * in the list and generates new nodes depending on the possible
	 * actions and the previous states.
	 */
	public node generateNodes(ArrayList<node> list, String strategy, boolean visualize, int heuristic) throws CloneNotSupportedException, ClassNotFoundException, IOException{
		if(list.isEmpty())
			return null;
		
		node node = list.get(0);
		if(goalTest(node)) {
			print("You won!!!", visualize);
			print("****************************\n****************************", visualize);
			print(((SWstate)node.state).map.toString(), visualize);
			print("COST:   ", visualize);print(node.cost+"", visualize);
			print("****************************\n****************************", visualize);
			
			return node;
			
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
						_node.heuristic = calculateHeuristic(_node, heuristic);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "B":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.moveJonSnow("B");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "B", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						_node.heuristic = calculateHeuristic(_node, heuristic);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "L":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.moveJonSnow("L");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "L", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						_node.heuristic = calculateHeuristic(_node, heuristic);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "R":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.moveJonSnow("R");
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "R", node.depth+1, pathCost(node) + 1, node);
						/* Add Heuristic */
						_node.heuristic = calculateHeuristic(_node, heuristic);
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
						node _node = new node(_state, "ATTACK", node.depth+1, pathCost(node) + 5, node);
						/* Add Heuristic */
						_node.heuristic = calculateHeuristic(_node, heuristic);
						list = addToList(strategy, count, list, _node);
						count++;
					}
				}
					break;
				case "REFILL":{
					Map map = (Map)clone(((SWstate)node.state).map);
					map.refill();
					SWstate _state = new SWstate(map);
					if(!previousStates.contains(_state)) {
						previousStates.add(_state);
						node _node = new node(_state, "REFILL", node.depth+1, pathCost(node) + 15, node);
						/* Add Heuristic */
						_node.heuristic = calculateHeuristic(_node, heuristic);
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
		return generateNodes(list, strategy, visualize, heuristic);
	}
	
	/* Function to calculate the heuristic of a node, the method
	 * int is there to check which type of heuristic function
	 * to use
	 */
	public int calculateHeuristic(node node, int method) {
		Map map = ((SWstate)node.state).map;
		int h = 0;
		if(method == 0) {
			h = (map.ww == 0) ? 0 : 
				(int)(((1/(1 + map.getWWInBlock(1))*10 + (1 +map.maxswords-map.jonswords))));
		} else {
			h = (map.ww == 0) ? 0 : 
				1 + (int)(map.jonswords + map.getDistToDS());
		}
		return h;
	}

	/* Adding the nodes to the list (tree) in the appropriate order
	 * using the strategy entered
	 */
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
				node.heuristic = 0;
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
	
	/* Cloning using serialization of the map
	 */
	public Object clone(Map obj) throws IOException, ClassNotFoundException {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}

	// Test a node to check if it's a goal node or not
	@Override
	public boolean goalTest(node node) {
		return ((SWstate)node.state).map.ww == 0;
	}

	// Calculate the cost of the path so far
	@Override
	public int pathCost(node node) {
		return node.cost;
	}
	
	public void print(String text, boolean condition) {
		if(condition)
			System.out.println(text);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		new saveWesteros("A*", false);
	}

}
