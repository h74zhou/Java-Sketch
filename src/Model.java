import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.ArrayList;

// this is meant to represent the model (also acts as a storage system for all objects)

enum selectedTool {
    SELECT, ERASER, LINE, CIRCLE, SQUARE, FILL
}

class CustomShape implements Serializable {
    Shape shape;
    Color lineColor = Color.BLUE;
    Color borderColor = Color.BLACK;
    Integer thickness = 5;

    CustomShape(Shape newShape, Color newColor, Integer newThickness) {
        shape = newShape;
        lineColor = newColor;
        thickness = newThickness;
    };
}

public class Model {
    private selectedTool currentTool = selectedTool.SELECT;
    private Color currentColor = Color.BLUE;
    private Integer currentLine = 5;
    private CustomShape drawingShape = null; // null for now, change later
    public CustomShape selectedShape = null;

    public ArrayList<CustomShape> shapes = new ArrayList<CustomShape>();

    public int getLineThickness() {
        return currentLine;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public selectedTool getCurrentTool() {
        return currentTool;
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