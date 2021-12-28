package slang;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * slang
 * Created by Minh Sĩ Lê
 * Date 12/27/2021 - 12:03 PM
 * Description: ...
 */
public class QuizFrame extends JPanel implements ActionListener {
    Data dictinary;
    private JLabel labelTitle = new JLabel();
    private JButton btnAns1 = new JButton("a");
    private JButton btnAns2 = new JButton("b");
    private JButton btnAns3 = new JButton("c");
    private JButton btnAns4 = new JButton("d");
    private String question;
    private String answer;
    private static JFrame frame = new JFrame("Quiz");
    private boolean flag;

    private Font myFontTitle = new Font("Tahoma", Font.PLAIN, 28);
    private Font myFontContent = new Font("Serif", Font.BOLD, 18);
    private Font myFontContent_1 = new Font("Serif", Font.ITALIC, 18);
    private Font myFontContent_2 = new Font("Serif", Font.PLAIN, 14);

    public QuizFrame(boolean flag) {
        setDictinary();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (flag)
            setQuestionByWord();
        else setQuestionByDef();
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        labelTitle.setFont(myFontTitle);
        labelTitle.setText(question);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(labelTitle);
        add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel panelAnswer = new JPanel();
        panelAnswer.setLayout(new BoxLayout(panelAnswer, BoxLayout.PAGE_AXIS));
        JPanel panelAnswer1 = new JPanel();
        panelAnswer1.setLayout(new BoxLayout(panelAnswer1, BoxLayout.LINE_AXIS));
        panelAnswer.add(panelAnswer1);
        panelAnswer1.add(Box.createRigidArea(new Dimension(20, 0)));
        panelAnswer1.add(btnAns1);
        btnAns1.setMaximumSize(new Dimension(200, 130));
        panelAnswer1.add(Box.createRigidArea(new Dimension(20, 0)));
        panelAnswer1.add(btnAns2);
        btnAns2.setMaximumSize(new Dimension(200, 130));
        panelAnswer1.add(Box.createRigidArea(new Dimension(20, 0)));
        panelAnswer.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelAnswer2 = new JPanel();
        panelAnswer2.setLayout(new BoxLayout(panelAnswer2, BoxLayout.LINE_AXIS));
        panelAnswer.add(panelAnswer2);
        panelAnswer2.add(Box.createRigidArea(new Dimension(20, 0)));
        panelAnswer2.add(btnAns3);
        btnAns3.setMaximumSize(new Dimension(200, 130));
        panelAnswer2.add(Box.createRigidArea(new Dimension(20, 0)));
        panelAnswer2.add(btnAns4);
        btnAns4.setMaximumSize(new Dimension(200, 130));
        panelAnswer2.add(Box.createRigidArea(new Dimension(20, 0)));
        add(panelAnswer);
        add(Box.createRigidArea(new Dimension(0, 20)));

        //Set action
        btnAns1.addActionListener(this);
        btnAns2.addActionListener(this);
        btnAns3.addActionListener(this);
        btnAns4.addActionListener(this);
    }

    private void setQuestionByWord() {
        question = dictinary.randomWord();
        answer = dictinary.randomDef(question);
        btnAns1.setText(dictinary.randomDef());
        btnAns2.setText(dictinary.randomDef());
        btnAns3.setText(dictinary.randomDef());
        btnAns4.setText(dictinary.randomDef());
        int randIdx = new Random().nextInt(4);
        if (randIdx == 0)
            btnAns1.setText(answer);
        else if (randIdx == 1)
            btnAns2.setText(answer);
        else if (randIdx == 2)
            btnAns3.setText(answer);
        else btnAns4.setText(answer);
    }

    private void setQuestionByDef() {
        answer = dictinary.randomWord();
        question = dictinary.randomDef(answer);
        btnAns1.setText(dictinary.randomWord());
        btnAns2.setText(dictinary.randomWord());
        btnAns3.setText(dictinary.randomWord());
        btnAns4.setText(dictinary.randomWord());
        int randIdx = new Random().nextInt(4);
        if (randIdx == 0)
            btnAns1.setText(answer);
        else if (randIdx == 1)
            btnAns2.setText(answer);
        else if (randIdx == 2)
            btnAns3.setText(answer);
        else btnAns4.setText(answer);
    }

    private void setDictinary() {
        this.dictinary = MainFrame.getDictionary();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JButton btn = (JButton) source;
        if (btn.getText().equals(answer))
            JOptionPane.showMessageDialog(this, "You are correct!");
        else JOptionPane.showMessageDialog(this, "You fail!");
        frame.dispose();
    }
    /**
     * Create and show GUI
     */
    private static void createAndShowGUI(boolean flag) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setBounds(300, 200, 500, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        QuizFrame newContentPane = new QuizFrame(flag);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Main function
//     * @param args
     */
    public static void startQuiz(boolean flag){
        createAndShowGUI(flag);
    }
}
