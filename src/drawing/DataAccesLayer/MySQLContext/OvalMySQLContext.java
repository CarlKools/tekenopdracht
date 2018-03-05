package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OvalMySQLContext extends DatabaseMediator {
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
