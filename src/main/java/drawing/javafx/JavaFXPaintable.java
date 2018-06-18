package drawing.javafx;
import drawing.domain.*;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.Serializable;

public class JavaFXPaintable implements IPaintable, Serializable {
    private transient GraphicsContext graphicsContext;

    public JavaFXPaintable(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paint(Oval oval) {
        this.graphicsContext.fillOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWeight(), oval.getHeight());
    }

    @Override
    public void paint(Image image) {
        this.graphicsContext.drawImage(new javafx.scene.image.Image(new File(image.getFile().getName()).toURI().toString(), true), image.getAnchor().getX(), image.getAnchor().getY());
    }

    @Override
    public void paint(Polygon polygon) {
        this.graphicsContext.strokePolygon(polygon.getXVertices(), polygon.getYVertices(), polygon.getVertices().size());
    }

    @Override
    public void paint(PaintedText text) {
        this.graphicsContext.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY());

    }
}
