package foo;

import org.simpleframework.xml.Element;

public class Player {
	@Element(name = "Name", required = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
