package drawing.domain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drawing extends DrawingItem implements Serializable {
    //Fields
    private String name;
    private List<DrawingItem> items = new ArrayList<>();
    private IPaintable iPaintable;


    //Properties
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Point getAnchor() {
        List<DrawingItem> drawingItems = getItems();

        return drawingItems.get(0).getAnchor();
    }

    @Override
    public double getWidth() {
        double width = 0;

        for(DrawingItem item: items){
            width = width + item.getWidth();
        }
        return width;
    }

    @Override
    public double getHeight() {
        double height = 0;

        for(DrawingItem item: items){
            height = height + item.getHeight();
        }

        return height;
    }

    public List<DrawingItem> getItems() {

        Collections.sort(items, new DrawingComparator());

        return new ArrayList<DrawingItem>(Collections.unmodifiableCollection(items));
    }

    public ObservableList<DrawingItem> getObservableList() {
        ObservableList<DrawingItem> observableList = FXCollections.observableList(items);

        return FXCollections.unmodifiableObservableList(observableList);
    }


    //constructor
    public Drawing(String name) {
        super(Color.WHITE);
        this.name = name;
    }

    //Methodes
    public void addDrawingItem(DrawingItem item){
        items.add(item);
    }

    public void removeDrawingItem(DrawingItem item){
        for(DrawingItem drawingItem : items){
            if(drawingItem.equals(item)){
                items.remove(item);
            }
        }
    }

    public boolean updateDrawingItem(DrawingItem selectedItem, DrawingItem updateItem){
        for(DrawingItem drawingItem : items){
            if(drawingItem.equals(selectedItem)){
                items.remove(selectedItem);
                items.add(updateItem);
                return true;
            }
        }
        return false;
    }

    public DrawingItem selectDrawingItem(Point point){
        for(DrawingItem item : items){
            if(point.getX() > item.getAnchor().getX() && point.getX() < (item.getAnchor().getX() + item.getWidth())){
                if(point.getY() > item.getAnchor().getY() && point.getY() < (item.getAnchor().getY() + item.getHeight()))
                {
                    return item;
                }
            }
        }
        return null;
    }

    public void paintUsing(IPaintable iPaintable) {
        this.iPaintable = iPaintable;
    }

    @Override
    public void paint() {
        for (DrawingItem drawingItem: items){
            drawingItem.paintUsing(this.iPaintable);
            drawingItem.paint();
        }
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

    @Override
    public String toString() {

        Collections.sort(items, new DrawingComparator());

        StringBuilder sb = new StringBuilder();

        for(DrawingItem item : this.getItems() ){
            sb.append(item.toString() + "\n");
        }

        return sb.toString();
    }
}
