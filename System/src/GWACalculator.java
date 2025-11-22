import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GWACalculator extends JFrame implements ActionListener, MouseListener {

    JButton submitBtn, toHome, toGCAL, toECAL, toCONFIG;
    JTextField prelimField, midtermField, prefinalField, finalField, subject;
    JPanel historypanel;

    DecimalFormat df = new DecimalFormat("0.00");

    int resulthistoryY = 70, subjecthistoryY = 50;

    ArrayList<String> historyList = new ArrayList<>();
    ArrayList<String> subjects = new ArrayList<>();

    GWACalculator() {
        this.setTitle("GWA Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        ImageIcon mainimg = new ImageIcon("img5.2.png");
        JLabel mainimglabel = new JLabel(mainimg);
        mainimglabel.setBounds(15, 15, 50, 50);

        ImageIcon homeIcon = new ImageIcon("toHOMEimg.png");
        toHome = new JButton(homeIcon);
        toHome.setBackground(new Color(0, 102, 204));
        toHome.setBounds(885, 8, 35, 35);
        toHome.addMouseListener(this);
        toppanel.add(toHome);

        ImageIcon ecalIcon = new ImageIcon("toECALimg.png");
        JLabel ecalText = new JLabel();
        ecalText.setText("ECAL");
        ecalText.setFont(new Font("Arial", Font.BOLD, 14));
        ecalText.setForeground(Color.white);
        ecalText.setBounds(36, 8, 50, 20);
        JLabel ecalLabel = new JLabel(ecalIcon);
        ecalLabel.setBounds(3, 2, 30, 30);
        toECAL = new JButton();
        toECAL.setLayout(null);
        toECAL.add(ecalLabel);
        toECAL.add(ecalText);
        toECAL.setBackground(new Color(0, 102, 204));
        toECAL.setBounds(800, 8, 80, 35);
        toECAL.addMouseListener(this);
        toppanel.add(toECAL);

        ImageIcon gcalIcon = new ImageIcon("toGCALimg.png");
        JLabel gcalText = new JLabel();
        gcalText.setText("GCAL");
        gcalText.setFont(new Font("Arial", Font.BOLD, 14));
        gcalText.setForeground(Color.white);
        gcalText.setBounds(38, 8, 50, 20);
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
        mainpanel.setBounds(50, 90, 560, 370);
        mainpanel.setBackground(new Color(0, 102, 204));
        mainpanel.add(mainimglabel);
        this.add(mainpanel);

        JLabel text = new JLabel();
        text.setText("GWA CALCULATOR");
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.white);
        text.setBounds(80, 15, 500, 50);
        mainpanel.add(text);

        JLabel prelimLabel = new JLabel();
        prelimLabel.setText("Prelim :");
        prelimLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        prelimLabel.setForeground(Color.white);
        prelimLabel.setBounds(41, 105, 250, 50);
        mainpanel.add(prelimLabel);

        prelimField = new JTextField(); // TextField
        prelimField.setBounds(41, 140, 200, 50);
        prelimField.setFont(new Font("Arial", Font.PLAIN, 14));
        prelimField.setText("0");
        prelimField.addMouseListener(this);
        prelimField.addActionListener(this);
        mainpanel.add(prelimField);

        JLabel midtermLabel = new JLabel();
        midtermLabel.setText("Midterm :");
        midtermLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        midtermLabel.setForeground(Color.white);
        midtermLabel.setBounds(319, 105, 250, 50);
        mainpanel.add(midtermLabel);

        midtermField = new JTextField(); // TextField
        midtermField.setBounds(319, 140, 200, 50);
        midtermField.setFont(new Font("Arial", Font.PLAIN, 14));
        midtermField.setText("0");
        midtermField.addMouseListener(this);
        midtermField.addActionListener(this);
        mainpanel.add(midtermField);

        JLabel prefinalLabel = new JLabel();
        prefinalLabel.setText("Prefinal :");
        prefinalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        prefinalLabel.setForeground(Color.white);
        prefinalLabel.setBounds(41, 180, 250, 50);
        mainpanel.add(prefinalLabel);

        prefinalField = new JTextField(); // TextField
        prefinalField.setBounds(41, 215, 200, 50);
        prefinalField.setFont(new Font("Arial", Font.PLAIN, 14));
        prefinalField.setText("0");
        prefinalField.addMouseListener(this);
        prefinalField.addActionListener(this);
        mainpanel.add(prefinalField);

        JLabel finalLabel = new JLabel();
        finalLabel.setText("Final :");
        finalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        finalLabel.setForeground(Color.white);
        finalLabel.setBounds(319, 180, 250, 50);
        mainpanel.add(finalLabel);

        finalField = new JTextField(); // TextField
        finalField.setBounds(319, 215, 200, 50);
        finalField.setFont(new Font("Arial", Font.PLAIN, 14));
        finalField.setText("0");
        finalField.addMouseListener(this);
        finalField.addActionListener(this);
        mainpanel.add(finalField);

        subject = new JTextField(); // TextField
        subject.setBounds(41, 90, 200, 20);
        subject.setFont(new Font("Arial", Font.PLAIN, 14));
        subject.setText("Subject");
        subject.addMouseListener(this);
        subject.addActionListener(this);
        mainpanel.add(subject);

        submitBtn = new JButton("Calculate"); // Button
        submitBtn.setBounds(150, 300, 260, 50);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setForeground(Color.white);
        submitBtn.setBackground(Color.darkGray);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this);
        mainpanel.add(submitBtn);

        // History Panel==========================================================
        historypanel = new JPanel();
        historypanel.setSize(245, 370);
        historypanel.setBounds(640, 90, 245, 370);
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

            double prelim = Double.parseDouble(prelimField.getText());
            double midterm = Double.parseDouble(midtermField.getText());
            double prefinal = Double.parseDouble(prefinalField.getText());
            double finals = Double.parseDouble(finalField.getText());

            double result = (prelim + midterm + prefinal) * GWAconf.prelimWeight + (finals * GWAconf.finalWeight);

            JOptionPane.showMessageDialog(null,
                    "Your GWA is " + df.format(result),
                    "STUPID MESSAGE", JOptionPane.INFORMATION_MESSAGE);

            subjects.add(subject.getText());
            historyList.add(String.valueOf(df.format(result)));

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
        if (e.getSource() == prelimField) {
            prelimField.setText(null);
        } else if (e.getSource() == midtermField) {
            midtermField.setText(null);
        } else if (e.getSource() == prefinalField) {
            prefinalField.setText(null);
        } else if (e.getSource() == finalField) {
            finalField.setText(null);
        } else if (e.getSource() == subject) {
            subject.setText(null);
        } else if (e.getSource() == toHome) {
            new MainMenu();
            dispose();
        } else if (e.getSource() == toGCAL) {
            new GradeCalculator();
            dispose();
        } else if (e.getSource() == toECAL) {
            new ExamCalculator();
            dispose();
        } else if (e.getSource() == toCONFIG) {
            new GWAconf();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

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
