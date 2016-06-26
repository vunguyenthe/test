package com.trade.atoc.message.base;

import java.util.UUID;

import com.trade.atoc.annotation.JsonSerializeType;

@JsonSerializeType
public abstract class BaseMessage {
	protected UUID messageId;
	protected String deviceAlias;
	protected Long createdDate;
	
	public UUID getMessageId() {
		return messageId;
	}

	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}

	public String getDeviceAlias() {
		return deviceAlias;
	}

	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((deviceAlias == null) ? 0 : deviceAlias.hashCode());
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
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
		BaseMessage other = (BaseMessage) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (deviceAlias == null) {
			if (other.deviceAlias != null)
				return false;
		} else if (!deviceAlias.equals(other.deviceAlias))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseMessage [messageId=" + messageId + ", deviceAlias=" + deviceAlias + ", createdDate=" + createdDate
				+ "]";
	}
	
}