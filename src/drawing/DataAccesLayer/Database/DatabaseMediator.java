package drawing.DataAccesLayer.Database;
import java.sql.*;

public class DatabaseMediator {
    private java.util.Properties properties;
    private Connection connection;
    private ResultSet resultSet;

    public DatabaseMediator(){
        this.properties = new java.util.Properties();
        this.properties.setProperty("user", "DrawingTool");
        this.properties.setProperty("password", "DrawingTool");
        this.properties.setProperty("useSSL", "false");
    }

    public boolean initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/drawingtool?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", this.properties);
            return true;
        } catch (SQLException sqle) {
            return false;
        }

    }

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
