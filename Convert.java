import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {

    /* degrees of Celsius to Point2D.Double */
    private static Point2D.Double degToPoint(int id, double len, double deg, double min, double max){
        double x = 125 - ((125/(max-min)) * (deg - min));
        System.out.println(x);
        return new Point2D.Double(id*(700.0/(len-1.0)), x); // first parameter is a part of length by the numbers of temperatures
    }

    /* Text values power of heating to Point2D.Double */
    private static Point2D.Double powerToPoint(int id, double len, String current){
        double value = 0.0;
        if(current.equals("ZERO")) value = 0.0;
        else if(current.equals("LOW")) value = 1.0;
        else if(current.equals("MEDIUM")) value = 2.0;
        else if(current.equals("HIGH")) value = 3.0;
        else if(current.equals("MAX")) value = 4.0;
        double x = 125 - ((125/4) * value);
        System.out.println(x);
        return new Point2D.Double(id*(700.0/(len-1.0)), x); // first parameter is a part of length by the numbers of temperatures
    }

    /* Generate Line from two Points */
    private static Line2D.Double pointToLine(Point2D.Double x1, Point2D.Double x2){
        return new Line2D.Double(x1, x2);
    }

    /* Generate list of lines from array of temperatures */
    static List<Line2D.Double> generateChartline(double[] temperatures){
        List<Point2D.Double> points = new ArrayList<>();
        double min = Arrays.stream(temperatures).min().getAsDouble();
        double max = Arrays.stream(temperatures).max().getAsDouble();
        points.add(degToPoint(0, temperatures.length, temperatures[0], min, max));
        for(int i=1; i<temperatures.length-1; i++){
            points.add(degToPoint(i, temperatures.length, temperatures[i], min, max));
            points.add(degToPoint(i, temperatures.length, temperatures[i], min, max));
        }
        points.add(degToPoint(temperatures.length, temperatures.length, temperatures[temperatures.length-1], min, max));

        List<Line2D.Double> list = new ArrayList<>();
        for(int i=0; i<points.size(); i+=2){
            list.add(pointToLine(points.get(i), points.get(i+1)));
        }

        return list;
    }

    /* Generate list of lines from array of temperatures with minimal and maximal values */
    static List<Line2D.Double> generateChartline(double[] temperatures, double min, double max){
        List<Point2D.Double> points = new ArrayList<>();
        points.add(degToPoint(0, temperatures.length, temperatures[0], min, max));
        for(int i=1; i<temperatures.length-1; i++){
            points.add(degToPoint(i, temperatures.length, temperatures[i], min, max));
            points.add(degToPoint(i, temperatures.length, temperatures[i], min, max));
        }
        points.add(degToPoint(temperatures.length, temperatures.length, temperatures[temperatures.length-1], min, max));

        List<Line2D.Double> list = new ArrayList<>();
        for(int i=0; i<points.size(); i+=2){
            list.add(pointToLine(points.get(i), points.get(i+1)));
        }

        return list;
    }

    /* Generate list of lines from array of string text values, and low and high values */
    static List<Line2D.Double> generateChartline(String[] power){
        List<Point2D.Double> points = new ArrayList<>();
        points.add(powerToPoint(0, power.length, power[0]));
        for(int i=1; i<power.length-1; i++){
            points.add(powerToPoint(i, power.length, power[i]));
            points.add(powerToPoint(i, power.length, power[i]));
        }
        points.add(powerToPoint(power.length-1, power.length, power[power.length-1]));

        List<Line2D.Double> list = new ArrayList<>();
        for(int i=0; i<points.size(); i+=2){
            list.add(pointToLine(points.get(i), points.get(i+1)));
        }

        return list;
    }

    public Convert(){

    }

}
