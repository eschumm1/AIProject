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

    @Override
    public String toString(){
		String total = "";
		
		for(int i = 0; i < 100; i++){
			total += this.knownMap[i] + ((i % 9 == 0) ? "\n" : "");
		}
		
		total += "-------------------\n";

		for(int i = 0; i < 100; i++){
			total += (this.yourField.at(i)) + ((i % 9 == 0) ? "\n" : "");
		}
		return total;
    }

}
