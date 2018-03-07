package drawing.domain;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends DrawingItem {
    //Fields
    private ArrayList<Point> vertices;
    private double weight;

    //Constructor
    public Polygon(Color color, ArrayList<Point> vertices, double weight) {
        super(color);
        this.vertices = vertices;
        this.weight = weight;
    }

    //Properties
    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public double[] getYVertices(){
        double[] Ypoints = new double[vertices.size()];
        for(int i = 0; i < vertices.size(); i++){
            Ypoints[i] = vertices.get(i).getY();
        }
        return Ypoints;
    }

    public double[] getXVertices(){
        double[] Xpoints = new double[vertices.size()];
        for(int i = 0; i < vertices.size(); i++){
            Xpoints[i] = vertices.get(i).getX();
        }
        return Xpoints;
    }

    public void setVertices(ArrayList<Point> vertices) {
        this.vertices = vertices;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Point getAnchor() {

        Point anchor;
        double x;
        double y;

        Point firstpoint = this.vertices.get(0);
        x = firstpoint.getX();
        y = firstpoint.getY();

        for(Point point : this.vertices){
            if(point.getX() < x){
                x = point.getX();
            }
            if(point.getY() < y){
                y = point.getY();
            }
        }

        anchor = new Point(x, y);

        return anchor;
    }

    @Override
    public double getWidth() {

        double startX = this.getAnchor().getX();
        double endX = this.vertices.get(0).getX();

        for(Point point : this.vertices){
            if (point.getX() > endX){
                endX = point.getX();
            }
        }

        double width = endX - startX;

        return width;
    }

    @Override
    public double getHeight() {

        double startY = this.getAnchor().getY();
        double endY = this.vertices.get(0).getY();

        for (Point point : this.vertices){
            if(point.getY() > endY){
                endY = point.getY();
            }
        }

        double height = endY - startY;

        return height;
    }

    @Override
    public String toString() {

        int counter = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("POLYGON \n");

        if(this.vertices.size() > 0){
            sb.append("Anchor: " + getAnchor().toString() + " Width: " + getWidth() +
                    " Height: " + getHeight() + " Weight: " + this.weight + " Color: " + super.getColor() + "\n");

            for(Point point : this.vertices){
                counter++;
                sb.append("point " + counter + ": x= " + point.getX() + ", Y= " + point.getY() + "\n");
            }
        }

        return sb.toString();
    }

}
