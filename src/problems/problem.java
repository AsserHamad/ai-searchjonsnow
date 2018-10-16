package problems;
import java.util.ArrayList;

import actions.action;
import states.state;
import nodes.node;

public abstract class problem {
	action[] actions;
	node root;
	ArrayList<state> stateSpace;
	
	public abstract boolean goalTest(node node);
	public abstract int pathCost(node node);
}
