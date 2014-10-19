package com.spring.controller;

public enum Action {
	list("list"), create("create"), update("update"), delete("delete");
	
	private String value;
	
	private Action(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		switch (this) {
			case list: 		return value;
			case create: 	return value;
			case update: 	return value;
			case delete: 	return value;
		}
		return super.toString();
	}
}
