import javax.swing.*;

public class SimulationParametersArea extends JComponent {

    private JLabel labelHeatPower;
    private JLabel labelInsulation;
    private JLabel labelArea;
    private JLabel labelOptimal;
    private JLabel labelStartTemperature;

    private JTextField fieldHeatPower;
    private JTextField fieldArea;
    private JTextField fieldMinTemperature;
    private JTextField fieldLowOptimal;
    private JTextField fieldHighOptimal;
    private JTextField fieldStartTemperature;

    private JComboBox comboBoxInsulation;

    public SimulationParametersArea(){
        setSize(200,600);

        labelArea = new JLabel("Powierzchnia:");
        labelArea.setBounds(0,0,200,15);
        add(labelArea);

        fieldArea = new JTextField("100.0");
        fieldArea.setBounds(0,20,60,20);
        fieldArea.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldArea);

        JLabel mkw = new JLabel("m kw.");
        mkw.setBounds(65,20,50,20);
        add(mkw);

        labelInsulation = new JLabel("Izolacja pomieszczenia:");
        labelInsulation.setBounds(0,120,200,15);
        add(labelInsulation);

        String[] insulationItems = {"brak", "średnia", "dobra"};
        comboBoxInsulation = new JComboBox(insulationItems);
        comboBoxInsulation.setSelectedIndex(0);
        comboBoxInsulation.setBounds(0,140,80,20);
        add(comboBoxInsulation);

        labelHeatPower = new JLabel("Moc pieca:");
        labelHeatPower.setBounds(0,240,200,15);
        add(labelHeatPower);

        fieldHeatPower = new JTextField("7.5");
        fieldHeatPower.setBounds(0,260,60,20);
        fieldHeatPower.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldHeatPower);

        labelStartTemperature = new JLabel("Temperatura początkowa:");
        labelStartTemperature.setBounds(0,290,200,15);
        add(labelStartTemperature);

        fieldStartTemperature = new JTextField("10.0");
        fieldStartTemperature.setBounds(0,310,60,20);
        fieldStartTemperature.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldStartTemperature);

        JLabel kW = new JLabel("kW");
        kW.setBounds(65,260,30,20);
        add(kW);

        labelOptimal = new JLabel("Zakres optymalnej temperatury");
        labelOptimal.setBounds(0,340,200,15);
        add(labelOptimal);

        JLabel od = new JLabel("od");
        od.setBounds(0,360,20,20);
        add(od);

        fieldLowOptimal = new JTextField("18.0");
        fieldLowOptimal.setBounds(20,360,50,20);
        fieldLowOptimal.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldLowOptimal);

        JLabel too = new JLabel("do");
        too.setBounds(75,360,20,20);
        add(too);

        fieldHighOptimal = new JTextField("20.0");
        fieldHighOptimal.setBounds(95,360,50,20);
        fieldHighOptimal.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldHighOptimal);

        JLabel min = new JLabel("Minimalna: ");
        min.setBounds(0,385,85,20);
        add(min);

        fieldMinTemperature = new JTextField("10.0");
        fieldMinTemperature.setBounds(95,385,50,20);
        fieldMinTemperature.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldMinTemperature);

    }

    public double getInsulation(){
        if(comboBoxInsulation.getSelectedIndex()==0) return 5.0;
        else if(comboBoxInsulation.getSelectedIndex()==1) return 10.0;
        else if(comboBoxInsulation.getSelectedIndex()==2) return 15.0;
        return 15.0;
    }

    public double getStartTemperature(){
        return Double.parseDouble(fieldStartTemperature.getText());
    }

    public double getFactor(){
        return (Double.parseDouble(fieldHeatPower.getText())/Double.parseDouble(fieldArea.getText()))*14.0;
    }

    public double getMinTemp(){
        return Double.parseDouble(fieldMinTemperature.getText());
    }

    public double getLowOpt(){
        return Double.parseDouble(fieldLowOptimal.getText());
    }

    public double getHighOpt(){
        return Double.parseDouble(fieldHighOptimal.getText());
    }
}
