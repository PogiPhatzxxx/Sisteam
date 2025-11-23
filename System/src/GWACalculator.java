import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GWACalculator extends JFrame implements ActionListener, MouseListener {

    // Summary Labels
    JLabel gwaLabel, unitsLabel, statusLabel;
    JButton addcourseBtn, calculateBtn;
    JButton submitBtn, toHome, toGWA, toECAL, toCONFIG;

    JScrollPane f2;
    JPanel container;

    DecimalFormat df = new DecimalFormat("0.00");

    ArrayList<JPanel> coursePanels = new ArrayList<>();

    public GWACalculator() {

        this.setTitle("GWA Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon topimg = new ImageIcon("img3.png");
        JLabel topimglabel = new JLabel(topimg);
        topimglabel.setBounds(11, -6, 70, 70);

        ImageIcon mainimg = new ImageIcon("img5.2.png");
        JLabel mainimglabel = new JLabel(mainimg);
        mainimglabel.setBounds(15, 15, 50, 50);

        JPanel toppanel = new JPanel();
        toppanel.setSize(950, 50);
        toppanel.setBackground(Color.yellow);
        toppanel.setLayout(null);
        toppanel.add(topimglabel);
        this.add(toppanel);

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

        ImageIcon gwaIcon = new ImageIcon("toGWAimg.png");
        JLabel gwaText = new JLabel();
        gwaText.setText("GWA");
        gwaText.setFont(new Font("Arial", Font.BOLD, 14));
        gwaText.setForeground(Color.white);
        gwaText.setBounds(38, 8, 50, 20);
        
        JLabel gwaLabel2 = new JLabel(gwaIcon);
        gwaLabel2.setBounds(3, 2, 30, 30);
        toGWA = new JButton();
        toGWA.setLayout(null);
        toGWA.add(gwaLabel2);
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

        JPanel mainpanel = new JPanel(null);
        mainpanel.setLayout(null);
        mainpanel.setBounds(0, 50, 950, 550);
        mainpanel.setBackground(new Color(0, 102, 204));
        mainpanel.add(mainimglabel);
        this.add(mainpanel);

        JLabel text = new JLabel();
        text.setText("GWA CALCULATOR");
        text.setFont(new Font("Arial", Font.BOLD, 24));
        text.setForeground(Color.white);
        text.setBounds(80, 15, 500, 50);
        mainpanel.add(text);

        JPanel f1 = new JPanel(null);
        f1.setBounds(63, 80, 810, 65);
        f1.setBackground(Color.white);
        mainpanel.add(f1);

        gwaLabel = new JLabel("GWA : 00.00%");
        gwaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gwaLabel.setBounds(20, 10, 250, 40);
        f1.add(gwaLabel);

        JPanel line1 = new JPanel();
        line1.setBounds(190, 0, 2, 65);
        line1.setBackground(Color.BLACK);
        f1.add(line1);

        unitsLabel = new JLabel("Total Units : 0");
        unitsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        unitsLabel.setBounds(205, 10, 250, 40);
        f1.add(unitsLabel);

        JPanel line2 = new JPanel();
        line2.setBounds(390, 0, 2, 65);
        line2.setBackground(Color.BLACK);
        f1.add(line2);

        statusLabel = new JLabel("Status : -");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setBounds(405, 10, 250, 40);
        f1.add(statusLabel);

        addcourseBtn = new JButton("Add Course");
        addcourseBtn.setBounds(692, 23, 160, 50);
        addcourseBtn.setFont(new Font("Arial", Font.BOLD, 22));
        addcourseBtn.setBackground(Color.darkGray);
        addcourseBtn.setForeground(Color.white);
        addcourseBtn.setFocusable(false);
        addcourseBtn.addActionListener(this);
        mainpanel.add(addcourseBtn);

        calculateBtn = new JButton("Calculate");
        calculateBtn.setBounds(630, 7, 160, 50);
        calculateBtn.setFont(new Font("Arial", Font.BOLD, 22));
        calculateBtn.setBackground(new Color(0, 102, 204));
        calculateBtn.setForeground(Color.white);
        calculateBtn.setFocusable(false);
        calculateBtn.addActionListener(this);
        f1.add(calculateBtn);

        f2 = new JScrollPane();
        f2.setBounds(63, 160, 810, 330);
        mainpanel.add(f2);

        container = new JPanel(null);
        container.setPreferredSize(new Dimension(780, 600));
        f2.setViewportView(container);

        addNewCoursePanel();

        this.setVisible(true);
    }

    public void addNewCoursePanel() {

        JPanel p = new JPanel(null);
        p.setBackground(Color.lightGray);
        p.setBounds(25, 25 + (coursePanels.size() * 150), 760, 130);

        JTextField coursecodeField = new JTextField("Course Code");
        coursecodeField.setBounds(15, 15, 165, 35);
        coursecodeField.addMouseListener(this);
        p.add(coursecodeField);

        JTextField coursenameField = new JTextField("Course Name");
        coursenameField.setBounds(203, 15, 165, 35);
        coursenameField.addMouseListener(this);
        p.add(coursenameField);

        JTextField unitsField = new JTextField("Units");
        unitsField.setBounds(393, 15, 165, 35);
        unitsField.addMouseListener(this);
        p.add(unitsField);

        JLabel averageLabel = new JLabel("AVG: 00.00%");
        averageLabel.setBounds(580, 8, 170, 50);
        averageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        p.add(averageLabel);

        JButton removeBtn = new JButton("X");
        removeBtn.setBounds(710, 15, 35, 35);
        removeBtn.setBackground(Color.red);
        removeBtn.setForeground(Color.white);
        removeBtn.setFocusable(false);
        p.add(removeBtn);

        JTextField prelimField = new JTextField("Prelim");
        prelimField.setBounds(15, 65, 165, 55);
        prelimField.addMouseListener(this);
        p.add(prelimField);

        JTextField midtermField = new JTextField("Midterm");
        midtermField.setBounds(203, 65, 165, 55);
        midtermField.addMouseListener(this);
        p.add(midtermField);

        JTextField prefinalField = new JTextField("Prefinal");
        prefinalField.setBounds(393, 65, 165, 55);
        prefinalField.addMouseListener(this);
        p.add(prefinalField);

        JTextField finalField = new JTextField("Final");
        finalField.setBounds(580, 65, 165, 55);
        finalField.addMouseListener(this);
        p.add(finalField);

        removeBtn.addActionListener(e -> {

            container.remove(p);
            coursePanels.remove(p);

            for (int i = 0; i < coursePanels.size(); i++) {
                JPanel panel = coursePanels.get(i);
                panel.setLocation(25, 25 + (i * 150));
            }

            container.repaint();
            

        });

        coursePanels.add(p);
        container.add(p);
        container.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addcourseBtn) {
            addNewCoursePanel();
        }

        if (e.getSource() == calculateBtn) {

            double totalUnits = 0;
            double totalWeighted = 0;

            for (JPanel p : coursePanels) {

                // get components
                JTextField unitsField = (JTextField) p.getComponent(2);
                JLabel avgLabel = (JLabel) p.getComponent(3);

                JTextField prelim = (JTextField) p.getComponent(5);
                JTextField mid = (JTextField) p.getComponent(6);
                JTextField prefi = (JTextField) p.getComponent(7);
                JTextField fin = (JTextField) p.getComponent(8);

                double u = 0, avg = 0;
                double p1 = 0, m1 = 0, pf1 = 0, f1 = 0;

                try {
                    u = Double.parseDouble(unitsField.getText());
                } catch (Exception ex) {
                }
                try {
                    p1 = Double.parseDouble(prelim.getText());
                } catch (Exception ex) {
                }
                try {
                    m1 = Double.parseDouble(mid.getText());
                } catch (Exception ex) {
                }
                try {
                    pf1 = Double.parseDouble(prefi.getText());
                } catch (Exception ex) {
                }
                try {
                    f1 = Double.parseDouble(fin.getText());
                } catch (Exception ex) {
                }

                avg = (p1 + m1 + pf1) * 0.2 + (f1 * 0.4);

                avgLabel.setText("AVG: " + df.format(avg) + "%");

                totalUnits += u;
                totalWeighted += avg * u;
            }

            unitsLabel.setText("Total Units : " + (int) totalUnits);

            if (totalUnits > 0) {
                double gwa = totalWeighted / totalUnits;
                gwaLabel.setText("GWA : " + df.format(gwa) + "%");

                if (gwa >= 75) {
                    statusLabel.setText("Status : PASSING");
                    statusLabel.setForeground(new Color(0, 150, 0));
                } else {
                    statusLabel.setText("Status : FAILING");
                    statusLabel.setForeground(Color.red);
                }
            } else {
                gwaLabel.setText("GWA : 00.00%");
                statusLabel.setText("Status : -");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTextField) {
            JTextField tf = (JTextField) e.getSource();
            tf.setText("");
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
