import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Human extends Player {
	int row, column;
	String[] location = new String[2];
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Human(String name) {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
				super.knownMap[i*10 + j] = '~';
    		}

		super.name = name;
      		this.placeShip( new Ship("Carrier") );
     	 	this.placeShip( new Ship("Battleship") );
      		this.placeShip( new Ship("Cruiser") );
      		this.placeShip( new Ship("Destroyer") );
     		this.placeShip( new Ship("Submarine") );
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
			else if(knownMap[row*10 + column] != '~') {
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
      	
      	System.out.println(this.toString());
      	   
    	do {
    		try {
    			System.out.println("\nWhere would you like to place the beginning of your " + ship.toString() + " (" + ship.size + " spaces)?");
				location[0] = br.readLine();
				
				if(location[0].length() != 2)
					throw new Exception();
				
				System.out.println("What direction will the end of the ship be in?\n0) Up\n1) Left\n2) Down\n3) Right");
				direction= Integer.parseInt(br.readLine());
				location = location[0].split("");
				location[0] = String.valueOf((int)location[0].toUpperCase().charAt(0) - (int)'A');
				
				if(Integer.valueOf(location[0]) < 0 || Integer.valueOf(location[0]) > 9 || Integer.valueOf(location[1]) < 0
						|| Integer.valueOf(location[1]) > 9 || direction < 0 || direction > 4)
					throw new Exception();
			} catch (Exception e) {
				System.out.println("Invalid entry. Please try again \n");
				continue;
			}
    		
			valid = yourField.place(ship, Integer.parseInt(location[1]), Integer.parseInt(location[0]), direction);
	
			if(!valid)
				System.out.println("Sorry, that wasn't a valid entry.\n");

		} while(!valid);

	}

}
