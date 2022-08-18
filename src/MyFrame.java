import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyPanel p;
    MyFrame(int size) {
        p = new MyPanel(size);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));

        this.add(p);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
