package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.IContext.IImageContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Image;

import java.util.ArrayList;

public class ImageRepository {
    private IImageContext iImageContext;

    public ImageRepository(IImageContext iImageContext){
        this.iImageContext = iImageContext;
    }

    public void Insert(Image image) {
        this.iImageContext.Insert(image);
    }

    public ArrayList<Image> getByDrawing(Drawing drawing) {
        return this.iImageContext.getByDrawing(drawing);
    }

}
