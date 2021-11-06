package pokemons;

import attacks.*;
import ru.ifmo.se.pokemon.*;

public class Solgaleo extends Cosmoes {
    Slash sl = new Slash();
    public Solgaleo(String name, int level) {
        super(name,level);
        super.addMove(sl);
        super.setStats(137,137,107,113,89,97);
        super.setType(Type.PSYCHIC, Type.STEEL);
    }
}
