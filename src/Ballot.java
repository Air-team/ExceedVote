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
	
	public void setBallot(int value)
	{
		this.value = value;
	}
	public void reduceBallot(int value)
	{
		
		this.value -= value; 
	}
}
