package AutomatDeterminist;
import java.io.*;
import java.util.*;
class Automaton {
    private String initial_state;
    private TransitionsList list;
    private String[] final_state;

    
    Automaton(String fileName) throws Exception {
    	try (BufferedReader buf = new BufferedReader(new FileReader(fileName))) {
            // validating the first line of the .txt file
            this.initial_state = buf.readLine();
            //first regex used
            if (!this.initial_state.matches("^[a-zA-Z]([1-9][0-9]*|0)$")) {
                throw new Exception("Invalid initial state: " + this.initial_state);
            }

            // validating the 2nd line of the .txt file
            String finalStates = buf.readLine();
            //2nd regex used
            if (!finalStates.matches("^([a-zA-Z]([1-9][0-9]*|0))(\s[a-zA-Z]([1-9][0-9]*|0))*$")) {
                throw new Exception("Invalid final states: " + finalStates);
            }

            //we split the 2nd line of the file, the one with final states
            this.final_state = finalStates.split(" "); 

            this.list = new TransitionsList();

            // validating the remaining lines of the .txt file
            String temp;
            while ((temp = buf.readLine()) != null) {
                // 3rd regex used
                if (!temp.matches("^[a-zA-Z]([1-9][0-9]*|0)\\s[a-zA-Z]\\s[a-zA-Z]([1-9][0-9]*|0)$")) {
                    throw new Exception("Invalid transition(transitions must be of form: state1 symbol state2): " + temp);
                }
                String[] elemente = temp.split(" ");
                char symbol = elemente[1].charAt(0); 

                Transition tr = new Transition(elemente[0], symbol, elemente[2]);
                this.list.addTranzitie(tr);
            }
        } catch (IOException e) {
            throw new Exception("Error when reading the file: " + e.getMessage());
        }
    }

    boolean analyzeWord(String str) {
        String currentState = initial_state;

        // We analyze each symbol of our word
        for (char symbol : str.toCharArray()) {
            Transition transition = list.findTransition(currentState, symbol);
            if (transition == null) return false; // The word is not accepted if there isn't a transition
            currentState = transition.getfinalState();   // We move on to the next state
        }

        // Cheking if the last state is one of the final states
        return Arrays.asList(final_state).contains(currentState);
    }
    boolean isDeterministic() {
        Map<String, Set<Character>> transitions = new HashMap<>();

        for (Transition transition : list.getTransitions()) {
            String state = transition.getinitialState();
            char symbol = transition.getSymbol();
            transitions.putIfAbsent(state, new HashSet<>());
            if (transitions.get(state).contains(symbol)) {
                return false; 
            }
            transitions.get(state).add(symbol);
        }

        return true;
    }
    
    @Override
    public String toString() {
        return "Automaton [initial_state=" + initial_state + ", final_state=" +
               Arrays.toString(final_state) + ", list=" + list + "]";
    }
}

