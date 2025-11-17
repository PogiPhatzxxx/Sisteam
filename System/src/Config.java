import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Config extends JFrame implements ActionListener{

    Config(){
        //Course
        this.setTitle("Course");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 400);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);

        JLabel text1 = new JLabel();
        text1.setText("TETE");
        text1.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(text1);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
