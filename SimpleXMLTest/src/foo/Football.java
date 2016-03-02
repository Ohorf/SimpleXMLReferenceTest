package foo;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class Football {
	@Element(name = "DESCRIPTION", required = true)
	String description;
	@ElementList(name = "Player", entry = "Player", inline = true, required = false)
	List<Player> players;
	@ElementList(name = "Team", entry = "Team", inline = true, required = false)
	List<Team> teams;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
