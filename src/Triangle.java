import javax.swing.*;
import java.awt.*;

public class Triangle extends JPanel {

    private Point A = new Point();
    private Point B = new Point();
    private Point C = new Point();
    private Color color;
    private boolean VISION = true;
    private int interfaceWidth = 1000, interfaceHeight = 500;
    private  int[] coordinates; //координаты точек треугольник: первые три по оси ох, оставшиеся три по оу




    public Triangle (int x1, int x2, int x3, int y1, int y2, int y3, Color c) {
        setLayout(null);
        setOpaque(false);
        A.setXY(x1,y1);
        B.setXY(x2,y2);
        C.setXY(x3,y3);
        color = c;
        this.coordinates = new int[] {A.getX(), B.getX(), C.getX(), A.getY(), B.getY(), C.getY()};

    }

    public Triangle (int x1, int x2, int x3, int y1, int y2, int y3) {
        setLayout(null);
        setOpaque(false);
        A.setXY(x1,y1);
        B.setXY(x2,y2);
        C.setXY(x3,y3);
        color= Color.BLACK;
        this.coordinates = new int[] {A.getX(), B.getX(), C.getX(), A.getY(), B.getY(), C.getY()};
    }

    public void MoveTo(int d1, int d2) {

        if (check(d1,d2)){
            for (int i =0; i < 6; i++){
                if ( i < 3){
                    this.coordinates[i] += d1;
                } else{
                    this.coordinates[i] += d2;
                }
            }

        } else{
            do {
                d1 = (int) (Math.random() * 600 - 100);
                d2 = (int) (Math.random() * 600 - 100);
            } while (!check(d1,d2));

            for (int i =0; i < 6; i++){
                if ( i < 3){
                    this.coordinates[i] += d1;
                } else{
                    this.coordinates[i] += d2;
                }
            }
        }

        A.setXY(coordinates[0], coordinates[3]);
        B.setXY(coordinates[1], coordinates[4]);
        C.setXY(coordinates[2], coordinates[5]);


    }

    private boolean check(int d1, int d2) {
        int[] tests = new int[6];
        boolean f = true;
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                tests[i] = coordinates[i] + d1;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceWidth)) {
                    f = false;
                    return f;
                }
            } else {
                tests[i] = coordinates[i] + d2;
                if (f & (tests[i] <= 0 || tests[i] >= interfaceHeight)) {
                    f = false;
                    return f;
                }
            }
        }
        return f;
    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        setVisible(this.VISION);
        this.VISION = true;
        this.repaint();
    }

    public void rotate() {
        double centerX = (coordinates[0] + coordinates[1] + coordinates[2]) / 3.0;
        double centerY = (coordinates[3] + coordinates[4] + coordinates[5]) / 3.0;
        double angle = Math.toRadians(45); // Угол поворота в радианах (45 градусов)

        // Создаем временный массив для хранения новых координат точек
        int[] newCoordinates = new int[6];

        for (int i = 0; i < 3; i++) {
            double x = coordinates[i] - centerX;
            double y = coordinates[i + 3] - centerY;

            // Поворачиваем точку вокруг центра треугольника
            double newX = x * Math.cos(angle) - y * Math.sin(angle);
            double newY = x * Math.sin(angle) + y * Math.cos(angle);

            newCoordinates[i] = (int) (newX + centerX);
            newCoordinates[i + 3] = (int) (newY + centerY);
        }

        // Проверяем, не выходят ли новые координаты за пределы интерфейса
        if (checkBounds(newCoordinates)) {
            // Если новые координаты не выходят за пределы, обновляем текущие координаты
            this.coordinates = newCoordinates;
        } else {
            JOptionPane.showMessageDialog(null, "Невозможно повернуть. Выходит за пределы холста.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        A.setXY(coordinates[0], coordinates[3]);
        B.setXY(coordinates[1], coordinates[4]);
        C.setXY(coordinates[2], coordinates[5]);
    }

    private boolean checkBounds(int[] newCoordinates) {
        for (int i = 0; i < 6; i++) {
            if (i < 3 && (newCoordinates[i] < 0 || newCoordinates[i] >= interfaceWidth)) {
                return false;
            } else if (i >= 3 && (newCoordinates[i] < 0 || newCoordinates[i] >= interfaceHeight)) {
                return false;
            }
        }
        return true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawPolygon(new int[] {A.getX(),B.getX(),C.getX()}, new int[] {A.getY(),B.getY(),C.getY()}, 3);
        }
    }

}