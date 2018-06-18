package drawing.DataAccesLayer.SerializationContext;

import drawing.DataAccesLayer.Properties;
import drawing.DataAccesLayer.Serialization.SerializationMediator;
import drawing.domain.Drawing;

import java.io.*;

public class DrawingSerializeContext extends SerializationMediator {
    @Override
    public Drawing load(String nameDrawing) {
        try{
            FileInputStream fileInputStream = new FileInputStream("drawing.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Drawing drawing = (Drawing)objectInputStream.readObject();

            return drawing;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Drawing drawing) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("drawing.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(drawing);
            outputStream.close();
            fileOutputStream.close();

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean init(Properties properties) {
        return false;
    }
}
