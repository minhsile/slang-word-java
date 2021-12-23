package slang;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ui
 * Created by Minh Sĩ Lê
 * Date 12/13/2021 - 8:53 PM
 * Description: ...
 */
public class MainFrame extends JPanel implements ActionListener {
    Data dictionary = new Data();
    JLabel labelTitlte = new JLabel("Dictionary");
    JTextField txtSearch = new JTextField();
    JButton btnSearchByWord = new JButton("Search by word");
    JButton btnSearchByDef = new JButton("Search by definition");
    JButton btnAdd = new JButton("Add");
    JButton btnDel = new JButton("Delete");
    JButton btnRefresh = new JButton();
    JButton btnViewHistory = new JButton("History");
    JTextArea txtRandomWord = new JTextArea();
    JButton btnReset = new JButton("Reset original sang words");
    JButton btnQuizWord = new JButton("Quiz slang word");
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
        txtSearch.setColumns(28);
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
        JPanel quiz = new JPanel();
        quiz.setLayout(new BoxLayout(quiz, BoxLayout.LINE_AXIS));
        txtRandomWord.setFont(myFontContent_1);
        txtRandomWord.setColumns(32);
        txtRandomWord.setRows(5);
        txtRandomWord.setEditable(false);
        txtRandomWord.setBorder(new TitledBorder(null, "Word for today", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtRandomWord.setText(dictionary.wordForToday());
        quiz.add(txtRandomWord);
        ImageIcon iconRefresh = new ImageIcon(MainFrame.class.getResource("/icon/refresh.png"));
        int scale = 45;
        int width = iconRefresh.getIconWidth();
        int newWidth = width / scale;
        btnRefresh.setIcon(new ImageIcon(iconRefresh.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
        btnRefresh.setToolTipText("Refresh");
        btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.addActionListener(this);
        quiz.add(Box.createRigidArea(new Dimension(10,0)));
        quiz.add(btnRefresh);
        add(quiz);
        add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtnFunction = new JPanel();
        panelBtnFunction.setLayout(new BoxLayout(panelBtnFunction, BoxLayout.Y_AXIS));
        panelBtnFunction.setAlignmentX(CENTER_ALIGNMENT);
        add(panelBtnFunction);

        JPanel panelBtn1 = new JPanel();
        panelBtn1.setLayout(new BoxLayout(panelBtn1, BoxLayout.LINE_AXIS));
        panelBtn1.add(btnAdd);
        btnAdd.addActionListener(this);
        panelBtn1.add(Box.createRigidArea(new Dimension(10,0)));

        panelBtn1.add(btnDel);
        btnDel.addActionListener(this);
        panelBtn1.add(Box.createRigidArea(new Dimension(10,0)));

        panelBtn1.add(btnReset);
        btnReset.addActionListener(this);
        panelBtn1.add(Box.createRigidArea(new Dimension(10,0)));

        panelBtn1.add(btnViewHistory);
        btnViewHistory.addActionListener(this);
        panelBtnFunction.add(panelBtn1);
        panelBtnFunction.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtn2 = new JPanel();
        panelBtn2.setLayout(new BoxLayout(panelBtn2, BoxLayout.LINE_AXIS));
        panelBtn2.add(btnQuizWord);
        panelBtn2.add(Box.createRigidArea(new Dimension(10,0)));
        panelBtn2.add(btnQuizDef);
        panelBtnFunction.add(panelBtn2);

        add(Box.createRigidArea(new Dimension(0,15)));
    }
    /**
     * Create and show GUI
     */
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Dictionary");
        frame.setBounds(300, 200, 500, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Data.writeData();
                System.exit(0);
            }
        });
        MainFrame newContentPane = new MainFrame();
        frame.setContentPane(newContentPane);
        frame.setResizable(true);
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
            frameDisplay.setBounds(400,300,320,200);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (!txtSearch.getText().equals(""))
            {
                dictionary.addHistory(txtSearch.getText());
                ArrayList<String> result = dictionary.search(txtSearch.getText(), true);
                if (result != null) {
                    frameDisplay.setContentPane(panelSearch(result, "Definition"));
                    frameDisplay.setResizable(false);
                    frameDisplay.setVisible(true);
                } else JOptionPane.showMessageDialog(this, "This word does not exist!");
            }
            else JOptionPane.showMessageDialog(this, "Empty!");
        }
        else if (e.getSource() == btnRefresh){
            txtRandomWord.setText(dictionary.wordForToday());
        }
        else if (e.getSource() == btnSearchByDef) {
            JFrame frameDisplay = new JFrame("Result");
            frameDisplay.setBounds(400,300,320,200);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (!txtSearch.getText().equals(""))
            {
                dictionary.addHistory(txtSearch.getText());
                ArrayList<String> result = dictionary.search(txtSearch.getText(), false);
                if (result != null) {
                    frameDisplay.setContentPane(panelSearch(result, "Word"));
                    frameDisplay.setResizable(false);
                    frameDisplay.setVisible(true);
                } else JOptionPane.showMessageDialog(this, "This word does not exist!");
            }
            else JOptionPane.showMessageDialog(this, "Empty!");
        } else if (e.getSource() == btnAdd) {
            JFrame frameDisplay = new JFrame("Add a new slang word");
            frameDisplay.setBounds(400,300,370,120);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panelAdd = new JPanel();
            panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.PAGE_AXIS));
            JPanel panelInput = new JPanel();
            panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.LINE_AXIS));
            panelAdd.add(Box.createRigidArea(new Dimension(0,10)));
            panelAdd.add(panelInput);

            JLabel labelWord = new JLabel("Word ");
            labelWord.setFont(myFontContent_1);
            panelInput.add(labelWord);

            JTextField txtWord = new JTextField();
            txtWord.setFont(myFontContent_1);
            panelInput.add(txtWord);
            panelInput.add(Box.createRigidArea(new Dimension(10,0)));

            JLabel labelDef = new JLabel("Definition ");
            labelDef.setFont(myFontContent_1);
            panelInput.add(labelDef);

            JTextField txtDef = new JTextField();
            txtDef.setFont(myFontContent_1);
            panelInput.add(txtDef);
            panelAdd.add(Box.createRigidArea(new Dimension(0,10)));

            JButton btnAdd = new JButton("Add");
            btnAdd.setAlignmentX(CENTER_ALIGNMENT);
            panelAdd.add(btnAdd);
            panelAdd.add(Box.createRigidArea(new Dimension(0,10)));
            btnAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!dictionary.checkWordExits(txtWord.getText()))
                        dictionary.addNewSlangWord(txtWord.getText(), txtDef.getText());
                    else {
                        Object[] choices = {"Overwrite", "Duplicate"};
                        int reply = JOptionPane.showOptionDialog(null,
                                "This word already exist. Do you want overwrite or duplicate",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                choices, null);
                        if (reply == JOptionPane.YES_OPTION) {
                            dictionary.removeKey(txtWord.getText());
                            dictionary.addNewSlangWord(txtWord.getText(), txtDef.getText());
                        } else {
                            dictionary.addNewDef(txtWord.getText(),txtDef.getText());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Add successfully.");
                    frameDisplay.dispose();
                }
            });

            frameDisplay.setContentPane(panelAdd);
            frameDisplay.setResizable(false);
            frameDisplay.setVisible(true);


