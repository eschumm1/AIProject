public class Human extends Player {
	
	public Human() {
		for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
    			super.yourField[i][j] = '~';
    			super.knownMap[i][j] = '~';
    		}
	}
	
	public void doMove(Player opponent){
		
	}
}
