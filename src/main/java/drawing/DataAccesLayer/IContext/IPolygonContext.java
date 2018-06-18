package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Polygon;

import java.util.ArrayList;

public interface IPolygonContext {
    public void Insert(Polygon polygon);
    public ArrayList<Polygon> getByDrawing(Drawing drawing);
}
