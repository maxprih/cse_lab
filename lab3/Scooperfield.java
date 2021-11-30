public class Scooperfield extends Human implements ISearch, ISearchAt, ISay {
    private boolean readyToSleep;
    private boolean calm;

    public Scooperfield(String name) {
        this.setName(name);
    }

    @Override
    public String search(OBJECTS obj) {
        return this + " ищет " + obj.getName();
    }

    @Override
    public String searchAt(OBJECTS obj, PLACE place) {
        System.out.println(this + " ищет " + obj.getName() + " на " + place.getName());
        if (obj.getPlace().equals(place.getName())) {
            return obj.getName() + " на " + place.getName();
        } else {
            return obj.getName() + " не на " + place.getName();
        }
    }

    public String isCalm() {
        if (calm) {
            return this + " спокоен ";
        } else {
            return this + " не спокоен ";
        }
    }

    public void setCalm(boolean calm) {
        this.calm = calm;
    }


    public String isReadyToSleep() {
        if (readyToSleep) {
            return this + " готов погрузиться в сон";
        } else {
            return this + " ещё не готов спать";
        }
    }

    public void setReadyToSleep(boolean readyToSleep) {
        this.readyToSleep = readyToSleep;
    }

    @Override
    public String say(String text) {
        return this + " сказал " + "\"" +text + "\"";
    }


    public String remember(OBJECTS obj) {
        return this + " вспомнил о " + obj.getName();
    }

    public String putHandIn(PLACE place) {
        return this + " сунул руку в " + place.getName();
    }


}
