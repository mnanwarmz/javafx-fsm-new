package com.example.javafxfsmnew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

class NFATest {
	@Test
	public void testNFA() {
		// Define the regular grammar
		String grammar = "A -> 1B | 0C\nB -> ε\nC -> B | 1A";

		// Use the parser to parse the grammar
		Map<String, Object> output = Parser.parse(grammar);
		Map<String, List<String>> rules = (Map<String, List<String>>) output.get("rules");
		List<String> nonTerminals = (List<String>) output.get("nonTerminals");
		List<String> terminals = (List<String>) output.get("terminals");

		// Create the NFA
		NFA nfa = new NFA(nonTerminals, terminals, rules);

		// Test that the start state is correct
		State startState = nfa.getStartState();
		assertEquals("A", startState.getName());

//		 Test that the transitions are correct
//		State stateA = nfa.getState("A");
//		State stateB = nfa.getState("B");
//		State stateC = nfa.getState("C");
//		System.out.println(stateA.transitions);
//		System.out.println(stateB.transitions);
//		System.out.println(stateC.transitions);
//		System.exit(0);
//		 assertTrue(stateA.hasTransition("1B"));
//		 assertTrue(stateA.hasTransition("0C"));
//		 assertTrue(stateB.hasTransition("ε"));
//		 assertTrue(stateC.hasTransition("B"));
//		 assertTrue(stateC.hasTransition("1A"));
	}

}