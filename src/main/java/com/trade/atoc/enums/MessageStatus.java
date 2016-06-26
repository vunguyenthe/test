package com.trade.atoc.enums;

public enum MessageStatus {
	Unkown(0), Approved(1), Rejected(2);

	private int value;

	private MessageStatus() {
	}

	private MessageStatus(int i) {
		this.value = i;
	}

	public void printValue() {
		System.out.println(this.value);
	}
}
