import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    public ArrayList<Integer> xPositions = new ArrayList<Integer>();
    public ArrayList<Integer> yPositions = new ArrayList<Integer>();
    public ArrayList<Integer> widths = new ArrayList<Integer>();
    public ArrayList<Integer> heights = new ArrayList<Integer>();
    MyPanel() {
        this.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g3D) {
        Graphics2D g = (Graphics2D) g3D;
        for (int i = 0; i < xPositions.size(); i++) {
            g.fillRect(xPositions.get(i),yPositions.get(i),widths.get(i),heights.get(i));
        }
    }

}
