package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

public class ImageRepository {
    private PersistencyMediator persistencyMediator;

    public ImageRepository(PersistencyMediator persistencyMediator){
        this.persistencyMediator = persistencyMediator;
    }

    public Drawing load(String nameDrawing) {
        return this.persistencyMediator.load(nameDrawing);
    }

    public boolean save(Drawing drawing) {
        return this.persistencyMediator.save(drawing);
    }

    public boolean init(Properties properties) {
        return this.persistencyMediator.init(properties);
    }
}
