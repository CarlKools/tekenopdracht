package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.IContext.IPolygonContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Polygon;

import java.util.ArrayList;

public class PolygonRepository {
    private IPolygonContext iPolygonContext;

    public PolygonRepository(IPolygonContext iPolygonContext){
        this.iPolygonContext = iPolygonContext;
    }

    public void Insert(Polygon polygon) {
        this.iPolygonContext.Insert(polygon);
    }

    public ArrayList<Polygon> getByDrawing(Drawing drawing) {
        return this.iPolygonContext.getByDrawing(drawing);
    }
}
