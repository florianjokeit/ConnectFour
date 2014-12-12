//Imports Java
import java.util.ArrayList;
//import java.net.ServerSocket;


// Import processing core , leave out this import in the pde and remove "public class ConnectFour extends PApplet"
import processing.core.*;

/**
 * @author florian jokeit 2014-12-06
 *
 */
@SuppressWarnings("serial")
public class ConnectFour extends PApplet 
{
	private boolean isPlayerBlue = true;
	private int turnCounter = 0;

	class ConnectFourGame 
	{
		public ArrayList<int[]> history;
		int[][] grid;		
		
		ConnectFourGame(int pRows, int pCols)
		{
			grid = new int[pRows][pCols];
			history = new ArrayList<int[]>();
		} //ConnectFourGame(int pRows, int pCols)
		
		public int count (int pType)
		{
			int z = 0;
			
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = 0; j < grid[0].length; j++)
				{
					if(grid[i][j] == pType)
					{
						z++;
					}
				}
			} return z;
		} //public int count (int pType)
		
		public void display(int pSize)
		{
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = 0; j < grid[0].length; j++)
				{
					fill(255);
					rect(i * pSize, j * pSize, pSize, pSize);
					if (grid[i][j] != 0)
					{
						fill(grid[i][j] == -1 ? 255 : 0, 0, grid[i][j] == 1 ? 255: 0);
						ellipse(i * pSize, j * pSize, pSize, pSize);
					}
				}
			}
			displayCounter();
		}//public void display(int pSize)
		
		private void displayCounter()
		{
			fill(255);        
			text("Turns: " + turnCounter, 400, 50);
		}
		
		public int lookForStone(int pX, int pY)
		{
			try
			{
				if(grid[pX][pY] != 0)
				{
					return lookForStone(pX, pY-1);
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				isPlayerBlue =!isPlayerBlue;
				pY++;
				println("Nice try ;)");
			}
			return pY;
		}
		
		public void clearHistory(int pPosition)
		{
			for (int i = history.size(); i > pPosition; i++)
			{
				history.remove(i);
			}
		}
		
		public int xMPos (int pX)
		{
			return (int) map(pX, 0, 7*50, 0, 7);
		}
		
//		boolean checkforfour(int pX, int pY)
//		{
//			int counter = -grid[pX][pY];
//			
//			//upside down
//			
//			return true;
//		}
	} //class ConnectFourGame 
	
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
	}
	
	public void mousePressed()
	{
		int x = (cf.xMPos(mouseX));
		isPlayerBlue = !isPlayerBlue;
		int y = cf.lookForStone(x,cf.grid[0].length-1);
		if (isPlayerBlue)
		{
			cf.grid[x][y] = 1;
		}
			
		else
			cf.grid[x][y] = -1;
		
		int[] coords = {x,y};
		cf.history.add(coords);
		
		if(turnCounter == cf.history.size()-1)
		{
			turnCounter++;
		}
		println(turnCounter);
		println(cf.history.size());
		if(turnCounter < cf.history.size())
			cf.clearHistory(turnCounter);
	}
	
	public void keyPressed()
	{
		if (key == CODED)
		{
			if (keyCode == LEFT && turnCounter > 0)
			{
				//Code for reverting the turns
				isPlayerBlue = !isPlayerBlue;
				int x = cf.history.get(turnCounter-1)[0];
				int y = cf.history.get(turnCounter-1)[1];
				cf.grid[x][y] = 0;
				
				if(turnCounter > 0)
				{
					turnCounter--;
				}
			}
			
			if (keyCode == RIGHT)
 			{
				isPlayerBlue = !isPlayerBlue;
				int x = cf.history.get(turnCounter)[0];
				int y = cf.history.get(turnCounter)[1];
				if (isPlayerBlue)
				{
					cf.grid[x][y] = 1;
				}
					
				else
					cf.grid[x][y] = -1;
				turnCounter++;
 			}//if (keyCode == RIGHT)
		}//if (key == CODED)
	}//public void keyPressed()
} //public class ConnectFour extends PApplet 
