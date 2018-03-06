package drawing.domain;

public enum Color {
    BLACK(0),
    WHITE(1),
    RED(2),
    BLUE(3),
    GREEN(4);

    private int color;

    Color(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
