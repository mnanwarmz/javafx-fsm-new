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
        private TextArea TransitionTableBox;

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


                TransitionTable tt = new TransitionTable(nfa.getStates(),terminals);

                // Print out the start state and the transitions of the NFA
                StringBuilder sb = new StringBuilder();
                sb.append("M = (Q,∑,δ,p0,F)\n");
                sb.append("Q = [");
                for (String state : nfa.getStates().keySet()) {
                        sb.append(state + ", ");
                }
                sb.append("]\n");
                sb.append("∑ = [");
                for (String symbol : nfa.getTerminals()) {
                        sb.append(symbol + ", ");
                }
                sb.append("]\n");
                sb.append("δ = \n");
                for (Map.Entry<String, State> entry : nfa.getStates().entrySet()) {
                        String stateName = entry.getKey();
                        State state = entry.getValue();
                        for (Map.Entry<String, State> transition : state.transitions.entrySet()) {
                                sb.append(stateName + " - " + transition.getKey() + " -> " + transition.getValue().getName() + "\n");
                        }
                }
                sb.append("p0 = [" + nfa.getStartState().getName() + "]\n");
                sb.append("F = [");
                for (State state : nfa.getFinalStates()) {
                        sb.append(state.getName() + ", ");
                }
                sb.append("]\n");

                outputBox.setText(sb.toString());
                TransitionTableBox.setText(tt.toString());
        }

}