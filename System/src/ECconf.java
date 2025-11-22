import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ECconf extends JFrame implements ActionListener {

    public static double examMaxScore = 70;
    public static double examPercentage = 50;

    JTextField maxScoreField, examPercentageField;
    JButton submitBtn;

    public ECconf() {

        this.setTitle("Config");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel BG = new JPanel();
        BG.setLayout(null);
        BG.setBackground(new Color(0, 102, 204));
        this.add(BG);

        JLabel title = new JLabel("EXAM CONFIG");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.white);
        title.setBounds(90, 10, 200, 30);
        BG.add(title);

        JPanel line = new JPanel();
        line.setBounds(0, 50, 350, 2);
        line.setBackground(Color.white);
        BG.add(line);

        JLabel l1 = new JLabel("Max Exam Score:");
        l1.setFont(new Font("Arial", Font.PLAIN, 16));
        l1.setBounds(30, 63, 200, 25);
        l1.setForeground(Color.white);
        BG.add(l1);

        maxScoreField = new JTextField(String.valueOf(examMaxScore));
        maxScoreField.setBounds(180, 60, 130, 30);
        BG.add(maxScoreField);

        JLabel l2 = new JLabel("Exam Percentage:");
        l2.setFont(new Font("Arial", Font.PLAIN, 16));
        l2.setBounds(25, 103, 200, 25);
        l2.setForeground(Color.white);
        BG.add(l2);

        examPercentageField = new JTextField(String.valueOf(examPercentage));
        examPercentageField.setBounds(180, 100, 130, 30);
        BG.add(examPercentageField);

        submitBtn = new JButton("Confirm");
        submitBtn.setBounds(95, 150, 150, 40);
        submitBtn.setBackground(Color.darkGray);
        submitBtn.setForeground(Color.white);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.addActionListener(this);
        BG.add(submitBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        examMaxScore = Double.parseDouble(maxScoreField.getText());
        examPercentage = Double.parseDouble(examPercentageField.getText());

        JOptionPane.showMessageDialog(this, "Configuration Saved!");

        dispose();

    }
}