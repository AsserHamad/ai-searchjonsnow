
public class main {
	public static void main(String[] args) {
		map map = new map();
		state initialstate = new state(map.ww, map.jonswords, map.jonsnowR, map.jonsnowC);
		System.out.println(map);
		System.out.println(initialstate);
	}
}
