import javax.swing.*;
import java.awt.*;

class ChooserLayout extends JPanel {
    public ChooserLayout() {
        this.setLayout(new GridLayout(1,1));
        JButton chooserButton = new JButton("Chooser");
        this.add(chooserButton);
    }
}
