package enums;

public enum ApplicationsUnderTestLinks {

	AMAZON("Amazon","www.amazon.com"),
	AMAZON_INDIA("Amazon India","www.amazon.in");
	
	String name;
	String link;
	
	private ApplicationsUnderTestLinks(String name, String link) {
		this.name = name;
		this.link = link;
	}
	
	public String getName() {
		return name;
	}

	public String getLink() {
		return link;
	}
}
