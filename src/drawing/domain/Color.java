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

    public static Color fromValue(int value){
        for(Color color: Color.values()){
            if(color.getColor() == value){
                return color;
            }
        }
        return null;
    }

    public int getColor() {
        return color;
    }
}
