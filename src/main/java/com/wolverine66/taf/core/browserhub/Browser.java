package com.wolverine66.taf.core.browserhub;

public enum Browser {
	
	CHROME("Chrome","CH"),
	FIREFOX("Firefox","FF");
	
	private final String name;
	private final String id;
	
	private Browser(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public static Browser fromName(String name) {
		for(Browser browser : Browser.values()) {
			if(browser.getName().equalsIgnoreCase(name)){
				return browser;
			}
		}
		return null;
	}

}
