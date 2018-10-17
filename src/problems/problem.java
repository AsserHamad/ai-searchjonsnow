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
	
	public abstract boolean goalTest(node node);
	public abstract int pathCost(node node);
	
	public abstract node search(Map map, String strategy, boolean visualize) throws ClassNotFoundException, CloneNotSupportedException, IOException;
}
