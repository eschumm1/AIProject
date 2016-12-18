import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Silent on 12/4/16.
 */
public class Board {
    public int size = 100;
   // public int type; // AI = 0, human = 1

    public ArrayList<Ship> ships = new ArrayList<Ship>(5);
    private int[] field = new int[100]; // char representation of field - only 1 for ship and 0 for notship

    public Board() {
        Arrays.fill(this.field,0); // default get filled with zero?
       // if(type == 0) { generateBoard(); }
    }

    public char at(int pos) {
        if( field[pos] > 0)
        	return 'X';
        
        return '~'; // returns 1 if ship there, 0 if not
    }

    // assumes you have determined there is a ship there
    public String addHit(int pos) {
        // determine which ship it is
        String type = "", ret = "";

        boolean dest = false;

        for(Ship ship : ships) {
            for(int n = 0; n < ship.size; n++) {
                if(ship.coordinates[n] == pos) {
                	dest = ship.isHit();
                	ship.display[n] = 'X';
                	type = ship.name;
                	break;
                }
            }
        }

        ret = dest ? "You sunk my " + type + "!" : "You hit my " + type + "!";

        // check if game is over?
        return ret;

    }

    /* Check if all ships have been destroyed */
    public boolean gameOver() {
        for(Ship ship : ships) {
            if(ship.destroyed == false) { return false; }
        }
        return true;
    }

    /* for generating the board randomly */
  //  public void generateBoard() {
        // operating on this board

        /* Want: to generate a battleship board
         *  1) pick a ship to place randomly -  (not done yet)
         *  2) pick point, direction which doesn't violate space constraints
         */
        //;

    //}

    /* type := 1:AircraftCarrier(5) ; 2:Battleship(4) ; 3:submarine(3) ; 4:cruiser(3) ; 5:destroyer(2) */
    /* direction := 0:Down, 1:Left, 2:Up, 3:Right*/
    public boolean place(Ship ship, int row, int col, int direction) {
        int[] coords = new int[ship.size];
        int pos = row * 10 + (col);
        
        if(pos < 0 || pos > 99)
        	return false;

        int orient = (direction - 2 >= 0) ? 1 : -1; // direction that you move
        int p = 0;

        for(int z = 0; z < ship.size; z++) {

            switch (direction % 2) {
                case 0: 
                	p = pos + z * orient * 10; // move one col in + or - direction
                	break;
                	
                case 1: 
                	p = pos + z * orient; // move one row in + or - direction
                	
                	if(z != 0 && coords[z - 1] / 10 != p / 10) //Check that horizontal ship placement doesn't leave row
                		return false;
                	
                	break;
            }
            
            if(p < 0 || p > 99) //If spot is off the board - NOT valid move
            	return false;
            
            if(this.field[p] == 1) //If spot is already taken - NOT valid move
            	return false;
            
            coords[z] = p; // else add to coordinate list
            this.field[p] = 1;
        }

        ship.coordinates = coords;
        this.ships.add(ship);

        for(int temp : ship.coordinates)
        	this.field[temp] = 1;
        
        //for(int zz = 0; zz < ship.size; zz++) { field[coords[zz]] = 1; System.out.println("Placed at " + coords[zz] + "\n"); } // for debugging

        return true;
    }
}
