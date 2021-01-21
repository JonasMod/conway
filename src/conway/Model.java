package conway;

public class Model implements IModel {
	private boolean[][] grid;
	
	/* 
	For the seed state:
		[f, f, f]
		[f, t, t]
		[f, f, f]
	
	Enter arguments:
		x=3, y=3, seed="ffffttfff"
	...which matches rows like [[fff][ftt][fff]]
	 */
	public Model(int x, int y, String seed) throws IllegalArgumentException {
		
		if(x < 1 || y < 1) {
			throw new IllegalArgumentException("Grid size must be at least 1x1.");
		}
		if(x*y != seed.length()) {
			throw new IllegalArgumentException("Seed length does not match grid size.");
		}
		
		grid = new boolean[y][x];
		
		int nr = 0;
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				char value = seed.charAt(nr++);
				grid[i][j] = value == 't' ? true : false;
			}
		}
	}
	
	public void tick() {
		int x = x(), y = y();
		boolean[][] newGrid = new boolean[y][x];
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				int neighbors = countNeighboursFor(j, i, grid);
				
				// Any live cell with two or three live neighbours survives.
				if(grid[i][j] && (neighbors == 2 || neighbors == 3)) {
					newGrid[i][j] = true;
				}
				// Any dead cell with three live neighbours becomes a live cell.
				else if(!grid[i][j] && neighbors == 3) {
					newGrid[i][j] = true;
				}
				// All other live cells die in the next generation. Similarly, all other dead cells stay dead.
				else {
					newGrid[i][j] = false;
				}
			}
		}
		
		grid = newGrid;
	}
	
	public boolean at(int x, int y) {
		int x_max = x(), y_max = y();
		if(x < 0 || x_max <= x || y < 0 || y_max <= y) {
			throw new IndexOutOfBoundsException("Index ("+x+","+y+") does not exist within ("+x_max+","+y_max+") grid.");
		}
		
		return grid[y][x];
	}
	
	public int x() {
		return grid[0].length;
	}
	
	public int y() {
		return grid.length;
	}
	
	// excluded from interface, added for testing convenience
	public String getState() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < y(); i++) {
			for(int j = 0; j < x(); j++) {
				stringBuilder.append(grid[i][j] ? 't' : 'f');
			}
		}
		return stringBuilder.toString();
	}
	
	private static int countNeighboursFor(int x, int y, boolean[][] grid) {
		int count = 0, x_max = grid[0].length, y_max = grid.length;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				if (y+i < 0 || y_max <= y+i) continue;
				if (x+j < 0 || x_max <= x+j) continue;
				
				if(grid[y+i][x+j] == true) count++;
			}
		}
		
		return count;
	}
}
