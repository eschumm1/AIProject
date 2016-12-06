import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player {
	public ArrayList<String> usedSpaces = new ArrayList<String>(15);
	public char[] knownMap = new char[100];
	//public char
	public Board yourField = new Board(); //1);

	protected String name;
	protected boolean loss = false;
	protected int shipsDestroyed = 0;

	public void fillBoard() {
		Arrays.fill(this.knownMap, '~');
	}
	
	public abstract void doMove(Player opponent);
	
	public boolean getLoss(){
		if(shipsDestroyed == 5)
			return true;

		return false;
	}

	public String mark(int pos) {
		if(yourField.at(pos) == '~') {
			return "missed!\n";
		}

		// determine your ship at position
		String hitstr = "";
		boolean sunk = false;
		// mark your ship as hit
		hitstr = yourField.addHit(pos);
		return hitstr;

	}

    public String toString(Player opponent){
		String total = "";
		
		for(int i = 0; i < 100; i++){
			if (i % 10 == 0)
				total += "\n";
			
			total += this.knownMap[i] + " ";
		}
		
		total += "\n-------------------";
		
		for(int i = 0; i < 100; i++){
			boolean found = false;
			
			if (i % 10 == 0)
				total += "\n";
			
			//total += opponent.knownMap[i] + " ";
			if(opponent.knownMap[i] == '~')
				for(Ship ship : this.yourField.ships)
					for(int current : ship.coordinates)
						if(current == i){
							total += ship.name.charAt(0) + " ";
							found = true;
						}
			
			if(!found)
				total += opponent.knownMap[i] + " ";
						
		}
		
		return total;
    }
    
    //toString function for use during ship placement
    @Override
    public String toString(){
		return null;
		
    }

}
