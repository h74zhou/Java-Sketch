import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LineLayout extends JPanel {
    public LineLayout() {
        this.setLayout(new GridLayout(3, 1));
        JButton lineThick1 = new JButton("Thin Lines");
        JButton lineThick2 = new JButton("Regular Lines");
        JButton lineThick3 = new JButton("Thick Lines");
        JButton[] lines = new JButton[] {lineThick1, lineThick2, lineThick3};

        lineThick1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineThick1.setForeground(Color.RED);
                lineThick2.setForeground(Color.BLACK);
                lineThick3.setForeground(Color.BLACK);
            }
        });

        lineThick2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineThick2.setForeground(Color.RED);
                lineThick1.setForeground(Color.BLACK);
                lineThick3.setForeground(Color.BLACK);
            }
        });

        lineThick3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineThick3.setForeground(Color.RED);
                lineThick2.setForeground(Color.BLACK);
                lineThick1.setForeground(Color.BLACK);
            }
        });

        for (int i = 0; i < lines.length; ++i) {
            this.add(lines[i]);
        }
    }
}