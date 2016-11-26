import java.util.ArrayList;

public class Player {
	ArrayList<String> usedSpaces = new ArrayList<String>(15);
    Object[][] yourField = new Object[10][10];
    char[][] knownMap = new char[10][10];
	
    @SuppressWarnings("unused")
	public Player() {
    	for(int i = 0; i < 10; i++)
    		for(int j = 0; j < 10; j++){
    			yourField[i][j] = '~';
    			knownMap[i][j] = '~';
    		}
	}
    
    public void doMove(Player opponent){
		
	}
    
    public void doMove(AI opponent){
		
	}
    
    public boolean getLoss(){
		return false;
    }
    
    @Override
    public String toString(){
		String total = "";
		
		for(Object[] temp1 : yourField){
			for(Object temp2 : temp1)
				total += temp2.toString().charAt(0);
			total += '\n';
		}
		
		for(char[] temp : knownMap){
			for(char temp2 : temp)
				total += temp2;
			total += '\n';
		}
		return total;
    }

}
