package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Image;

public interface IImageContext {
    public void Insert(Image image);
    public Image getByDrawing(Drawing drawing);
}
