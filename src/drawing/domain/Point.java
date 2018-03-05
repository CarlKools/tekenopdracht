package drawing.domain;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("X = " + x + ", Y = " + y + "; ");

        return sb.toString();
    }
}
