package attacks;

import ru.ifmo.se.pokemon.*;

public class Psychic extends SpecialMove {
    public Psychic() {
        super(Type.PSYCHIC, 90, 100);
    }

    @Override
    public void applyOppEffects(Pokemon enemy) {
        Effect ef = new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1);
        enemy.addEffect(ef);
    }

    @Override
    public String describe() {
        return "использует Psychic";
    }
}