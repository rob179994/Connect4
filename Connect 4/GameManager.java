import java.io.*;
import java.util.*;

public class GameManager {

	private ArrayList<Player> playerArray;		// initialse all variables needed

	private Player currentPlayer;
	private int playerArrayIterator;
	private int numberOfPlayers;
	
	private int inARow;
	private int gridXDimension;
	private int gridYDimension;
	private Grid theGrid;

	private BufferedReader in;
	private PrintStream out;

	public static void main(String[] args){		// runs from the command line
		GameManager currentGame = new GameManager();
		currentGame.playGame();
	}

	public GameManager(){		// intialises input/output and creates first player
		in = new BufferedReader(new InputStreamReader(System.in));	// initialise input and output
		out = new PrintStream((OutputStream)(System.out));
		playerArray = new ArrayList<Player>();
		playerArray.add(new HumanPlayer(1));
		playerArrayIterator =0;
	}

	public void playGame(){		// main method used to run game

		out.println("\n\n WELCOME TO CONNECT 4 \n\n\n");
		
		// Game parameters designed by the user.
		setInputParameters();

		theGrid = new Grid(gridXDimension,gridYDimension,inARow);		// Create new grid and player
		
		
		setOpponents();		// choose and set opponents method

		out.println("\n\n Player 1, you will go first.");

		while(true){	// keep on having move until unable
			currentPlayer = playerArray.get(playerArrayIterator);
			out.print(theGrid.toString(currentPlayer.getPlayerNumber()));	// currentPlayer has their go until game is won

			while(true){	// get input from player and print if the move is a success
				int nextMove = currentPlayer.getMove()-1;
				String moveSuccess = theGrid.playPiece(currentPlayer.getPlayerNumber(), nextMove);

				if(moveSuccess.equals("Success")){
					out.println("\n Move has been made.\n");
					break;
				}
				else{
					out.println(" "+moveSuccess);			// check that the move is valid
				}

			}

			if(theGrid.checkWinningCondition(currentPlayer.getPlayerNumber()) == true){		// check if game is won/print finishing celebration string if won
				out.print(theGrid.toString(currentPlayer.getPlayerNumber()));
				out.print("\n\n\n" + theGrid.getSeperatorString() + "\n" + theGrid.getSeperatorString());
				out.println("\n ------------------ PLAYER " + currentPlayer.getPlayerNumber() + " (symbol - " + theGrid.getPlayerSymbol(currentPlayer.getPlayerNumber()) + ")"+ " WINS ------------------");
				out.printf(" Note: Last placed piece was coordinate: (%d,%d)",(theGrid.getLastXInput()+1),(theGrid.getLastXInput())+1);
				out.print("\n" + theGrid.getSeperatorString() + "\n" + theGrid.getSeperatorString());
				break;
			}
					// check if game is drawn and print end of game string if so
			if(theGrid.checkIfAMoveExists() == false){	
				out.print(theGrid.toString(currentPlayer.getPlayerNumber()));
				out.print("\n\n\n" + theGrid.getSeperatorString() + "\n" + theGrid.getSeperatorString());
				out.println("\n ------------------ PLAYERS HAVE DRAWN ------------------");
				out.print("\n" + theGrid.getSeperatorString() + "\n" + theGrid.getSeperatorString());
				break;
			}
			
			playerArrayIterator++; // iterate on to the next player
			if(playerArrayIterator == numberOfPlayers){
				playerArrayIterator =0;
			}
		}





	}

	private int getInput(String question){  // method to ask a specific question and return the input

		String input = "";
		int integerInput = 0;

		while(true){		// try to read the user input
			try{
				out.print("\n\n " + question + ": ");
				input = in.readLine();
				out.print("");
				try{ 		// try to convert String to Int
					integerInput = Integer.parseInt(input);
					break;

				}
				catch(NumberFormatException invalidNumber){ 	// try again if it didn't work
					out.println(" You didnt enter a number, please try again!");
					continue;
				}
			}
			catch(IOException invalidInput){
				out.println(" You entry was invalid, please try again!");
				continue;
			}
		}

		return integerInput;

	}

	private void setInputParameters(){	// asks multiple questions and sets variables
		inARow = getInput("Please input the number of connected pieces for the winning condition");
		gridXDimension = getInput("Please input the X-dimension of the grid");
		gridYDimension = getInput("Please input the Y-dimension of the grid");
	}

	private void setOpponents(){	// choose and set amount of opponents wanted
		int numberOfOpponents =1;
		while(true){		// get number of opponents input
			numberOfOpponents = getInput("Choose the number of opponents to face (between 1 and 3 opponents)");
			numberOfPlayers = numberOfOpponents+1;
			if(!(numberOfOpponents < 1 || numberOfOpponents > 3)){
				break;
			}
			out.println("\n You didn't choose between 1 and 3 opponents, choose again.");
		}
		
		int i=2;
		while(numberOfOpponents-i+2>0){						// Choose and set each opponent
			String playerType = chooseOpponentType(i-1);

			if(playerType.equals("human")){
				playerArray.add(new HumanPlayer(i));
			}
			else if(playerType.equals("computer")){
				playerArray.add(new ComputerPlayer(i));
				playerArray.get(i-1).setGridXValues(gridXDimension);
			}
			else{
				out.println(" You did not enter 'human' or 'computer', try again. ");
				continue;
			}
			i++;
		}
	}

	private String chooseOpponentType(int playerNumber){  // select either human or computer
		String input = "";
		try{
			out.print("\n\n Choose the type of opponent "+ playerNumber + ", 'human' or 'computer': ");
			input = in.readLine();
			out.print("");
		}
		catch(IOException invalidInput){
		}
		return input;
	}

}
