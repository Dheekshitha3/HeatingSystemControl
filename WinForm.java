import javax.swing.*;
import java.awt.*;

public class WinForm extends JFrame {

    private Chart interiorChart;
    private Chart outdoorChart;
    private TemperaturesArea temperArea;
    private JButton buttonStart;

    public WinForm() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1200,620);
        setResizable(false);
        setVisible(true);

        double[] doubles = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        interiorChart = new Chart("Temperatura pomieszczenia", doubles, Color.BLUE);
        interiorChart.setLocation(350,40);
        add(interiorChart);

        outdoorChart = new Chart("Zewnętrzna temperatura", doubles, 5.0, 26.0,  Color.red);
        outdoorChart.setLocation(350,240);
        add(outdoorChart);

        temperArea = new TemperaturesArea();
        temperArea.setLocation(240,42);
        add(temperArea);

        buttonStart = new JButton("Start Simulation");
        buttonStart.setBounds(350,500,160,30);
        buttonStart.addActionListener(e -> {
            setVisible(false);
            remove(interiorChart);
            interiorChart = new Chart("Temperatura pomieszczenia", temperArea.get(), Color.BLUE);
            interiorChart.setLocation(350,40);
            add(interiorChart);
            setVisible(true);


        });
        add(buttonStart);
    }
}
