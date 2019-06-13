import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ToolBarLayout extends JPanel {

    private JButton pointerButton = new JButton();
    private JButton eraserButton = new JButton();
    private JButton lineButton = new JButton();
    private JButton circleButton = new JButton();
    private JButton squareButton = new JButton();
    private JButton fillButton = new JButton();
    private Image pointerImg;
    private Image eraserImg;
    private Image lineImg;
    private Image circleImg;
    private Image squareImg;
    private Image fillImg;
    private Image clickedCursorImg;
    private Image clickedEraserImg;
    private Image clickedLineImg;
    private Image clickedCircleImg;
    private Image clickedSquareImg;
    private Image clickedFillImg;

    Model model;

    public void setAllOtherButtonsToDefault(JButton[] arrayofButtons, JButton onlyButtonKept) {
        for (int i = 0; i < arrayofButtons.length; ++i) {
            if (arrayofButtons[i] != onlyButtonKept) {
                if (arrayofButtons[i] == pointerButton) {
                    pointerButton.setIcon(new ImageIcon(pointerImg));
                } else if (arrayofButtons[i] == eraserButton) {
                    eraserButton.setIcon(new ImageIcon(eraserImg));
                } else if (arrayofButtons[i] == lineButton) {
                    lineButton.setIcon(new ImageIcon(lineImg));
                } else if (arrayofButtons[i] == circleButton) {
                    circleButton.setIcon(new ImageIcon(circleImg));
                } else if (arrayofButtons[i] == squareButton) {
                    squareButton.setIcon(new ImageIcon(squareImg));
                } else if (arrayofButtons[i] == fillButton) {
                    fillButton.setIcon(new ImageIcon(fillImg));
                }
            }
        }
    }

    public ToolBarLayout(Model m) {
        model = m; // set model
        this.setLayout(new GridLayout(3, 2));

        try {
            pointerImg = ImageIO.read(getClass().getResource("resources/cursor.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            eraserImg = ImageIO.read(getClass().getResource("resources/eraser.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            lineImg = ImageIO.read(getClass().getResource("resources/line.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            circleImg = ImageIO.read(getClass().getResource("resources/circle.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            squareImg = ImageIO.read(getClass().getResource("resources/square.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            fillImg = ImageIO.read(getClass().getResource("resources/fill.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);

            clickedCursorImg = ImageIO.read(getClass().getResource("resources/clickedCursor.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            clickedEraserImg = ImageIO.read(getClass().getResource("resources/clickedEraser.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            clickedLineImg = ImageIO.read(getClass().getResource("resources/clickedLine.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);
            clickedCircleImg = ImageIO.read(getClass().getResource("resources/clickedCircle.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;
            clickedSquareImg =  ImageIO.read(getClass().getResource("resources/clickedSquare.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;
            clickedFillImg = ImageIO.read(getClass().getResource("resources/clickedFill.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;

        } catch(Exception ex) {
            System.out.println(ex);
        }

        pointerButton.setIcon(new ImageIcon(pointerImg));
        eraserButton.setIcon(new ImageIcon(eraserImg));
        lineButton.setIcon(new ImageIcon(lineImg));
        circleButton.setIcon(new ImageIcon(circleImg));
        squareButton.setIcon(new ImageIcon(squareImg));
        fillButton.setIcon(new ImageIcon(fillImg));

        JButton[] toolButtonArray = new JButton[] {pointerButton, eraserButton, lineButton, circleButton, squareButton, fillButton};

        pointerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointerButton.setIcon(new ImageIcon(clickedCursorImg));
                setAllOtherButtonsToDefault(toolButtonArray, pointerButton);
            }
        });

        eraserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eraserButton.setIcon(new ImageIcon(clickedEraserImg));
                setAllOtherButtonsToDefault(toolButtonArray, eraserButton);
            }
        });

        lineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineButton.setIcon(new ImageIcon(clickedLineImg));
                setAllOtherButtonsToDefault(toolButtonArray, lineButton);
            }
        });

        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circleButton.setIcon(new ImageIcon(clickedCircleImg));
                setAllOtherButtonsToDefault(toolButtonArray, circleButton);
            }
        });

        squareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                squareButton.setIcon(new ImageIcon(clickedSquareImg));
                setAllOtherButtonsToDefault(toolButtonArray, squareButton);
            }

        });

        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fillButton.setIcon(new ImageIcon(clickedFillImg));
                setAllOtherButtonsToDefault(toolButtonArray, fillButton);
            }
        });

        this.add(pointerButton);
        this.add(eraserButton);
        this.add(lineButton);
        this.add(circleButton);
        this.add(squareButton);
        this.add(fillButton);

    }
}