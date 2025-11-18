import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Config extends JFrame implements ActionListener {

    public static double examMaxScore = 70;
    public static double cutOffGrade = 70;
    public static double prelimWeight = 0.2;
    public static double midtermWeight = 0.2;
    public static double prefinalWeight = 0.2;
    public static double finalWeight = 0.4;

    JTextField maxScoreField, cutoffField;
    JTextField prelimField, midtermField, prefinalField, finalField;
    JButton submitBtn;

    public Config() {

        this.setTitle("Config");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 430);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel BG = new JPanel();
        BG.setLayout(null);
        BG.setBackground(new Color(0, 102, 204));
        this.add(BG);

        JLabel title = new JLabel("CONFIG");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.white);
        title.setBounds(120, 10, 200, 30);
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

        JLabel l2 = new JLabel("Cut-Off Grade:");
        l2.setFont(new Font("Arial", Font.PLAIN, 16));
        l2.setBounds(52, 103, 200, 25);
        l2.setForeground(Color.white);
        BG.add(l2);

        cutoffField = new JTextField(String.valueOf(cutOffGrade));
        cutoffField.setBounds(180, 100, 130, 30);
        BG.add(cutoffField);

        JLabel l3 = new JLabel("Prelim Weight:");
        l3.setFont(new Font("Arial", Font.PLAIN, 16));
        l3.setBounds(52, 153, 200, 25);
        l3.setForeground(Color.white);
        BG.add(l3);

        prelimField = new JTextField(String.valueOf(prelimWeight));
        prelimField.setBounds(180, 150, 130, 30);
        BG.add(prelimField);

        JLabel l4 = new JLabel("Midterm Weight:");
        l4.setFont(new Font("Arial", Font.PLAIN, 16));
        l4.setBounds(40, 192, 200, 25);
        l4.setForeground(Color.white);
        BG.add(l4);

        midtermField = new JTextField(String.valueOf(midtermWeight));
        midtermField.setBounds(180, 190, 130, 30);
        BG.add(midtermField);

        JLabel l5 = new JLabel("Prefinal Weight:");
        l5.setFont(new Font("Arial", Font.PLAIN, 16));
        l5.setBounds(45, 232, 200, 25);
        l5.setForeground(Color.white);
        BG.add(l5);

        prefinalField = new JTextField(String.valueOf(prefinalWeight));
        prefinalField.setBounds(180, 230, 130, 30);
        BG.add(prefinalField);

        JLabel l6 = new JLabel("Final Weight:");
        l6.setFont(new Font("Arial", Font.PLAIN, 16));
        l6.setBounds(65, 272, 200, 25);
        l6.setForeground(Color.white);
        BG.add(l6);

        finalField = new JTextField(String.valueOf(finalWeight));
        finalField.setBounds(180, 270, 130, 30);
        BG.add(finalField);

        submitBtn = new JButton("Confirm");
        submitBtn.setBounds(95, 330, 150, 40);
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
        cutOffGrade = Double.parseDouble(cutoffField.getText());

        prelimWeight = Double.parseDouble(prelimField.getText());
        midtermWeight = Double.parseDouble(midtermField.getText());
        prefinalWeight = Double.parseDouble(prefinalField.getText());
        finalWeight = Double.parseDouble(finalField.getText());

        JOptionPane.showMessageDialog(this, "Configuration Saved!");

        dispose();

    }
}