public abstract class Ship{
	protected int size, hits;
	protected boolean destroyed;

	public boolean isHit(){
		hits++;
		if (hits == size)
			destroyed = true;
		
		return destroyed;
	}
}