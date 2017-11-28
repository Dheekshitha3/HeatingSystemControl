import javax.swing.*;
import java.awt.*;

public class WinForm extends JFrame {

    private Chart interiorChart;
    private Chart outdoorChart;
    private TemperaturesArea temperArea;

    public WinForm() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1200,620);
        setResizable(false);
        setVisible(true);

        double[] doubles = {10.0, 11.0, 14.0, 15.0, 22.0, 21.0, 20.0, 19.0, 18.0, 16.0, 12.0, 9.0, 9.0, 7.0};

        interiorChart = new Chart("Temperatura pomieszczenia", doubles, Color.BLUE);
        interiorChart.setLocation(350,40);
        add(interiorChart);

        outdoorChart = new Chart("Zewnętrzna temperatura", doubles, 5.0, 26.0,  Color.red);
        outdoorChart.setLocation(350,240);
        add(outdoorChart);

        temperArea = new TemperaturesArea();
        temperArea.setLocation(240,57);
        add(temperArea);

    }
}
