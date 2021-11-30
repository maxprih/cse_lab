public enum PLACE {
    UPPER_SHELVES("Вверхние полки"),
    FLOOR("Пол"),
    LAVKA("Лавка"),
    LAVKA_TOLSTYAKA("Лавка толстяка"),
    NOWHERE("Нигде");

    private String name;

    PLACE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PLACE{" +
                "name='" + name + '\'' +
                '}';
    }
}

