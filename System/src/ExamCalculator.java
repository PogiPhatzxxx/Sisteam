import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ExamCalculator extends JFrame implements ActionListener, MouseListener {
    JTextField textfield, subject, maxScore;
    JButton submitBtn, toHome, toGWA, toGCAL, toCONFIG;
    JLabel result;
    JPanel historypanel;

    ArrayList<String> historyList = new ArrayList<>();
    ArrayList<String> subjects = new ArrayList<>();

    int resulthistoryY = 70, subjecthistoryY = 50;

    ExamCalculator() {
        // Settings
        this.setTitle("EXAM Calculator");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(950, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon topimg = new ImageIcon("img3.png");
        JLabel topimglabel = new JLabel(topimg);
        topimglabel.setBounds(11, -6, 70, 70);

        JPanel toppanel = new JPanel();
        toppanel.setSize(950, 50);
        toppanel.setBackground(Color.yellow);
        toppanel.setLayout(null);
        toppanel.add(topimglabel);
        this.add(toppanel);

        ImageIcon mainimg = new ImageIcon("img6.2.png");
        JLabel mainimglabel = new JLabel(mainimg);
        mainimglabel.setBounds(15, 15, 50, 50);

        ImageIcon homeIcon = new ImageIcon("toHOMEimg.png");
        toHome = new JButton(homeIcon);
        toHome.setBackground(new Color(0, 102, 204));
        toHome.setBounds(885, 8, 35, 35);
        toHome.addMouseListener(this);
        toppanel.add(toHome);

        ImageIcon gwaIcon = new ImageIcon("toGWAimg.png");
        JLabel gwaText = new JLabel();
        gwaText.setText("GWA");
        gwaText.setFont(new Font("Arial", Font.BOLD, 14));
        gwaText.setForeground(Color.white);
        gwaText.setBounds(38, 8, 50, 20);
        JLabel gwaLabel = new JLabel(gwaIcon);
        gwaLabel.setBounds(3, 2, 30, 30);
        toGWA = new JButton();
        toGWA.setLayout(null);
        toGWA.add(gwaLabel);
        toGWA.add(gwaText);
        toGWA.setBackground(new Color(0, 102, 204));
        toGWA.setBounds(800, 8, 80, 35);
        toGWA.addMouseListener(this);
        toppanel.add(toGWA);

        ImageIcon gcalIcon = new ImageIcon("toGCALimg.png");
        JLabel gcalText = new JLabel();
        gcalText.setText("GCAL");
        gcalText.setFont(new Font("Arial", Font.BOLD, 14));
        gcalText.setForeground(Color.white);
        gcalText.setBounds(36, 8, 50, 20);
        JLabel gcalLabel = new JLabel(gcalIcon);
        gcalLabel.setBounds(3, 2, 30, 30);
        toGCAL = new JButton();
        toGCAL.setLayout(null);
        toGCAL.add(gcalLabel);
        toGCAL.add(gcalText);
        toGCAL.setBackground(new Color(0, 102, 204));
        toGCAL.setBounds(715, 8, 80, 35);
        toGCAL.addMouseListener(this);
        toppanel.add(toGCAL);

        ImageIcon configIcon = new ImageIcon("toCONFIGimg.png");
        JLabel configText = new JLabel();
        configText.setText("CONFIG");
        configText.setFont(new Font("Arial", Font.BOLD, 14));
        configText.setForeground(Color.white);
        configText.setBounds(36, 8, 60, 20);
        JLabel configLabel = new JLabel(configIcon);
        configLabel.setBounds(3, 2, 30, 30);
        toCONFIG = new JButton();
        toCONFIG.setLayout(null);
        toCONFIG.add(configLabel);
        toCONFIG.add(configText);
        toCONFIG.setBackground(new Color(0, 102, 204));
        toCONFIG.setBounds(615, 8, 95, 35);
        toCONFIG.addMouseListener(this);
        toppanel.add(toCONFIG);

        // Mainpanel=======================================================
        JPanel mainpanel = new JPanel();
        mainpanel.setSize(500, 370);
        mainpanel.setLayout(null);
        mainpanel.setBounds(50, 116, 560, 370);
        mainpanel.setBackground(new Color(0, 102, 204));
        mainpanel.add(mainimglabel);
        this.add(mainpanel);

        JLabel text = new JLabel();
        text.setText("EXAM INITIAL GRADE CALCULATOR");
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.white);
        text.setBounds(80, 15, 500, 50);
        mainpanel.add(text);

        subject = new JTextField(); // TextField
        subject.setBounds(160, 80, 200, 20);
        subject.setFont(new Font("Arial", Font.PLAIN, 14));
        subject.setText("Subject");
        subject.addMouseListener(this);
        subject.addActionListener(this);
        mainpanel.add(subject);

        maxScore = new JTextField(); // TextField
        maxScore.setBounds(160, 110, 200, 20);
        maxScore.setFont(new Font("Arial", Font.PLAIN, 14));
        maxScore.setText("Enter Max Score");
        maxScore.addMouseListener(this);
        maxScore.addActionListener(this);
        mainpanel.add(maxScore);

        JLabel label1 = new JLabel();
        label1.setText("Enter You Exam Score:");
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        label1.setForeground(Color.white);
        label1.setBounds(160, 130, 250, 50);
        mainpanel.add(label1);

        textfield = new JTextField(); // TextField
        textfield.setBounds(160, 170, 200, 50);
        textfield.setFont(new Font("Arial", Font.PLAIN, 14));
        textfield.setText("Enter here");
        textfield.addMouseListener(this);
        textfield.addActionListener(this);
        mainpanel.add(textfield);

        submitBtn = new JButton(">"); // Button
        submitBtn.setBounds(360, 170, 50, 50);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setForeground(Color.black);
        submitBtn.setBackground(Color.gray);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this);
        submitBtn.addMouseListener(this);
        mainpanel.add(submitBtn);

        JLabel label2 = new JLabel();
        label2.setText("Result: ");
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setForeground(Color.white);
        label2.setBounds(160, 220, 200, 50);
        mainpanel.add(label2);

        JPanel resultpanel = new JPanel();
        resultpanel.setBounds(160, 260, 250, 50);
        resultpanel.setBackground(Color.white);
        resultpanel.setLayout(null);
        mainpanel.add(resultpanel);

        result = new JLabel();
        result.setFont(new Font("Arial", Font.BOLD, 22));
        result.setForeground(Color.BLACK);
        result.setBounds(10, 0, 200, 50);
        resultpanel.add(result);

        // History Panel==========================================================
        historypanel = new JPanel();
        historypanel.setSize(245, 370);
        historypanel.setBounds(640, 116, 245, 370);
        historypanel.setBackground(Color.lightGray);
        historypanel.setLayout(null);
        this.add(historypanel);

        JPanel line = new JPanel();
        line.setSize(245, 2);
        line.setBounds(0, 50, 245, 2);
        line.setBackground(Color.BLACK);
        line.setLayout(null);
        historypanel.add(line);

        JLabel historylabel = new JLabel();
        historylabel.setText("HISTORY");
        historylabel.setFont(new Font("Arial", Font.BOLD, 24));
        historylabel.setForeground(Color.BLACK);
        historylabel.setBounds(70, 5, 250, 50);
        historypanel.add(historylabel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            float ans = (Float.parseFloat(textfield.getText()) / Integer.parseInt(maxScore.getText())) * 50;
            if (ans > 30) {
                result.setText(String.valueOf(ans));
            } else if (ans < 29.99) {
                result.setText(String.valueOf(ans));
                JOptionPane.showMessageDialog(null, "Tang Ina mo Mag Aral ka ng Mabuti BOBO",
                        "STUPID MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }

            subjects.add(subject.getText());
            historyList.add(String.valueOf(ans));

            if (historyList.size() > 1 && subjects.size() > 1) {
                String historyprevResult = historyList.get(historyList.size() - 2);
                String subjectprevResult = subjects.get(subjects.size() - 2);

                JLabel subjectLabel = new JLabel();
                subjectLabel.setText(subjectprevResult);
                subjectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                subjectLabel.setForeground(Color.black);
                subjectLabel.setBounds(20, subjecthistoryY, 200, 50);
                historypanel.add(subjectLabel);

                JLabel historyLabel = new JLabel();
                historyLabel.setText(historyprevResult);
                historyLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                historyLabel.setForeground(Color.black);
                historyLabel.setBounds(20, resulthistoryY, 200, 50);
                historypanel.add(historyLabel);

                historypanel.repaint();

                resulthistoryY += 40;
                subjecthistoryY += 40;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == textfield) {
            textfield.setText(null);
        }
        if (e.getSource() == subject) {
            subject.setText(null);
        }
        if (e.getSource() == maxScore) {
            maxScore.setText(null);
        }
        if (e.getSource() == toHome) {
            new MainMenu();
            dispose();
        }
        if (e.getSource() == toGWA){
            new GWACalculator();
            dispose();
        }
        if (e.getSource() == toGCAL){
            new GradeCalculator();
            dispose();
        }
        if (e.getSource() == toCONFIG){
            new Config();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}