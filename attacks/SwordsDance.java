package attacks;

import ru.ifmo.se.pokemon.*;

public class SwordsDance extends StatusMove {
    public SwordsDance() {
        super(Type.NORMAL,0,0);
    }

    @Override
    public void applyOppEffects(Pokemon self) {
        Effect ef = new Effect().stat(Stat.ATTACK, +2);
        self.addEffect(ef);
    }

    @Override
    public String describe() {
        return "использует Swords Dance";
    }
}
