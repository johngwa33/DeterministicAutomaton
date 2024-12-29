package AutomatDeterminist;

public class Test {
    public static void main(String[] args) {
        try {

        	Automaton m = new Automaton("src\\AutomatDeterminist\\automat.txt");
            String word="bcccb";
            if(m.analyzeWord(word)) {
            	System.out.println("The word \""+word+"\" is accepted by the automaton");
            }
            else
            {
            	System.out.println("The word \""+word+"\" is not accepted by the automaton");
            }
            if(m.isDeterministic()) {
            	System.out.println("The automaton is deterministic");
            }
            else
            {
            	System.out.println("The automaton is not deterministic");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}