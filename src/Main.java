import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Main {
    static MyPanel p;
    static MyFrame f;
    public static void main(String[] args) {
        f = new MyFrame();
        p = f.p;
        f.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                p.xPositions.add(p.getMousePosition().x);
                p.yPositions.add(p.getMousePosition().y);
                p.widths.add(10);
                p.heights.add(10);
                p.repaint();
                System.out.println(p.xPositions);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
