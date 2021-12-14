public class Scooperfield extends Human implements ISearch, ISearchAt {
    private boolean readyToSleep;
    private boolean calm;

    public Scooperfield(String name) throws NotProperNameException {
        if (!(name.matches(".*\\d.*"))) {
            this.setName(name);
        }
        else {
            throw new NotProperNameException("Имя не должно содержать цифры.");
        }
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

    public void sleepAt(String place, boolean isComf) throws NotReadyToSleepException { //локальный класс placetosleep
        class PlaceToSleep {

            boolean isComfort;
            String place;

            public PlaceToSleep(String place, boolean isComfort) {
                this.place = place;
                this.isComfort = isComfort;
            }

        }
        PlaceToSleep placetosleep = new PlaceToSleep(place, isComf);
        System.out.println(Scooperfield.this.getName() + " собирается спать");
        if (Scooperfield.this.readyToSleep) {
            if (placetosleep.isComfort) {
                System.out.println(this + " спит на удобном " + placetosleep.place);
            } else {
                System.out.println(this + " спит на неудобном " + placetosleep.place);
            }
        } else {
            throw new NotReadyToSleepException(Scooperfield.this.getName() + " ещё не готов спать");
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

    AbleToSay toSay = new AbleToSay() { // анонимный класс
        @Override
        public void say(String text) {
            System.out.println(Scooperfield.this + " сказал: " + text);
        }
    };

    public String remember(OBJECTS obj) {
        return this + " вспомнил о " + obj.getName();
    }

    public String putHandIn(PLACE place) {
        return this + " сунул руку в " + place.getName();
    }


}
