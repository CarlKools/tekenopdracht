package drawing.DataAccesLayer.IContext;

import drawing.domain.Drawing;
import drawing.domain.Polygon;

public interface IPolygonContext {
    public void Insert(Polygon polygon);
    public Polygon getByDrawing(Drawing drawing);
}
