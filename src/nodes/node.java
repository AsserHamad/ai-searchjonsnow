package nodes;
import states.*;
import nodes.node;

public class node implements Comparable<Object>{
	public state state;
	public node parent;
	public String operator;
	public int depth;
	public int cost;
	public int heuristic;
	
	public node(state state, String operator, int depth, int cost, node parent) {
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.cost = cost;
		this.heuristic = 0;
	}
	

	public node(state state, String operator, int depth, int cost, int heuristic, node parent) {
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.cost = cost;
		this.heuristic = heuristic;
	}
	
	public String toString() {
		return "\n<<<State: " + this.state + 
				"\nOperator: " + this.operator +
				"\nDepth: " + this.depth + 
				"\nCost: " + this.cost + 
				"\nHeuristic: " + this.heuristic+ 
				"\n>>>\n";
	}
	

	@Override
	public int compareTo(Object node) {
        int compareage=((node)node).cost + ((node)node).heuristic;
        /* For Ascending order*/
        return (this.cost + this.heuristic)-compareage;
	}
	

	@Override
    public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof node)) { 
            return false; 
        } 
        
        node node = (node) o; 
        
        return this.state.equals(node.state);
        } 
}
