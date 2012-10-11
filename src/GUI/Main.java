package GUI;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
       
       TeamDescription durianDes = new TeamDescription("name: Durian");
        TeamDescription amazeDes = new TeamDescription("name: Amaze");
        TeamDescription desTest1 = new TeamDescription("name: Test1");
        TeamDescription desTest2 = new TeamDescription("name: Test2");
        
        Team  durian = new Team("Durian",durianDes);
        Team  amaze = new Team("Amaze",amazeDes);
        Team  test1 = new Team("Test1",amazeDes);
        Team  test2 = new Team("Test2",amazeDes);
        TeamList list = new TeamList();
        list.addTeam(durian);
        list.addTeam(amaze);
        list.addTeam(test1);
        list.addTeam(test2);
        
        Ballot ballot = new Ballot();
        BallotBox ballotbox = new BallotBox(list);
        Voter voter = new Voter("Air SKE09","STUDENT",ballot,ballotbox,list);
        
        VoteUI voteUI = new VoteUI(voter);
        voteUI.run();
    }

}
