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
    private static Data dictionary = new Data();
    private JLabel labelTitle = new JLabel("Dictionary");
    private JTextField txtSearch = new JTextField();
    private JButton btnSearchByWord = new JButton("Search by word");
    private JButton btnSearchByDef = new JButton("Search by definition");
    private JButton btnAdd = new JButton("Add");
    private JButton btnDel = new JButton("Delete");
    private JButton btnRefresh = new JButton();
    private JButton btnViewHistory = new JButton("History");
    private JTextArea txtRandomWord = new JTextArea();
    private JButton btnReset = new JButton("Reset original sang words");
    private JButton btnQuizWord = new JButton("Quiz slang word");
    private JButton btnQuizDef = new JButton("Quiz definition");

    private Font myFontTitle = new Font("Tahoma", Font.PLAIN, 28);
    private Font myFontContent = new Font("Serif", Font.BOLD, 18);
    private Font myFontContent_1 = new Font("Serif", Font.ITALIC, 18);
    private Font myFontContent_2 = new Font("Serif", Font.PLAIN, 14);

    public MainFrame(){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        labelTitle.setFont(myFontTitle);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(labelTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        txtSearch.setFont(myFontContent);
        txtSearch.setColumns(28);
        txtSearch.setAlignmentX(CENTER_ALIGNMENT);
        txtSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        mainPanel.add(txtSearch);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtnSearch = new JPanel();
        panelBtnSearch.setLayout(new BoxLayout(panelBtnSearch, BoxLayout.LINE_AXIS));
        panelBtnSearch.add(btnSearchByWord);
        panelBtnSearch.add(Box.createRigidArea(new Dimension(10,0)));
        panelBtnSearch.add(btnSearchByDef);
        btnSearchByWord.addActionListener(this);
        btnSearchByDef.addActionListener(this);
        mainPanel.add(panelBtnSearch);

        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
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
        mainPanel.add(quiz);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelBtnFunction = new JPanel();
        panelBtnFunction.setLayout(new BoxLayout(panelBtnFunction, BoxLayout.Y_AXIS));
        panelBtnFunction.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(panelBtnFunction);

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
        btnQuizWord.addActionListener(this);
        panelBtn2.add(Box.createRigidArea(new Dimension(10,0)));
        panelBtn2.add(btnQuizDef);
        btnQuizDef.addActionListener(this);
        panelBtnFunction.add(panelBtn2);

        mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
        add(Box.createRigidArea(new Dimension(20,0)));
        add(mainPanel);
        add(Box.createRigidArea(new Dimension(20,0)));
    }
    /**
     * Create and show GUI
     */
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Dictionary");
        frame.setBounds(300, 200, 500, 400);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Data.writeData();
                int dialogResult = JOptionPane.showConfirmDialog (frame, "Do you want to exit?","Message",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
            frameDisplay.setBounds(400,300,400,200);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (!txtSearch.getText().equals(""))
            {
                dictionary.addHistory(txtSearch.getText());
                ArrayList<String> result = dictionary.search(txtSearch.getText(), true);
                if (result != null) {
                    frameDisplay.setContentPane(panelSearchWord(result, "Definition"));
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
                    frameDisplay.setContentPane(panelSearchDef(result, "Word"));
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
        } else if (e.getSource() == btnDel){
            JFrame frameDisplay = new JFrame("Delete a slang word");
            frameDisplay.setBounds(400,300,350,125);
            frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panelAddDef = new JPanel();
            panelAddDef.setLayout(new BoxLayout(panelAddDef, BoxLayout.PAGE_AXIS));
            panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));

            JPanel panelContent = new JPanel();
            panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.LINE_AXIS));
            panelContent.add(Box.createRigidArea(new Dimension(10,0)));
            JLabel label = new JLabel("Word need to delete: ");
            label.setFont(myFontContent_1);
            JTextField txtWord = new JTextField();
            txtWord.setFont(myFontContent_1);
            panelContent.add(label);
            panelContent.add(txtWord);
            panelContent.add(Box.createRigidArea(new Dimension(10,0)));
            panelAddDef.add(panelContent);

            JButton btnAdd = new JButton("Delete");
            btnAdd.setAlignmentX(CENTER_ALIGNMENT);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (dictionary.removeKey(txtWord.getText()))
                        JOptionPane.showMessageDialog(null, "Add successfully!");
                    else JOptionPane.showMessageDialog(null, "Word doesn't exist!");
                    frameDisplay.dispose();
                }
            });
            panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));
            panelAddDef.add(btnAdd);
            panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));

            frameDisplay.setContentPane(panelAddDef);
            frameDisplay.setResizable(false);
            frameDisplay.setVisible(true);
        } else if (e.getSource() == btnQuizWord){
            QuizFrame.startQuiz(true);
        } else if (e.getSource() == btnQuizDef){
            QuizFrame.startQuiz(false);
        }
    }

    private JPanel panelSearchWord(ArrayList<String> search, String title){
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

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        panel.add(middlePanel);
        viewList.setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
        for (String s: search) {
            model.addElement(s);
        }
        JScrollPane scrollBar = new JScrollPane(viewList);
        scrollBar.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        middlePanel.add(scrollBar);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
        btnPanel.setBorder(new TitledBorder(null, "Function", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        middlePanel.add(btnPanel);
        JButton btnAdd = new JButton("Add new definition");
        btnAdd.setAlignmentX(CENTER_ALIGNMENT);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameDisplay = new JFrame("New definition");
                frameDisplay.setBounds(400,300,350,125);
                frameDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel panelAddDef = new JPanel();
                panelAddDef.setLayout(new BoxLayout(panelAddDef, BoxLayout.PAGE_AXIS));
                panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));

                JPanel panelContent = new JPanel();
                panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.LINE_AXIS));
                panelContent.add(Box.createRigidArea(new Dimension(10,0)));
                JLabel label = new JLabel("Add new definition: ");
                label.setFont(myFontContent_1);
                JTextField txtDef = new JTextField();
                txtDef.setFont(myFontContent_1);
                panelContent.add(label);
                panelContent.add(txtDef);
                panelContent.add(Box.createRigidArea(new Dimension(10,0)));
                panelAddDef.add(panelContent);

                JButton btnAdd = new JButton("Add");
                btnAdd.setAlignmentX(CENTER_ALIGNMENT);
                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dictionary.addNewDef(txtSearch.getText(), txtDef.getText());
                        JOptionPane.showMessageDialog(null, "Add successfully.");
                        frameDisplay.dispose();
                        model.addElement(txtDef.getText());
                    }
                });
                panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));
                panelAddDef.add(btnAdd);
                panelAddDef.add(Box.createRigidArea(new Dimension(0,10)));

                frameDisplay.setContentPane(panelAddDef);
                frameDisplay.setResizable(false);
                frameDisplay.setVisible(true);
            }
        });
        btnPanel.add(Box.createRigidArea(new Dimension(0,20)));
        btnPanel.add(btnAdd);

        JButton btnDel = new JButton("Delete definition");
        btnDel.setAlignmentX(CENTER_ALIGNMENT);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String defCurr = (String) viewList.getSelectedValue();
                if (defCurr != null) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want delete this definition?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        dictionary.deleteDef(txtSearch.getText(), defCurr);
                        JOptionPane.showMessageDialog(null, "Delete successfully.");
                        model.clear();
                        for (String s : search) {
                            if (!s.equals(defCurr))
                                model.addElement(s);
                        }
                    }
                } else JOptionPane.showConfirmDialog(null, "You don't choose any definitions. Please choose again!", "Notification", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

            }
        });
        btnPanel.add(Box.createRigidArea(new Dimension(0,25)));
        btnPanel.add(btnDel);
        btnPanel.add(Box.createRigidArea(new Dimension(0,25)));

        return panel;
    }

    private JPanel panelSearchDef(ArrayList<String> search, String title){
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

    public static Data getDictionary() {
        return dictionary;
    }
}



