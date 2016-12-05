import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {
	
	public Human() {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
				super.knownMap[i + 10*j] = '~';
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

		int pos = row*10 + column;
		String hit;
		hit = opponent.mark(pos); // returns "hit [type]" "miss", "already..." or "destroyed [type]"
		System.out.println(this.name + hit);
		knownMap[pos] = (hit.contains("miss")) ? 'O' : 'X'; // mark move in map
	
	}

}
