package drawing.domain;

import java.io.Serializable;

public abstract class DrawingItem implements Serializable {
    private Color color;
    private DrawingItem previousState;
    private IPaintable iPaintable;

    public int getColor(){
        return color.getColor();
    }
    public void setColor(Color color){
        this.color = color;
    }

    public IPaintable getiPaintable(){
        return this.iPaintable;
    }

    public DrawingItem(Color color){
        this.color = color;
    }

    public abstract Point getAnchor();

    public abstract double getWidth();

    public abstract double getHeight();

    public void paintUsing(IPaintable iPaintable){
        this.iPaintable = iPaintable;
    }

    public abstract void paint();

    public void paint(Oval oval){
        iPaintable.paint(oval);
    }

    public void paint(Polygon polygon){
        iPaintable.paint(polygon);
    }

    public void paint(PaintedText text){
        iPaintable.paint(text);
    }

    public void paint(Image image){
        iPaintable.paint(image);
    }

}
