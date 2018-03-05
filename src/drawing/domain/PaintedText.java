package drawing.domain;

public class PaintedText extends DrawingItem {
    private String content;
    private String fontName;
    private Point anchor;
    private double width;
    private double height;

    public PaintedText(Color color, String content, String fontName, Point anchor, double width, double height) {
        super(color);
        this.content = content;
        this.fontName = fontName;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
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
        sb.append("PaintedText \n");
        sb.append("Anchor: " + getAnchor().toString() + " Width: " + getWidth() +
                " Height: " + getHeight() + " Content: " + this.content +
                " FontName: " + this.fontName + " Color: " + super.getColor() + "\n");

        return sb.toString();
    }
}

