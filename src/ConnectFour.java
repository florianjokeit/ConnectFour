import java.util.ArrayList;
import java.net.ServerSocket;

import processing.core.*;

public class ConnectFour extends PApplet 
{
	private boolean isplayerblue = true;
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
			text("Turns: " + turnCounter, 400, 50);
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
			return (int) map(x, 0, 7*50, 0, 7);
		}
	}
	
	ConnectFourGame cf = new ConnectFourGame(7,6);
	
	
	public void setup()
	{
		size(550,450);
		ellipseMode(CORNER);
		textSize(30);
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
		isplayerblue = !isplayerblue;
		int y = cf.lookForStone(x,5);
		if (isplayerblue)
		{
			cf.grid[x][y] = 1;
		}
			
		else
			cf.grid[x][y] = -1;
		
		int[] coords = {x,y};
		
		if(turnCounter == cf.history.size()-1)
		{
			turnCounter++;
		}
		cf.history.add(coords);
	}
	
	public void keyPressed()
	{
		if (key == CODED)
		{
			if (keyCode == LEFT)
			{
				isplayerblue = !isplayerblue;
				int x = cf.history.get(turnCounter)[0];
				int y = cf.history.get(turnCounter)[1];
				cf.grid[x][y] = 0;
				if(turnCounter > 0)
				{
					cf.history.remove(turnCounter);
					turnCounter--;
				}
			}
			
//			if (keyCode == RIGHT)
//			{
//				
//			}
		}
	}
	
	
	
}
