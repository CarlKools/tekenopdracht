package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IPaintedTextContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.Drawing;
import drawing.domain.PaintedText;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaintedTextMySQLContext extends DatabaseMediator implements IPaintedTextContext{

    @Override
    public void Insert(PaintedText paintedText) {
        try{
            if(super.initConnection()){

                String queryItem = "INSERT INTO drawingtool(color) VALUES (?)";

                PreparedStatement preparedStatementItem = super.getConnection().prepareStatement(queryItem, Statement.RETURN_GENERATED_KEYS);
                preparedStatementItem.setInt(1, paintedText.getColor());

                int rowsaffected = preparedStatementItem.executeUpdate();
                ResultSet resultSet = preparedStatementItem.getGeneratedKeys();
                if(resultSet.next()){
                    paintedText.setId(resultSet.getInt(1));
                }

                String query = "INSERT INTO paintedtext(content, fontname, anchorx, anchory, width, height, drawingtoolid) VALUES (?,?,?,?,?,?,?)";

                //to prevent sql injection
                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, paintedText.getContent());
                preparedStatement.setString(2, paintedText.getFontName());
                preparedStatement.setDouble(3, paintedText.getAnchor().getX());
                preparedStatement.setDouble(4, paintedText.getAnchor().getY());
                preparedStatement.setDouble(5, paintedText.getWidth());
                preparedStatement.setDouble(6, paintedText.getHeight());
                preparedStatement.setInt(7, paintedText.getId());


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public PaintedText getByDrawing(Drawing drawing) {
        return null;
    }
}
