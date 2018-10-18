package problems;
import java.io.IOException;
import java.util.ArrayList;

import actions.action;
import grids.Map;
import states.*;
import nodes.node;

public abstract class problem {
	action[] actions;
	node root;
	ArrayList<state> stateSpace;

	/*Function that takes in a node and checks if it is
	 * in fact, a goal node */
	public abstract boolean goalTest(node node);
	
	/*Function that takes in a node and returns with the total cost
	 * up to this point in the search procedure*/
	public abstract int pathCost(node node);
	
	//The search function
	public abstract node search(Map map, String strategy, boolean visualize) throws ClassNotFoundException, CloneNotSupportedException, IOException;
}
