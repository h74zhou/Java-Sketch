import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// this is meant to represent the model (also acts as a storage system for all objects)

enum selectedTool {
    SELECT, ERASE, LINE, CIRCLE, RECTANGLE, FILL
}

enum selectedColor {
    BLUE, RED, ORANGE, YELLOW, GREEN, PINK
}

enum selectedLine {
    THIN, MEDIUM, THICK
}

class CustomShape {
    Shape shape;
    Color lineColor = Color.BLUE;
    Color fillColor = null;
    Integer thickness = 1;

    CustomShape(Shape newShape, Color newColor, Integer newThickness) {
        shape = newShape;
        lineColor = newColor;
        thickness = newThickness;
    };
}

public class Model {
    private selectedTool currentTool = selectedTool.SELECT;
    private selectedColor currentColor = selectedColor.BLUE;
    private selectedLine currentLine = selectedLine.THIN;
    private CustomShape drawingShape = null; // null for now, change later
    private CustomShape selectedShape = null;

    public int getLineThickness() {
        if (currentLine == selectedLine.THIN) {
            return 1;
        } else if (currentLine == selectedLine.MEDIUM) {
            return 3;
        } else {
            return 5;
        }
    }

    public Color getCurrentColor() {
        if (currentColor == selectedColor.BLUE) {
            return Color.BLUE;
        } else if (currentColor == selectedColor.RED) {
            return Color.RED;
        } else if (currentColor == selectedColor.ORANGE) {
            return Color.ORANGE;
        } else if (currentColor == selectedColor.YELLOW) {
            return Color.YELLOW;
        } else if (currentColor == selectedColor.GREEN) {
            return Color.GREEN;
        } else if (currentColor == selectedColor.PINK) {
            return Color.PINK;
        } else {
            return Color.BLACK;
        }
    }
}

//public class Model {
//
//    private tool currentTool = tool.SELECT;
//    private Integer currentThickness = 0;
//    private Color currentColor = Color.BLACK;
//    private CustomShape currentDrawingShape = null;
//    private CustomShape currentSelectedShape = null;
//
//    private ArrayList<CustomShape> shapeList = new ArrayList<CustomShape>();
//
//    public tool getCurrentTool() {
//        return currentTool;
//    }
//
//    public Color getCurrentColor() {
//        return currentColor;
//    }
//
//    public Integer getCurrentThickness() {
//        return currentThickness;
//    }
//
//    public CustomShape getDrawingShape() {
//        return currentDrawingShape;
//    }
//
//    public CustomShape getSelectedShape() {
//        return currentSelectedShape;
//    }
//
//    public ArrayList<CustomShape> getShapeList() {
//        return shapeList;
//    }
//
//    public void addShapeToQueue() {
//        shapeList.add(currentDrawingShape);
//        currentDrawingShape = null;
//    }
//
////    public void drawTheShape(Graphics2D g2) {
////        int x = Math.min(x1, x2);
////        int y = Math.min(y1, y2);
////        int width = Math.abs(x1 - x2);
////        int height = Math.abs(y1 - y2);
////        currentDrawingShape = new CustomShape(new Rectangle2D.Float(x,y,width,height), Color.green, 1);
////        g2.setColor(Color.BLACK);
////
////    }
//
//
//
//
//}