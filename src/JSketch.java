import javax.imageio.ImageIO;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.peer.CanvasPeer;
import java.io.*;
import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


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

        JFileChooser fileChooser = new JFileChooser();

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
        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.shapes.clear();
                canvasPanel.repaint();
            }
        });

        // The load/save code was taken from "Filechoosers" example on Java Oracle doc
        // https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
        loadMenu = new JMenuItem("Load");
        loadMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File loadFile = fileChooser.getSelectedFile();
                    String extension = loadFile.getName().substring(loadFile.getName().lastIndexOf(".") + 1, loadFile.getName().length());

                    try {
                        FileInputStream fileInputStream = new FileInputStream(loadFile);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        ArrayList<CustomShape> loadedShapes = (ArrayList<CustomShape>) objectInputStream.readObject();

                        model.shapes = loadedShapes;
                        objectInputStream.close();
                    } catch (FileNotFoundException e1){
                        System.out.println("YOU SCREWED UP 1");
                    } catch (ClassNotFoundException e2) {
                        System.out.println("YOU SCREWED UP 2");
                    } catch (IOException e3) {
                        System.out.println("YOU SCREWED UP 3");
                    }
                }
                canvasPanel.repaint();
            }
        });

        saveMenu = new JMenuItem("Save");
        saveMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File savedfile = fileChooser.getSelectedFile();
                    String savedFilePath = savedfile.getAbsolutePath();
                    if (!savedFilePath.endsWith(".txt")) {
                        savedfile = new File(savedFilePath + ".txt");
                    }

                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(savedfile);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(model.shapes);
                        objectOutputStream.close();
                    } catch (FileNotFoundException e1) {
                        System.out.println("YOU SCREWED UP 1");
                    } catch (IOException e2) {
                        System.out.println("YOU SCREWED UP 2");
                        e2.printStackTrace();
                    }

                }
            }
        });


        fileMenu.add(newMenu);
        fileMenu.add(loadMenu);
        fileMenu.add(saveMenu);

        // create layout for left side
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        toolPanel = new ToolBarLayout(model); // create toolpanel

        colorPanel = new ColorLayout(model); // create color panle
        chooserPanel = new ChooserLayout(model); // create chooser panel
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
    ColorLayout colorLayout;
    Point startDrag, endDrag;

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

    private Line2D.Float dummyLineObject = new Line2D.Float(0,0,0,0);

    public void paintComponent(Graphics g) {
        // general structure of paintComponent, taken from Oracle Doc
        // https://docs.oracle.com/javase/tutorial/2d/geometry/primitives.html

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (CustomShape s: model.shapes) {
            if (s == model.selectedShape) {
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            } else {
                g2.setStroke(new BasicStroke(s.thickness));
            }

            if (!s.shape.getClass().equals(dummyLineObject.getClass())) {
                g2.setColor(s.lineColor);
                g2.fill(s.shape);
                g2.setColor(s.borderColor);
            } else {
                g2.setColor(s.lineColor);
            }
            g2.draw(s.shape);
        }

        if (isDrawingTool()) {
            if (startDrag != null && endDrag != null) {
                g2.setStroke(new BasicStroke(model.getLineThickness()));
                CustomShape cShape = createTheShape();
                if (model.getCurrentTool() == selectedTool.CIRCLE || model.getCurrentTool() == selectedTool.SQUARE) {
                    g2.setColor(model.getCurrentColor());
                    g2.fill(cShape.shape);
                    g2.setColor(cShape.borderColor);
                    g2.draw(cShape.shape);
                } else {
                    g2.setColor(model.getCurrentColor());
                    g2.draw(cShape.shape);
                }
            }
        }
    }

    public void eraseShape(int x, int y) {
        // LOOP BACKWARDS, so can select the "upper" layer first
        for (int i = model.shapes.size() - 1; i >= 0; i--) {
            if (model.shapes.get(i).shape.contains(x,y) || model.shapes.get(i).shape.intersects(x-4,y-4, 7, 7)) {
                model.shapes.remove(i);
                break;
            }
        }
    }

    public void fillShape(int x, int y) {
        // LOOP BACKWARDS, so can select the "upper" layer first
        for (int i = model.shapes.size() - 1; i >= 0; i--) {
            if (model.shapes.get(i).shape.contains(x,y) || model.shapes.get(i).shape.intersects(x-4,y-4, 7, 7)) {
                model.shapes.get(i).lineColor = model.getCurrentColor();
                break;
            }
        }
    }

    double preSetX;
    double preSetY;

    // dashed lines code was taken from Java Oracle doc
    // https://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
    public void selectShape(int x, int y) {
        for (int i = model.shapes.size() - 1; i >= 0; i--) {
            if (model.shapes.get(i).shape.contains(x, y) || model.shapes.get(i).shape.intersects(x - 4, y - 4, 7, 7)) {

                model.selectedShape = model.shapes.get(i);
                preSetX = model.selectedShape.shape.getBounds().getX() - x; // set preset x and y for dragging
                preSetY = model.selectedShape.shape.getBounds().getY() - y;


//                if (model.selectedShape.lineColor == Color.BLUE) {
//                    model.setCurrentColor(Color.BLUE);
//                    colorLayout.setBlueButtonToTrue();
//                    colorLayout.resetAllOtherColors();
//                }

            }
        }

    }

    // Affline transfomrations code taken from java oracle docs
    // https://docs.oracle.com/javase/7/docs/api/java/awt/geom/AffineTransform.html
    public void moveShape(int x, int y) {
        if (model.selectedShape != null) {
            double oldX = model.selectedShape.shape.getBounds().getX();
            double oldY = model.selectedShape.shape.getBounds().getY();

            AffineTransform newPosition = new AffineTransform();

            newPosition.translate(x - oldX + preSetX, y - oldY + preSetY);

            if (model.selectedShape.shape.getClass().equals(dummyLineObject.getClass())) {
                model.selectedShape.borderColor = model.selectedShape.lineColor;
            }
            model.selectedShape.shape = newPosition.createTransformedShape(model.selectedShape.shape);
        }
    }

    private CustomShape currentDrawingShape;

    public DrawingCanvas(Model m) {
        // Listern Code is based off of in class example
        model = m;
//        colorLayout = myColors;
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;

                if (model.getCurrentTool() == selectedTool.ERASER) {
                    eraseShape(e.getX(), e.getY());
                } else if (model.getCurrentTool() == selectedTool.SELECT) {
                    selectShape(e.getX(), e.getY());
                } else if (model.getCurrentTool() == selectedTool.FILL) {
                    fillShape(e.getX(), e.getY());
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (isDrawingTool()) {
                    currentDrawingShape = createTheShape();
                    model.shapes.add(currentDrawingShape);
                }
                startDrag = null;
                endDrag = null;
                repaint();
            }

        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                if (model.getCurrentTool() == selectedTool.SELECT && model.selectedShape != null) {
                    moveShape(e.getX(), e.getY());
                }

                repaint();
            }
        });

        // the following code to detect escape key using Keyboard focus manager is
        // taken from ORACLE docs.
        // https://docs.oracle.com/javase/tutorial/uiswing/misc/focus.html
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE && e.getID() == KeyEvent.KEY_PRESSED) {
                    System.out.println("ESCAPE KEY PRESSED");
                    model.selectedShape = null;
                    repaint();
                }
                return false;
            }
        });
    }
}

