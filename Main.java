import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	private static int players, difficulty;
	private static Human player1, player2;
	private static AI computer1, computer2;
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
				players = 0;
			}
		}while(players < 1 || players > 2);
		
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
			
			computer1 = new AI(difficulty);
		}
		
		//If there's at least 1 human player
		if(players != 0)
			player1 = new Human();
		
		//If there's 2 human players
		if(players == 2)	//2
			player2 = new Human();
		
		//If there's no human players
		if(players == 0){
			//Loop until a valid difficulty is entered
			do{
				System.out.println("What dificulty should computer 2 be?\n1) Easy\n2) Medium\n3) Hard");
				try {
					difficulty = System.in.read();
				} catch (IOException e) {
					System.err.println("Invalid entry.");
					difficulty = 0;
				}
			}while(difficulty < 1 || difficulty > 3);
			
			computer2 = new AI(difficulty);
		}
		
		//Primary control logic
		switch(players){
		case 0:
			do{
				System.out.println("Press any key for the next player...");
				new java.util.Scanner(System.in).nextLine();
				computer1.doMove(computer2);
				won = computer2.getLoss();
				
				if(!won){
					System.out.println("Press any key for the next player...");
					new java.util.Scanner(System.in).nextLine();
					computer2.doMove(computer1);
					won = computer1.getLoss();
				}
			}while(!won);
			
			break;
			
		case 1:
			do{
				player1.doMove(computer1);
				won = computer1.getLoss();
				if(!won){
					computer1.doMove(player1);
					won = player1.getLoss();
				}
			}while(!won);
			
			break;
			
		case 2:
			do{
				System.out.println(player1.toString());
				player1.doMove(player2);
				won = player2.getLoss();
				if(!won){
					System.out.println("Press any key for the next player...");
					new java.util.Scanner(System.in).nextLine();
					player2.doMove(player1);
					won = player1.getLoss();
				}
			}while(!won);
			
			break;
		}
	}
}
