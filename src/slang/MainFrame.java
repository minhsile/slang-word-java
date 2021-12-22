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
    Data dictinary = new Data();
    JLabel labelTitlte = new JLabel("Dictionary");
    JTextField txtSearch = new JTextField();
    JButton btnSearchByWord = new JButton("Search by word");
    JButton btnSearchByDef = new JButton("Search by definition");
    JButton btnAdd = new JButton();
    JButton btnRefresh = new JButton();
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
        JPanel quiz = new JPanel();
        quiz.setLayout(new BoxLayout(quiz, BoxLayout.LINE_AXIS));
        txtRandomWord.setFont(myFontContent_1);
        txtRandomWord.setColumns(32);
        txtRandomWord.setRows(5);
        txtRandomWord.setEditable(false);
        txtRandomWord.setMaximumSize(txtRandomWord.getPreferredSize());
        txtRandomWord.setBorder(new TitledBorder(null, "Word for today", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtRandomWord.setText(dictinary.wordForToday());
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
            frameDisplay.setBounds(400,300,300,150);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (!txtSearch.getText().equals(""))
            {
                frameDisplay.setContentPane(panelSearch(dictinary.search(txtSearch.getText(), true), "Definition"));
                frameDisplay.setResizable(false);
                frameDisplay.setVisible(true);
            }
            else JOptionPane.showMessageDialog(this,
                    "Empty!");
        }
        else if (e.getSource() == btnRefresh){
            txtRandomWord.setText(dictinary.wordForToday());
        }
        else if (e.getSource() == btnSearchByDef) {
            JFrame frameDisplay = new JFrame("Result");
            frameDisplay.setBounds(400,300,320,200);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (!txtSearch.getText().equals(""))
            {
                frameDisplay.setContentPane(panelSearch(dictinary.search(txtSearch.getText(), false), "Word"));
                frameDisplay.setResizable(false);
                frameDisplay.setVisible(true);
            }
            else JOptionPane.showMessageDialog(this,
                    "Empty!");
        }
    }

    private JPanel panelSearch(ArrayList<String> search, String tilte){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel labelWord = new JLabel();
        labelWord.setFont(myFontContent_1);
        labelWord.setAlignmentX(CENTER_ALIGNMENT);
        labelWord.setText(txtSearch.getText());
        panel.add(labelWord);

        JTextArea txtDef = new JTextArea();
        JScrollPane scrollBar = new JScrollPane(txtDef);
        scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        txtDef.setBorder(new TitledBorder(null, tilte, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        txtDef.setEditable(false);
        txtDef.setColumns(20);
        txtDef.setRows(5);
        txtDef.setText(dictinary.toString(search));
        panel.add(scrollBar);

        return panel;
    }




}



