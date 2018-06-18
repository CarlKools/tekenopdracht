package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Oval;

import java.util.ArrayList;

public interface IOvalContext {
    public void Insert(Oval oval);
    public ArrayList<Oval> getByDrawing(Drawing drawing);
}
