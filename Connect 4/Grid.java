
public class Grid {

	private int[][] grid; 		// class variables
	private int lastYInput;
	private int lastXInput;
	private int inARow;
	private String seperatorString;

	public Grid(int gridXDimension, int gridYDimension, int inARow){
		this.inARow = inARow;		// initialise all variables
		grid = new int[gridYDimension][gridXDimension];

		seperatorString = "---------";
		for(int i=0;i<grid[0].length;i++){  // sets length of string so it looks nice when printed
			seperatorString += "-------";
		}
	}

	public String playPiece(int playerNumber,int xCoordinate){	// places piece according to player input

		int yCoordinate =0;		// work out y coordinate to place new piece

		if(xCoordinate>grid[0].length){		// error checks
			return "Index not valid, try again.";
		}

		if(grid[0][xCoordinate] != 0){	// error check that the column selected has space
			return "Column full, index not valid.";
		}

		for(int i=1; i< grid.length;i++){		// works out what the current Y-position is for the X picked
			if(grid[i][xCoordinate] == 0){
				yCoordinate++;
			}
			else{
				break;
			}
		}


		if(playerNumber == 1){	// set player number to grid input
			grid[yCoordinate][xCoordinate] = 1;
		}
		else if(playerNumber == 2){
			grid[yCoordinate][xCoordinate] = 2;
		}
		else if(playerNumber == 3){
			grid[yCoordinate][xCoordinate] = 3;
		}
		else if(playerNumber == 4){
			grid[yCoordinate][xCoordinate] = 4;
		}

		lastYInput = yCoordinate;		// takes note of last input
		lastXInput = xCoordinate;

		return "Success";
	}

	public String toString(int playerNumber){	// returns board string

		String gridString = "\n\n"+ seperatorString;
		gridString += "\n CurrentPlayer: " + playerNumber + "\t Symbol: " + getPlayerSymbol(playerNumber) +"\n Current Board: \n";


		for (int j = 0; j<grid.length; j++){		// adds player symbols instead of numbers
			gridString += "\n\t ";
			for (int i = 0; i<grid[0].length; i++){
				if(grid[j][i] == 1){
					gridString += "x\t";
				}
				else if(grid[j][i] == 2){
					gridString += "o\t";
				}
				else if(grid[j][i] == 3){
					gridString += "#\t";
				}
				else if(grid[j][i] == 4){
					gridString += "$\t";
				}
				else{
					gridString += " \t";
				}
			}   
		}

		gridString += "\n index:\t ";		// adds index (useful for human player)

		for (int j = 0; j<grid[0].length; j++){
			gridString += (j+1) +"\t";
		}
		gridString += "\n";
		gridString += seperatorString;

		return gridString;
	}

	public String getPlayerSymbol(int playerNumber){	// returns player symbol relating to player number
		if(playerNumber == 1){
			return "x";
		}
		else if(playerNumber == 2){
			return "o";
		}
		else if(playerNumber == 3){
			return "#";
		}
		else{
			return "$";
		}
	}

	public boolean checkWinningCondition(int playerNumber){		// checks if game is won

		int piecesInOrder = 1;

		// check horizontal win
		int i = lastXInput-1;
		while(i>=0){	// checks to the left of input
			if(grid[lastYInput][i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i--;
		}

		i=lastXInput+1;
		while(i<grid[0].length){	// checks to the right of input
			if(grid[lastYInput][i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		if(piecesInOrder >= inARow){	// checks if greater than winning condition
			return true;
		}


		// check vertical win
		piecesInOrder =1;
		i = lastYInput+1;
		while(i<grid.length){
			if(grid[i][lastXInput] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		if(piecesInOrder >= inARow){
			return true;
		}

		// check diagonal (up/right & down/left) win

		piecesInOrder =1;
		i = 1;
		while(lastYInput - i>=0 && lastXInput+i <grid[0].length){
			if(grid[lastYInput - i][lastXInput + i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		i = 1;
		while(lastYInput + i< grid.length && lastXInput-i >=0){
			if(grid[lastYInput + i][lastXInput - i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		if(piecesInOrder >= inARow){
			return true;
		}

		// check diagonal (up/left & down/right) win

		piecesInOrder =1;
		i = 1;
		while(lastYInput - i>=0 && lastXInput-i >=0){
			if(grid[lastYInput - i][lastXInput - i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		i = 1;
		while(lastYInput + i< grid.length && lastXInput+i < grid[0].length){
			if(grid[lastYInput + i][lastXInput + i] != playerNumber){
				break;
			}
			piecesInOrder++;
			i++;
		}

		if(piecesInOrder >= inARow){
			return true;
		}

		return false;
	}

	public int getLastXInput(){		// returns last inputs
		return lastXInput;
	}

	public int getLastYInput(){
		return lastYInput;
	}

	public String getSeperatorString(){		// returns separator string
		return seperatorString;
	}
	
	public boolean checkIfAMoveExists(){	// checks if game is drawn
		for(int i =0; i<grid[0].length;i++){
			if(grid[0][i] ==0){
				return true;
			}
		}
		return false;
	}
}
