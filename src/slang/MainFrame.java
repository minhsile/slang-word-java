package slang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Random;
import java.util.Set;

/**
 * ui
 * Created by Minh Sĩ Lê
 * Date 12/13/2021 - 8:53 PM
 * Description: ...
 */
public class MainFrame extends JPanel implements ActionListener {
    Data dictinary = new Data();
    JLabel labelTitlte = new JLabel("Dictionary");
    JTextField txtSearch = new JTextField();
    JButton btnSearchByWord = new JButton("Search by word");
    JButton btnSearchByDef = new JButton("Search by definition");
    JButton btnAdd = new JButton();
    JTextArea txtRandomWord = new JTextArea();
    JButton btnReset = new JButton("Reset original sang words");
    JButton btnQuizWord = new JButton("\tQuiz slang word\t");
    JButton btnQuizDef = new JButton("Quiz definition");

    Font myFontTitle = new Font("Tahoma", Font.PLAIN, 28);
    Font myFontContent = new Font("Serif", Font.BOLD, 18);
    Font myFontContent_1 = new Font("Serif", Font.ITALIC, 18);
    Font myFontContent_2 = new Font("Serif", Font.PLAIN, 14);

    public MainFrame(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        labelTitlte.setAlignmentX(CENTER_ALIGNMENT);
        labelTitlte.setFont(myFontTitle);
        add(labelTitlte);
        add(Box.createRigidArea(new Dimension(0,10)));
        txtSearch.setFont(myFontContent);
        txtSearch.setColumns(30);
        txtSearch.setMaximumSize(txtSearch.getPreferredSize());
        txtSearch.setAlignmentX(CENTER_ALIGNMENT);
        txtSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(txtSearch);
        add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtnSearch = new JPanel();
        panelBtnSearch.setLayout(new BoxLayout(panelBtnSearch, BoxLayout.LINE_AXIS));
        panelBtnSearch.add(btnSearchByWord);
        panelBtnSearch.add(Box.createRigidArea(new Dimension(10,0)));
        panelBtnSearch.add(btnSearchByDef);
        btnSearchByWord.addActionListener(this);
        btnSearchByDef.addActionListener(this);
        add(panelBtnSearch);

        add(Box.createRigidArea(new Dimension(0,10)));
        txtRandomWord.setFont(myFontContent_1);
        txtRandomWord.setColumns(30);
        txtRandomWord.setRows(5);
        txtRandomWord.setEditable(false);
        txtRandomWord.setMaximumSize(txtRandomWord.getPreferredSize());
        txtRandomWord.setBorder(new TitledBorder(null, "Word for today", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtRandomWord.setText(dictinary.wordForToday());
        add(txtRandomWord);
        add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtnFunction = new JPanel();
        panelBtnFunction.setLayout(new BoxLayout(panelBtnFunction, BoxLayout.Y_AXIS));
        panelBtnFunction.setAlignmentX(CENTER_ALIGNMENT);
        btnReset.setAlignmentX(CENTER_ALIGNMENT);
        panelBtnFunction.add(btnReset);
        panelBtnFunction.add(Box.createRigidArea(new Dimension(0,10)));
        btnQuizWord.setAlignmentX(CENTER_ALIGNMENT);
        panelBtnFunction.add(btnQuizWord);
        panelBtnFunction.add(Box.createRigidArea(new Dimension(0,10)));
        btnQuizDef.setAlignmentX(CENTER_ALIGNMENT);
        panelBtnFunction.add(btnQuizDef);
        add(panelBtnFunction);

    }
    /**
     * Create and show GUI
     */
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Dictionary");
        frame.setBounds(300, 200, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainFrame newContentPane = new MainFrame();
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
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
        if (e.getSource() == btnSearchByWord) {
            JFrame frameDisplay = new JFrame("Result");
            frameDisplay.setBounds(400,300,300,150);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameDisplay.setContentPane(panelSearch(dictinary.search(txtSearch.getText())));
            frameDisplay.setResizable(false);
            frameDisplay.setVisible(true);
        }
    }

    private JPanel panelSearch(ArrayList<String> search){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel labelWord = new JLabel();
        labelWord.setFont(myFontContent_1);
        labelWord.setAlignmentX(CENTER_ALIGNMENT);
        labelWord.setText(txtSearch.getText());
        panel.add(labelWord);

        JTextArea txtDef = new JTextArea();
        txtDef.setBorder(new TitledBorder(null, "Definition", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtDef.setEditable(false);
        txtDef.setColumns(30);
        txtDef.setRows(5);
        txtDef.setText(dictinary.toString(search));
        panel.add(txtDef);

        return panel;
    }




}



