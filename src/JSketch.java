import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;


public class JSketch {
    private JFrame mainFrame;
    private JMenu fileMenu;
    private JMenuItem newMenu, loadMenu, saveMenu;
    private JMenuBar menuBar;
    private JPanel leftPanel, toolPanel, colorPanel, linePanel, chooserPanel, canvasPanel;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        JSketch program = new JSketch();
    }

    JSketch() {
        // create program
        mainFrame = new JFrame("J-Sketch");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // add menu bar
        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        // add file menu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // add each file item
        newMenu = new JMenuItem("New");
        loadMenu = new JMenuItem("Load");
        saveMenu = new JMenuItem("Save");
        fileMenu.add(newMenu);
        fileMenu.add(loadMenu);
        fileMenu.add(saveMenu);

        // create layout for left side
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        toolPanel = new ToolBarLayout(); // create toolpanel
        colorPanel = new ColorLayout(); // create color panle
        chooserPanel = new ChooserLayout(); // create chooser panel
        linePanel = new LineLayout();

        // add toolpanel to left panel
        leftPanel.add(toolPanel);
        leftPanel.add(colorPanel);
        leftPanel.add(chooserPanel);
        leftPanel.add(linePanel);

        // add canvas
        canvasPanel = new DrawingCanvas();

        mainFrame.add(leftPanel, BorderLayout.WEST);
        mainFrame.add(canvasPanel, BorderLayout.CENTER);

        // set size and view properties
        mainFrame.setSize(900,600);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

}

class DrawingCanvas extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;

    public DrawingCanvas() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
    }

}

class ToolBarLayout extends JPanel {
    public ToolBarLayout() {
        this.setLayout(new GridLayout(3, 2));

        // Add toolbar button icons
        JButton pointerButton = new JButton();
        JButton eraserButton = new JButton();
        JButton lineButton = new JButton();
        JButton circleButton = new JButton();
        JButton squareButton = new JButton();
        JButton fillButton = new JButton();

        try {
            // Get Pointer Image
            Image pointerImg = ImageIO.read(getClass().getResource("resources/pointer.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;
            pointerButton.setIcon(new ImageIcon(pointerImg));

            // Get Eraser Image
            Image eraserImg = ImageIO.read(getClass().getResource("resources/eraser.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;;
            eraserButton.setIcon(new ImageIcon(eraserImg));

            // Get Line Image
            Image lineImg = ImageIO.read(getClass().getResource("resources/line.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;;
            lineButton.setIcon(new ImageIcon(lineImg));

            // Get Circle Image
            Image circleImg = ImageIO.read(getClass().getResource("resources/circle.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;;
            circleButton.setIcon(new ImageIcon(circleImg));

            // Get Square Image
            Image sqaureImg = ImageIO.read(getClass().getResource("resources/square.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;;
            squareButton.setIcon(new ImageIcon(sqaureImg));

            // Get Fill Image
            Image fillImg = ImageIO.read(getClass().getResource("resources/fill.png")).getScaledInstance(50,50, Image.SCALE_DEFAULT);;;
            fillButton.setIcon(new ImageIcon(fillImg));

        } catch(Exception ex) {
            System.out.println(ex);
        }

        this.add(pointerButton);
        this.add(eraserButton);
        this.add(lineButton);
        this.add(circleButton);
        this.add(squareButton);
        this.add(fillButton);
    }
}

class ColorLayout extends JPanel {
    public ColorLayout() {
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

        for (int i = 0; i < colorArray.length; ++i) {
            colorArray[i].setSize(50, 50);
            colorArray[i].setOpaque(true);
            colorArray[i].setBorderPainted(false);
            this.add(colorArray[i]);
        }
    }
}

class ChooserLayout extends JPanel {
    public ChooserLayout() {
        this.setLayout(new GridLayout(1,1));
        JButton chooserButton = new JButton("Chooser");
        this.add(chooserButton);
    }
}

class LineLayout extends JPanel {
    public LineLayout() {
        this.setLayout(new GridLayout(3, 1));
        JButton lineThick1 = new JButton("Thin Lines");
        JButton lineThick2 = new JButton("Regular Lines");
        JButton lineThick3 = new JButton("Thick Lines");
        JButton[] lines = new JButton[] {lineThick1, lineThick2, lineThick3};


        for (int i = 0; i < lines.length; ++i) {
            this.add(lines[i]);
        }
    }
}