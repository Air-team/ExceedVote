/**
 * Ballot limits quota for the voter to vote.
 * 
 * @author Busarat Jum
 *
 */
public class Ballot {
	
	/** The quotas for vote */
	public int quota;
	
	/**  
	 * Ballot Constructor.
	 * @param value is the quotas for vote.
	 */
	public Ballot(int value)
	{
		this.quota = value;
	}
	
	/**  
	 * Get the remaining quota.
	 * @return value is the remaining quota.
	 */
	public int getballot()
	{
		return quota;
	}
	
	/** 
	 * Change the value of quota.
	 * @param value is the amount of value for change the quota.
	 */
	public void setBallot(int value)
	{
		this.quota = value;
	}
	
	/** 
	 * Reduce the quota by the given value.
	 * @param value is the amount of value for change the quota.
	 */
	public void reduceBallot(int value)
	{
		this.quota -= value; 
	}
}
