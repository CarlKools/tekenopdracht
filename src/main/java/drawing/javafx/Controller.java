package drawing.javafx;
import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.Factory.DrawingFactory;
import drawing.DataAccesLayer.Repository.DrawingRepository;
import drawing.domain.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private ComboBox comboBoxDrawings;
    private JavaFXPaintable javaFXPaintable;
    private Drawing drawing = new Drawing("drawing");

    @FXML
    private void onDrawOval(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);
        Oval oval = new Oval(Color.BLACK, 50, new Point(50,50), 50,50);
        drawing.addDrawingItem(oval);
        this.drawing.paint(oval);
}

    @FXML
    private void onDrawText(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);
        PaintedText paintedText = new PaintedText(Color.BLUE, "hallo", "Comic Sans", new Point(50,50), 50, 50);
        drawing.addDrawingItem(paintedText);
        this.drawing.paint(paintedText);
    }

    @FXML
    private void onDrawPolygon(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(40, 50));
        points.add(new Point(30,80));
        points.add(new Point(80,30));
        points.add(new Point(50,60));
        points.add(new Point(140,110));

        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);
        Polygon polygon = new Polygon(Color.GREEN, points, 30);
        drawing.addDrawingItem(polygon);
        this.drawing.paint(polygon);
    }

    @FXML
    private void onDrawImage(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);

        this.drawing.paint(new Image(Color.RED, new File("drawing/images/heart.png"), new Point(70,70), 70,80));

    }

    @FXML
    private void onSave(){
        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.DatabaseMediator));
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(40, 40));
        points.add(new Point(30,30));

        Drawing newDrawing = new Drawing("New");
        newDrawing.addDrawingItem(new Oval(Color.BLACK, 50, new Point(50,50), 50,50));
        newDrawing.addDrawingItem(new PaintedText(Color.BLUE, "hallo", "Comic Sans", new Point(55,55), 50, 50));
        newDrawing.addDrawingItem(new Polygon(Color.GREEN, points, 30));

        drawingRepository.save(drawing);



        drawing.addDrawingItem(newDrawing);
        setItems(drawing.getObservableList());

        //test
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);

        this.drawing.paint();

        for(int i = 0; i < drawing.getItems().size(); i++){
            int j = i + 1;

            System.out.println(drawing.toString());

            if(j < drawing.getItems().size()){
                if(drawing.getItems().get(j).overlaps(drawing.getItems().get(i))){
                 System.out.println("Overlapping occurred: \n" + drawing.getItems().get(i).toString() + "OVERLAPS > \n" + drawing.getItems().get(j).toString());
                }
            }
            else {
                if(i != 0 && drawing.getItems().get(i).overlaps(drawing.getItems().get(i - 1))){
                    drawing.getItems().get(i).toString();
                }
            }

        }

    }
    @FXML
    private void onLoadSer(){
        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.SerializationMediator));
        Drawing drawing = drawingRepository.load("drawing.ser");

        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        drawing.paintUsing(this.javaFXPaintable);

        drawing.paint();

    }

    @FXML
    private void onLoadDat(){
        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.DatabaseMediator));
        Drawing drawing = drawingRepository.load("drawing");

        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        drawing.paintUsing(this.javaFXPaintable);


        drawing.paint();
    }

    @FXML
    private void onSaveSer(){
        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.SerializationMediator));
        drawingRepository.save(this.drawing);
    }

    private void setItems(ObservableList List){
        this.comboBoxDrawings.setItems(List);

    }


    @FXML
    private void onClose(){
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
