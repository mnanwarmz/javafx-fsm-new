package com.example.javafxfsmnew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransitionTable {
        private Map<State, Map<String, State>> table;
        private Map<String,State> states;
        private List<String> symbols;

        public TransitionTable(Map<String,State> states, List<String> symbols) {
            this.table = new HashMap<>();
            this.states = states;
            this.symbols = symbols;
        }

//        public void addTransition(State currentState, String symbol, State nextState) {
//            if (!table.containsKey(currentState)) {
//                table.put(currentState, new HashMap<>());
//            }
//            table.get(currentState).put(symbol, nextState);
//        }

//        public State getNextState(State currentState, String symbol) {
//            return table.get(currentState).get(symbol);
//        }

//        public void displayTable() {
//            for (State currentState : states) {
//                for (String symbol : symbols) {
//                    State nextState = getNextState(currentState, symbol);
//                    System.out.println(currentState.getName() + " --" + symbol + "--> " + nextState.getName());
//                }
//            }
//        }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Current State\tInput Symbol\tNext State\n");
//        for (Map.Entry<String, State> entry : states.entrySet()) {
//            State state = entry.getValue();
//            for (String symbol : state.transitions.keySet()) {
//                if (state.hasTransition(symbol)) {
//                    State nextState = state.getTransition(symbol);
//                    sb.append(entry.getKey() + " --" + symbol + "--> " + nextState.getName() + "\n");
//                }
//            }
//        }
//        return sb.toString();
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transition Table:\n");
        sb.append("State/Symbol\t");
        for(Map.Entry<String, State> entry : states.entrySet()) {
            State state = entry.getValue();
            Map<String, State> trans = state.transitions;
            for(String symbol: trans.keySet()) {
                sb.append(symbol + "\t");
            }
            break;
        }
        sb.append("\n");
        for (Map.Entry<String, State> entry : states.entrySet()) {
            State state = entry.getValue();
            Map<String, State> trans = state.transitions;
            sb.append(entry.getKey() + "\t");
            for(String symbol: trans.keySet()) {
                State nextState = trans.get(symbol);
                sb.append(nextState.getName() + "\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
