package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

public class PaintedTextMySQLContext implements PersistencyMediator{

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
