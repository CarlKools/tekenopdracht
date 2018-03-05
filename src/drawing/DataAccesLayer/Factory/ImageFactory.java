package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.MySQLContext.ImageMySQLContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.SerializationContext.ImageSerializeContext;

public class ImageFactory {
    public static PersistencyMediator getContext(Context context){
        switch (context){
            case SerializationMediator:
                return new ImageSerializeContext();

            case DatabaseMediator:
                return new ImageMySQLContext();
        }
        return null;
    }
}
