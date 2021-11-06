import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Cosmoes cs = new Cosmoes("pokemon1", 3);
        Regigigas rg = new Regigigas("pokemon2", 3);
        Solgaleo sg = new Solgaleo("pokemon3", 3);
        Nihilego ng = new Nihilego("pokemon4", 3);
        Pansage pg = new Pansage("pokemon5", 3);
        Simisage ss = new Simisage("pokemon6", 3);

        b.addAlly(cs);
        b.addAlly(rg);
        b.addAlly(sg);

        b.addFoe(ng);
        b.addFoe(pg);
        b.addFoe(ss);

        b.go();

    }
}
