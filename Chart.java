import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chart extends JComponent implements Serializable {

    private TitledBorder titledBorder;
    private BackgroundChart backgroundChart;
    private LabelsY labelsY;
    private LabelsX labelsX;
    private ChartLine chartLine;
    private List<Line2D.Double> lines;

    public Chart(String title, double[] temperatures, Color color) {

        /* Set bounds and border with title component */
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(),title);
        titledBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        setBorder(titledBorder);
        setSize(780,190);

        /* Convert received temperatures to points on chart */
        lines = Convert.generateChartline(temperatures, Arrays.stream(temperatures).min().getAsDouble()-5.0, Arrays.stream(temperatures).max().getAsDouble()+5.0);

        /* implements line with color */
        chartLine = new ChartLine(lines, color);
        chartLine.setLocation(50,32);
        add(chartLine);

        /* implements label with temperatures from min do max */
        labelsY = new LabelsY(Arrays.stream(temperatures).min().getAsDouble()-5.0, Arrays.stream(temperatures).max().getAsDouble()+5.0);
        labelsY.setLocation(10,30);
        add(labelsY);

        /* implements label with hours */
        labelsX = new LabelsX();
        labelsX.setLocation(30,155);
        add(labelsX);

        /* implements background chart */
        backgroundChart = new BackgroundChart();
        backgroundChart.setLocation(50,32);
        add(backgroundChart);

    }

    public Chart(String title, double[] temperatures, double min, double max, Color color) {

        /* Set bounds and border with title component */
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(),title);
        titledBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        setBorder(titledBorder);
        setSize(780,190);

        /* Convert received temperatures to points on chart */
        lines = Convert.generateChartline(temperatures, min, max);

        /* implements line with color */
        chartLine = new ChartLine(lines, color);
        chartLine.setLocation(50,32);
        add(chartLine);

        /* implements label with temperatures from min do max */
        labelsY = new LabelsY(min, max);
        labelsY.setLocation(10,30);
        add(labelsY);

        /* implements label with hours */
        labelsX = new LabelsX();
        labelsX.setLocation(30,155);
        add(labelsX);

        /* implements background chart */
        backgroundChart = new BackgroundChart();
        backgroundChart.setLocation(50,32);
        add(backgroundChart);

    }


    public Chart(String title, String[] power, String[] valuesLabel, Color color) {

        /* Set bounds and border with title component */
        titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(),title);
        titledBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        setBorder(titledBorder);
        setSize(780,190);

        /* Convert received temperatures to points on chart */
        lines = Convert.generateChartline(power);

        /* implements line with color */
        chartLine = new ChartLine(lines, color);
        chartLine.setLocation(50,32);
        add(chartLine);

        /* implements label with temperatures from min do max */
        labelsY = new LabelsY(valuesLabel);
        labelsY.setLocation(5,20);
        add(labelsY);

        /* implements label with hours */
        labelsX = new LabelsX();
        labelsX.setLocation(30,155);
        add(labelsX);

        /* implements background chart */
        backgroundChart = new BackgroundChart();
        backgroundChart.setLocation(50,32);
        add(backgroundChart);
    }

    /* internal class - set line on chart */
    class ChartLine extends JComponent{

        private java.util.List<Line2D.Double> lines = new ArrayList<>();
        private Color color;

        public ChartLine(List<Line2D.Double> lines, Color color) {
            this.lines = lines;
            this.color = color;
            this.paint(this.getGraphics());
            setSize(700,125);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2)); // grubość linii
            g2.setColor(color);
            for(Line2D l : lines){
                g2.draw(l);
            }
        }

    }

    /* internal class - setting background parameters */
    class BackgroundChart extends JComponent {

        public BackgroundChart() {
            setSize(700,125);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.fill(new Rectangle2D.Double(0,0,699,124));
            g2.setColor(Color.GRAY);
            g2.draw(new Rectangle2D.Double(0,0,699,124));
            g2.setColor(Color.BLACK);

            g2.setColor(Color.lightGray);
            for(int i=1; i<9; i++){
                g2.draw(new Line2D.Double(0,15.5*i,699,15.5*i));
            }
        }
    }


    class LabelsY extends JComponent{

        public LabelsY(double minV, double maxV) {
            setSize(50,150);
            double div = (maxV-minV)/8;
            double value = minV;
            int posY = 115;
            DecimalFormat dec = new DecimalFormat("##.##");
            for(int i=0; i<9; i++){ // max value it labels which has been created on axis Y.
                JLabel label = new JLabel("", SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.BOLD, 9));
                label.setText(dec.format(value));
                value+=div;
                label.setBounds(0,posY,35,20);
                posY-=15; // leading between labels
                add(label);
            }
        }
        public LabelsY(String low, String high) {
            setSize(50,150);
            int posY = 115;
            JLabel label1 = new JLabel(low, SwingConstants.RIGHT);
            JLabel label2 = new JLabel(high, SwingConstants.RIGHT);
            label1.setFont(new Font("Dialog", Font.BOLD, 9));
            label2.setFont(new Font("Dialog", Font.BOLD, 9));
            label1.setBounds(0,115,35,20);
            label2.setBounds(0,0,35,20);
            add(label1);
            add(label2);
        }

        public LabelsY(String[] values) {
            setSize(55,160);
            int posY = 125;
            double div = 31;
            for(int i=0; i<values.length; i++){ // max value it labels which has been created on axis Y.
                JLabel label = new JLabel("", SwingConstants.RIGHT);
                label.setFont(new Font("Dialog", Font.BOLD, 8));
                label.setText(values[i]);
                label.setBounds(0,posY,35,20);
                posY-=div; // leading between labels
                add(label);
            }
        }
    }

    class LabelsX extends JComponent{

        public LabelsX() {
            setSize(730,30);
            int posX = 5;
            for(int i=0; i<25; i++){
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setFont(new Font("Dialog", Font.BOLD, 9));
                if(i<10) label.setText("0" + String.valueOf(i) + ":00");
                else label.setText(String.valueOf(i) + ":00");
                label.setBounds(posX, 0, 35, 25);
                add(label);
                posX+=29;
            }
        }
    }
}