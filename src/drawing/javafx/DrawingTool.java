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



        DrawingRepository drawingRepository = new DrawingRepository(DrawingFactory.getContext(Context.SerializationMediator));



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
