package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IOvalContext;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Oval;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OvalMySQLContext extends DatabaseMediator implements IOvalContext {

    @Override
    public void Insert(Oval oval) {
        try{
            if(super.initConnection()){

                String queryItem = "INSERT INTO drawingtool(color) VALUES (?)";

                PreparedStatement preparedStatementItem = super.getConnection().prepareStatement(queryItem, Statement.RETURN_GENERATED_KEYS);
                preparedStatementItem.setInt(1, oval.getColor());

                int rowsaffected = preparedStatementItem.executeUpdate();
                ResultSet resultSet = preparedStatementItem.getGeneratedKeys();
                if(resultSet.next()){
                    oval.setId(resultSet.getInt(1));
                }

                String query = "INSERT INTO oval(weight, anchorx, anchory, width, height, drawingtoolid) VALUES (?,?,?,?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setDouble(1, oval.getWeight());
                preparedStatement.setDouble(2, oval.getAnchor().getX());
                preparedStatement.setDouble(3, oval.getAnchor().getY());
                preparedStatement.setDouble(4, oval.getWidth());
                preparedStatement.setDouble(5, oval.getHeight());
                preparedStatement.setInt(6, oval.getId());


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public Oval getByDrawing(Drawing drawing) {
        return null;
    }
}
