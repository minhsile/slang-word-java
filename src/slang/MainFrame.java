package slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * ui
 * Created by Minh Sĩ Lê
 * Date 12/13/2021 - 8:53 PM
 * Description: ...
 */
public class MainFrame extends JPanel implements ActionListener {
    public MainFrame(){
        JTabbedPane tabbedPane = new JTabbedPane();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel panelConf = new JPanel();
        JPanel panelManage = new JPanel();
        tabbedPane.add("Dictionary",panelConf);
        tabbedPane.add("Management",panelManage);

        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
        panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
        panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE };

        //Add to main frame
        add(tabbedPane);

    }
    /**
     * Create and show GUI
     */
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Dictionary");
        frame.setBounds(300, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainFrame newContentPane = new MainFrame();
        frame.setContentPane(newContentPane);

        frame.setVisible(true);
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[]args){
        createAndShowGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



