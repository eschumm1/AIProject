public abstract class Ship{
	protected int size, hits;
	protected boolean destroyed = false;

	public boolean isHit(){
		hits++;
		
		if (hits == size)
			destroyed = true;
		
		return destroyed;
	}

	public String toString(){
		return this.getClass().getName();
	}
}