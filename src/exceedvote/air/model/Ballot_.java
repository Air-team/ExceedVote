package exceedvote.air.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-25T23:17:19.163+0700")
@StaticMetamodel(Ballot.class)
public class Ballot_ {
	public static volatile SingularAttribute<Ballot, Integer> id;
	public static volatile SingularAttribute<Ballot, String> teamName;
	public static volatile SingularAttribute<Ballot, String> topic;
	public static volatile SingularAttribute<Ballot, Committee> committee;
	public static volatile SingularAttribute<Ballot, Voter> voter;
	public static volatile SingularAttribute<Ballot, Team> team;
	public static volatile ListAttribute<Ballot, Team> list;
	public static volatile SingularAttribute<Ballot, String> time;
}
