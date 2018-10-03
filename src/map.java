
public class map {
	public int rows, columns;
	public int jonsnowR, jonsnowC;
	public block[][] grid;
	
	public map() {
		this.rows = (int)(4 + (Math.random()*96));
		this.columns = (int)(4 + (Math.random()*96));
		this.grid = new block[this.rows][this.columns];
	}
	
	private void populateGrid() {
		this.grid = generateEmptySpaces(
				generateWhitewalkers(
						generateDragonstone(
								generateJonSnow(grid)
								)
						)
				);
	}
	
	//Generates Jon Snow block in bottom right corner
	public static block[][] generateJonSnow(block[][] grid){
		grid[grid.length-1][grid[0].length-1] = new block("jonsnow");
		return grid;
	}
	
	public static block[][] generateDragonstone(block[][] grid){
		int row = (int)(grid.length*Math.random()),
			column = (int)(grid[0].length*Math.random());
		if(!isAvailable(grid, row, column))
			return generateDragonstone(grid);
		else {
			grid[row][column] = new block("dragonstone");
			return grid;
		}
	}
	
	public static block[][] generateWhitewalkers(block[][] grid){
		int num = (int)(Math.random() * (grid.length*grid[0].length));
		for (int i = 0; i < num; i++) {
			int row = (int)(Math.random() * grid.length),
				column = (int)(Math.random() * grid[0].length);
			while(!isAvailable(grid, row, column)) {
				row = (int)(Math.random() * grid.length);
				column = (int)(Math.random() * grid[0].length);
			}
			grid[row][column] = new block("whitewalker");
		}
		return grid;
	}
	
	public static block[][] generateEmptySpaces(block[][] grid){
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(isAvailable(grid, i, j))
					grid[i][j] = new block("empty");
			}
		}
		return grid;
	}
	
	
	//Function to determine if block is not empty nor undefined
	public static boolean isAvailable(block[][] grid, int row, int column) {
		return grid[row][column] == null || grid[row][column].isEmpty();
	}

	
	
	
	public String toString() {
		String str = "";
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				str += grid[i][j];
			}
			str += "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		map map = new map();
		map.populateGrid();
		System.out.println(map);
	}

}
