package drawing.domain;

import java.util.Comparator;

public class DrawingComparator implements Comparator<DrawingItem> {
    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        Point anchor = new Point(0, 0);

        Point anchor01 = o1.getAnchor();
        Point anchor02 = o2.getAnchor();

        double sideo1A = anchor01.getX() * anchor01.getX();
        double sideo1B = anchor01.getY() * anchor01.getY();
        double sideo1C = sideo1A + sideo1B;

        double sideo2A = anchor02.getX() * anchor02.getX();
        double sideo2B = anchor02.getY() * anchor02.getY();
        double sideo2C = sideo2A + sideo2B;

        double distanceO1 = Math.sqrt(sideo1C);
        double distanceO2 = Math.sqrt(sideo2C);

        if(distanceO1 > distanceO2){
            return 1;
        }
        else if(distanceO1 < distanceO2){
            return -1;
        }

        return 0;
    }
}
