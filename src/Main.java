import javax.swing.*;
import java.awt.*;

public  class Main{
    private JFrame fNL = new JFrame("Лабораторная работа №1");

    private JPanel cP = centerPanel();
    private JPanel sP;

    // список всех кнопок, необходимых для интерфейса
    private JButton[] buttons;
    private int interfaceWight = 1000, interfaceHight = 500;
    //ключ-код активной кнопки
    private int buttonKey = 0;

    //для работы с объектами
    private JPanel line = null;
    private Line [] lines = null;
    private JPanel circle = null;
    private Circle [] circles = null;
    private JPanel ring = null;
    private Ring [] rings = null;
    private JPanel rectangle = null;
    private Rectangle [] rectangles = null;
    private JPanel triangle = null;
    private Triangle[] triangles = null;

    private  int[] numbers;
    private boolean vision, visionRect, visionCircle, visionTriangle, visionRing;
    private int a,b,c,d,e =0;
    private int cntCircles = 10, cntTriangles = 10, cntRects = 10, cntLines = 10, cntRings = 10;
    private  int cntErors = 0;


    private Main() {
        buttons = new JButton[]{
                new JButton("Назад"), //0
                new JButton("Линия"), //1
                new JButton("Окружность"), //2
                new JButton("Прямоугольник"), //3
                new JButton("Треугольник"), //4
                new JButton("Создать"), //5
                new JButton("Переместить"), //6
                new JButton("Удалить"), //7
                new JButton("Повернуть на 45"), //8
                new JButton("Изменить радиус"), //9
                new JButton("Изменить размер"), //10
                new JButton("Сделать видимым/невидимым"), //11
                new JButton("Массив"), //12
                new JButton("Кольцо") //13
        };
        sP = southPanel();

        //создаем основное окно
        fNL.setLayout(new BorderLayout());
        fNL.setSize(1200,700);
        fNL.add(cP, BorderLayout.CENTER);
        fNL.add(sP, BorderLayout.SOUTH);
        fNL.setResizable(false);
        fNL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fNL.setVisible(true);
    }

