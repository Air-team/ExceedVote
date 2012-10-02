/**
 * 
 * @author Busarat Jum
 *
 */
public class Ballot {
	
	public int value;
	
	public Ballot(int value)
	{
		this.value = value;
	}
	
	public int getballot()
	{
		return this.value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	public void reduceValue(int value)
	{
		
		this.value -= value; 
	}
}
