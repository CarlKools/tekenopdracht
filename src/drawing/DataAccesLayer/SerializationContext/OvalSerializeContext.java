package drawing.DataAccesLayer.SerializationContext;

import drawing.DataAccesLayer.Properties;
import drawing.DataAccesLayer.Serialization.SerializationMediator;
import drawing.domain.Drawing;

public class OvalSerializeContext extends SerializationMediator {
    @Override
    public Drawing load(String nameDrawing) {
        return null;
    }

    @Override
    public boolean save(Drawing drawing) {
        return false;
    }

    @Override
    public boolean init(Properties properties) {
        return false;
    }
}
