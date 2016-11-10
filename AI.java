public class AI {
    int difficulty;
    char[][] knownMap = new char[10][10];
    
	public AI(int difficulty){
	    this.difficulty = difficulty;
	}
	
	public int getDifficulty(){
	    return this.difficulty;
	}
	
	public void doMove(){
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
}