package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.PaintedText;

public interface IPaintedTextContext {
    public void Insert(PaintedText paintedText);
    public PaintedText getByDrawing(Drawing drawing);
}
