package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IOvalContext;
import drawing.DataAccesLayer.Properties;
import drawing.domain.*;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

                int id = 0;
                if(resultSet.next()){
                    id = resultSet.getInt(1);
                }

                String query = "INSERT INTO oval(weight, anchorx, anchory, width, height, drawingtoolid) VALUES (?,?,?,?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setDouble(1, oval.getWeight());
                preparedStatement.setDouble(2, oval.getAnchor().getX());
                preparedStatement.setDouble(3, oval.getAnchor().getY());
                preparedStatement.setDouble(4, oval.getWidth());
                preparedStatement.setDouble(5, oval.getHeight());
                preparedStatement.setInt(6, id);


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Oval> getByDrawing(Drawing drawing) {
        try {
            if(super.initConnection()){

                String query = "select drawingtool.color, oval.weight, oval.anchorx, oval.anchory, oval.width, oval.height from oval inner join drawingtool on oval.drawingtoolid = drawingtool.id inner join drawing on drawing.id = drawingtool.drawingid WHERE drawing.name = ?";

                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, drawing.getName());

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList<Oval> ovals = new ArrayList<>();
                while(resultSet.next()){

                    ovals.add(new Oval(Color.fromValue(resultSet.getInt(1)), resultSet.getDouble(2),
                            new Point(resultSet.getDouble(3), resultSet.getDouble(4)), resultSet.getDouble(5),
                            resultSet.getDouble(6)));
                }
                return ovals;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
