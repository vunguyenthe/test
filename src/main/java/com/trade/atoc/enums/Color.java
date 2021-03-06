package com.trade.atoc.enums;

public enum Color {
	RED(1), YELLOW(2), BLUE(3); // each is an instance of Color

	private int value;

	private Color() {
	}

	private Color(int i) {
		this.value = i;
	}

	// define instance method
	public void printValue() {
		System.out.println(this.value);
	}
}