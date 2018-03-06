package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.IContext.IPaintedTextContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.PaintedText;

public class PaintedTextRepository{
    private IPaintedTextContext iPaintedTextContext;

    public PaintedTextRepository(IPaintedTextContext iPaintedTextContext){
        this.iPaintedTextContext = iPaintedTextContext;
    }

    public void Insert(PaintedText paintedText) {
        this.iPaintedTextContext.Insert(paintedText);
    }

    public PaintedText getByDrawing(Drawing drawing) {
        return this.iPaintedTextContext.getByDrawing(drawing);
    }
}
