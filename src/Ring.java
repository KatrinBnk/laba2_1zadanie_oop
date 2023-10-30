import javax.swing.*;
import java.awt.*;

public class Ring extends JPanel {

    private Point O = new Point();
    private int D;
    private Color color;
    private Circle One, Two;
    private boolean VISION = true;
    private int interfaceWidth = 1000, interfaceHeight = 500;


    public Ring (int x, int y, int r, Color c) {
        System.out.println("\nвызов конструктора 1\n");
        setLayout(null);
        setOpaque(false);
        if ((x+r) >= 1100) { r = 1000 - x;}
        if ((y+r) >= 400) { r = 300 - y;}
        if (r > x) { r = x; }
        if (r >= y) { r = y; }
        One = new Circle(x,y,r, c);
        Two = new Circle(x,y,r*2/3, c);
        D = r*2;
        O.setXY(x,y);
        this.VISION = true;
        System.out.println("Объект Ring создан");
    }

    public Ring (int x, int y, int r) {
        setLayout(null);
        setOpaque(false);
        One = new Circle(x,y,r, Color.BLACK);
        Two = new Circle(x,y,r*2/3, Color.BLACK);
        O.setXY(x,y); D=r*2;
        this.VISION = true;
        System.out.println("Объект Ring создан");
    }

    public void MoveTo(int dx, int dy) {
        int testx = O.getX() + dx, testy = O.getY() + dy;
        int x, y;

        if (testx - D >= 0 && testx + D <= interfaceWidth && testy - D >= 0 && testy + D <= interfaceHeight) {
            x = testx;
            y = testy;
            O.setXY(x+dx, y+dy);
        }
        else {
            do {
                x = (int) (Math.random() * (interfaceWidth - 2 * D) + D);
                y = (int) (Math.random() * (interfaceHeight - 2 * D) + D);
            } while (x + D > interfaceWidth || y + D > interfaceHeight);
            O.setXY(x, y);

        }
    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.repaint();
    }

    public void chengeD(int r) {
        int Diam = r *2;
        int x = O.getX(), y = O.getY();
        if (x - Diam >= 0 && x + Diam <= interfaceWidth && y - Diam >= 0 && y + Diam <= interfaceHeight){
            D = Diam;
        } else {
            D = 10;
            r = 5;
        }
        System.out.println("Новый радиус: " + r + ", Координаты: x=" + O.getX() + ", y=" + O.getY());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawOval(O.getX()-D/2, O.getY()-D/2, D, D);
            g.drawOval(O.getX()-D/4, O.getY()-D/4, D/2, D/2);

        }
    }
}
