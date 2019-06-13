import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;


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

    public JSketch() {
        // Initialize Model
        Model model = new Model();

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

        toolPanel = new ToolBarLayout(model); // create toolpanel
        colorPanel = new ColorLayout(model); // create color panle
        chooserPanel = new ChooserLayout(); // create chooser panel
        linePanel = new LineLayout(model);

        // add toolpanel to left panel
        leftPanel.add(toolPanel);
        leftPanel.add(colorPanel);
        leftPanel.add(chooserPanel);
        leftPanel.add(linePanel);

        // add canvas
        canvasPanel = new DrawingCanvas(model);

        mainFrame.add(leftPanel, BorderLayout.WEST);
        mainFrame.add(canvasPanel, BorderLayout.CENTER);

        // set size and view properties
        mainFrame.setSize(900,600);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

}

class DrawingCanvas extends JPanel {
    Model model;

    Point startDrag, endDrag;

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        paintAllShapes(g2);
//        shapeModel.drawTheShape(g2);

        for (CustomShape s: shapes) {
            g2.setStroke(new BasicStroke(s.thickness));
            g2.setPaint(s.lineColor);
            g2.draw(s.shape);
        }

        if (startDrag != null && endDrag != null) {
            g2.setPaint(model.getCurrentColor());
            g2.setStroke(new BasicStroke(model.getLineThickness()));
            CustomShape cShape = new CustomShape(makeRectangle(startDrag.x,startDrag.y,endDrag.x,endDrag.y), model.getCurrentColor(), model.getLineThickness());
//            Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            g2.draw(cShape.shape);
        }

    }

    // array of all the shapes
    ArrayList<CustomShape> shapes = new ArrayList<CustomShape>();

    private CustomShape currentDrawingShape;

    public DrawingCanvas(Model m) {
        model = m;

        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;


                System.out.println("hello");
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                currentDrawingShape = new CustomShape(makeRectangle(startDrag.x,startDrag.y,e.getX(),e.getY()), model.getCurrentColor(), model.getLineThickness());
//                Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                shapes.add(currentDrawingShape);
                startDrag = null;
                endDrag = null;
                repaint();
            }

        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }
}

