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
	
	private void placeShips(){
		boolean valid;
		do{
			valid = true;
			
			//int row = (int)(Math.random() * 10);
			//int column = (int)(Math.random() * 10);
			//int direction = (int)(Math.random() * 4);
			int direction = 1;
			//1 = Up, 2 = Down, 3 = Right, 4 = Left
			
			switch(direction){
				case 1:
					int row = (int)(Math.random() * 5) + 5;
					int column = (int)(Math.random() * 10);
					
					for(int i = row; i != row - 5; i--)
						if(usedSpaces.contains(Integer.toString(i) + ", " + Integer.toString(column)));
							valid = false;
					
					if(valid){
						
					}
					
					break;
				case 2:
					
				case 3:
					
				default:
					
			}
		}while(!valid);
		
		
		
		Battleship temp = new Battleship();
		
	}
}