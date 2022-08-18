import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Main {
    static MyPanel p;
    static MyFrame f;
    static int cellSize = 10;
    static int frameSize = 30;
    public static Cell[][] grid = new Cell[frameSize][frameSize];
    public static final int FATE = 100;
    public static void main(String[] args) {
        f = new MyFrame(frameSize*cellSize);
        p = f.p;

        for (int x = 0; x < p.getWidth()/cellSize; x++) {
            for (int y = 0; y < p.getWidth()/cellSize; y++) {
                DrawRect(x * cellSize, y * cellSize, cellSize, cellSize);
                grid[x][y] = new Cell(0, 0);
            }
        }



        grid[5][5].current = 1;
        grid[6][5].current = 1;
        grid[7][5].current = 1;
        grid[4][6].current = 1;
        grid[5][6].current = 1;
        grid[6][6].current = 1;
//        grid[1][0].current = 1;
//        grid[0][1].current = 1;
//        grid[1][1].current = 1;


        try {
            loop();
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static void loop() throws InterruptedException {
        while (true) {
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    //Draw them
                    if (grid[x][y].current == 1) FillRect(x*cellSize,y*cellSize,cellSize,cellSize); else DrawRect(x*cellSize,y*cellSize,cellSize,cellSize);

                    //Calculate Fate
                    grid[x][y] = calculateFate(getNeighbours(x, y), grid[x][y].current, x, y);
                }
            }
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    //Set fate to current
                    grid[x][y].current = grid[x][y].next;
                }
            }

            TimeUnit.SECONDS.sleep(1);
        }
    }
    public static int getNeighbours(int x, int y) {
        int neighbours = 0;
        if (x > 0 && y > 0)                                             neighbours += grid[x - 1][y - 1].current;
        if (y > 0)                                                      neighbours += grid[x    ][y - 1].current;
        if (y > 0 && x < frameSize - 1)                                 neighbours += grid[x + 1][y - 1].current;
        if (x > 0)                                                      neighbours += grid[x - 1][y    ].current;
        if (x < frameSize - 1)                                          neighbours += grid[x + 1][y    ].current;
        if (x > 0 && y < frameSize - 1)                                 neighbours += grid[x - 1][y + 1].current;
        if (y < frameSize - 1)                                          neighbours += grid[x    ][y + 1].current;
        if (y < frameSize - 1 && x < frameSize - 1)                     neighbours += grid[x + 1][y + 1].current;

        return neighbours;
    }

    public static Cell calculateFate(int ne, int state, int x, int y) {

        // NOTE: doing it with if statements is faster than switch-range
        if (ne == 3 && state == 0) System.out.println("HOLD UP " + x + "AND" + y);

//        Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        if (ne < 2 && state == 1) return new Cell(state,0);
//        Any live cell with two or three live neighbours lives on to the next generation.
        if (ne == 2 && state == 1 || ne == 3 && state == 1) return new Cell(state,1);
//        Any live cell with more than three live neighbours dies, as if by overpopulation.
        if (ne > 3 && state == 1) return new Cell(state,0);
//        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        if (ne == 3 && state == 0) return new Cell(state,1);
        return new Cell(state,0);
    }
    public static void FillRect(int x, int y, int w, int h) {
        p.frxPositions.add(x);
        p.fryPositions.add(y);
        p.frwidths.add(w);
        p.frheights.add(h);
        for (int i = 0; i < p.rxPositions.size(); i++) {
            try {
                if (p.rxPositions.get(i) == x && p.fryPositions.get(i) == y) {
                    p.rxPositions.remove(i);
                    p.ryPositions.remove(i);
                    p.rwidths.remove(i);
                    p.rheights.remove(i);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds");
            }
        }
        p.repaint();
    }
    public static void DrawRect(int x, int y, int w, int h) {
        p.rxPositions.add(x);
        p.ryPositions.add(y);
        p.rwidths.add(w);
        p.rheights.add(h);
        for (int i = 0; i < p.frxPositions.size(); i++) {
            if (p.frxPositions.get(i) == x && p.fryPositions.get(i) == y) {
                p.frxPositions.remove(i);
                p.fryPositions.remove(i);
                p.frwidths.remove(i);
                p.frheights.remove(i);
            }
        }
        p.repaint();
    }
}
