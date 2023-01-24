package com.example.javafxfsmnew;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ParserTest {
	@Test
	public void testParse() {
		String input = "A -> 1B | 0C\nB -> ε\nC -> B | 1A";
		Map<String, Object> output = Parser.parse(input);

		Map<String, List<String>> rules = (Map<String, List<String>>) output.get("rules");
		List<String> nonTerminals = (List<String>) output.get("nonTerminals");
		List<String> terminals = (List<String>) output.get("terminals");

		 assertEquals(3, rules.size());
		 assertTrue(rules.containsKey("A"));
		 assertTrue(rules.containsKey("B"));
		 assertTrue(rules.containsKey("C"));
		 assertEquals(2, rules.get("A").size());
		 assertEquals(1, rules.get("B").size());
		 assertEquals(2, rules.get("C").size());
		assertTrue(rules.get("A").stream().map(String::trim).collect(Collectors.toList()).contains("1B"));
		assertTrue(rules.get("A").stream().map(String::trim).collect(Collectors.toList()).contains("0C"));
		assertTrue(rules.get("B").stream().map(String::trim).collect(Collectors.toList()).contains("ε"));
		assertTrue(rules.get("C").stream().map(String::trim).collect(Collectors.toList()).contains("B"));
		assertTrue(rules.get("C").stream().map(String::trim).collect(Collectors.toList()).contains("1A"));
		assertEquals(3, nonTerminals.size());
		assertTrue(nonTerminals.contains("A"));
		assertTrue(nonTerminals.contains("B"));
		assertTrue(nonTerminals.contains("C"));
		assertEquals(2, terminals.size());
		assertTrue(terminals.contains("1"));
		assertTrue(terminals.contains("0"));
	}
}
