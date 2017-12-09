import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TemperaturesArea extends JComponent {

    private JLabel label;
    private JTextArea dataTextArea;
    private List<Double> list = new ArrayList<>();
    private JScrollPane scrollPane;
    private JButton buttonLoad;
    private JButton buttonClear;

    private JFileChooser fileChooser;

    public TemperaturesArea() {
        setSize(90,460);

        label = new JLabel("Temperatures:");
        label.setBounds(0,0,90,15);
        add(label);

        dataTextArea = new JTextArea();
        dataTextArea.setColumns(1);
        dataTextArea.setRows(70);
        scrollPane = new JScrollPane(dataTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(dataTextArea);
        scrollPane.setBounds(0,20,90,370);
        add(scrollPane);

        buttonLoad = new JButton("Load");
        buttonLoad.setBounds(0,400,90,25);
        buttonLoad.addActionListener(e -> loadData());
        add(buttonLoad);

        buttonClear = new JButton("Clear");
        buttonClear.setBounds(0,435,90,25);
        buttonClear.addActionListener(e -> dataTextArea.setText(null));
        add(buttonClear);
    }

    public double[] get(){
        double[] doubles = new double[dataTextArea.getLineCount()];
        String[] strings = dataTextArea.getText().split("\n");
        for(int i=0; i<doubles.length; i++){
            doubles[i] = Double.parseDouble(strings[i]);
            System.out.println(strings[i]);
        }
        return doubles;
    }

    public void set(String[] power){
        dataTextArea.setText(null);
        for(String p : power) dataTextArea.append(p + "\n");
    }

    public void loadData(){
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        else {
                            String fileName = f.getName().toLowerCase();
                            return fileName.endsWith(".txt");
                        }
                    }
                    @Override
                    public String getDescription() {
                        return "text file (*.txt)";
                    }
                }
        );

        int ret = fileChooser.showOpenDialog(null);
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scanner = new Scanner(file);
                dataTextArea.setText(null);
                int pos = 0;
                while(scanner.hasNextLine()){
                    dataTextArea.append(scanner.nextLine());
                    if(scanner.hasNextLine()) dataTextArea.append("\n");
                }
                System.out.println("Wczytano: " + dataTextArea.getLineCount() + " linii.");
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
