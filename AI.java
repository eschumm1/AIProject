public class AI extends Player{
    int difficulty;
    
	public AI(int difficulty, String name){
	    this.difficulty = difficulty;
	    super.name = name;
	    
	    for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
    			super.yourField[i][j] = "~";
    			super.knownMap[i][j] = '~';
    		}
	    
        placeShip(new Carrier());
        placeShip(new Battleship());
        placeShip(new Cruiser());
        placeShip(new Destroyer());
        placeShip(new Submarine());	
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
	
	//TODO
	private void easyMove(Player opponent){
		int row = (int)(Math.random() * 10);
		int column = (int)(Math.random() * 10);
		boolean destroyed;
		
		try{
			Ship target = (Ship)(opponent.yourField[row][column]);
			destroyed = target.isHit();
			
			
			if(!destroyed)
				System.out.println("\n" + this.name + " hit " + opponent.name + "'s " + target.getClass().getName().toString() + "!");
			else{
				System.out.println("\n" + this.name + " destroyed " + opponent.name + "'s " + target.getClass().getName().toString() + "!");
				opponent.shipsDestroyed++;
			}
			
			opponent.yourField[row][column] = "X";
			this.knownMap[row][column] = 'X';
			
		} catch(Exception ex){
			if(this.knownMap[row][column] == '~'){
				System.out.println("\n" + this.name + " missed!");
				opponent.yourField[row][column] = "O";
				this.knownMap[row][column] = 'O';
			}
			else{
				System.out.println("\n" + this.name + " already hit that space!");
				easyMove(opponent);
			}
		}
	}

	private void mediumMove(){
		
	}
	
	private void hardMove(){
		
	}

	private void placeShip(Ship ship) {
            boolean valid;
            
            do {
                valid = true;
                int row, column;
                int direction = (int) (Math.random() * 4);
                
                //0 = Up, 1 = Down, 2 = Left, 3 = Right
                switch (direction) {
                    case 0:                    	
                        row = (int) (Math.random() * (10 - ship.size)) + ship.size;
                        column = (int) (Math.random() * 10);

                        for (int i = row; i != row - ship.size && valid; i--){
                        	if(yourField[i][column] != "~")
                        		valid = false;
                        }
                        
                        if(!valid)
                        	continue;
                        
                        for (int i = row; i != row - ship.size; i--)
                        	super.yourField[i][column] = ship;
                        
                        break;

                    case 1:
                        row = (int) (Math.random() * (10 - ship.size));
                        column = (int) (Math.random() * 10);

                        for (int i = row; i != row + ship.size && valid; i++){
                        	if(yourField[i][column] != "~")
                        		valid = false;
                        }

                        if(!valid)
                        	continue;
                        
                        for (int i = row; i != row + ship.size; i++)
                        	yourField[i][column] = ship;

                        break;

                    case 2:
                        row = (int) (Math.random() * 10);
                        column = (int) (Math.random() * (10 - ship.size)) + ship.size;

                        for (int i = column; i != column - ship.size && valid; i--){
                        	if(yourField[row][i] != "~")
                        		valid = false;
                        }
                        
                        if(!valid)
                        	continue;
                        
                        for (int i = column; i != column - ship.size; i--)
                            yourField[row][i] = ship;

                        break;

                    default:
                        row = (int) (Math.random() * 10);
                        column = (int) (Math.random() * (10 - ship.size));

                        for (int i = column; i != column + ship.size && valid; i++){
                        	if(yourField[row][i] != "~")
                        		valid = false;
                        }

                        if(!valid)
                        	continue;
                        
                        for (int i = column; i != column + ship.size; i++)
                            yourField[row][i] = ship;
                        
                        break;                        	
                }
            } while(!valid);
	}
        
}