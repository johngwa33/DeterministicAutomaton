package AutomatDeterminist;

import java.util.*;

class TransitionsList {
    private ArrayList<Transition> list = new ArrayList<>();

    void addTranzitie(Transition tr) {
        list.add(tr);
    }

    Transition findTransition(String stare, char simbol) {
        for (Transition tmp : list) {
            if (tmp.getinitialState().equals(stare) && tmp.getSymbol() == simbol) {
                return tmp;
            }
        }
        return null;
    }

    ArrayList<Transition> getTransitions() {
        return list;
    }

    @Override
    public String toString() {
        return "Tranzitii: " + list;
    }
}
