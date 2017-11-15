import java.io.*;

public class HumanPlayer extends Player{

	private BufferedReader in;	// class variables
	private PrintStream out;
	private int playerNumber;
	
	public HumanPlayer(int playerNumber){
		in = new BufferedReader(new InputStreamReader(System.in));	// initialise input/output/number
		out = new PrintStream((OutputStream)(System.out));
		this.playerNumber = playerNumber;
	}
	
	public int getMove(){	// use of isomorphisism to replace Player method for getMove()
		
		String input = "";	// initialise inputs
		int integerInput = 0;
		
		while(true){		// try to read the user input / try again if fail
			try{
				out.print("\n\n Enter your grid index and place a piece: ");
				input = in.readLine();
				out.print("");
				try{
					integerInput = Integer.parseInt(input);
					break;
				}
				catch(NumberFormatException invalidNumber){
					out.println(" You didnt enter a number, please try again!");
					continue;
				}
			}
			catch(IOException invalidInput){
				out.println(" You entry was invalid, please try again!");
				continue;
			}
		}
		
		return integerInput;   // relates to index 
		
	}
	
	public int getPlayerNumber(){		// returns player number
		return playerNumber;
	}
	
}
