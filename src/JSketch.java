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

    // array of all the shapes
    ArrayList<CustomShape> shapes = new ArrayList<CustomShape>();

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Ellipse2D.Float makeCircle(int x1, int y1, int x2, int y2) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);

        int radius = Math.min(maxX - minX, maxY - minY);

        if (minX < x1) {
            minX = x1 - radius;
        }

        if (minY < y1) {
            minY = y1 - radius;
        }

        return new Ellipse2D.Float(minX, minY, radius, radius);
    }

    private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private CustomShape createTheShape() {
        if (model.getCurrentTool() == selectedTool.SQUARE) {
            return new CustomShape(makeRectangle(startDrag.x,startDrag.y,endDrag.x,endDrag.y), model.getCurrentColor(), model.getLineThickness());
        } else if (model.getCurrentTool() == selectedTool.CIRCLE) {
            return new CustomShape(makeCircle(startDrag.x,startDrag.y,endDrag.x,endDrag.y), model.getCurrentColor(), model.getLineThickness());
        } /*else if (model.getCurrentTool() == selectedTool.LINE)*/ else {
            return new CustomShape(makeLine(startDrag.x,startDrag.y,endDrag.x,endDrag.y), model.getCurrentColor(), model.getLineThickness());
        }
    }

    private Boolean isDrawingTool() {
        if (model.getCurrentTool() == selectedTool.LINE ||
                model.getCurrentTool() == selectedTool.CIRCLE ||
                model.getCurrentTool() == selectedTool.SQUARE) {
            return true;
        } return false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (CustomShape s: shapes) {
            g2.setStroke(new BasicStroke(s.thickness));
            g2.setPaint(s.lineColor);
            g2.draw(s.shape);
        }

        if (isDrawingTool()) {
            if (startDrag != null && endDrag != null) {
                g2.setPaint(model.getCurrentColor());
                g2.setStroke(new BasicStroke(model.getLineThickness()));
                CustomShape cShape = createTheShape();
                g2.draw(cShape.shape);
            }
        }
    }

    public void eraseShape(int x, int y) {
        for (int i = shapes.size(); i > 0; --i) {
            if (shapes.get(i-1).shape.contains(x,y) || shapes.get(i-1).shape.intersects(x - 2, y - 2, 5, 5)) {
                shapes.remove(i-1); // removes starting at (2,2) off from origin with a 5 by 5 box
                break;
            }
        }
    }

    private CustomShape currentDrawingShape;

    public DrawingCanvas(Model m) {
        model = m;

        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;

                if (model.getCurrentTool() == selectedTool.ERASER) {
                    eraseShape(e.getX(), e.getY());
                }

                repaint();
            }

            public void mouseReleased(MouseEvent e) {

                if (isDrawingTool()) {
                    currentDrawingShape = createTheShape();
                    shapes.add(currentDrawingShape);
                }
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

