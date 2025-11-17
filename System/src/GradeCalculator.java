import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;


public class GradeCalculator extends JFrame implements ActionListener, MouseListener {

    JButton submitBtn, toHome, toGWA, toECAL, toCONFIG;
    JTextField prelimField, midtermField, prefinalField, finalField;
    DecimalFormat df = new DecimalFormat("0.00");

    GradeCalculator() {
        // Settings
        this.setTitle("GWA Calculator");
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

        ImageIcon mainimg = new ImageIcon("img4.2.png");
        JLabel mainimglabel = new JLabel(mainimg);
        mainimglabel.setBounds(15, 15, 50, 50);

        ImageIcon homeIcon = new ImageIcon("toHOMEimg.png");
        toHome = new JButton(homeIcon);
        toHome.setBackground(new Color(0, 102, 204));
        toHome.setBounds(885, 8, 35, 35);
        toHome.addMouseListener(this);
        toppanel.add(toHome);

        ImageIcon ecalIcon = new ImageIcon("toECALimg.png");
        JLabel gcalText = new JLabel();
        gcalText.setText("ECAL");
        gcalText.setFont(new Font("Arial", Font.BOLD, 14));
        gcalText.setForeground(Color.white);
        gcalText.setBounds(36, 8, 50, 20);
        JLabel gcalLabel = new JLabel(ecalIcon);
        gcalLabel.setBounds(3, 2, 30, 30);
        toECAL = new JButton();
        toECAL.setLayout(null);
        toECAL.add(gcalLabel);
        toECAL.add(gcalText);
        toECAL.setBackground(new Color(0, 102, 204));
        toECAL.setBounds(800, 8, 80, 35);
        toECAL.addMouseListener(this);
        toppanel.add(toECAL);

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
        toGWA.setBounds(715, 8, 80, 35);
        toGWA.addMouseListener(this);
        toppanel.add(toGWA);

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
        mainpanel.setBounds(195, 80, 560, 370);
        mainpanel.setBackground(new Color(0, 102, 204));
        mainpanel.add(mainimglabel);
        this.add(mainpanel);

        JLabel text = new JLabel();
        text.setText("GRADE CALCULATOR");
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.white);
        text.setBounds(80, 15, 500, 50);
        mainpanel.add(text);

        prelimField = new JTextField(); // TextField
        prelimField.setBounds(41, 134, 200, 50);
        prelimField.setFont(new Font("Arial", Font.PLAIN, 14));
        prelimField.setText("0");
        prelimField.addMouseListener(this);
        prelimField.addActionListener(this);
        mainpanel.add(prelimField);

        midtermField = new JTextField(); // TextField
        midtermField.setBounds(319, 134, 200, 50);
        midtermField.setFont(new Font("Arial", Font.PLAIN, 14));
        midtermField.setText("0");
        midtermField.addMouseListener(this);
        midtermField.addActionListener(this);
        mainpanel.add(midtermField);

        prefinalField = new JTextField(); // TextField
        prefinalField.setBounds(41, 213, 200, 50);
        prefinalField.setFont(new Font("Arial", Font.PLAIN, 14));
        prefinalField.setText("0");
        prefinalField.addMouseListener(this);
        prefinalField.addActionListener(this);
        mainpanel.add(prefinalField);

        finalField = new JTextField(); // TextField
        finalField.setBounds(319, 213, 200, 50);
        finalField.setFont(new Font("Arial", Font.PLAIN, 14));
        finalField.setText("0");
        finalField.addMouseListener(this);
        finalField.addActionListener(this);
        mainpanel.add(finalField);

        submitBtn = new JButton("Calculate"); // Button
        submitBtn.setBounds(150, 300, 260, 50);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setForeground(Color.white);
        submitBtn.setBackground(Color.darkGray);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this);
        mainpanel.add(submitBtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {

            double prelim = Double.parseDouble(prelimField.getText());
            double midterm = Double.parseDouble(midtermField.getText());
            double prefinal = Double.parseDouble(prefinalField.getText());
            double target = 75;

            double completed = 0;
            if (prelim > 0)
                completed += prelim * 0.2;
            if (midterm > 0)
                completed += midterm * 0.2;
            if (prefinal > 0)
                completed += prefinal * 0.2;

            int missing = 0;
            if (prelim == 0)
                missing++;
            if (midterm == 0)
                missing++;
            if (prefinal == 0)
                missing++;

            double remainingWeight = missing * 0.2 + 0.4;

            double neededAverage = (target - completed) / remainingWeight;

            double needed = neededAverage;

            JOptionPane.showMessageDialog(null,
                    "You need an average of " + df.format(needed) + " in the remaining exams to reach " + target,
                    "STUPID MESSAGE", JOptionPane.INFORMATION_MESSAGE);
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
        } else if (e.getSource() == toHome) {
            new MainMenu();
            dispose();
        } else if (e.getSource() == toGWA) {
            new GWACalculator();
            dispose();
        } else if (e.getSource() == toECAL) {
            new ExamCalculator();
            dispose();
        } else if (e.getSource() == toCONFIG) {
            new Config();
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
