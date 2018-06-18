package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.IContext.IPaintedTextContext;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.domain.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

                int id = 0;
                if(resultSet.next()){
                    id  = resultSet.getInt(1);
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
                preparedStatement.setInt(7, id);


                int rowsAffected = preparedStatement.executeUpdate();

            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public ArrayList<PaintedText> getByDrawing(Drawing drawing) {
        try {
            if(super.initConnection()){

                String query = "select drawingtool.color, paintedtext.content, paintedtext.fontname, paintedtext.anchorx, paintedtext.anchory, paintedtext.width, paintedtext.height from paintedtext inner join drawingtool on paintedtext.drawingtoolid = drawingtool.id inner join drawing on drawing.id = drawingtool.drawingid WHERE drawing.name = ?";

                PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
                preparedStatement.setString(1, drawing.getName());

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList<PaintedText> paintedTexts = new ArrayList<>();
                while(resultSet.next()){

                    paintedTexts.add(new PaintedText(Color.fromValue(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3),
                            new Point(resultSet.getDouble(4), resultSet.getDouble(5)), resultSet.getDouble(6),
                            resultSet.getDouble(7)));
                }
                return paintedTexts;


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
