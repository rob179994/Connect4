import java.util.*;

public class ComputerPlayer extends Player{

	private int gridxMax;		// class variables
	private int playerNumber;		
	
	public ComputerPlayer(int playerNumber){		// initialise player number
		this.playerNumber = playerNumber;
	}
	
	public int getPlayerNumber(){		// return player number
		return playerNumber;
	}
	
	public int getMove(){		// currently chooses random square
	    Random rand = new Random();
	    int randomNum = rand.nextInt(gridxMax)+1;
	    return randomNum;
	}
	
	public void setGridXValues(int xMax){		// relates to grid xValue. Used to getMove()
		gridxMax = xMax;
	}
}
