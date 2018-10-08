
public class map {
	public int rows, columns;
	public int jonsnowR, jonsnowC;
	public int jonswords;
	public int cost;
	public int ww;
	public block[][] grid;
	
	public map() {
		this.rows = (int)(4 + (Math.random()*6));
		this.columns = (int)(4 + (Math.random()*6));
		this.grid = new block[this.rows][this.columns];
		this.cost= 0;
		populateGrid();
	}
	
	void populateGrid() {
		this.jonswords = (int)(1 + Math.random()*10);
		this.grid = generateEmptySpaces(
				generateObstacles(
					generateWhitewalkers(
						generateDragonstone(
							generateJonSnow(grid)
							)
						)
					)
				);
	}
	
	//Generates Jon Snow block in bottom right corner
	public block[][] generateJonSnow(block[][] grid){
		grid[grid.length-1][grid[0].length-1] = new block("jonsnow");
		jonsnowR = grid.length-1;
		jonsnowC = grid[0].length-1;
		return grid;
	}
	
	//Generates the dragonstone in a random place on the grid
	public block[][] generateDragonstone(block[][] grid){
		int row = (int)(grid.length*Math.random()),
			column = (int)(grid[0].length*Math.random());
		if(!isAvailable(grid, row, column))
			return generateDragonstone(grid);
		else {
			grid[row][column] = new block("dragonstone");
			return grid;
		}
	}
	
	//Generates a random number of whitewalkers (less than a third of the grid)
	public block[][] generateWhitewalkers(block[][] grid){
		int num = (int)(Math.random() * (grid.length*grid[0].length)/3);
		this.ww = num;
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

	//Genrates a random number of obstacles (less than a quarter of the grid)
	public block[][] generateObstacles(block[][] grid){
		int num = (int)(Math.random() * (grid.length*grid[0].length)/3);
		for (int i = 0; i < num; i++) {
			int row = (int)(Math.random() * grid.length),
				column = (int)(Math.random() * grid[0].length);
			while(!isAvailable(grid, row, column)) {
				row = (int)(Math.random() * grid.length);
				column = (int)(Math.random() * grid[0].length);
			}
			grid[row][column] = new block("obstacle");
		}
		return grid;
	}
	
	//Fills the grid with empty blocks
	public block[][] generateEmptySpaces(block[][] grid){
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if(isAvailable(grid, i, j))
					grid[i][j] = new block("empty");
			}
		}
		return grid;
	}
	
	public map moveJonSnow(String motion) {
		switch(motion) {
		case "UP":{
			if(jonsnowR>0 && isAvailable(grid, jonsnowR-1, jonsnowC)) {
				jonsnowR--;
				grid[jonsnowR][jonsnowC] = new block("jonsnow");
				grid[jonsnowR+1][jonsnowC] = new block("empty");
			}
		};break;
		case "DOWN":{
			if(jonsnowR<grid.length-1 && isAvailable(grid, jonsnowR+1, jonsnowC)) {
				jonsnowR++;
				grid[jonsnowR][jonsnowC] = new block("jonsnow");
				grid[jonsnowR-1][jonsnowC] = new block("empty");
			
		};break;
		}
		case "LEFT":{
			if(jonsnowC>0 && isAvailable(grid, jonsnowR, jonsnowC-1)) {
				jonsnowC--;
				grid[jonsnowR][jonsnowC] = new block("jonsnow");
				grid[jonsnowR][jonsnowC+1] = new block("empty");
			
		}
			
		};break;
		case "RIGHT":{
			if(jonsnowC<grid[0].length-1 && isAvailable(grid, jonsnowR, jonsnowC+1)) {
				jonsnowC++;
				grid[jonsnowR][jonsnowC] = new block("jonsnow");
				grid[jonsnowR][jonsnowC-1] = new block("empty");
			
		}
		};break;
		}
		return this;
	}
	
	//Function to determine if block is not empty nor undefined
	public static boolean isAvailable(block[][] grid, int row, int column) {
		return grid[row][column] == null || grid[row][column].isEmpty();
	}

	//String representation of the map
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


}
