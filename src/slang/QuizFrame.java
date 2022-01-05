package slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * slang
 * Created by Minh Sĩ Lê
 * Date 12/27/2021 - 12:03 PM
 * Description: Quiz Frame
 */
public class QuizFrame extends JPanel implements ActionListener {
    Data dictionary;
    private final JButton btnAns1 = new JButton("a");
    private final JButton btnAns2 = new JButton("b");
    private final JButton btnAns3 = new JButton("c");
    private final JButton btnAns4 = new JButton("d");
    private String question;
    private String answer;
    private static final JFrame frame = new JFrame("Quiz");

    /**
     * Create Frame
     * @param flag Quiz word or definition
     */
    public QuizFrame(boolean flag) {
        setDictinary();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        if (flag)
            setQuestionByWord();
        else setQuestionByDef();
        JLabel labelTitle = new JLabel();
        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        Font myFontTitle = new Font("Tahoma", Font.PLAIN, 28);
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

    /**
     * Add question (Word) to quiz
     */
    private void setQuestionByWord() {
        question = dictionary.randomWord();
        answer = dictionary.randomDef(question);
        btnAns1.setText(dictionary.randomDef());
        btnAns2.setText(dictionary.randomDef());
        btnAns3.setText(dictionary.randomDef());
        btnAns4.setText(dictionary.randomDef());
        int randIdx = new Random().nextInt(4);
        if (randIdx == 0)
            btnAns1.setText(answer);
        else if (randIdx == 1)
            btnAns2.setText(answer);
        else if (randIdx == 2)
            btnAns3.setText(answer);
        else btnAns4.setText(answer);
    }

    /**
     * Add question (Definition) to quiz
     */
    private void setQuestionByDef() {
        answer = dictionary.randomWord();
        question = dictionary.randomDef(answer);
        btnAns1.setText(dictionary.randomWord());
        btnAns2.setText(dictionary.randomWord());
        btnAns3.setText(dictionary.randomWord());
        btnAns4.setText(dictionary.randomWord());
        int randIdx = new Random().nextInt(4);
        if (randIdx == 0)
            btnAns1.setText(answer);
        else if (randIdx == 1)
            btnAns2.setText(answer);
        else if (randIdx == 2)
            btnAns3.setText(answer);
        else btnAns4.setText(answer);
    }

    /**
     * Set data of dictionary
     */
    private void setDictinary() {
        this.dictionary = MainFrame.getDictionary();
    }

    /**
     * Action
     * @param e event
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JButton btn = (JButton) source;
        if (btn.getText().equals(answer))
            JOptionPane.showMessageDialog(this, "You are correct!");
        else JOptionPane.showMessageDialog(this, "You fail! The answer is: " + answer );
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
