package com.example.javafxfsmnew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	// Function to parse the input string and extract the grammar rules
	public static Map<String, Object> parse(String input) {
		Map<String, List<String>> rules = new HashMap<>();
		List<String> nonTerminals = new ArrayList<>();
		List<String> terminals = new ArrayList<>();

		// Split the input string into lines
		String[] lines = input.split("\n");

		// Iterate through each line
		for (String line : lines) {
			// Split the line into left-hand side and right-hand side
			String[] parts = line.split("->");
			String lhs = parts[0].trim();
			String rhs = parts[1].trim();

			// Add the left-hand side to the non-terminals list
			if (!nonTerminals.contains(lhs)) {
				nonTerminals.add(lhs);
			}

			// Extract the symbols from the right-hand side
			String[] symbols = rhs.split("\\|");

			for (String symbol : symbols) {
				symbol = symbol.trim();
				String[] chars = symbol.split("");
				for (String c : chars) {
					if (!nonTerminals.contains(c) && !terminals.contains(c) && !c.equals("ε")) {
						if (c.matches("[a-zA-Z]+")) {
							if (!nonTerminals.contains(c)) {
								nonTerminals.add(c);
							}
						}else {
							terminals.add(c);
						}
					}
				}
			}





			// Add the rule to the rules map
			if (!rules.containsKey(lhs)) {
				rules.put(lhs, new ArrayList<>());
			}
			rules.get(lhs).addAll(Arrays.asList(symbols));
		}

		return new HashMap<String, Object>() {
			{
				put("rules", rules);
				put("nonTerminals", nonTerminals);
				put("terminals", terminals);
			}
		};
	}
}
