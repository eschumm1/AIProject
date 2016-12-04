import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {
	
	public Human() {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
    			super.yourField[i][j] = '~';
    			super.knownMap[i][j] = '~';
    		}
	}
	
	public void doMove(Player opponent){
		int row, column;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		do{
       			System.out.print("Row: ");
			try {
				row = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				row = 0;
			}
		}while(row < 0 || row > 9);

		do{
	      	  	System.out.print("Column: ");
			try {
				column = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				column = 0;
			}
		}while(column < 0 || column > 9);

		boolean destroyed;
		try{
			Ship target = (Ship)(opponent.yourField[row][column]);
			destroyed = target.isHit();
			
			
			if(!destroyed)
				System.out.println(this.name + " hit " + opponent.name + "'s " + target.getClass().getName().toString() + "!");
			else
				System.out.println(this.name + " destroyed " + opponent.name + "'s " + target.getClass().getName().toString() + "!");
			
			opponent.yourField[row][column] = "X";
			this.knownMap[row][column] = 'X';
			
		} catch(Exception ex){
			
			if(this.knownMap[row][column] == '~'){
				System.out.println(this.name + " missed!");
				opponent.yourField[row][column] = "O";
				this.knownMap[row][column] = 'O';
			}
			else{
				System.out.println(this.name + " already hit that space!");
				doMove(opponent);
			}
		}
	
	}

}
