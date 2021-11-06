package pokemons;

import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Regigigas extends Pokemon {
    Confusion cf = new Confusion();
    Tackle tc = new Tackle();
    CottonSpore cs = new CottonSpore();
    SwordsDance sd = new SwordsDance();
    public Regigigas(String name, int level) {
        super(name,level);
        super.setType(Type.NORMAL);
        super.setStats(110,160,110,80,110,110);
        super.setMove(cf,tc,cs,sd);
    }
}