//            else JOptionPane.showMessageDialog(this, "Empty!");
        }  else if (e.getSource() == btnViewHistory){
            JFrame frameDisplay = new JFrame("History");
            frameDisplay.setBounds(400,300,320,200);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ArrayList<String> result = dictionary.getHistoryList();
            if (result != null) {
                frameDisplay.setContentPane(panelHistory(result));
                frameDisplay.setResizable(false);
                frameDisplay.setVisible(true);
            } else JOptionPane.showMessageDialog(this, "This word does not exist!");
        } else if (e.getSource() == btnReset){
            dictionary.readData(true);
            JOptionPane.showMessageDialog(this, "Reset successfully!");
        }
    }

    private JPanel panelSearch(ArrayList<String> search, String title){
        JList<String> viewList;
        DefaultListModel<String> model = new DefaultListModel<>();
        viewList = new JList<>(model);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel labelWord = new JLabel();
        labelWord.setFont(myFontContent_1);
        labelWord.setAlignmentX(CENTER_ALIGNMENT);
        labelWord.setText(txtSearch.getText());
        panel.add(labelWord);
        viewList.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        viewList.setPreferredSize(new Dimension(200, 200));
        for (String s: search) {
            model.addElement(s);
        }
        panel.add(viewList);
        JScrollPane scrollBar = new JScrollPane(viewList);
        scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add(scrollBar);

        return panel;
    }
//
//    private JPanel panelSearch(ArrayList<String> search, String title){
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        JLabel labelWord = new JLabel();
//        labelWord.setFont(myFontContent_1);
//        labelWord.setAlignmentX(CENTER_ALIGNMENT);
//        labelWord.setText(txtSearch.getText());
//        panel.add(labelWord);
//
//        JTextArea txtDef = new JTextArea();
//        JScrollPane scrollBar = new JScrollPane(txtDef);
//        scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        txtDef.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
//        txtDef.setEditable(false);
//        txtDef.setColumns(20);
//        txtDef.setRows(5);
//        txtDef.setText(dictinary.toString(search));
//        panel.add(scrollBar);
//
//        return panel;
//    }
    private JPanel panelHistory(ArrayList<String> history){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JTextArea txtDef = new JTextArea();
        JScrollPane scrollBar = new JScrollPane(txtDef);
        scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        txtDef.setBorder(new TitledBorder(null, "History", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtDef.setEditable(false);
        txtDef.setColumns(20);
        txtDef.setRows(5);
        txtDef.setText(dictionary.toString(history));
        panel.add(scrollBar);

        return panel;
    }
}



