package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.IContext.IPaintedTextContext;
import drawing.DataAccesLayer.MySQLContext.PaintedTextMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.PaintedTextSerializeContext;

public class PaintedTextFactory {
    public static IPaintedTextContext getContext(){
        return new PaintedTextMySQLContext();
    }

}
