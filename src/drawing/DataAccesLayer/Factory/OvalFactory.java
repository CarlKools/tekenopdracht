package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.MySQLContext.OvalMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.OvalSerializeContext;

public class OvalFactory {
    public static PersistencyMediator getContext(Context context){
        switch (context){
            case SerializationMediator:
                return new OvalSerializeContext();

            case DatabaseMediator:
                return new OvalMySQLContext();
        }
        return null;
    }
}
