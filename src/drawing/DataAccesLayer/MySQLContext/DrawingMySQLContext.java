package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrawingMySQLContext extends DatabaseMediator {
    @Override
    public Drawing load(String nameDrawing) {
        return null;
    }

    @Override
    public boolean save(Drawing drawing) {
        try{
            if(super.initConnection()){

                String query = "INSERT INTO drawing(name) VALUES (?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, drawing.getName());

                int rowsAffected = preparedStatement.executeUpdate();
                return true;
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean init(Properties properties) {
        return false;
    }
}
