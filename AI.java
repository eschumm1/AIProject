public class AI extends Player{
    int difficulty;
//    public Board yourField = new Board(0);

    public AI(int difficulty, String name){
        this.difficulty = difficulty;
        super.name = name;

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++){
                //super.yourField[i + 10*j] = "~";
                super.knownMap[i*10 + j] = '~';
            }

        this.placeShip( new Ship("carrier") );
        this.placeShip( new Ship("battleship") );
        this.placeShip( new Ship("cruiser") );
        this.placeShip( new Ship("destroyer") );
        this.placeShip( new Ship("submarine") );
    }

    public int getDifficulty(){
        return this.difficulty;
    }


    public void doMove(Player opponent){
		if(this.difficulty == 1)
			easyMove(opponent);
		else if(this.difficulty == 2)
			mediumMove();
		else
			hardMove();
	}

	private void easyMove(Player opponent){
		int row = (int)(Math.random() * 10);
		int column = (int)(Math.random() * 10);
		int pos = row*10 + column;
		String hit;

        hit = opponent.mark(pos); // returns "hit [type]" "miss" or "destroyed [type]"

        System.out.println(this.name + " " + hit);
			
        knownMap[pos] = (hit.contains("miss")) ? 'O' : 'X'; // mark move in map
	}

	private void mediumMove(){
        ;
	}
	
	private void hardMove(){
		// do with model of prob(ship in square)

		// ? optimize ship placement based on winning initial configurations
	}

	private void placeShip(Ship ship) {

        /* replacing soon - left in so revision works */
        boolean valid;

        do {
            valid = true;
            int row, column, pos;
            int direction = (int) (Math.random() * 4);

            //0 = Down, 1 = Left, 2 = Up, 3 = Right
            switch (direction) {
                case 0: {
                    row = (int) (Math.random() * (10 - ship.size)) + ship.size;
                    column = (int) (Math.random() * 10);
                    valid = yourField.place(ship, row, column, direction);

                    if (!valid) {
                        continue;
                    }
                    break;
                }
                case 1: {
                    row = (int) (Math.random() * (10 - ship.size));
                    column = (int) (Math.random() * 10);

                    valid = yourField.place(ship, row, column, direction);

                    if (!valid) {
                        continue;
                    }

                    break;
                }
                case 2: {
                    row = (int) (Math.random() * 10);
                    column = (int) (Math.random() * (10 - ship.size)) + ship.size;

                    valid = yourField.place(ship, row, column, direction);

                    if (!valid) {
                        continue;
                    }

                    break;
                }
                case 3: {
                    row = (int) (Math.random() * 10);
                    column = (int) (Math.random() * (10 - ship.size));

                    valid = yourField.place(ship, row, column, direction);

                    if (!valid) {
                        continue;
                    }

                    break;
                }
            }
        } while (!valid);
    }
        
}