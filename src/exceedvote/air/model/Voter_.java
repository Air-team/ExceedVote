package exceedvote.air.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-25T23:17:19.205+0700")
@StaticMetamodel(Voter.class)
public class Voter_ {
	public static volatile SingularAttribute<Voter, Integer> id;
	public static volatile SingularAttribute<Voter, String> name;
	public static volatile SingularAttribute<Voter, String> type;
	public static volatile SingularAttribute<Voter, String> password;
	public static volatile SingularAttribute<Voter, Integer> amountOfBallot;
}
