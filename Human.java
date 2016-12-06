import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Human extends Player {
	int row, column;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Human(String name) {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
				super.knownMap[i*10 + j] = '~';
    		}

		super.name = name;
      		this.placeShip( new Ship("carrier") );
     	 	this.placeShip( new Ship("battleship") );
      		this.placeShip( new Ship("cruiser") );
      		this.placeShip( new Ship("destroyer") );
     		this.placeShip( new Ship("submarine") );
     		Arrays.fill(this.intmap, -1);
	}
	
	public void doMove(Player opponent){
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

			if((row < 0) || (row >= 10) || (column < 0) || (column >= 10)) {
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
		intmap[pos] = (hit.contains("miss")) ? 0 : (hit.contains("hit") ? 1 : (hit.contains("royer") ? 2 : (hit.contains("ubmarin") ? 3 : (hit.contains("attlesh") ? 4 : 5))));

	}

	private void placeShip(Ship ship) {
		boolean valid;
		int direction;

      	    	valid = true;
    	   	do {
			System.out.print("Choose a row to place your " + ship.name + ": ");
			try {
				row = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				row = -1;
			}

			System.out.print("Choose a column to place your ship: ");
			try {
				column = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				column = -1;
			}

			System.out.println("What direction should the ship be?\n0) Down\n1) Left\n2) Up\n3) Right");
			try {
				direction= Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.err.println("Invalid entry.");
				direction = -1;
			}
			valid = yourField.place(ship, row, column, direction);

			if((row >= 10) || (row < 0) || (column >= 10) || (column < 0) || (direction < 0) || (direction > 4)) {
				System.out.println("Bad coordinates. try again \n");
			}
	
			if((!valid)) {
				System.out.println("You already placed a ship in that position! \n");
			}

		} while((row >= 10) || (row < 0) || (column >= 10) || (column < 0) || (direction < 0) || (direction > 4) || (!valid));

	}

}
