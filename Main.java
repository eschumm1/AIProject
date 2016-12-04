import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	private static int players, difficulty;
	private static Player player1, player2, computer1, computer2, current, opponent;
	private static boolean won = false;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to Battleship!\nHow many players will there be? (Enter 0 for computer vs. computer)");		//Initial welcome message
		
		//Loop until a valid number of players is entered. Choices are 0 (AI vs. AI), 1, or 2.
		do{
			try {
				players = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				players = -1;
			}
		}while(players < 0 || players > 2);
		
		//If there's at least one computer
		if(players != 2){
			//Loop until a valid difficulty is entered
			do{
				System.out.println("What dificulty should computer 1 be?\n1) Easy\n2) Medium\n3) Hard");
				try {
					difficulty = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					System.err.println("Invalid entry.");
					difficulty = 0;
				}
			}while(difficulty < 1 || difficulty > 3);
			
			computer1 = new AI(difficulty, "Computer 1");
		}
		
		//If there's at least 1 human player
		if(players != 0)
			player1 = new Human("Player 1");
		
		//If there's 2 human players
		if(players == 2)	//2
			player2 = new Human("Player 2");
		
		//If there's no human players
		if(players == 0){
			//Loop until a valid difficulty is entered
			do{
				System.out.println("What dificulty should computer 2 be?\n1) Easy\n2) Medium\n3) Hard");
				try {
					difficulty = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					System.err.println("Invalid entry.");
					difficulty = 0;
				}
			}while(difficulty < 1 || difficulty > 3);
			
			computer2 = new AI(difficulty, "Computer 2");
		}
		
		//Primary control logic
		/*switch(players){
			case 0:
				do{
					System.out.println("Press any key for the next player...");
					new java.util.Scanner(System.in).nextLine();
					current = computer1;
					System.out.println("Computer 1's field:\n" + current);
					current.doMove(computer2);
					won = computer2.getLoss();
					
					if(!won){
						current = computer2;
						current.doMove(computer1);
						won = computer1.getLoss();
					}
				} while(!won);
				
				break;
				
			case 1:
				do{
					current = player1;
					current.doMove(computer1);
					won = computer1.getLoss();
					
					if(!won){
						current = computer1;
						current.doMove(player1);
						won = player1.getLoss();
					}
				} while(!won);
				
				break;
				
			case 2:
				do{
					current = player1;
					System.out.println(current.toString());
					current.doMove(player2);
					won = player2.getLoss();
					
					if(!won){
						current = player2;
						System.out.println("Press any key for the next player...");
						new java.util.Scanner(System.in).nextLine();
						current.doMove(player1);
						won = player1.getLoss();
					}
				} while(!won);
				
				break;
		}*/
		
		do{
			if(players == 0){
				if(current != null && current.equals(computer1)){
					current = computer2;
					opponent = computer1;
				}
				else{
					current = computer1;
					opponent = computer2;
					System.out.println("\n" + current.name + "'s field:\n" + current);
					System.out.println("Press any key for the next player...");
					new java.util.Scanner(System.in).nextLine();
				}
			}
			else if(players == 1){
				if(current != null && current.equals(player1)){
					current = computer1;
					opponent = player1;
				}
				else{
					current = player1;
					opponent = computer1;
					System.out.println("\n" + current.name +"'s field:\n" + current);
					System.out.println("Press any key to continue...");
					new java.util.Scanner(System.in).nextLine();
				}
			}
			else{
				if(current.equals(player1)){
					current = player2;
					opponent = player1;
				}
				else{
					current = player1;
					opponent = player2;
				}
			}
			
			current.doMove(opponent);
			won = opponent.getLoss();
		} while(!won);
		
		System.out.println(current.name + " won!");
	}
}
