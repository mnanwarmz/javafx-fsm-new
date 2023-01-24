package com.example.javafxfsmnew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NFA {
	private State initialState;
	private State startState;
	private Map<String,State> states = new HashMap<>();
	private List<String> terminals;
	private Map<State, Map<String, List<State>>> transitions;

	public NFA(List<String> nonTerminals, List<String> terminals, Map<String, List<String>> rules) {
		// Initialize the states
		for (String nonTerminal : nonTerminals) {
			states.put(nonTerminal, new State(nonTerminal));
		}

		// Create the start state
		startState = states.get(nonTerminals.get(0));
		startState.setStart(true);

		// Create the transitions
		for (Map.Entry<String, List<String>> entry : rules.entrySet()) {
			String lhs = entry.getKey();
			State currentState = states.get(lhs);
			List<String> rhs = entry.getValue();

			for (String symbol : rhs) {
				if (nonTerminals.contains(symbol)) {
					State nextState = states.get(symbol);
					currentState.addTransition(nextState.getName(),nextState);
				} else {
					State nextState = new State(symbol);
					currentState.addTransition(nextState.getName(),nextState);
				}
			}
		}
	}


	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public void setTransition(State current, String symbol, State next) {
		transitions.get(current).get(symbol).add(next);
	}

	public State getInitialState() {
		return initialState;
	}

	public Map<String, State> getStates() {
		return states;
	}

	public State getStartState() {
		return startState;
	}

	public List<State> getFinalStates() {
		List<State> finalStates = new ArrayList<>();
		for (State state : states.values()) {
			if (state.isFinal()) {
				finalStates.add(state);
			}
		}
		return finalStates;
	}

	public State getState(String name) {
		for (State state : states.values()) {
			if (state.getName().equals(name)) {
				return state;
			}
		}
		return null;
	}

	public List<String> getTerminals() {
		return terminals;
	}

	public Map<State, Map<String, List<State>>> getTransitions() {
		return transitions;
	}
}
