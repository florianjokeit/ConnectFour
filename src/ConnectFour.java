import processing.core.*;

public class ConnectFour extends PApplet 
{
	private boolean player = true;
	class ConnectFour1 
	{
		int[][] grid;
		boolean playerBlue;
		
		ConnectFour1(int rows, int cols)
		{
			grid = new int[rows][cols];
			this.playerBlue = playerBlue;
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
	
	ConnectFour1 cf = new ConnectFour1(7,6);
	
	public void setup()
	{
		size(7*50,6*50);
		ellipseMode(CORNER);
		//noLoop();
	
	}
	
	public void draw()
	{
		cf.display(50);
	}
	
	public void mousePressed()
	{
		int x = (cf.xMPos(mouseX));
		player = !player;
		if (player)
		{
			cf.grid[x][cf.lookForStone(x,5)] = 1;
		}
			
		else
			cf.grid[x][cf.lookForStone(x,5)] = -1;
	}
	
}
