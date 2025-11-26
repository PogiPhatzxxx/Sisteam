import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener, MouseListener {
    public static String idnum, usename, selectedCourse;
    public static String currentCourse = "";

    JButton loginbtn;
    JLabel leftlabel, rightlabel;
    JPanel left, right, bg;
    JTextField idTextField, userTextField;
    ImageIcon leftimage, rightimage;
    JComboBox course;

    String[] courses = { "Select Course", "BSIT", "BMMA", "BSIS", "BSBA", "BSA", "BSTM", "BSHM" };

    LoginFrame() {
        this.setTitle("ASTIG-CAL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        leftimage = new ImageIcon("img1.png");
        leftlabel = new JLabel(leftimage);
        leftlabel.setBounds(0, 0, 560, 600);
        this.add(leftlabel);

        rightimage = new ImageIcon("img2.png");
        rightlabel = new JLabel(rightimage);
        rightlabel.setBounds(32, -10, 320, 320);
        right = new JPanel();
        right.setSize(390, 600);
        right.setBounds(560, 0, 390, 600);
        right.setLayout(null);
        right.setBackground(Color.WHITE);
        right.add(rightlabel);
        this.add(right);

        course = new JComboBox<>(courses);
        course.setBounds(60, 270, 260, 50);
        course.setFont(new Font("Arial", Font.PLAIN, 14));
        course.setBackground(Color.WHITE);
        course.addActionListener(this);
        right.add(course);

        userTextField = new JTextField();
        userTextField.setBounds(60, 330, 260, 50);
        userTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        userTextField.setText("Username");
        userTextField.addMouseListener(this);
        userTextField.addActionListener(this);
        right.add(userTextField);

        idTextField = new JTextField();
        idTextField.setBounds(60, 390, 260, 50);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        idTextField.setText("ID No.");
        idTextField.addMouseListener(this);
        idTextField.addActionListener(this);
        right.add(idTextField);

        loginbtn = new JButton("Login");
        loginbtn.setBounds(60, 450, 260, 50);
        loginbtn.setFont(new Font("Arial", Font.BOLD, 18));
        loginbtn.setForeground(Color.WHITE);
        loginbtn.setBackground(new Color(0, 102, 204));
        loginbtn.setFocusable(false);
        loginbtn.addActionListener(this);
        right.add(loginbtn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbtn) {
            String selectedCourse = String.valueOf(course.getSelectedItem());
            String idText = idTextField.getText();
            String userText = userTextField.getText();

            if (selectedCourse.equals("Select Course")) {
                JOptionPane.showMessageDialog(null, "Please Select Course", "Error", JOptionPane.ERROR_MESSAGE);

                return;
            } else if ((userText.isEmpty() || userText.equals("Username"))) {
                JOptionPane.showMessageDialog(null, "Please Enter Username ", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (idText.isEmpty() || idText.equals("ID No.")) {
                JOptionPane.showMessageDialog(null, "Please Enter ID ", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

idnum = idText;
            usename = userText;
            selectedCourse = String.valueOf(course.getSelectedItem());
            currentCourse = selectedCourse; // Update currentCourse when logging in
            this.dispose();
            new MainMenu();
            JOptionPane.showMessageDialog(null, "Welcome : " + usename, "Welcome User",
                    JOptionPane.INFORMATION_MESSAGE);
        }

if (e.getSource() == course) {
            String selectedCourse = String.valueOf(course.getSelectedItem());
            
            if (!selectedCourse.equals("Select Course")) {
                currentCourse = selectedCourse;
            }

            NGconf.cutOffGrade = 70;
            ECconf.examPercentage = 50;

            if (selectedCourse.equals("BSIT")) {
                NGconf.cutOffGrade = 70.50;
            } else if (selectedCourse.equals("BSA")) {
                NGconf.cutOffGrade = 76.00;
                ECconf.examPercentage = 80;
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == idTextField) {
            idTextField.setText(null);
        } else if (e.getSource() == userTextField) {
            userTextField.setText(null);
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
