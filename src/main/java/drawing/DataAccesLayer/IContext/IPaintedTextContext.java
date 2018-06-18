package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.PaintedText;

import java.util.ArrayList;

public interface IPaintedTextContext {
    public void Insert(PaintedText paintedText);
    public ArrayList<PaintedText> getByDrawing(Drawing drawing);
}
