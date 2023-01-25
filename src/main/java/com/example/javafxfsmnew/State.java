package com.example.javafxfsmnew;

import java.util.HashMap;
import java.util.List;

class State {
	public boolean isStart;
	public HashMap<String, State> transitions;
	public boolean isFinal;
	private final String name;

	public State(String name) {
		isStart = false;
		isFinal = false;
		transitions = new HashMap<>();
		this.name = name;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public String getName() {
		return name;
	}

	public boolean hasTransition(String symbol) {
		return transitions.containsKey(symbol);
	}
	public void addTransition(String symbol, State nextState) {
		transitions.put(symbol, nextState);
	}

//	Get transition
	public State getTransition(String symbol) {
		return transitions.get(symbol);
	}


	public boolean isFinal() {
		return isFinal;
	}

	public String toString() {
		return name;
	}
}
