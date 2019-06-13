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
                model.setCurrentColor(Color.BLUE);
                blueButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, blueButton);
            }
        });

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.RED);
                redButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, redButton);
            }
        });

        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.ORANGE);
                orangeButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, orangeButton);
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.YELLOW);
                yellowButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, yellowButton);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.GREEN);
                greenButton.setBorderPainted(true);
                resetAllOtherColors(colorArray, greenButton);
            }
        });

        pinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.PINK);
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

        blueButton.setBorderPainted(true); // set init state

    }
}