package exceedvote.air.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-25T23:17:19.202+0700")
@StaticMetamodel(Committee.class)
public class Committee_ {
	public static volatile SingularAttribute<Committee, Integer> id;
	public static volatile SingularAttribute<Committee, String> username;
	public static volatile SingularAttribute<Committee, String> password;
	public static volatile SingularAttribute<Committee, String> type;
	public static volatile SingularAttribute<Committee, Integer> amountOfBallot;
	public static volatile SingularAttribute<Committee, String> teamName;
	public static volatile SingularAttribute<Committee, String> topicName;
	public static volatile SingularAttribute<Committee, Date> date;
}
