package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Pansage extends Pokemon {
    Facade fc = new Facade();
    Tackle tc = new Tackle();
    Rest rs = new Rest();
    public Pansage(String name, int level) {
        super(name,level);
        super.setMove(fc,tc,rs);
        super.setType(Type.GRASS);
        super.setStats(50,53,48,53,48,64);
    }
}
