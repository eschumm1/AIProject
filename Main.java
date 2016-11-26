import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	private static int players, difficulty;
	private static Player player1, player2;
	private static AI computer1, computer2;
	private static boolean won = false;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to Battleship!\nHow many players will there be? (Enter 0 for computer vs. computer)");
		do{
			try {
				players = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				players = 0;
			}
		}while(players < 1 || players > 2);
		
		if(players != 2){
			do{
				System.out.println("What dificulty should computer 1 be?\n1) Easy\n2) Medium\n3) Hard");
				try {
					difficulty = Integer.parseInt(br.readLine());
				} catch (IOException e) {
					System.err.println("Invalid entry.");
					difficulty = 0;
				}
			}while(difficulty < 1 || difficulty > 3);
			
			AI computer1 = new AI(difficulty);
		}
		
		if(players != 0){
			Player player1 = new Player();
		}
		
		if(players == 1){
			Player player2 = new Player();
			
			do{
				System.out.println("What dificulty should computer 2 be?\n1) Easy\n2) Medium\n3) Hard");
				try {
					difficulty = System.in.read();
				} catch (IOException e) {
					System.err.println("Invalid entry.");
					difficulty = 0;
				}
			}while(difficulty < 1 || difficulty > 3);
			
			AI computer2 = new AI(difficulty);
		}
		
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
