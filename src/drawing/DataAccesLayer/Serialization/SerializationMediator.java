package drawing.DataAccesLayer.Serialization;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

public abstract class SerializationMediator implements PersistencyMediator {
    public abstract Drawing load(String nameDrawing);

    public abstract boolean save(Drawing drawing);

    public abstract boolean init(Properties properties);
}
