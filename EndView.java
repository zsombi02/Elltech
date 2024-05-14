import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EndView extends View{
    
    JFrame frame = new JFrame("Winner");
    JLabel label;

    public EndView(String winner)
    {   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label= new JLabel("Gratulalok a gyoztes csapatnak: " +winner);
        label.setBorder(new LineBorder(Color.WHITE));
        label.setForeground(Color.WHITE);
        add(label);
        setPreferredSize(new Dimension(400, 300));
        
        setBackground(Color.BLACK);

        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(150, 50));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(okButton);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
   

}
