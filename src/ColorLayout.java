import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ColorLayout extends JPanel {
    Model model;

    public JButton blueButton;
    public JButton redButton;
    public JButton orangeButton;
    public JButton yellowButton;
    public JButton pinkButton;
    public JButton greenButton;

    public void resetAllOtherColors(JButton[] arrayofButtons, JButton onlyButtonKept) {
        for (int i = 0; i < arrayofButtons.length; ++i) {
            if (arrayofButtons[i] != onlyButtonKept) {
                arrayofButtons[i].setBorderPainted(false);
            }
        }
    }

    public void setBlueButtonToTrue(JButton [] colorArray) {
        blueButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, blueButton);
    }

    public void setGreenButtonToTrue(JButton [] colorArray) {
        greenButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, greenButton);
    }

    public void setRedButtonToTrue(JButton [] colorArray) {
        redButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, redButton);
    }

    public void setOrangeButtonToTrue(JButton [] colorArray) {
        orangeButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, orangeButton);
    }

    public void setPinkButtonToTrue(JButton [] colorArray) {
        pinkButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, pinkButton);
    }

    public void setYellowButtonToTrue(JButton [] colorArray) {
        yellowButton.setBorderPainted(true);
        resetAllOtherColors(colorArray, yellowButton);
    }

    public ColorLayout(Model m) {
        model = m; // set model

        this.setLayout(new GridLayout(3, 2));

        // Add colour button icons
        blueButton = new JButton();
        blueButton.setBackground(Color.blue);
        redButton = new JButton();
        redButton.setBackground(Color.red);
        orangeButton = new JButton();
        orangeButton.setBackground(Color.orange);
        yellowButton = new JButton();
        yellowButton.setBackground(Color.yellow);
        greenButton = new JButton();
        greenButton.setBackground(Color.green);
        pinkButton = new JButton();
        pinkButton.setBackground(Color.pink);

        JButton[] colorArray = new JButton[] {blueButton, redButton, orangeButton, yellowButton, greenButton, pinkButton};

        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.BLUE);
                setBlueButtonToTrue(colorArray);
            }
        });

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.RED);
                setRedButtonToTrue(colorArray);
            }
        });

        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.ORANGE);
                setOrangeButtonToTrue(colorArray);
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.YELLOW);
                setYellowButtonToTrue(colorArray);
            }
        });

        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.GREEN);
                setGreenButtonToTrue(colorArray);
            }
        });

        pinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setCurrentColor(Color.PINK);
                setPinkButtonToTrue(colorArray);
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