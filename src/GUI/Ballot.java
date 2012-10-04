package GUI;

/**
 * 
 * @author Busarat Jum
 *
 */
public class Ballot {
	
	public int value;
	
	public Ballot()
	{
	    value = 0;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setValue(String name)
	{
		if(name.equals("STUDENT"))
		this.value = 1;
		else if(name.equals("TEACHER"))
		this.value = 3;
	}
	public void reduceValue(int value)
	{
		if(this.value>0) this.value -= value; 
		else this.value = 0;
	}
}
