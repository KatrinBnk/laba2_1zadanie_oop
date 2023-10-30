import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {

    private Point O = new Point();
    private int w, h;
    private int interfaceWidth = 1000, interfaceHeight = 500;
    private Color color;
    private boolean VISION = true;

    public Rectangle (int x, int y, int w, int h, Color c) {
        setLayout(null);
        setOpaque(false);
        System.out.println("Координаты центра: x=" + x + ", y=" + y);
        System.out.println("Начальные ширина/высота: " + w + "; " + h);
        if ((x+w) >= 1000) { w = 1000 - x;}
        if ((y+h) >= 500) { h = 500 - y;}
        System.out.println("Ширина/высота: " + w + "; " + h);
        O.setXY(x,y);
        this.w = w;
        this.h = h;
        color = c;
        System.out.println("Объект Rectangle создан");
    }

    public Rectangle (int x, int y, int w, int h) {
        setLayout(null);
        setOpaque(false);
        O.setXY(x,y);
        this.w = w;
        this.h = h;
        color= Color.BLACK;
    }

    public void MoveTo(int dx, int dy) {
        int testx = O.getX() + dx, testy = O.getY() + dy;
        int x = O.getX(), y = O.getY();
        if (testx > 0 && testy > 0 && testx < interfaceWidth && testy < interfaceHeight &&testx +w > 0 && testy + h > 0 && testx+w < interfaceWidth && testy+h < interfaceHeight ){
            x += dx;
            y += dy;
            O.setXY(x+dx, y+dy);
        } else {
            do {
                x = (int) (Math.random() * 400 + 1);
                y = (int) (Math.random() * 400 + 1);
            } while (x + w > interfaceWidth || y + h > interfaceHeight);
            O.setXY(x, y);
        }
    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    public void chSize(int dw, int dh) {
        int testw = w, testh = h;
        testw += dw;
        testh+= dh;
        if (testw > 0 && testh > 0 && testw < interfaceWidth && testh < interfaceHeight &&testw +w > 0 && testh + h > 0 && testw+w < interfaceWidth && testh+h < interfaceHeight ){
            this.w += dw;
            this.h += dh;
        } else {
            do {
                this.w = (int) (Math.random() * 400 + 1);
                this.h = (int) (Math.random() * 400 + 1);
            } while (O.getX() + w > interfaceWidth || O.getY() + h > interfaceHeight);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawRect(O.getX(), O.getY(), w, h);
        }
    }
}