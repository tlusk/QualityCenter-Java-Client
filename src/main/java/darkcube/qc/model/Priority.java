package darkcube.qc.model;

public enum Priority {
    BLUE(4), YELLOW(3), ORANGE(2), RED(1);

    private int value;

    private Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
