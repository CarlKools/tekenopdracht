package drawing.DataAccesLayer.Database;

import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

import java.sql.*;

public abstract class DatabaseMediator implements PersistencyMediator {
    private Properties properties;
    private Connection connection;
    private ResultSet resultSet;

    public DatabaseMediator(){
        this.properties = new Properties();
    }

    public boolean initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/drawingtool?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", this.properties.getUsername(), this.properties.getPassword());
            return true;
        } catch (SQLException sqle) {
            return false;
        }

    }


    @Override
    public abstract Drawing load(String nameDrawing) ;

    @Override
    public abstract boolean save(Drawing drawing);

    @Override
    public abstract boolean init(Properties properties);

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Connection getConnection() {
        return connection;
    }
}
