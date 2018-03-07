package drawing.DataAccesLayer.MySQLContext;
import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IImageContext;
import drawing.domain.Color;
import drawing.domain.Drawing;
import drawing.domain.Image;
import drawing.domain.Point;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImageMySQLContext extends DatabaseMediator implements IImageContext {

    @Override
    public void Insert(Image image) {
        try{
            if(super.initConnection()){

                String queryItem = "INSERT INTO drawingtool(color) VALUES (?)";

                PreparedStatement preparedStatementItem = super.getConnection().prepareStatement(queryItem, Statement.RETURN_GENERATED_KEYS);
                preparedStatementItem.setInt(1, image.getColor());

                int rowsaffected = preparedStatementItem.executeUpdate();
                ResultSet resultSet = preparedStatementItem.getGeneratedKeys();

                int id = 0;
                if(resultSet.next()){
                    id = resultSet.getInt(1);
                }

                String query = "INSERT INTO image(filepath, anchorx, anchory, width, height, drawingtoolid) VALUES (?,?,?,?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, image.getFile().getPath());
                preparedStatement.setDouble(2, image.getAnchor().getX());
                preparedStatement.setDouble(3, image.getAnchor().getY());
                preparedStatement.setDouble(4, image.getWidth());
                preparedStatement.setDouble(5, image.getHeight());
                preparedStatement.setInt(6, id);


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Image> getByDrawing(Drawing drawing) {
        try {
            if(super.initConnection()){

                String query = "select drawingtool.color, image.filepath, image.anchorx, image.anchory, image.width, image.height from image inner join drawingtool on image.drawingtoolid = drawingtool.id inner join drawing on drawing.id = drawingtool.drawingid WHERE drawing.name = ?";

                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, drawing.getName());

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList<Image> images = new ArrayList<>();
                while(resultSet.next()){

                    images.add(new Image(Color.fromValue(resultSet.getInt(1)), new File(resultSet.getString(2)),
                            new Point(resultSet.getDouble(3), resultSet.getDouble(4)), resultSet.getDouble(5),
                            resultSet.getDouble(6)));
                }
                return images;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

