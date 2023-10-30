import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {

    private Point O = new Point();
    private int D;
    private Color color;
    private boolean VISION = true;
    private int interfaceWidth = 1000, interfaceHeight = 500;


    public Circle (int x, int y, int r, Color color) {
        setLayout(null);
        setOpaque(false);
        System.out.println("Координаты центра: x=" + x + ", y=" + y);
        System.out.print("Начальный радиус: " + r + "; ");
        if ((x+r) >= 1200) { r = 1200 - x;}
        if ((y+r) >= 500) { r = 500 - y;}
        if (r>x) { r = x; }
        if (r>=y) { r= y; }
        System.out.println("Итоговый радиус: " + r);
        O.setXY(x,y);
        this.D = r*2;
        this.color = color;
        this.VISION = true;
        System.out.println("Объект Circle создан");
    }

    public Circle (int x, int y, int r) {
        setLayout(null);
        setOpaque(false);
        O.setXY(x,y); this.D = r*2; this.color= Color.BLACK;
        this.VISION = true;
        System.out.println("Объект Circle создан");
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
        }
        System.out.println("Новый радиус: " + r + ", Координаты: x=" + O.getX() + ", y=" + O.getY());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawOval(O.getX()-D/2, O.getY()-D/2, D, D);
        }
    }
}