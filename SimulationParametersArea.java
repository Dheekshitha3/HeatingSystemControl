import javax.swing.*;
import java.awt.*;

public class SimulationParametersArea extends JComponent {

    /* Declaration labels and fields */
    private JLabel labelHeatPower;
    private JLabel labelInsulation;
    private JLabel labelArea;
    private JLabel labelHeight;
    private JLabel labelOptimal;
    private JLabel labelStartTemperature;

    private JTextField fieldHeatPower;
    private JTextField fieldArea;
    private JTextField fieldHeight;
    private JTextField fieldMinTemperature;
    private JTextField fieldLowOptimal;
    private JTextField fieldHighOptimal;
    private JTextField fieldStartTemperature;

    private JComboBox comboBoxInsulation; // izolacja pomieszczenia.

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

        labelHeight = new JLabel("Wysokość:");
        labelHeight.setBounds(0,45,200,15);
        add(labelHeight);

        fieldHeight = new JTextField("2.5");
        fieldHeight.setBounds(0,65,60,20);
        fieldHeight.setHorizontalAlignment(SwingConstants.RIGHT);
        add(fieldHeight);

        JLabel mh = new JLabel("m");
        mh.setBounds(65,65,50,20);
        add(mh);

        labelInsulation = new JLabel("Izolacja pomieszczenia:");
        labelInsulation.setBounds(0,120,200,15);
        add(labelInsulation);

        String[] insulationItems = {"brak", "średnia", "dobra"};
        comboBoxInsulation = new JComboBox(insulationItems);
        comboBoxInsulation.setSelectedIndex(0);
        comboBoxInsulation.setBounds(0,140,80,20);
        add(comboBoxInsulation);

        JLabel opis2 = new JLabel("<html><br>Optymalna moc pieca dla pomieszczenia</br>" +
                "<br>o powierzchni 100 m2 i dobrej</br><br>izolacji to w przybliżeniu 7,7 kW.</br></html>");
        opis2.setFont(new Font("Dialog", Font.PLAIN, 10));
        opis2.setBounds(0,175,200,60);
        add(opis2);

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

    /* Method get choose value from combobox */
    public double getInsulation(){
        if(comboBoxInsulation.getSelectedIndex()==0) return 5.0;
        else if(comboBoxInsulation.getSelectedIndex()==1) return 10.0;
        else if(comboBoxInsulation.getSelectedIndex()==2) return 15.0;
        return 15.0;
    }

    public double getStartTemperature(){
        return Double.parseDouble(fieldStartTemperature.getText());
    }

    /* Współczynnik ogrzewania pomieszczenia */
    public double getFactor(){
        return (Double.parseDouble(fieldHeatPower.getText())/Double.parseDouble(fieldArea.getText()))*(35/Double.parseDouble(fieldHeight.getText()));
        // moc pieca / powierzchnia * (14.0)
    }

    public double getMinTemp(){
        return Double.parseDouble(fieldMinTemperature.getText());
    }

    /* return minimal optimal temperatures */
    public double getLowOpt(){
        return Double.parseDouble(fieldLowOptimal.getText());
    }

    /* return highest optimal temperatures */
    public double getHighOpt(){
        return Double.parseDouble(fieldHighOptimal.getText());
    }
}
