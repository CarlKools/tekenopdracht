package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.IContext.IOvalContext;
import drawing.DataAccesLayer.MySQLContext.OvalMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.OvalSerializeContext;

public class OvalFactory {
    public static IOvalContext getContext(){
        return new OvalMySQLContext();
    }
}
