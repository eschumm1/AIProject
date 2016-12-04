import java.util.ArrayList;

public abstract class Player {
	protected ArrayList<String> usedSpaces = new ArrayList<String>(15);
    protected Object[][] yourField = new Object[10][10];
    protected char[][] knownMap = new char[10][10];
    protected String name;
    protected boolean loss = false;
    protected int shipsDestroyed = 0;
    
    public boolean getLoss(){
    	if(shipsDestroyed == 5)
    		return true;
    	
    	return false;
    }
    
    public abstract void doMove(Player opponent);
    
    @Override
    public String toString(){
		String total = "";
		
		for(char[] temp : knownMap){
			for(char temp2 : temp)
				total += temp2 + " ";
			total += '\n';
		}
		
		total += "-------------------\n";
		
		for(Object[] temp1 : yourField){
			for(Object temp2 : temp1)
				total += temp2.toString().charAt(0) + " ";
			total += '\n';
		}
		return total;
    }

}
