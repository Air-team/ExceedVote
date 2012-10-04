package GUI;

import java.util.List;

/**
 * 
 * @author Busarat Jum
 *
 * Voter Class 
 */
public class Voter {

    private String name;
    private String type;
    private Ballot ballot;
    private TeamList teamList;
    private List<Team> list;
    private BallotBox bb;

    public Voter(String name,String type,Ballot ballot,BallotBox bb)
    {   
        this.name = name;
        this.type = type;
        this.ballot = ballot;
        this.bb = bb;
        this.teamList = new TeamList();
        this.list = teamList.getTeam();
    }

    public String getName()
    {
        return this.name;
    }

    public String getType()
    {
        return this.type;
    }
    
    public List showTeam(){
        return list;
    }

    public Ballot getBallot()
    {    
        ballot.setValue(type);
        return this.ballot;
    }

    public boolean pullBollot(String nameT)
    {
        return bb.putBallot(nameT);
    }
}
