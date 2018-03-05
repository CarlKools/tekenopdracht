package drawing.DataAccesLayer;

import drawing.domain.Drawing;

public interface PersistencyMediator {
    public Drawing load(String nameDrawing);
    public boolean save(Drawing drawing);
    public boolean init(Properties properties);
}
