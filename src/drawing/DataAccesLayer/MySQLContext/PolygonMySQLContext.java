package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IPolygonContext;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Point;
import drawing.domain.Polygon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PolygonMySQLContext extends DatabaseMediator implements IPolygonContext {

    @Override
    public void Insert(Polygon polygon) {
        try{
            if(super.initConnection()){

                String queryItem = "INSERT INTO drawingtool(color) VALUES (?)";

                PreparedStatement preparedStatementItem = super.getConnection().prepareStatement(queryItem, Statement.RETURN_GENERATED_KEYS);
                preparedStatementItem.setInt(1, polygon.getColor());

                int rowsaffected = preparedStatementItem.executeUpdate();
                ResultSet resultSet = preparedStatementItem.getGeneratedKeys();
                if(resultSet.next()){
                    polygon.setId(resultSet.getInt(1));
                }

                String query = "INSERT INTO polygon(weight, drawingtoolid) VALUES (?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, polygon.getWeight());
                preparedStatement.setInt(2, polygon.getId());


                int rowsAffected = preparedStatement.executeUpdate();
                ResultSet resultSet1 = preparedStatement.getGeneratedKeys();

                int polygonId = 0;
                if(resultSet1.next()){
                    polygonId = resultSet1.getInt(1);
                }

                for(Point point: polygon.getVertices()){
                    insertVertex(polygonId, point);
                }

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public Polygon getByDrawing(Drawing drawing) {
        return null;
    }
    private void insertVertex(int id, Point point){
        try{
            if(super.initConnection()){

                String query = "INSERT INTO vertex(x, y, polygonid) VALUES (?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setDouble(1, point.getX());
                preparedStatement.setDouble(2, point.getY());
                preparedStatement.setInt(3, id);

                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
}
