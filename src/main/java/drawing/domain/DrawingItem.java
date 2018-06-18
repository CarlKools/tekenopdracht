package drawing.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public boolean overlaps(DrawingItem drawingItem){

        List<Point> boundingBox = new ArrayList<>();

        if(!(drawingItem instanceof Drawing)){
        boundingBox.add(drawingItem.getAnchor());
        boundingBox.add(new Point(drawingItem.getAnchor().getX() + drawingItem.getWidth(), drawingItem.getAnchor().getY()));
        boundingBox.add(new Point(drawingItem.getAnchor().getX(), drawingItem.getAnchor().getY() + getHeight()));
        boundingBox.add(new Point(drawingItem.getAnchor().getX() + drawingItem.getWidth(), drawingItem.getAnchor().getY() + getHeight()));

        for(Point point: boundingBox){
            if(insideBoundingBox(point)){
                return true;
            }
        }
        }

        return false;
    }

    public boolean insideBoundingBox(Point point){

        if(this.getAnchor().getX() > point.getX() && point.getX() < (this.getAnchor().getX() + this.getWidth())){

            if(point.getY() > this.getAnchor().getY() && point.getY() < (this.getAnchor().getY() + this.getHeight())){
                return true;
            }
        }
        return false;
    }

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
