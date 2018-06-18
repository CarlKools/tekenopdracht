package drawing.DataAccesLayer.Factory;

import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.IContext.IImageContext;
import drawing.DataAccesLayer.MySQLContext.ImageMySQLContext;


public class ImageFactory {
    public static IImageContext getContext(){
        return new ImageMySQLContext();

    }

}
