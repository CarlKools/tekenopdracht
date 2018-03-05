package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.MySQLContext.DrawingMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.DrawingSerializeContext;

public class DrawingFactory {
    public static PersistencyMediator getContext(Context context){
        switch (context){
            case SerializationMediator:
                return new DrawingSerializeContext();

            case DatabaseMediator:
                return new DrawingMySQLContext();
        }
        return null;
    }
}
