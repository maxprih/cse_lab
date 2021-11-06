package attacks;

import ru.ifmo.se.pokemon.*;

public class CottonSpore extends StatusMove {
    public CottonSpore() {
        super(Type.GRASS, 0, 100);
    }

    @Override
    public void applyOppEffects(Pokemon enemy) {
        Effect ef = new Effect().stat(Stat.SPEED, -2);
        enemy.addEffect(ef);
    }

    @Override
    public String describe() {
        return "использует Cotton Spore";
    }
}
