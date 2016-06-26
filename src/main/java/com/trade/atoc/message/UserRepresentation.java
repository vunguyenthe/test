package com.trade.atoc.message;

public class UserRepresentation {
	private String deviceEmail;
	private String deviceUserName;
	public String getDeviceEmail() {
		return deviceEmail;
	}
	public void setDeviceEmail(String deviceEmail) {
		this.deviceEmail = deviceEmail;
	}
	public String getDeviceUserName() {
		return deviceUserName;
	}
	public void setDeviceUserName(String deviceUserName) {
		this.deviceUserName = deviceUserName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceEmail == null) ? 0 : deviceEmail.hashCode());
		result = prime * result + ((deviceUserName == null) ? 0 : deviceUserName.hashCode());
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
		UserRepresentation other = (UserRepresentation) obj;
		if (deviceEmail == null) {
			if (other.deviceEmail != null)
				return false;
		} else if (!deviceEmail.equals(other.deviceEmail))
			return false;
		if (deviceUserName == null) {
			if (other.deviceUserName != null)
				return false;
		} else if (!deviceUserName.equals(other.deviceUserName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserRepresentation [deviceEmail=" + deviceEmail + ", deviceUserName=" + deviceUserName + "]";
	}
	
}
