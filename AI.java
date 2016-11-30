public class AI extends Player{
    int difficulty;
    
	public AI(int difficulty){
	    this.difficulty = difficulty;
	    
	    for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
    			yourField[i][j] = '~';
    			knownMap[i][j] = '~';
    		}
	    
	    placeShips();
	}
	
	public int getDifficulty(){
	    return this.difficulty;
	}
	
	public void doMove(Player opponent){
		if(this.difficulty == 1)
			easyMove();
		else if(this.difficulty == 2)
			mediumMove();
		else
			hardMove();
	}
	
	//TODO
	private void easyMove(){
		
	}

	private void mediumMove(){
		
	}
	
	private void hardMove(){
		
	}
	
	private void placeShips() {
            System.out.println("Press");
            Carrier carrier = new Carrier();
            placeShip(carrier);

            Battleship battleship = new Battleship();
            placeShip(battleship);

            Cruiser cruiser = new Cruiser();
            placeShip(cruiser);

            Destroyer destroyer = new Destroyer();
            placeShip(destroyer);
	
            Submarine submarine = new Submarine();
            placeShip(submarine);	
	}

	private void placeShip(Ship ship) {
            System.out.println("Press any");
            boolean valid;
            do {
                valid = true;

                //int row = (int)(Math.random() * 10);
                //int column = (int)(Math.random() * 10);
                int row, column;
                int direction = (int) (Math.random() * 4);
                //int direction = 1;
                //0 = Up, 1 = Down, 2 = Left, 3 = Right

                switch (direction) {
                    case 0:
                        row = (int) (Math.random() * (10 - ship.size)) + ship.size;
                        column = (int) (Math.random() * 10);

                        for (int i = row; i != row - ship.size; i--) {
                            if (usedSpaces.contains(Integer.toString(i) + ", " + Integer.toString(column))) {
                                valid = false;
                            }
                        }

                        if (valid) {
                            for (int i = row; i != row - ship.size; i--) {
                                usedSpaces.add(Integer.toString(i) + ", " + Integer.toString(column));
                            }
                        }

                        break;

                    case 1:
                        row = (int) (Math.random() * (10 - ship.size));
                        column = (int) (Math.random() * 10);

                        for (int i = row; i != row + ship.size; i++) {
                            if (usedSpaces.contains(Integer.toString(i) + ", " + Integer.toString(column))) {
                                valid = false;
                            }
                        }

                        if (valid) {
                            for (int i = row; i != row + ship.size; i++) {
                                usedSpaces.add(Integer.toString(i) + ", " + Integer.toString(column));
                            }
                        }

                        break;

                    case 2:
                        row = (int) (Math.random() * 10);
                        column = (int) (Math.random() * (10 - ship.size)) + ship.size;

                        for (int i = column; i != column - ship.size; i--) {
                            if (usedSpaces.contains(Integer.toString(row) + ", " + Integer.toString(i))) {
                                valid = false;
                            }
                        }

                        if (valid) {
                            for (int i = column; i != column - ship.size; i--) {
                                usedSpaces.add(Integer.toString(row) + ", " + Integer.toString(i));
                            }
                        }

                        break;

                    default:
                        row = (int) (Math.random() * 10);
                        column = (int) (Math.random() * (10 - ship.size));

                        for (int i = column; i != column + ship.size; i++) {
                            if (usedSpaces.contains(Integer.toString(row) + ", " + Integer.toString(i))) {
                                valid = false;
                            }
                        }

                        if (valid) {
                            for (int i = column; i != column + ship.size; i++) {
                                usedSpaces.add(Integer.toString(row) + ", " + Integer.toString(i));
                            }
                        }

                        break;
                        
                }

            } while (!valid);

	}
        
}