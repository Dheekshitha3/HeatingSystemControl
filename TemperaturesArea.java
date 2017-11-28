import javax.swing.*;

public class TemperaturesArea extends JComponent {

    private JTextArea dataTextArea;
    private JScrollPane scrollPane;

    public TemperaturesArea() {
        setSize(80,400);

        dataTextArea = new JTextArea();
        dataTextArea.setColumns(4);
        dataTextArea.setRows(70);
        scrollPane = new JScrollPane(dataTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(dataTextArea);
        scrollPane.setBounds(00,0,80,370);
        add(scrollPane);
    }

    // add get, set

}
