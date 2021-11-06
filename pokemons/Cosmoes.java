package pokemons;

import ru.ifmo.se.pokemon.*;

import attacks.*;

public class Cosmoes extends Pokemon {
    DoubleTeam dt = new DoubleTeam();
    StoneEdge se = new StoneEdge();
    public Cosmoes(String name, int level) {
        super(name,level);
        super.setMove(dt,se);
        super.setStats(43,29,131,29,131,37);
        super.setType(Type.PSYCHIC);
    }
}
