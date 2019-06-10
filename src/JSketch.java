import javax.swing.*;
import java.awt.BorderLayout;


public class JSketch {
    private JFrame mainFrame;
    private JMenu fileMenu;
    private JMenuItem newMenu, loadMenu, saveMenu;
    private JMenuBar menuBar;

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

        // set size and view properties
        mainFrame.setSize(900,600);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }

}

//class LayoutDemoFrame extends JFrame {
//    private static int xPos = 10;
//    private static int yPos = 10;
//    private static final int OFFSET = 50;
//    private JMenuBar menuBar;
//    public LayoutDemoFrame(String title, JPanel contents) {
//        super(title);
//        this.setContentPane(contents);
//        this.setSize(1200, 900);
//        // set this to keep the JFrame from shrinking too small
//        //this.setMinimumSize(new Dimension(150,  100));
//        this.setLocation(xPos, yPos);
//        xPos += OFFSET;
//        yPos += OFFSET;
//        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        this.setResizable(false);
//        this.setJMenuBar(menuBar);
//    }
//}

//class DemoBorderLayout extends JPanel {
//    public DemoBorderLayout() {
//        this.setBackground(Color.DARK_GRAY);
//        // use BorderLayout
//        this.setLayout(new BorderLayout());
//        // Add the components
//
////        this.add(new JMenu("ToolBar"), BorderLayout.NORTH);
////        this.add(new JButton("North"), BorderLayout.NORTH);
////        this.add(new JButton("East"), BorderLayout.EAST);
////        this.add(new JButton("West"), BorderLayout.WEST);
//
//        // Layouts can be nested ...
//
//        // Box is an easy-to-create JPanel with a BoxLayout
////        Box b = Box.createHorizontalBox();
////        b.add(Box.createHorizontalGlue());
////        b.add(new JButton("Ok"));
////        b.add(Box.createHorizontalStrut(20));
////        b.add(new JButton("Cancel"));
//
////        this.add(b, BorderLayout.SOUTH);
//
//        // nesting a previous demo panel ...
////        this.add(new DemoBoxLayout1(), BorderLayout.CENTER);
//    }
//}