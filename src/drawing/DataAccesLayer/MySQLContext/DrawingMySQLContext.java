package drawing.DataAccesLayer.MySQLContext;

import drawing.DataAccesLayer.Database.DatabaseMediator;
import drawing.DataAccesLayer.Enum.Context;
import drawing.DataAccesLayer.Factory.ImageFactory;
import drawing.DataAccesLayer.Factory.OvalFactory;
import drawing.DataAccesLayer.Factory.PaintedTextFactory;
import drawing.DataAccesLayer.Factory.PolygonFactory;
import drawing.DataAccesLayer.PersistencyMediator;
import drawing.DataAccesLayer.Properties;
import drawing.DataAccesLayer.Repository.ImageRepository;
import drawing.DataAccesLayer.Repository.OvalRepository;
import drawing.DataAccesLayer.Repository.PaintedTextRepository;
import drawing.DataAccesLayer.Repository.PolygonRepository;
import drawing.domain.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrawingMySQLContext extends DatabaseMediator implements PersistencyMediator {
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

                ImageRepository imageRepository = new ImageRepository(ImageFactory.getContext());
                OvalRepository ovalRepository = new OvalRepository(OvalFactory.getContext());
                PaintedTextRepository paintedTextRepository = new PaintedTextRepository(PaintedTextFactory.getContext());
                PolygonRepository polygonRepository = new PolygonRepository(PolygonFactory.getContext());

                for(DrawingItem item: drawing.getItems()){
                    if(item instanceof Image){
                        imageRepository.Insert((Image) item);
                    }
                    if(item instanceof Oval){
                        ovalRepository.Insert((Oval) item);
                    }
                    if(item instanceof PaintedText){
                        paintedTextRepository.Insert((PaintedText) item);
                    }
                    if(item instanceof Polygon){
                        polygonRepository.Insert((Polygon) item
                        );
                    }
                }


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
