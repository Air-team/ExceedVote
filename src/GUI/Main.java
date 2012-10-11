package GUI;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
       
        TeamDescription durianDes = new TeamDescription("name: Durian");
        TeamDescription amazeDes = new TeamDescription("name: Amaze");
        Team  durian = new Team("Durian",durianDes);
        Team  amaze = new Team("Amaze",amazeDes);
        TeamList list = new TeamList();
        list.addTeam(durian);
        list.addTeam(amaze);
        
        Ballot ballot = new Ballot();
        BallotBox ballotbox = new BallotBox(list);
        Voter voter = new Voter("Air at Bangkok","STUDENT",ballot,ballotbox);
        
        VoteUI voteUI = new VoteUI(voter);
        voteUI.run();
        
        /*
         * 
        String name = "air";
        Voter voter = new Voter(name);
        TeamDescription des = new TeamDescription("www");
        Team a = new Team("a",des);
        TeamList list = new TeamList();
        list.addTeam(a);
        BallotBox ballotbox = new BallotBox(list);
        Ballot ballot = new Ballot(10);

    
        System.out.println( voter.getName() );
        System.out.println(  ((Team)list.getTeam().get(0)).getName() );
        System.out.println( ballotbox.putBallot("a") );
        if(ballotbox.putBallot("a"))
        {
        ballot.reduceValue(1);
        }
        System.out.println(ballot.getballot());
         */

    }

}
