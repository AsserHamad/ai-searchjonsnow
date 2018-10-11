import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public String[] operators = new String[]{"F", "B", "L", "R", "ATTACK", "REFILL"};
	
	public static void main(String[] args) throws IOException {
		map map = new map();
		state initialstate = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
		node root = new node(initialstate, "", 0, 0, -1);
		System.out.println(root);
		
		
//		System.out.println(map);
//		System.out.println(initialstate);
//		while(true) {
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			map = move(map, br.readLine());
//			System.out.println(map);
//			System.out.println("cost: "+ map.cost);
//		}
	}
	
	//COST: 1
	public static map move(map map, String direction) {
		map.cost++;
		return map.moveJonSnow(direction);
	}
	
	public ArrayList<node> generateNodes(node node){
		for(String operator: operators) {
			switch(operator) {
			case "F":{
				
			}
				break;
			case "B":{
				
			}
				break;
			case "L":{
				
			}
				break;
			case "R":{
				
			}
				break;
			case "ATTACK":{
				
			}
				break;
			case "REFILL":{
				
			}
				break;
			}
		}
		return null;
	}
}
