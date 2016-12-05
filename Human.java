import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {
	
	public Human() {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
				super.knownMap[i*10 + j] = '~';
    		}
	}
	
	public void doMove(Player opponent){
		int row, column;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean repeat = false;

		do{
			System.out.print("Row: ");
			try {
				row = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				row = -1;
			}

			System.out.print("Column: ");
			try {
				column = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				column = -1;
			}

			if((row < 0) && (row >= 10) && (column < 0) && (column >= 10)) {
				System.out.println("Bad coordinates. try again \n");
			}
			else if(knownMap[row*10 + column] == 'X') {
				System.out.println("You already made that move! \n");
				row = -1; column = -1;
			}
		} while((row >= 10) || (row < 0) || (column >= 10) || (column < 0));

		int pos = row*10 + column;
		String hit;
		hit = opponent.mark(pos); // returns "hit [type]" "miss", "already..." or "destroyed [type]"
		System.out.println(this.name + " " +  hit);
		knownMap[pos] = (hit.contains("miss")) ? 'O' : 'X'; // mark move in map
	
	}

}
