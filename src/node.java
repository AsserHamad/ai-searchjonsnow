
public class node {
	public state state;
	public int id;
	public int parent_id;
	public String operator;
	public int depth;
	public int cost;
	
	public node(state state, String operator, int depth, int cost, int parent_id) {
		//Generates random id for this node to be used to reference it from its children
		this.id = (int)(Math.random()*1000000);
		
		this.state = state;
		this.parent_id = parent_id;
		this.operator = operator;
		this.depth = depth;
		this.cost = cost;
	}
	
	public String toString() {
		return "\n<<<State: " + this.state + " \nID: " + this.id + "\nParent id: " + this.parent_id + "\nOperator: " + this.operator
				+ "\nDepth: " + this.depth + "\nCost: " + this.cost+ " >>>\n";
	}
}
