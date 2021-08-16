package enums;

public enum Protocols {

	HTTP("http", "http://"),
	HTTPS("https","https://");
	
	String protocol;
	String protocolLinkPrefix;
	
	private Protocols(String protocol, String protocolLinkPrefix) {
		this.protocol = protocol;
		this.protocolLinkPrefix = protocolLinkPrefix;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getProtocolLinkPrefix() {
		return protocolLinkPrefix;
	}
}
