import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ColorLayout extends JPanel {
    Model model;

    public void resetAllOtherColors(JButton[] arrayofButtons, JButton onlyButtonKept) {
        for (int i = 0; i < arrayofButtons.length; ++i) {
            if (arrayofButtons[i] != onlyButtonKept) {
                arrayofButtons[i].setBorderPainted(false);
            }
        }
    }

    public ColorLayout(Model m) {
        model = m; // set model

        this.setLayout(new GridLayout(3, 2));

        // Add colour button icons
        JButton blueButton = new JButton();
        blueButton.setBackground(Color.blue);
        JButton redButton = new JButton();
        redButton.setBackground(Color.red);
        JButton orangeButton = new JButton();
        orangeButton.setBackground(Color.orange);
        JButton yellowButton = new JButton();
        yellowButton.setBackground(Color.yellow);
        JButton greenButton = new JButton();
        greenButton.setBackground(Color.green);
        JButton pinkButton = new JButton();
        pinkButton.setBackground(Color.pink);

        JButton[] colorArray = new JButton[] {blueButton, redButton, orangeButton, yellowButton, greenButton, pinkButton};

        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blueButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, blueButton);
            }
        });

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, redButton);
            }
        });

        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orangeButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, orangeButton);
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yellowButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, yellowButton);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                greenButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, greenButton);
            }
        });

        pinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pinkButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, pinkButton);
            }
        });

        for (int i = 0; i < colorArray.length; ++i) {
            colorArray[i].setSize(50, 50);
            colorArray[i].setOpaque(true);
            colorArray[i].setBorderPainted(false);
            this.add(colorArray[i]);
        }


    }
}