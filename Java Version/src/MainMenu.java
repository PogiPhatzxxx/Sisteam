import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener, MouseListener {

    JPanel firstpanel, secondpanel, thirdpanel;
    JButton openCalcBtn, openGWABtn, openExamBtn;

    MainMenu() {
        this.setTitle("Main Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon topimg = new ImageIcon("img3.png");
        JLabel topimglabel = new JLabel(topimg);
        topimglabel.setBounds(11, -6, 70, 70);

        JLabel username = new JLabel();
        username.setText(LoginFrame.usename);
        username.setFont(new Font("Arial", Font.BOLD, 14));
        username.setBounds(810, 3, 100, 30);
        username.setForeground(Color.BLACK);

        JLabel id = new JLabel();
        id.setText(" ID: " + LoginFrame.idnum);
        id.setFont(new Font("Arial", Font.PLAIN, 12));
        id.setBounds(807, 20, 120, 30);
        id.setForeground(Color.BLACK);

        JPanel toppanel = new JPanel();
        toppanel.setSize(950, 50);
        toppanel.setBackground(Color.yellow);
        toppanel.setLayout(null);
        toppanel.add(id);
        toppanel.add(username);
        toppanel.add(topimglabel);
        this.add(toppanel);

        JLabel maintittle = new JLabel("Welcome to ASTIG-CAL");
        maintittle.setFont(new Font("Arial", Font.BOLD, 26));
        maintittle.setBounds(323, 50, 300, 50);
        this.add(maintittle);

        JLabel description = new JLabel("Choose a calculator to get started");
        description.setFont(new Font("Arial", Font.PLAIN, 16));
        description.setBounds(350, 80, 300, 50);
        this.add(description);

        // First Panel ==========================================================
        ImageIcon fpanelimg = new ImageIcon("img4.png");
        JLabel fpanellabel = new JLabel(fpanelimg);
        fpanellabel.setBounds(75, 25, 100, 100);

        JLabel title1 = new JLabel("Needed Grade Calculator");
        title1.setFont(new Font("Arial", Font.BOLD, 16));
        title1.setBounds(30, 135, 250, 25);

        JLabel desc1 = new JLabel("Calculate your needed grades to pass");
        desc1.setFont(new Font("Arial", Font.PLAIN, 12));
        desc1.setBounds(20, 160, 220, 20);

        JLabel desc2 = new JLabel("with the 4-term grading system");
        desc2.setFont(new Font("Arial", Font.PLAIN, 12));
        desc2.setBounds(40, 175, 220, 20);

        openCalcBtn = new JButton("Open Calculator");
        openCalcBtn.setBackground(new Color(0, 102, 255));
        openCalcBtn.setForeground(Color.WHITE);
        openCalcBtn.setBounds(55, 205, 140, 30);
        openCalcBtn.setFocusPainted(false);
        openCalcBtn.addActionListener(this);

        firstpanel = new JPanel();
        firstpanel.setLayout(null);
        firstpanel.setBounds(40, 130, 250, 250);
        firstpanel.setBackground(Color.LIGHT_GRAY);
        firstpanel.add(fpanellabel);
        firstpanel.add(title1);
        firstpanel.add(openCalcBtn);
        firstpanel.add(desc1);
        firstpanel.add(desc2);
        firstpanel.addMouseListener(this);
        this.add(firstpanel);

        // Second Panel=============================================
        ImageIcon spanelimg = new ImageIcon("img5.png");
        JLabel spanellabel = new JLabel(spanelimg);
        spanellabel.setBounds(75, 25, 100, 100);

        JLabel title2 = new JLabel("GWA Calculator");
        title2.setFont(new Font("Arial", Font.BOLD, 16));
        title2.setBounds(63, 135, 250, 25);

        JLabel desc3 = new JLabel("Calculate your GWA across multiple");
        desc3.setFont(new Font("Arial", Font.PLAIN, 12));
        desc3.setBounds(27, 160, 220, 20);

        JLabel desc4 = new JLabel(" courses with units");
        desc4.setFont(new Font("Arial", Font.PLAIN, 12));
        desc4.setBounds(68, 175, 220, 20);

        openGWABtn = new JButton("Open GWA Calculator");
        openGWABtn.setBackground(new Color(0, 102, 255));
        openGWABtn.setForeground(Color.WHITE);
        openGWABtn.setBounds(45, 205, 160, 30);
        openGWABtn.setFocusPainted(false);
        openGWABtn.addActionListener(this);

        secondpanel = new JPanel();
        secondpanel.setSize(250, 250);
        secondpanel.setBackground(Color.lightGray);
        secondpanel.setLayout(null);
        secondpanel.setBounds(343, 130, 250, 250);
        secondpanel.add(spanellabel);
        secondpanel.add(title2);
        secondpanel.add(desc3);
        secondpanel.add(desc4);
        secondpanel.add(openGWABtn);
        secondpanel.addMouseListener(this);
        this.add(secondpanel);

        // Third Panel =================================================
        ImageIcon tpanelimg = new ImageIcon("img6.png");
        JLabel tpanellabel = new JLabel(tpanelimg);
        tpanellabel.setBounds(75, 25, 100, 100);

        JLabel title3 = new JLabel("EXAM Calculator");
        title3.setFont(new Font("Arial", Font.BOLD, 16));
        title3.setBounds(63, 135, 250, 25);

        JLabel desc5 = new JLabel("Calculate your Initial grade in exams");
        desc5.setFont(new Font("Arial", Font.PLAIN, 12));
        desc5.setBounds(26, 160, 220, 20);

        openExamBtn = new JButton("Open EXAM Calculator");
        openExamBtn.setBackground(new Color(0, 102, 255));
        openExamBtn.setForeground(Color.WHITE);
        openExamBtn.setBounds(45, 205, 165, 30);
        openExamBtn.setFocusPainted(false);
        openExamBtn.addActionListener(this);

        thirdpanel = new JPanel();
        thirdpanel.setSize(250, 250);
        thirdpanel.setBackground(Color.lightGray);
        thirdpanel.setLayout(null);
        thirdpanel.setBounds(645, 130, 250, 250);
        thirdpanel.add(tpanellabel);
        thirdpanel.add(title3);
        thirdpanel.add(desc5);
        thirdpanel.add(openExamBtn);
        thirdpanel.addMouseListener(this);
        this.add(thirdpanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(40, 420, 855, 85);
        infoPanel.setBackground(Color.lightGray);
        infoPanel.setLayout(null);

        JLabel gradingLabel = new JLabel(
                "Grading System: Prelim 20%    •    Midterm 20%    •    Pre-final 20%  •    Final 40%");
        gradingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gradingLabel.setForeground(Color.black);
        gradingLabel.setBounds(20, 15, 800, 25);

        JLabel passingLabel = new JLabel("Passing Grade: 69.50%");
        passingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passingLabel.setForeground(Color.black);
        passingLabel.setBounds(20, 45, 200, 25);

        infoPanel.add(gradingLabel);
        infoPanel.add(passingLabel);
        this.add(infoPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openCalcBtn) {
            new NeededGradeCalculator();
            dispose();
        }
        if (e.getSource() == openGWABtn) {
            new GWACalculator();
            dispose();
        }
        if (e.getSource() == openExamBtn) {
            new ExamCalculator();
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == firstpanel) {
            new NeededGradeCalculator();
            dispose();
        }
        if (e.getSource() == secondpanel) {
            new GWACalculator();
            dispose();
        }
        if (e.getSource() == thirdpanel) {
            new ExamCalculator();
            dispose();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == firstpanel) {
            firstpanel.setBackground(Color.GRAY);
            firstpanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        } else if (e.getSource() == secondpanel) {
            secondpanel.setBackground(Color.GRAY);
            secondpanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        } else if (e.getSource() == thirdpanel) {
            thirdpanel.setBackground(Color.GRAY);
            thirdpanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == firstpanel) {
            firstpanel.setBackground(Color.LIGHT_GRAY);
            firstpanel.setBorder(null);
        } else if (e.getSource() == secondpanel) {
            secondpanel.setBackground(Color.LIGHT_GRAY);
            secondpanel.setBorder(null);
        } else if (e.getSource() == thirdpanel) {
            thirdpanel.setBackground(Color.LIGHT_GRAY);
            thirdpanel.setBorder(null);

        }
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
