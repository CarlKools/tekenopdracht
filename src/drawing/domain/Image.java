package drawing.domain;

import java.io.File;

public class Image extends DrawingItem {
    private File file;
    private Point anchor;
    private double width;
    private double height;

    public Image(Color color, File file, Point anchor, double width, double height) {
        super(color);
        this.file = file;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Point getAnchor() {
        return this.anchor;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IMAGE \n");
        sb.append("Filepath: " + this.file + " Anchor: " + this.anchor.toString() +
                " Width: " + this.width + " Height: " + this.height + this.getColor() + "\n");

        return sb.toString();
    }
}
