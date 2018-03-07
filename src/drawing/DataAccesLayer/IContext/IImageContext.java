package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Image;

import java.util.ArrayList;

public interface IImageContext {
    public void Insert(Image image);
    public ArrayList<Image> getByDrawing(Drawing drawing);
}
