package exceedvote.air.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-25T23:17:19.203+0700")
@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Integer> id;
	public static volatile SingularAttribute<Team, String> name;
	public static volatile SingularAttribute<Team, Integer> score;
	public static volatile SingularAttribute<Team, TeamDescription> teamdes;
}
