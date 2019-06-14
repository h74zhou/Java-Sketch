import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChooserLayout extends JPanel {
    private JButton chooserButton;

    Model model;

    public ChooserLayout(Model m) {
        model = m;
        this.setLayout(new GridLayout(1,1));
        chooserButton = new JButton("Chooser");
        chooserButton.setForeground(model.getCurrentColor());
        this.add(chooserButton);

        chooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color oldColor = model.getCurrentColor();
                Color newColor = JColorChooser.showDialog(null, "Pick a Color", oldColor);

                if (newColor == null) {
                    System.out.println("COLOR NOT WORKING");
                    model.setCurrentColor(oldColor);
                } else {
                    chooserButton.setForeground(newColor);
                    model.setCurrentColor(newColor);
                }
            }
        });
    }
}
