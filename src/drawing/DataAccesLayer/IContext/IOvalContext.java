package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Oval;

public interface IOvalContext {
    public void Insert(Oval oval);
    public Oval getByDrawing(Drawing drawing);
}
