import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {

    private static Point2D.Double degToPoint(int id, double deg, double min, double max){
        double x = 125 - ((125/(max-min)) * (deg - min));
        System.out.println(x);
        return new Point2D.Double(id*10.0, x);
    }

    private static Line2D.Double pointToLine(Point2D.Double x1, Point2D.Double x2){
        return new Line2D.Double(x1, x2);
    }

    static List<Line2D.Double> generateChartline(double[] temperatures){
        List<Point2D.Double> points = new ArrayList<>();
        double min = Arrays.stream(temperatures).min().getAsDouble();
        double max = Arrays.stream(temperatures).max().getAsDouble();
        points.add(degToPoint(0, temperatures[0], min, max));
        for(int i=1; i<temperatures.length-1; i++){
            points.add(degToPoint(i, temperatures[i], min, max));
            points.add(degToPoint(i, temperatures[i], min, max));
        }
        points.add(degToPoint(temperatures.length, temperatures[temperatures.length-1], min, max));

        List<Line2D.Double> list = new ArrayList<>();
        for(int i=0; i<points.size(); i+=2){
            list.add(pointToLine(points.get(i), points.get(i+1)));
        }

        return list;
    }

    static List<Line2D.Double> generateChartline(double[] temperatures, double min, double max){
        List<Point2D.Double> points = new ArrayList<>();
        points.add(degToPoint(0, temperatures[0], min, max));
        for(int i=1; i<temperatures.length-1; i++){
            points.add(degToPoint(i, temperatures[i], min, max));
            points.add(degToPoint(i, temperatures[i], min, max));
        }
        points.add(degToPoint(temperatures.length, temperatures[temperatures.length-1], min, max));

        List<Line2D.Double> list = new ArrayList<>();
        for(int i=0; i<points.size(); i+=2){
            list.add(pointToLine(points.get(i), points.get(i+1)));
        }

        return list;
    }

    public Convert(){

    }

}
