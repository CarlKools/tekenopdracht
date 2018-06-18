package drawing.DataAccesLayer.Repository;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

public class DrawingRepository {
    private PersistencyMediator persistencyMediator;

    public DrawingRepository(PersistencyMediator persistencyMediator){
        this.persistencyMediator = persistencyMediator;
    }

    public Drawing load(String nameDrawing) {
        return persistencyMediator.load(nameDrawing);
    }

    public boolean save(Drawing drawing) {
        return persistencyMediator.save(drawing);
    }

    public boolean init(Properties properties) {
        return persistencyMediator.init(properties);
    }
}
