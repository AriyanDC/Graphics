import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    public ArrayList<Integer> frxPositions = new ArrayList<Integer>();
    public ArrayList<Integer> fryPositions = new ArrayList<Integer>();
    public ArrayList<Integer> frwidths = new ArrayList<Integer>();
    public ArrayList<Integer> frheights = new ArrayList<Integer>();
    public ArrayList<Integer> rxPositions = new ArrayList<Integer>();
    public ArrayList<Integer> ryPositions = new ArrayList<Integer>();
    public ArrayList<Integer> rwidths = new ArrayList<Integer>();
    public ArrayList<Integer> rheights = new ArrayList<Integer>();
    MyPanel(int size) {
        this.setPreferredSize(new Dimension(size,size));
    }

    public void paint(Graphics g3D) {
        Graphics2D g = (Graphics2D) g3D;
        for (int i = 0; i < rxPositions.size(); i++) {
            Paint prevPaint = g.getPaint();
            g.setPaint(Color.white);
            g.fillRect(rxPositions.get(i),ryPositions.get(i),rwidths.get(i),rheights.get(i));
            g.setPaint(prevPaint);
            g.drawRect(rxPositions.get(i),ryPositions.get(i),rwidths.get(i),rheights.get(i));
        }
        for (int i = 0; i < frxPositions.size(); i++) {
            g.setPaint(Color.black);
            g.fillRect(frxPositions.get(i),fryPositions.get(i),frwidths.get(i),frheights.get(i));
        }
    }

}
