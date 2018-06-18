package drawing.domain;

import java.io.Serializable;

public class Oval extends DrawingItem implements Serializable {
    private double weight;
    private Point anchor;
    private double width;
    private double height;

    public Oval(Color color, double weight, Point anchor, double width, double height) {
        super(color);
        this.weight = weight;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Point getAnchor() {
        return this.anchor;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public void paint() {
        super.getiPaintable().paint(this);
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("OVAL \n");
        sb.append("Anchor: " + this.anchor.toString() + "Width: " + this.width +
                " Height: " + this.height + " Weight: " + this.weight + " Color: " + super.getColor() + "\n");

        return sb.toString();
    }
}
