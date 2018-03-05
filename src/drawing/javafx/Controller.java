package drawing.javafx;
import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.Factory.DrawingFactory;
import drawing.DataAccesLayer.Repository.DrawingRepository;
import drawing.domain.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;
    private JavaFXPaintable javaFXPaintable;
    private Drawing drawing = new Drawing("drawing");

    @FXML
    private void onDrawOval(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);
        this.drawing.paint(new Oval(Color.BLACK, 50, new Point(50,50), 50,50));
}

    @FXML
    private void onDrawText(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);
        this.drawing.paint(new PaintedText(Color.BLUE, "hallo", "Comic Sans", new Point(50,50), 50, 50));
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
        this.drawing.paint(new Polygon(Color.GREEN, points, 30));
    }

    @FXML
    private void onDrawImage(){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        this.javaFXPaintable = new JavaFXPaintable(graphicsContext);
        this.drawing.paintUsing(this.javaFXPaintable);

        this.drawing.paint(new Image(Color.RED, new File("C:\\User\\Carl Kools\\Pictures\\wallpapers\\Computers_Microsoft_Windows_retro_042865_23.jpg"), new Point(70,70), 70,80));
    }

    @FXML
    private void onSave(){
        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.DatabaseMediator));
        drawingRepository.save(drawing);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
