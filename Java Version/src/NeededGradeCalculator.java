import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class NeededGradeCalculator extends JFrame implements ActionListener, MouseListener {

    JButton submitBtn, toHome, toGWA, toECAL, toCONFIG;
    JTextField prelimField, midtermField, prefinalField, finalField;
    JComboBox<String> subject;

    JPanel historypanel;
    DecimalFormat df = new DecimalFormat("0.00");

    String course = LoginFrame.currentCourse;
    ArrayList<String> subjectList = SubjectManager.getSubjectsByCourse(course);

    int resulthistoryY = 70, subjecthistoryY = 50;
    ArrayList<String> historyList = new ArrayList<>();
    ArrayList<String> subjects = new ArrayList<>();

    NeededGradeCalculator() {
        this.setTitle("Needed Grade Calculator");
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

        JPanel mainpanel = new JPanel();
        mainpanel.setSize(500, 370);
        mainpanel.setLayout(null);
        mainpanel.setBounds(50, 90, 560, 370);
        mainpanel.setBackground(new Color(0, 102, 204));
        mainpanel.add(mainimglabel);
        this.add(mainpanel);

        JLabel text = new JLabel();
        text.setText("NEEDED GRADE CALCULATOR");
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

        subject = new JComboBox<>(subjectList.toArray(new String[0]));
        subject.setBounds(41, 90, 200, 30);
        subject.setBackground(Color.WHITE);
        subject.setForeground(Color.BLACK);
        subject.setFocusable(false);
        subject.addActionListener(this);
        mainpanel.add(subject);

        submitBtn = new JButton("Calculate");
        submitBtn.setBounds(150, 300, 260, 50);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setForeground(Color.white);
        submitBtn.setBackground(Color.darkGray);
        submitBtn.setFocusable(false);
        submitBtn.addActionListener(this);
        mainpanel.add(submitBtn);

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

            String empty = "";

            if (prelimField.getText().isEmpty())
                empty += "Prelim\n";
            if (midtermField.getText().isEmpty())
                empty += "Midterm\n";
            if (prefinalField.getText().isEmpty())
                empty += "Pre-Final\n";
            if (finalField.getText().isEmpty())
                empty += "Final\n";

            if (!empty.equals("")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error: These fields are empty:\n" + empty + "\nPlease enter '0' in empty fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double prelim = Double.parseDouble(prelimField.getText());
            double midterm = Double.parseDouble(midtermField.getText());
            double prefinal = Double.parseDouble(prefinalField.getText());
            double finalExam = Double.parseDouble(finalField.getText());
            double target = NGconf.cutOffGrade;

            if (prelim > 0 && midterm > 0 && prefinal > 0 && finalExam > 0) {

                double gwa = ((prelim * NGconf.prelimWeight) + (midterm * NGconf.midtermWeight)
                        + (prefinal * NGconf.prefinalWeight) + (finalExam * NGconf.finalWeight));

                if (gwa >= NGconf.cutOffGrade) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your Final GWA is: " + df.format(gwa) +
                                    "\n\nCongratulations! You have PASSED this subject!",
                            "GWA Result",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your Final GWA is: " + df.format(gwa) +
                                    "\n\nYou have FAILED this subject. Whahahaha",
                            "GWA Result",
                            JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

            double completed = 0;
            if (prelim > 0)
                completed += prelim * NGconf.prelimWeight;
            if (midterm > 0)
                completed += midterm * NGconf.midtermWeight;
            if (prefinal > 0)
                completed += prefinal * NGconf.prefinalWeight;

            int missing = 0;
            if (prelim == 0)
                missing++;
            if (midterm == 0)
                missing++;
            if (prefinal == 0)
                missing++;

            double remainingWeight = missing * NGconf.prelimWeight + NGconf.finalWeight;
            double neededAverage = (target - completed) / remainingWeight;

            String msg = "You need at least:\n";

            if (prelim == 0)
                msg += df.format(neededAverage) + " in Prelim\n";
            if (midterm == 0)
                msg += df.format(neededAverage) + " in Midterm\n";
            if (prefinal == 0)
                msg += df.format(neededAverage) + " in Pre-Final\n";
            if (finalExam == 0)
                msg += df.format(neededAverage) + " in Final\n";

            String motivationalMessage = "";
            if (neededAverage > 100) {
                motivationalMessage = "Bagsak Kana Boiii";
            } else if (neededAverage >= 90) {
                motivationalMessage = "Du Alanganin Na Haw";
            } else if (neededAverage >= 80) {
                motivationalMessage = "Kaya Mo Yan Laban Lang";
            } else if (neededAverage >= 70) {
                motivationalMessage = "Sus Basic Lang ni Saimo Ah";
            } else if (neededAverage >= 60) {
                motivationalMessage = "Gwapooo, Gamay Gamay lang Lagson mo Syaro ma Bagsak";
            }

            if (!motivationalMessage.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        motivationalMessage,
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "Required Grades",
                    JOptionPane.INFORMATION_MESSAGE);

            subjects.add(String.valueOf(subject.getSelectedItem()));
            historyList.add(String.valueOf(df.format(neededAverage)));

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
            new NGconf();
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