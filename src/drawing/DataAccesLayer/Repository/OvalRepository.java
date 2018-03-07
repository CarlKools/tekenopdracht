package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.IContext.IOvalContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Oval;

import java.util.ArrayList;

public class OvalRepository {
    private IOvalContext iOvalContext;

    public OvalRepository(IOvalContext iOvalContext){
        this.iOvalContext = iOvalContext;
    }

    public void Insert(Oval oval) {
        this.iOvalContext.Insert(oval);
    }

    public ArrayList<Oval> getByDrawing(Drawing drawing) {
        return this.iOvalContext.getByDrawing(drawing);
    }
}
