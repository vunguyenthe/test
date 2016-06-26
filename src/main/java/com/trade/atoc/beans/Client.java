package com.trade.atoc.beans;

import java.util.Arrays;

public class Client {
	public String id;
	public String clientId;
	public String name;
	public String adminUrl;
	public String baseUrl;
	public boolean surrogateAuthRequired;
	public boolean enabled;
	public String secret;
	public String defaultRoles;
	public String[] redirectUris;
	public String[] webOrigins;
	public String notBefore;
	public boolean bearerOnly;
	public boolean consentRequired;
	public boolean directGrantsOnly;
	public boolean frontchannelLogout;
	public String protocol;
	public boolean fullScopeAllowed;
	public String nodeReRegistrationTimeout;
	public String registeredNodes;
	public Mapper[] protocolMappers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdminUrl() {
		return adminUrl;
	}

	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public boolean isSurrogateAuthRequired() {
		return surrogateAuthRequired;
	}

	public void setSurrogateAuthRequired(boolean surrogateAuthRequired) {
		this.surrogateAuthRequired = surrogateAuthRequired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getDefaultRoles() {
		return defaultRoles;
	}

	public void setDefaultRoles(String defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	public String[] getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(String[] redirectUris) {
		this.redirectUris = redirectUris;
	}

	public String[] getWebOrigins() {
		return webOrigins;
	}

	public void setWebOrigins(String[] webOrigins) {
		this.webOrigins = webOrigins;
	}

	public String getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(String notBefore) {
		this.notBefore = notBefore;
	}

	public boolean isBearerOnly() {
		return bearerOnly;
	}

	public void setBearerOnly(boolean bearerOnly) {
		this.bearerOnly = bearerOnly;
	}

	public boolean isConsentRequired() {
		return consentRequired;
	}

	public void setConsentRequired(boolean consentRequired) {
		this.consentRequired = consentRequired;
	}

	public boolean isDirectGrantsOnly() {
		return directGrantsOnly;
	}

	public void setDirectGrantsOnly(boolean directGrantsOnly) {
		this.directGrantsOnly = directGrantsOnly;
	}

	public boolean isFrontchannelLogout() {
		return frontchannelLogout;
	}

	public void setFrontchannelLogout(boolean frontchannelLogout) {
		this.frontchannelLogout = frontchannelLogout;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public boolean isFullScopeAllowed() {
		return fullScopeAllowed;
	}

	public void setFullScopeAllowed(boolean fullScopeAllowed) {
		this.fullScopeAllowed = fullScopeAllowed;
	}

	public String getNodeReRegistrationTimeout() {
		return nodeReRegistrationTimeout;
	}

	public void setNodeReRegistrationTimeout(String nodeReRegistrationTimeout) {
		this.nodeReRegistrationTimeout = nodeReRegistrationTimeout;
	}

	public String getRegisteredNodes() {
		return registeredNodes;
	}

	public void setRegisteredNodes(String registeredNodes) {
		this.registeredNodes = registeredNodes;
	}

	public Mapper[] getProtocolMappers() {
		return protocolMappers;
	}

	public void setProtocolMappers(Mapper[] protocolMappers) {
		this.protocolMappers = protocolMappers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adminUrl == null) ? 0 : adminUrl.hashCode());
		result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
		result = prime * result + (bearerOnly ? 1231 : 1237);
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + (consentRequired ? 1231 : 1237);
		result = prime * result
				+ ((defaultRoles == null) ? 0 : defaultRoles.hashCode());
		result = prime * result + (directGrantsOnly ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + (frontchannelLogout ? 1231 : 1237);
		result = prime * result + (fullScopeAllowed ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((nodeReRegistrationTimeout == null) ? 0
						: nodeReRegistrationTimeout.hashCode());
		result = prime * result
				+ ((notBefore == null) ? 0 : notBefore.hashCode());
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result + Arrays.hashCode(protocolMappers);
		result = prime * result + Arrays.hashCode(redirectUris);
		result = prime * result
				+ ((registeredNodes == null) ? 0 : registeredNodes.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		result = prime * result + (surrogateAuthRequired ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(webOrigins);
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
		Client other = (Client) obj;
		if (adminUrl == null) {
			if (other.adminUrl != null)
				return false;
		} else if (!adminUrl.equals(other.adminUrl))
			return false;
		if (baseUrl == null) {
			if (other.baseUrl != null)
				return false;
		} else if (!baseUrl.equals(other.baseUrl))
			return false;
		if (bearerOnly != other.bearerOnly)
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (consentRequired != other.consentRequired)
			return false;
		if (defaultRoles == null) {
			if (other.defaultRoles != null)
				return false;
		} else if (!defaultRoles.equals(other.defaultRoles))
			return false;
		if (directGrantsOnly != other.directGrantsOnly)
			return false;
		if (enabled != other.enabled)
			return false;
		if (frontchannelLogout != other.frontchannelLogout)
			return false;
		if (fullScopeAllowed != other.fullScopeAllowed)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nodeReRegistrationTimeout == null) {
			if (other.nodeReRegistrationTimeout != null)
				return false;
		} else if (!nodeReRegistrationTimeout
				.equals(other.nodeReRegistrationTimeout))
			return false;
		if (notBefore == null) {
			if (other.notBefore != null)
				return false;
		} else if (!notBefore.equals(other.notBefore))
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (!Arrays.equals(protocolMappers, other.protocolMappers))
			return false;
		if (!Arrays.equals(redirectUris, other.redirectUris))
			return false;
		if (registeredNodes == null) {
			if (other.registeredNodes != null)
				return false;
		} else if (!registeredNodes.equals(other.registeredNodes))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		if (surrogateAuthRequired != other.surrogateAuthRequired)
			return false;
		if (!Arrays.equals(webOrigins, other.webOrigins))
			return false;
		return true;
	}

}