    private JPanel centerPanel () {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);
        return p;
    }

    //панель с кнопками
    private JPanel southPanel () {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.LIGHT_GRAY);
        p.add(buttons[1]);
        p.add(buttons[2]);
        p.add(buttons[3]);
        p.add(buttons[4]);
        p.add(buttons[13]);

        // назад
        buttons[0].addActionListener(e -> {
            if (buttonKey >= 1 && buttonKey <= 5) {
                sP.removeAll();
                for (int i = 1; i <= 4; i++) {
                    sP.add(buttons[i]);
                }
                sP.add(buttons[13]);
            }
            if (buttonKey >= 11 && buttonKey <= 15) {
                if (buttonKey == 11) { sP.add(buttons[8]); buttonKey = 1; }
                if (buttonKey == 12) { sP.add(buttons[9]); buttonKey = 2; }
                if (buttonKey == 13) { sP.add(buttons[10]); buttonKey = 3; }
                if (buttonKey == 14) { sP.add(buttons[10]); buttonKey = 4; }
                if (buttonKey == 15) {sP.add(buttons[9]); buttonKey = 13;}
                sP.add(buttons[12]);
            }
            sP.revalidate();
            sP.repaint();
        });

        //линия
        buttons[1].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 8, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 1;

        });

        //окружность
        buttons[2].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 9, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 2;
        });

        //кольцо
        buttons[13].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 9, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 5;
        });

        //прямоугольник
        buttons[3].addActionListener(e ->{
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 10, 11, 12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 3;
        });

        //треугольник
        buttons[4].addActionListener( e-> {
            sP.removeAll();
            numbers = new int[]{0,5,6,7,8,11,12};
            for (int number : numbers){
                sP.add(buttons[number]);
            }
            sP.revalidate();
            sP.repaint();
            buttonKey = 4;
        });

        //создание
        buttons[5].addActionListener(e ->{
            int tag;

            try {
                tag = Integer.parseInt(JOptionPane.showInputDialog("Хотите ввести параметры вручную? (0-нет, 1-да)"));
                if (tag != 0 && tag != 1) {
                    JOptionPane.showMessageDialog(null, "Ошибка: Введите 0 или 1.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tag == 1){
                        create();
                    }
                    else {
                        createRandom();
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка: некорректный ввод.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        });

        //сдвинуть
        buttons[6].addActionListener( e -> {

            int x = (int) (Math.random() * 100) + 10;
            int y = (int) (Math.random() * 100) + 10;

            if (buttonKey == 1){
                if (line != null) {
                    ((Line) line).MoveTo(x, y);
                    ((Line) line).Show(vision);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            } else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i=0; i<cntLines; i++) {
                        lines[i].MoveTo(x, y);
                        lines[i].Show(vision);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            } else if(buttonKey == 2){
                if (circle != null) {
                    ((Circle) circle).MoveTo(x, y);
                    ((Circle) circle).Show(visionCircle);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            } else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i=0; i<cntCircles; i++) {
                        circles[i].MoveTo(x, y);
                        circles[i].Show(visionCircle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден");
                }
            } else if (buttonKey == 5) {
                if(ring != null){
                    ((Ring) ring).MoveTo(x, y);
                    ((Ring) ring).Show(visionRing);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Кольцо не найдено");
                }

            } else if (buttonKey == 15) {
                if (rings != null) {
                    for (int i=0; i<cntRings; i++) {
                        rings[i].MoveTo(x, y);
                        rings[i].Show(visionCircle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив колец не найден");
                }

            } else if (buttonKey == 3) {
                if (rectangle != null) {
                    ((Rectangle) rectangle).MoveTo(x, y);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            } else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i=0; i<cntRects; i++) {
                        rectangles[i].MoveTo(x, y);
                        rectangles[i].Show(visionRect);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив Прямоугольников не найден");
                }
            } else if (buttonKey == 4) {
                if (triangle != null) {
                    ((Triangle) triangle).MoveTo(x, y);
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.repaint();
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            } else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i=0; i<cntTriangles; i++) {
                        triangles[i].MoveTo(x, y);
                        triangles[i].Show(visionTriangle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив Треугольников не найден");
                }
            }
            cntCircles = cntTriangles = cntRects = cntLines = 10;
        });

        //удалить фигуру/ы
        buttons[7].addActionListener( e-> {
            if (buttonKey == 1) {
                if (line != null) {
                    cP.remove(line);
                    ((Line) line).Show(false);
                    line = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i=0; i<cntLines; i++) {
                        cP.remove(lines[i]);
                        lines[i].Show(false);
                    }
                    lines = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 2) {
                if (circle != null) {
                    cP.remove(circle);
                    ((Circle) circle).Show(false);
                    circle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i=0; i<cntCircles; i++) {
                        cP.remove(circles[i]);
                        circles[i].Show(false);
                    }
                    circles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден");
                }
            }else if (buttonKey == 5) {
                if(ring != null){
                    cP.remove(ring);
                    ((Ring) ring).Show(false);
                    circle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Кольцо не найдено");
                }

            } else if (buttonKey == 15) {
                if (rings != null) {
                    for (int i = 0; i < cntRings; i++) {
                        cP.remove(rings[i]);
                        rings[i].Show(false);
                    }
                    circles = null;
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив колец не найден");
                }
            }
            else if (buttonKey == 3) {
                if (rectangle != null) {
                    cP.remove(rectangle);
                    ((Rectangle) rectangle).Show(false);
                    rectangle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i=0; i<cntRects; i++) {
                        cP.remove(rectangles[i]);
                        rectangles[i].Show(false);
                    }
                    rectangles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    cP.remove(triangle);
                    ((Triangle) triangle).Show(false);
                    triangle = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i=0; i<cntTriangles; i++) {
                        cP.remove(triangles[i]);
                        triangles[i].Show(false);
                    }
                    triangles = null;
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
        });

        //вращение фигур(линия и треугольник)
        buttons[8].addActionListener( e -> {
            if (buttonKey == 1) {
                if (line != null) {
                    ((Line) line).Turn();
                    ((Line) line).Show(vision);
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    for (int i = 0; i < cntLines; i++) {
                        lines[i].Turn();
                        lines[i].Show(vision);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив не создан");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    ((Triangle) triangle).rotate();
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.revalidate();
                    cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    for (int i = 0; i < cntTriangles; i++) {
                        triangles[i].rotate();
                        triangles[i].Show(visionTriangle);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив треугольников не создан");
                }
            }
        });

        //изменить диаметр(окружность)
        buttons[9].addActionListener(e-> {
            if (buttonKey == 2) {
                int x = 10 + (int) (Math.random() * 150);
                if (circle != null) {
                    ((Circle) circle).chengeD(x);
                    ((Circle) circle).Show(visionCircle);
                    x = 0;
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    for (int i = 0; i < cntCircles; i++) {
                        int x = 10 + (int) (Math.random() * 150);
                        circles[i].chengeD(x);
                        circles[i].Show(visionCircle);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден");
                }
            } else if (buttonKey == 5){
                int x = 10 + (int) (Math.random() * 150);
                if (ring != null) {
                    ((Ring) ring).chengeD(x);
                    ((Ring) ring).Show(visionRing);
                    x = 0;
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Кольцо не найдено");
                }
            } else if (buttonKey == 15) {
                if (rings != null) {
                    for (int i = 0; i < cntRings; i++) {
                        int x = 10 + (int) (Math.random() * 150);
                        rings[i].chengeD(x);
                        rings[i].Show(visionRing);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив колец не найден");
                }
            }
        });
        //изменение размера для прямоугольника
        buttons[10].addActionListener(e -> {
            int x = 10 + (int) (Math.random() * 50);
            int y = 10 + (int) (Math.random() * 50);
            if (buttonKey == 3) {
                if (rectangle != null) {
                    ((Rectangle) rectangle).chSize(x, y);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    for (int i = 0; i < cntRects; i++) {
                        rectangles[i].chSize(x, y);
                        rectangles[i].Show(visionRect);
                    }
                    cP.revalidate();
                    cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан");
                }
            }

            x = y = 0;
        });

        //изменение видимости активных фигур
        buttons[11].addActionListener(e -> {
            if (buttonKey == 1) {
                if (line != null) {
                    vision = !vision;
                    ((Line) line).Show(vision);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Линия не найдена");
                }
            }
            else if (buttonKey == 11) {
                if (lines != null) {
                    vision = !vision;
                    for (int i=0; i<10; i++) {
                        lines[i].Show(vision);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 2) {
                if (circle != null) {
                    visionCircle = !visionCircle;
                    ((Circle) circle).Show(visionCircle);
                    cP.revalidate(); cP.repaint();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Окружность не найдена");
                }
            }
            else if (buttonKey == 12) {
                if (circles != null) {
                    visionCircle = !visionCircle;
                    for (int i=1; i<cntCircles; i++) {
                        circles[i].Show(visionCircle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден");
                }
            }
            else if (buttonKey == 3) {
                if (rectangle != null) {
                    visionRect = !visionRect;
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 13) {
                if (rectangles != null) {
                    visionRect = !visionRect;
                    for (int i=0; i<cntRects; i++) {
                        rectangles[i].Show(visionRect);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 5) {
                if (rings != null) {
                    visionRing = !visionRing;
                    ((Ring) ring).Show(visionRing);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден");
                }
            }
            else if (buttonKey == 15) {
                if (rings != null) {
                    visionRing = !visionRing;
                    for (int i=0; i<cntRings; i++) {
                        rings[i].Show(visionRing);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
            else if (buttonKey == 4) {
                if (triangle != null) {
                    visionTriangle = !visionTriangle;
                    ((Triangle) triangle).Show(visionTriangle);
                    cP.revalidate(); cP.repaint();
                } else {
                    JOptionPane.showMessageDialog(fNL, "Треугольник не найден");
                }
            }
            else if (buttonKey == 14) {
                if (triangles != null) {
                    visionTriangle = !visionTriangle;
                    System.out.println("Видимость: " + visionTriangle);
                    for (int i=0; i<cntTriangles; i++) {
                        triangles[i].Show(visionTriangle);
                        cP.repaint();
                    }
                    cP.revalidate();
                }
                else {
                    JOptionPane.showMessageDialog(fNL, "Массив линий не найден");
                }
            }
        });

        //кнопки для работы с массивом
        buttons[12].addActionListener(e -> {
            sP.removeAll();
            numbers = new int[]{0, 5, 6, 7, 11};
            for (int number: numbers){
                sP.add(buttons[number]);
            }
            JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива объектов");
            if (buttonKey == 1 ) {
                sP.add(buttons[8]);
                buttonKey = 11;
            }
            if (buttonKey == 2 ) {
                sP.add(buttons[9]);
                buttonKey = 12;
            }
            if (buttonKey == 3 ) {
                sP.add(buttons[10]);
                buttonKey = 13;
            }
            if (buttonKey == 4 ) {
                sP.add(buttons[8]);
                buttonKey = 14;
            }
            if (buttonKey == 5 ) {
                sP.add(buttons[10]);
                buttonKey = 15;
            }
            sP.revalidate();
            sP.repaint();
        });


        return p;
    }

    //созадание фигур
    private void create(){
        if(buttonKey == 1){
            if (line == null){
                vision = true;

                //добавить обработку корректности?
                int x1 = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату начала отрезка:"));
                check(x1, 1);
                int y1 = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату начала отрезка:"));
                check(y1, 2);
                int x2 = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату конца отрезка:"));
                check(x2, 1);
                int y2 = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));
                check(y2, 1);
                if (cntErors == 0){
                    line = new Line(x1, y1, x2, y2, Color.RED);
                    ((Line) line).Show(vision);
                    cP.add(line, BorderLayout.CENTER);
                    cP.revalidate();
                    cntErors = 0;
                }
                else{
                    cntErors = 0;
                    JOptionPane.showMessageDialog(fNL, "Некорректный ввод выход за пределы холста при создании фигуры.");
                }
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Линия уже нарисована");
            }
        } else if(buttonKey == 11){
            if (lines == null) {
                JOptionPane.showMessageDialog(fNL, "Линии будут созданы по случайным координатам.");
                cntLines = Integer.parseInt(JOptionPane.showInputDialog("Сколько линий необходимо создать?"));
                createRandom();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Линии уже нарисованы");
            }

        } else if (buttonKey == 2) {
            if (circle == null) {
                visionCircle = true;
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату центра:"));
                check(x, 1);
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату центра:"));
                check(y, 2);
                int D = Integer.parseInt(JOptionPane.showInputDialog("Введите диаметр окружности:"));
                check(D, 2);
                if (D+ x> interfaceWight || D + y > interfaceHight || D - y < 0 || D - x < 0) {
                    cntErors += 1;
                }
                if (cntErors == 0){
                    circle = new Circle (x, y, D, Color.GREEN);
                    ((Circle) circle).Show(visionCircle);
                    cP.add(circle, BorderLayout.CENTER);
                    cP.revalidate();
                }
                else{
                    cntErors = 0;
                    JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры.");
                }


            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }

        }else if(buttonKey == 12){
            if (circles == null) {
                JOptionPane.showMessageDialog(fNL, "Окружности будут созданы по случайным координатам.");
                cntCircles = Integer.parseInt(JOptionPane.showInputDialog("Сколько окружностей необходимо создать?"));
                if (cntCircles <= 0){
                    JOptionPane.showMessageDialog(fNL, "Условие нарушено. Будет создано 10 окружностей.");
                    cntCircles = 10;
                }
                createRandom();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Окружности уже нарисованы");
            }
        }else if (buttonKey == 5) {
            if (ring == null) {
                visionRing = true;
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату центра:"));
                check(x, 1);
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату центра:"));
                check(y, 2);
                int D = Integer.parseInt(JOptionPane.showInputDialog("Введите диаметр:"));
                check(D, 2);
                if (D+ x> interfaceWight || D + y > interfaceHight || D - y < 0 || D - x < 0) {
                    cntErors += 1;
                }
                if (cntErors == 0){
                    ring = new Ring (x, y, D, Color.GREEN);
                    ((Ring) ring).Show(visionRing);
                    cP.add(ring, BorderLayout.CENTER);
                    cP.revalidate();
                }
                else{
                    cntErors = 0;
                    JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры.");
                }


            } else {
                JOptionPane.showMessageDialog(fNL, "Кольцо уже нарисовано");
            }

        }else if(buttonKey == 15){
            if (rings == null) {
                JOptionPane.showMessageDialog(fNL, "Окружности будут созданы по случайным координатам.");
                cntRings = Integer.parseInt(JOptionPane.showInputDialog("Сколько колец необходимо создать?"));
                if (cntRings <= 0){
                    JOptionPane.showMessageDialog(fNL, "Условие нарушено. Будет создано 10 колец.");
                    cntRings = 10;
                }
                createRandom();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Кольца уже нарисованы");
            }
        }
        else if (buttonKey == 3) {
            if(rectangle == null){
                visionRect = true;
                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату начала отрезка:"));
                check(x,1);
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату начала отрезка:"));
                check(y, 2);
                int w = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату конца отрезка:"));
                check(w, 2);
                int h = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату конца отрезка:"));
                check(h, 2);
                if (cntErors == 0){
                    rectangle = new Rectangle(x, y, w, h, Color.GREEN);
                    ((Rectangle) rectangle).Show(visionRect);
                    cP.add(rectangle, BorderLayout.CENTER);
                    cP.revalidate();
                }
                else{
                    cntErors = 0;
                    JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры.");
                }

            } else {
                JOptionPane.showMessageDialog(fNL, "Прямоугольник уже нарисован");
            }
        } else if (buttonKey == 13) {
            if (rectangles == null) {
                JOptionPane.showMessageDialog(fNL, "Прямоугольники будут созданы по случайным координатам.");
                cntRects = Integer.parseInt(JOptionPane.showInputDialog("Сколько прямоугольники необходимо создать?"));
                createRandom();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Прямоугольники уже нарисованы");
            }
        } else if (buttonKey == 4) {
            if (triangle == null){
                visionTriangle = true;

                int x = Integer.parseInt(JOptionPane.showInputDialog("Введите X координату  треугольника:"));
                check(x, 1);
                int y = Integer.parseInt(JOptionPane.showInputDialog("Введите Y координату  треугольника:"));
                check(y, 2);
                int z = Integer.parseInt(JOptionPane.showInputDialog("Введите точку треугольника:"));
                check(z, 1);
                int t = Integer.parseInt(JOptionPane.showInputDialog("Введите точку треугольника:"));
                check(t, 2);
                int h = Integer.parseInt(JOptionPane.showInputDialog("Введите точку треугольника:"));
                check(h, 2);
                int f =Integer.parseInt(JOptionPane.showInputDialog("Введите точку треугольника:"));
                check(f, 2);
                if (cntErors == 0){
                    triangle = new Triangle(x, y, z, t, h,f, Color.YELLOW);
                    ((Triangle) triangle).Show(vision);
                    cP.add(triangle, BorderLayout.CENTER);
                    cP.revalidate();
                }
                else{
                    cntErors = 0;
                    JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры.");
                }
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Треугольник уже нарисован");
            }
        } else if (buttonKey == 14) {
            if (triangles == null){
                JOptionPane.showMessageDialog(fNL,"Треугольники будут созданы по случайным координатам.");
                cntTriangles = Integer.parseInt(JOptionPane.showInputDialog("Сколько треугольников необходимо создать?"));
                createRandom();
            } else {
                JOptionPane.showMessageDialog(fNL, "Треугольники уже нарисованы");
            }
        }

    }

    //проверка входных данных
    private void  check(int a, int key){
        if (key == 1){
            if (a <= 0 || a >= 800){
                cntErors ++;
            }
        }else if ( key == 2){
            if (a <= 0 || a >= 500){
                cntErors ++;
            }
        } else {
            System.out.println("Передан некорректный ключ символа. Проверка не была произведена.");
        }
    }

    //создание фигуры со случайными координатами
    private  void createRandom(){
        a = (int) (Math.random() * 300) +1;
        b = (int) (Math.random() * 300)+1;
        c = (int) (Math.random() * 300)+1;
        d = (int) (Math.random() * 300)+1;
        e = (int) (Math.random() * 300)+1;
        if (buttonKey == 1) {
            if (line == null) {
                vision = true;
                line = new Line (a, b, c, d, Color.BLACK);
                ((Line) line).Show(vision);
                cP.add(line, BorderLayout.CENTER);
                cP.revalidate();
            }
            else {
                JOptionPane.showMessageDialog(fNL, "Линия уже нарисована");
            }
        }else if(buttonKey == 11){
            if (lines == null){
                lines = new Line[cntLines];
                vision = true;
                lines[0] = new Line (a, b, c, d, Color.BLACK);
                for (int i = 1; i < cntLines; i++){
                    lines[i] = new Line (a, b, c, d, Color.BLACK);
                    lines[i].Show(vision);
                    cP.add(lines[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    d = (int) (Math.random() * 300) + 1;
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив линий уже создан");

            }
        } else if (buttonKey == 2) {
            if (circle == null) {
                a = (int) (Math.random() * (600-100)) + 100;
                b = (int) (Math.random() * (600-100)) + 100;
                c = (int) (Math.random() * (300));
                visionCircle = true;
                circle = new Circle (a, b, c, Color.GREEN);
                ((Circle) circle).Show(visionCircle);
                cP.add(circle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }

        } else if(buttonKey == 12){
            if (circles == null){
                circles = new Circle[cntCircles];
                visionCircle = true;
                for (int i = 0; i < cntCircles; i++){
                    a = (int) (Math.random() * (900-100)) + 100;
                    b = (int) (Math.random() * (500-100)) + 100;
                    c = (int) (Math.random() * (100-1)) + 100;
                    circles[i] = new Circle (a, b, c, Color.BLACK);
                    circles[i].Show(visionCircle);
                    cP.add(circles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            } else{
                JOptionPane.showMessageDialog(fNL, "Массив окружностей уже создан");
            }
        } else if (buttonKey == 5) {
            if (ring == null) {
                a = (int) (Math.random() * (600-100)) + 100;
                b = (int) (Math.random() * (600-100)) + 100;
                c = (int) (Math.random() * (300));
                visionRing = true;
                ring = new Ring (a, b, c, Color.GREEN);
                ((Ring) ring).Show(visionRing);
                cP.add(ring, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована");
            }

        } else if(buttonKey == 15){
            if (rings == null){
                rings = new Ring[cntRings];
                visionRing = true;
                for (int i = 0; i < cntRings; i++){
                    a = (int) (Math.random() * (900-100)) + 100;
                    b = (int) (Math.random() * (500-100)) + 100;
                    c = (int) (Math.random() * (100-1)) + 100;
                    rings[i] = new Ring (a, b, c, Color.BLACK);
                    rings[i].Show(visionRing);
                    cP.add(rings[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            } else{
                JOptionPane.showMessageDialog(fNL, "Массив окружностей уже создан");
            }
        }
        else if (buttonKey == 3) {
            if (rectangle == null) {
                visionRect = true;
                rectangle = new Rectangle (a, b, c,d, Color.MAGENTA);
                ((Rectangle) rectangle).Show(visionRect);
                cP.add(rectangle, BorderLayout.CENTER);
                cP.revalidate();
            } else {
                JOptionPane.showMessageDialog(fNL, "Прямоугольник уже нарисован");
            }
        } else if (buttonKey== 13) {
            if (rectangles == null){
                rectangles = new Rectangle[cntRects];
                visionRect = true;
                rectangles[0] = new Rectangle (a, b, c, d, Color.ORANGE);
                for (int i = 1; i < cntRects; i++){
                    a = (int) (Math.random() * 300)+1;
                    b = (int) (Math.random() * 300)+1;
                    c = (int) (Math.random() * 300)+1;
                    d = (int) (Math.random() * 300)+1;
                    rectangles[i] = new Rectangle (a, b, c, d, Color.BLACK);
                    rectangles[i].Show(visionRect);
                    cP.add(rectangles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив прямоугольников уже создан");

            }
        } else if (buttonKey == 14) {
            if (triangles == null){
                triangles = new Triangle[cntTriangles];
                visionTriangle = true;
                int f = (int) (Math.random() * 300) +1;
                triangles[0] = new Triangle (a, b, c, d, e,f, Color.BLACK);
                for (int i = 1; i < cntTriangles; i++){
                    a = (int) (Math.random() * 300)+1;
                    b = (int) (Math.random() * 300)+1;
                    c = (int) (Math.random() * 300)+1;
                    d = (int) (Math.random() * 300)+1;
                    e = (int) (Math.random() * 300)+1;
                    f = (int) (Math.random() * 300) +1;
                    triangles[i] = new Triangle (a, b, c, d,e,f, Color.BLACK);
                    triangles[i].Show(visionTriangle);
                    cP.add(triangles[i], BorderLayout.CENTER);
                    cP.validate();
                    cP.repaint();
                }
                cP.revalidate();
            }else{
                JOptionPane.showMessageDialog(fNL, "Массив треугольников уже создан");

            }
        } else if (buttonKey == 4) {
            if (triangle == null){
                visionTriangle = true;
                int f = (int) (Math.random() * 300) +1;
                triangle = new Triangle (a, b, c,d,e, f,Color.MAGENTA);
                ((Triangle) triangle).Show(visionTriangle);
                cP.add(triangle, BorderLayout.CENTER);
                cP.revalidate();
            }
            else{
                JOptionPane.showMessageDialog(fNL, "Треугольник уже нарисован");
            }
        }
    }

    public static void main (String[] argc) {
        SwingUtilities.invokeLater(Main::new);
    }

}