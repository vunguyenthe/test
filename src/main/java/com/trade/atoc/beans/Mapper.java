package com.trade.atoc.beans;

/**
 * Created by vu.nt on 2/26/2016.
 */
public class Mapper {
	private String name;
	private String protocol;
	private String protocolMapper;
	private boolean consentRequired;
	private String consentText;
	private Config config;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocolMapper() {
		return protocolMapper;
	}

	public void setProtocolMapper(String protocolMapper) {
		this.protocolMapper = protocolMapper;
	}

	public boolean isConsentRequired() {
		return consentRequired;
	}

	public void setConsentRequired(boolean consentRequired) {
		this.consentRequired = consentRequired;
	}

	public String getConsentText() {
		return consentText;
	}

	public void setConsentText(String consentText) {
		this.consentText = consentText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((config == null) ? 0 : config.hashCode());
		result = prime * result + (consentRequired ? 1231 : 1237);
		result = prime * result
				+ ((consentText == null) ? 0 : consentText.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result
				+ ((protocolMapper == null) ? 0 : protocolMapper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapper other = (Mapper) obj;
		if (config == null) {
			if (other.config != null)
				return false;
		} else if (!config.equals(other.config))
			return false;
		if (consentRequired != other.consentRequired)
			return false;
		if (consentText == null) {
			if (other.consentText != null)
				return false;
		} else if (!consentText.equals(other.consentText))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (protocolMapper == null) {
			if (other.protocolMapper != null)
				return false;
		} else if (!protocolMapper.equals(other.protocolMapper))
			return false;
		return true;
	}

}
