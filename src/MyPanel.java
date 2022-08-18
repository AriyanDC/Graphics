import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyPanel() {
        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g3D) {
        Graphics2D g = (Graphics2D) g3D;
        g.drawLine(0,0,500,500);
    }
}
