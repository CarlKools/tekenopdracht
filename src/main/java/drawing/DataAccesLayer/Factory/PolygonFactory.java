package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.IContext.IPolygonContext;
import drawing.DataAccesLayer.MySQLContext.PolygonMySQLContext;

public class PolygonFactory {
    public static IPolygonContext getContext(){
        return new PolygonMySQLContext();
    }
}
