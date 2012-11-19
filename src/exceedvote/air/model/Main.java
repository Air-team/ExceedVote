package exceedvote.air.model;

import java.util.ArrayList;
import java.util.List;

import exceedvote.air.persistence.DaoFactory;
import exceedvote.air.persistence.TeamDao;
import exceedvote.air.persistence.VoteTopicDao;
import exceedvote.air.persistence.VoterDao;

import exceedvote.air.ui.LoginUI;
import exceedvote.air.ui.SeviceUI;
import exceedvote.air.ui.VoteTypeUI;
import exceedvote.air.ui.VoteUI;

/**
 * Main class for launch the program.
 * 
 * @author Air Team
 * @version 2012.11.20
 */
public class Main {

	public static void main(String[] args) {
		VoteTopicDao dao1 = DaoFactory.getInstance().getVoteTopicDao();
		VoteTopic beauti = new VoteTopic("Beautiful UI");
		dao1.save(beauti);
		VoteTopic goodFunc = new VoteTopic("Good Function");
		dao1.save(goodFunc);
		VoteTopic noBug = new VoteTopic("No Bug");
		dao1.save(noBug);
		VoteTopic presentation = new VoteTopic("Presentation");
		dao1.save(presentation);
		List<VoteTopic> topic = new ArrayList<VoteTopic>();

		topic.add(beauti);
		topic.add(goodFunc);
		topic.add(noBug);
		topic.add(presentation);

		// test team
		TeamDescription durianDes = new TeamDescription("name: Durian");
		TeamDescription amazeDes = new TeamDescription("name: Amaze");
		TeamDescription desTest1 = new TeamDescription("name: OOP");
		TeamDescription desTest2 = new TeamDescription("name: PANDA");

		TeamDao dao2 = DaoFactory.getInstance().getTeamDao();
		Team durian = new Team("Durian", durianDes);
		dao2.save(durian);
		Team amaze = new Team("Amaze", amazeDes);
		dao2.save(amaze);
		Team test1 = new Team("OOP", amazeDes);
		dao2.save(test1);
		Team test2 = new Team("PANDA", amazeDes);
		dao2.save(test2);
		TeamList list = new TeamList();
		list.addTeam(durian);
		list.addTeam(amaze);
		list.addTeam(test1);
		list.addTeam(test2);

		Ballot ballot = new Ballot(list);
		Voter voter = new Voter("Air", "STUDENT");
		VoterDao dao = DaoFactory.getInstance().getVoterDao();
		dao.save(voter);

		VoteUI voteUI = new VoteUI(voter, ballot);
		VoteTypeUI voteTypeUI = new VoteTypeUI(voter);

		LoginUI loginUI = new LoginUI();

		SeviceUI serviceUI = new SeviceUI();
		serviceUI.addUI("voteUI", voteUI);
		serviceUI.addUI("voteTypeUI", voteTypeUI);
		voteTypeUI.addService(serviceUI);
		voteUI.addService(serviceUI);

		voteTypeUI.run("");

		// loginUI.run();

	}
}