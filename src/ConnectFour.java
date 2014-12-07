import java.util.ArrayList;

import processing.core.*;

public class ConnectFour extends PApplet 
{
	private boolean player = true;
	private int placeInHistory = 0;
	private int turnCounter = 0;

	class ConnectFourGame 
	{
		public ArrayList<int[]> history;
		int[][] grid;		
		
		ConnectFourGame(int rows, int cols)
		{
			grid = new int[rows][cols];
			history = new ArrayList<int[]>();
		}
		
		int count (int type)
		{
			int z = 0;
			
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = 0; j < grid[0].length; j++)
				{
					if(grid[i][j] == type)
					{
						z++;
					}
							
				}
			} return z;
		}
		
		void display(int size)
		{
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = 0; j < grid[0].length; j++)
				{
					fill(255);
					rect(i*size, j*size, size, size);
					if (grid[i][j] != 0)
					{
						fill(grid[i][j] == -1 ? 255 : 0, 0, grid[i][j] == 1 ? 255: 0);
						ellipse(i*size, j*size, size, size);
					}
				}
			}
		}
		
		void displayCounter()
		{
			text(turnCounter, 500, 50);
		}
		
		int lookForStone(int x, int y)
		{
			if(grid[x][y] != 0)
			{
				return lookForStone(x, y-1);
				
			}
			return y;
		}
		
		int xMPos (int x)
		{
			return (int) map(x, 0, width, 0, 7);
		}
	}
	
	ConnectFourGame cf = new ConnectFourGame(7,6);
	
	
	public void setup()
	{
		size(550,6*50);
		ellipseMode(CORNER);
		textSize(30);
		//noLoop();
	
	}
	
	public void draw()
	{
		background(128);
		cf.display(50);
		cf.displayCounter();
	}
	
	public void mousePressed()
	{
		int x = (cf.xMPos(mouseX));
		player = !player;
		int y = cf.lookForStone(x,5);
		turnCounter++;
		if (player)
		{
			cf.grid[x][y] = 1;
		}
			
		else
			cf.grid[x][y] = -1;
		
		int[] koordinates = {x,y};
		if(placeInHistory == cf.history.size()-1)
		{
			placeInHistory++;
		}
		cf.history.add(koordinates);
		
		
	}
	
	public void keyPressed()
	{
		if (key == CODED)
		{
			if (keyCode == LEFT)
			{
				//cf.grid[5][5] = 0;
				
				int x = cf.history.get(placeInHistory)[0];
				int y = cf.history.get(placeInHistory)[1];
				cf.grid[x][y] = 0;
				if(placeInHistory > 0){
					placeInHistory--;
				}
			}
			if (keyCode == RIGHT)
			{
				
			}
		}
	}
	
	
	
}
