public enum OBJECTS {
    CANE("Трость"),
    CYLINDER("Цилиндр", PLACE.LAVKA);

    private String name;
    private PLACE place;

    OBJECTS(String name, PLACE place) {
        this.name = name;
        this.place = place;
    }

    OBJECTS(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OBJECTS{" +
                "name='" + name + '\'' +
                ", place=" + place +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPlaceWithName() {
        if (this.place == null) {
            return this.getName() + " " + PLACE.NOWHERE.getName();
        }
        return this.getName() + " " + place.getName();
    }
    public String getPlace() {
        if (this.place == null) {
            return PLACE.NOWHERE.getName();
        }
        return place.getName();
    }

    public void setPlace(PLACE place) {
        this.place = place;
    }

}
