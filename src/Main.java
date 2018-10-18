import java.io.IOException;

import problems.*;
public class Main {
	public static void main(String[] args) throws ClassNotFoundException, CloneNotSupportedException, IOException {
		/*
		 * saveWesteros("strategy", visualize);
		 * 
		 * Viable strategies: DF, BF, UC, ID, GREEDY, A*
		 * 
		 * When visualize is set to false it only prints minimal visualization,
		 * however when set to true, it shows the expanded nodes in sequence with much more
		 * detail.
		 * 
		 * Getting a FAIL means there is no solution to be had, while getting an empty bracket []
		 * means the initial state is actually a goal state, so Jon's work isn't that hard after all.
		 */
		new saveWesteros("A*", false);
	}
}
