package drawing.javafx;
import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.Factory.DrawingFactory;
import drawing.DataAccesLayer.Repository.DrawingRepository;
import drawing.domain.*;
import drawing.domain.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DrawingTool extends javafx.application.Application {
    private Drawing drawing;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("DrawingTool.fxml"));
        primaryStage.setTitle("Hello World");

        primaryStage.setScene(new Scene(root));

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(40, 40));
        points.add(new Point(30,30));

        drawing = new Drawing("Test");
        drawing.addDrawingItem(new Oval(Color.BLACK, 50, new Point(50,50), 50,50));
        drawing.addDrawingItem(new PaintedText(Color.BLUE, "hallo", "Comic Sans", new Point(50,50), 50, 50));
        drawing.addDrawingItem(new Polygon(Color.GREEN, points, 30));
        drawing.addDrawingItem(new Image(Color.RED, new File("C:\\Users\\Carl Kools\\Pictures\\wallpapers\\Computers_Microsoft_Windows_retro_042865_23"), new Point(70,70), 70,80));

        System.out.println(drawing.toString());

        List<DrawingItem> drawingItems = drawing.getItems();

        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.DatabaseMediator));
        drawingRepository.save(drawing);


        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

    public void Draw(){
        for(DrawingItem drawingItem : drawing.getItems()){
            if(drawingItem instanceof Oval){
                drawingItem.paint((Oval)drawingItem);
            }
            if(drawingItem instanceof PaintedText){
                drawingItem.paint((PaintedText)drawingItem);
            }
            if(drawingItem instanceof Polygon){
                drawingItem.paint((Polygon)drawingItem);
            }
            if(drawingItem instanceof Image){
                drawingItem.paint((Image)drawingItem);
            }
        }
    }
}
