import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Line extends JPanel {

    private Point O1 = new Point();
    private Point O2 = new Point();
    private Color color;
    private boolean VISION = true;
    private int interfaceWidth = 1000, interfaceHeight = 500;


    public Line (int x1, int y1, int x2, int y2, Color c) {
        setLayout(null);
        setOpaque(false);
        O1.setXY(x1,y1);
        O2.setXY(x2,y2);
        color = c;
        System.out.println("Объект линия(Line) создан");
    }

    public Line (int x1, int y1, int x2, int y2) {
        setLayout(null);
        setOpaque(false);
        O1.setXY(x1,y1);
        O2.setXY(x2,y2);
        color = Color.BLACK;
    }

    public void MoveTo(int dx, int dy) {
        int newx1 = O1.getX()+dx, newx2 = O2.getX()+dx;
        int newy1 = O1.getY()+dx, newy2 = O2.getY()+dx;
        int x1 = O1.getX(), x2 = O2.getX();
        int y1 = O1.getY(), y2 = O2.getY();
        if (newx1 > 0 && newx2 > 0 && newx1 < interfaceWidth && newx2 < interfaceWidth && newy1 > 0 && newy2 > 0 && newy1 < interfaceHeight && newy2 < interfaceHeight) {
            x1 = newx1;
            y1 = newy1;
            x2 = newx2;
            y2 = newy2;
        } else {
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;
            Random random = new Random();
            x1 = random.nextInt(interfaceWidth - deltaX);
            y1 = random.nextInt(interfaceHeight - deltaY);
            x2 = x1 + deltaX;
            y2 = y1 + deltaY;
        }
        System.out.println("Координаты линии: (" + x1 + ", " + y1 + ") - (" + x2 + ", " + y2 + ")");
        O1.setXY(x1,y1);
        O2.setXY(x2,y2);
    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    public void Turn() {
        double c1, c2;
        c1 = (O2.getX() - O1.getX()) * Math.cos(Math.toRadians(45.0)) - (O2.getY() - O1.getY()) * Math.sin(Math.toRadians(45.0)) + O1.getX();
        c2 = (O2.getX() - O1.getX()) * Math.sin(Math.toRadians(45.0)) + (O2.getY() - O1.getY()) * Math.cos(Math.toRadians(45.0)) + O1.getY();
        if (c1 >= interfaceWidth || c2 >= interfaceHeight || c1 <= 0 || c2 <= 0) {
            c1 = (O1.getX() - O2.getX()) * Math.cos(Math.toRadians(45.0)) - (O1.getY() - O2.getY()) * Math.sin(Math.toRadians(45.0)) + O2.getX();
            c2 = (O1.getX() - O2.getX()) * Math.sin(Math.toRadians(45.0)) + (O1.getY() - O2.getY()) * Math.cos(Math.toRadians(45.0)) + O2.getY();
            JOptionPane.showMessageDialog(null, "Turn the other way", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        O2.setXY((int)c1,(int)c2);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawLine(O1.getX(), O1.getY(), O2.getX(), O2.getY());
        }
    }
}