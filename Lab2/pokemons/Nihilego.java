package pokemons;

import attacks.*;
import ru.ifmo.se.pokemon.*;

public class Nihilego extends Solgaleo {
    FireFang ff = new FireFang();
    public Nihilego(String name, int level) {
        super(name, level);
        super.addMove(ff);
        super.setType(Type.ROCK, Type.POISON);
        super.setStats(109,53,47,127,131,103);
    }
}
