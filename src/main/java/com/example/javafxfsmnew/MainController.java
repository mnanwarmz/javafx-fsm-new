package com.example.javafxfsmnew;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Map;

public class MainController {

        @FXML
        private TextArea RGInput;
        @FXML
        private TextArea outputBox;

        @FXML
        protected void onNFAButtonClick() {
                // Get the input from the regular grammar input text area
                String input = RGInput.getText();

                // Use the Parser to parse the regular grammar
                Map<String, Object> output = Parser.parse(input);
                Map<String, List<String>> rules = (Map<String, List<String>>) output.get("rules");
                List<String> nonTerminals = (List<String>) output.get("nonTerminals");
                List<String> terminals = (List<String>) output.get("terminals");

                // Create the NFA
                NFA nfa = new NFA(nonTerminals, terminals, rules);

                // Print out the start state and the transitions of the NFA
                StringBuilder sb = new StringBuilder();
                sb.append("Start State: " + nfa.getStartState().getName() + "\n");
                for (Map.Entry<String, State> entry : nfa.getStates().entrySet()) {
                        String stateName = entry.getKey();
                        State state = entry.getValue();
                        sb.append("State " + stateName + " Transitions: " + state.transitions + "\n");
                }
                outputBox.setText(sb.toString());
        }

}