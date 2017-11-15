
public class Player {

	private int playerNumber;
	
	public Player(){	// initilise to 999 if something goes wrong in program
		playerNumber = 999;
	}
	
	public int getMove(){	// Overwritten by both computer and humanPlayer
		return 999;
	}
	
	public int getPlayerNumber(){	// Overwritten by both computer and humanPlayer
		return playerNumber;
	}
	
	public void setGridXValues(int maxXValue){	// only relevent to computer players
	}
	
}
