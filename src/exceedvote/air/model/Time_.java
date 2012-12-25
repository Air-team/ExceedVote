package exceedvote.air.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-25T23:17:19.204+0700")
@StaticMetamodel(Time.class)
public class Time_ {
	public static volatile SingularAttribute<Time, Integer> id;
	public static volatile SingularAttribute<Time, Long> time;
	public static volatile SingularAttribute<Time, Committee> committee;
	public static volatile ListAttribute<Time, Time> list;
}
