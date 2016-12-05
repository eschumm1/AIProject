import java.util.Arrays;

public class Ship {
	public int size, hits;
	public boolean destroyed = false;
	public String name;
	public char[] display;
	public int[] coordinates;

	public Ship(String name) {
		this.name = name;
		switch(name)
		{ 	case "battleship": { this.size = 4; }
			case "carrier": { this.size = 5; }
			case "cruiser": { this.size = 3; }
			case "destroyer": { this.size = 2; }
			case "submarine": { this.size = 3; } }
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