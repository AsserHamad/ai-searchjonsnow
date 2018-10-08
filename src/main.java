import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	public static void main(String[] args) throws IOException {
		map map = new map();
		state initialstate = new state(map.ww, map.jonswords, map.jonsnowC, map.jonsnowR);
		System.out.println(map);
		System.out.println(initialstate);
		while(true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			map = move(map, br.readLine());
			System.out.println(map);
			System.out.println("cost: "+ map.cost);
		}
	}
	
	//COST: 1
	public static map move(map map, String direction) {
		map.cost++;
		return map.moveJonSnow(direction);
	}
}
