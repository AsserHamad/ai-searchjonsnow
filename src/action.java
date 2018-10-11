
public class action {
	public String operator;
	public int cost;
	
	public action(String operator, int cost) {
		this.operator = operator;
		this.cost = cost;
	}
	
	public static action[] populateActions() {
		String[] operators = new String[]{"F", "B", "L", "R", "ATTACK", "REFILL"};
		action[] actions = new action[operators.length];
		actions[0] = new action("F", 1);
		actions[1] = new action("B", 1);
		actions[2] = new action("L", 1);
		actions[3] = new action("R", 1);
		actions[4] = new action("ATTACK", 3);
		actions[5] = new action("REFILL", 10);

		return actions;
	}
}
