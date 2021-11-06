package pokemons;
import attacks.*;
import ru.ifmo.se.pokemon.*;

public class Simisage extends Pansage {
    Psychic ps = new Psychic();
    public Simisage(String name, int level) {
        super(name, level);
        super.addMove(ps);
        super.setType(Type.GRASS);
        super.setStats(75,98,63,98,63,101);
    }
}
