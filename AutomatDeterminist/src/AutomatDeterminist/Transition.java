package AutomatDeterminist;


class Transition {
    private String initialState;  
    private char symbol; 
    private String finalState;  

    Transition(String initialState, char symbol, String finalState) {
        this.initialState = initialState;
        this.symbol = symbol;
        this.finalState = finalState;
    }

    String getinitialState() {
        return this.initialState;
    }

    char getSymbol() {
        return this.symbol;
    }

    String getfinalState() {
        return this.finalState;
    }
}
