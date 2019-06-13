import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// this is meant to represent the model (also acts as a storage system for all objects)

enum selectedTool {
    SELECT, ERASER, LINE, CIRCLE, SQUARE, FILL
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
    private Color currentColor = Color.BLUE;
    private Integer currentLine = 1;
    private CustomShape drawingShape = null; // null for now, change later
    private CustomShape selectedShape = null;

    public int getLineThickness() {
        if (currentLine == 1) {
            return 1;
        } else if (currentLine == 5) {
            return 5;
        } else {
            return 10;
        }
    }

    public Color getCurrentColor() {
        if (currentColor == Color.BLUE) {
            return Color.BLUE;
        } else if (currentColor == Color.RED) {
            return Color.RED;
        } else if (currentColor == Color.ORANGE) {
            return Color.ORANGE;
        } else if (currentColor == Color.YELLOW) {
            return Color.YELLOW;
        } else if (currentColor == Color.GREEN) {
            return Color.GREEN;
        } else if (currentColor == Color.PINK) {
            return Color.PINK;
        } else {
            return Color.BLACK;
        }
    }

    public void setCurrentColor(Color mycolor) {
        currentColor = mycolor;
    }

    public void setCurrentTool(selectedTool myTool) {
        currentTool = myTool;
    }

    public void setCurrentLine(Integer myLine) {
        currentLine = myLine;
    }
}