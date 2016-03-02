package foo;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class Team {
	@ElementList(name = "Player", entry = "Player", inline = true, required = false)
	List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
