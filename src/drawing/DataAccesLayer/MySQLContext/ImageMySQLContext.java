package drawing.DataAccesLayer.MySQLContext;

import com.mysql.cj.api.mysqla.result.Resultset;
import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IImageContext;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.Image;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                if(resultSet.next()){
                    image.setId(resultSet.getInt(1));
                }

                String query = "INSERT INTO image(filepath, anchorx, anchory, width, height, drawingtoolid) VALUES (?,?,?,?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, image.getFile().getPath());
                preparedStatement.setDouble(2, image.getAnchor().getX());
                preparedStatement.setDouble(3, image.getAnchor().getY());
                preparedStatement.setDouble(4, image.getWidth());
                preparedStatement.setDouble(5, image.getHeight());
                preparedStatement.setInt(6, image.getId());


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public Image getByDrawing(Drawing drawing) {
        return null;
    }
}

