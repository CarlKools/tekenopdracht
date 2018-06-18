package drawing.DataAccesLayer.Serialization;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

import java.io.Serializable;

public abstract class SerializationMediator implements PersistencyMediator, Serializable {
    public abstract Drawing load(String nameDrawing);

    public abstract boolean save(Drawing drawing);

    public abstract boolean init(Properties properties);
}
