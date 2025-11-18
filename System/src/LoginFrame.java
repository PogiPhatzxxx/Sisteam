import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener, MouseListener {
    public static String idnum, usename;

    JButton loginbtn;
    JLabel leftlabel, rightlabel;
    JPanel left, right, bg;
    JTextField idTextField, userTextField;
    ImageIcon leftimage, rightimage;

    LoginFrame() {

        // login frame
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

        userTextField = new JTextField(); // TextField
        userTextField.setBounds(60, 280, 260, 50);
        userTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        userTextField.setText("Username");
        userTextField.addMouseListener(this);
        userTextField.addActionListener(this);
        right.add(userTextField);

        idTextField = new JTextField(); // TextField
        idTextField.setBounds(60, 340, 260, 50);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        idTextField.setText("ID No.");
        idTextField.addMouseListener(this);
        idTextField.addActionListener(this);
        right.add(idTextField);

        loginbtn = new JButton("Login"); // Button
        loginbtn.setBounds(60, 410, 260, 50);
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
            idnum = idTextField.getText();
            usename = userTextField.getText();
            if (idTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter ID ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.dispose();
                new MainMenu();
                JOptionPane.showMessageDialog(null, "Welcome : " + usename, "Welcome User",
                        JOptionPane.INFORMATION_MESSAGE);
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
