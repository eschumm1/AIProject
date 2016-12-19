import java.util.Arrays;

public class Ship {
	public int size, hits;
	public boolean destroyed = false;
	public String name;
	public char[] display;
	public int[] coordinates;

	public Ship(String name) {
		this.name = name;
		switch(this.name)
		{ 	case "Battleship": { this.size = 4; break; }
			case "Carrier": { this.size = 5; break; }
			case "Cruiser": { this.size = 3; break; }
			case "Destroyer": { this.size = 2; break; }
			case "Submarine": { this.size = 3; break; }
		}
		//System.out.println(" New " + this.name + "with size " + this.size + "\n");
		display = new char[this.size] ; Arrays.fill(display, 'O');
	}

	public boolean isHit(){
		hits++;
		if (hits == size)
			destroyed = true;

		return destroyed;
	}

	public String toString(){
		return name;
	}
}