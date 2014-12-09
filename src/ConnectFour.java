//Imports Java
import java.util.ArrayList;
import java.net.ServerSocket;

// Import processing core , leave this import in the pde and remove "extends PApplet"
import processing.core.*;

public class ConnectFour extends PApplet
{
	private boolean isplayerblue = true;
	private int turnCounter;


	class ConnectFourGame
	{
		public ArrayList<int[]> history;
		int[][] grid;
		private int countermessage;

		public void setCountermessage(int p_countermessage)
		{
			countermessage = p_countermessage;
		}
		public int getCountermessage()
		{
			return countermessage;
		}

		ConnectFourGame(int rows, int cols)
		{
			grid = new int[rows][cols];
			history = new ArrayList<int[]>();
			countermessage = 0;
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
			displayCounter(countermessage);
		}

		void displayCounter(int p_anzahl)
		{
			text("Turns: " + (p_anzahl), 400, 50);
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
		size(550, 450);
		ellipseMode(CORNER);
		textSize(30);
		turnCounter = -1;
		cf.setCountermessage(0);
	}

	public void draw()
	{
		background(128);
		cf.display(50);
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
		else if(turnCounter < cf.history.size()-1) // wenn nach dem zurückgehen ein neuer stein gesetzt wird
		{
			flushHistory(turnCounter, cf.history);
			turnCounter++;
		}
		cf.history.add(coords);
		cf.setCountermessage(cf.getCountermessage()+1);


	}

	private void flushHistory(int p_turnCounter, ArrayList<int[]> p_history)
	{
		for (int i = 0; i < p_history.size(); i++)
		{
			if (p_turnCounter > i)
			{
				p_history.remove(i);
			}
		}
	}

	public void keyPressed()
	{
		if (key == CODED)
		{
			isplayerblue = !isplayerblue;

			if (keyCode == LEFT)
			{
				int x = cf.history.get(turnCounter)[0];
				int y = cf.history.get(turnCounter)[1];
				cf.grid[x][y] = 0;
				//cf.history.remove(turnCounter);
				if (cf.getCountermessage() > 0)
				{
					cf.setCountermessage(cf.getCountermessage()-1);
				}
				if (turnCounter > 0)
				{
					turnCounter--;
				}
			}

			if (keyCode == RIGHT && turnCounter < cf.history.size())
			{
				int x = cf.history.get(turnCounter)[0];
				int y = cf.history.get(turnCounter)[1];


				if (turnCounter < cf.history.size()-1)
				{
					turnCounter++;
				}
				if (cf.getCountermessage() < cf.history.size())
				{
					cf.setCountermessage(cf.getCountermessage() + 1);

					if (isplayerblue)
					{
						cf.grid[x][y] = 1;
					}
					else
					{
						cf.grid[x][y] = -1;
					}
				}
			}
		}
	}



}
