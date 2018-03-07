package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IPolygonContext;
import drawing.DataAccesLayer.Properties;
import drawing.domain.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

                int id = 0;
                if(resultSet.next()){
                    id = resultSet.getInt(1);
                }

                String query = "INSERT INTO polygon(weight, drawingtoolid) VALUES (?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, polygon.getWeight());
                preparedStatement.setInt(2, id);


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
    public ArrayList<Polygon> getByDrawing(Drawing drawing) {
        try {
            if(super.initConnection()){

                String query = "select drawingtool.color, polygon.id, polygon.weight from polygon inner join drawingtool on polygon.drawingtoolid = drawingtool.id inner join drawing on drawing.id = drawingtool.drawingid WHERE drawing.name = ?";

                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, drawing.getName());

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList<Polygon> polygons = new ArrayList<>();
                ArrayList<Polygon> newPolygons = new ArrayList<>();
                ArrayList<Integer> ids = new ArrayList<>();

                while(resultSet.next()){
                    ids.add(resultSet.getInt(2));
                    polygons.add(new Polygon(Color.fromValue(resultSet.getInt(1)), new ArrayList<Point>(), resultSet.getDouble(3)));
                }

                for(int i = 0; i < ids.size(); i++){
                    Polygon polygon = polygons.get(i);
                    polygon.setVertices(getPoints(ids.get(i)));
                    newPolygons.add(polygon);
                }

                return newPolygons;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
    private ArrayList<Point> getPoints(int id){
        try{
            if(super.initConnection()){

                String query = "SELECT vertex.x, vertex.y from vertex WHERE vertex.polygonid = ?";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setDouble(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList<Point> points = new ArrayList<>();

                while(resultSet.next()){
                    points.add(new Point(resultSet.getInt(1), resultSet.getInt(2)));
                }

                return points;

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
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
