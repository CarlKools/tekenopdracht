package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.IContext.IImageContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Image;

public class ImageRepository {
    private IImageContext iImageContext;

    public ImageRepository(IImageContext iImageContext){
        this.iImageContext = iImageContext;
    }

    public void Insert(Image image) {
        this.iImageContext.Insert(image);
    }

    public Image getByDrawing(Drawing drawing) {
        return this.iImageContext.getByDrawing(drawing);
    }

}
