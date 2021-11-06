package attacks;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    public void applySelfEffects(Pokemon self) {
        Effect ef1 = new Effect().turns(2);
        Effect ef2 = new Effect().stat(Stat.HP, +999999);
        ef1.sleep(self);
        self.addEffect(ef2);
    }

    @Override
    public String describe() {
        return "использует Rest";
    }
}
