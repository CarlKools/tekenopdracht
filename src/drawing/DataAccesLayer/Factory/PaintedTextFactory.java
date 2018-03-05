package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.MySQLContext.PaintedTextMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.PaintedTextSerializeContext;

public class PaintedTextFactory {
    public static PersistencyMediator getContext(Context context){
        switch (context){
            case SerializationMediator:
                return new PaintedTextSerializeContext();

            case DatabaseMediator:
                return new PaintedTextMySQLContext();
        }
        return null;
    }

}
