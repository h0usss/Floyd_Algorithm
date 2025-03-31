package com.h0uss.floyd_algorithm.logic;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Geometry {

    public Point2D getMiddleCoordinateForLine(double x1, double y1, double x2, double y2, double height) {

        double dx = x2 - x1;
        double dy = y2 - y1;
        double midX = (x1 + x2) / 2;
        double midY = (y1 + y2) / 2;

        double lengthAB = Math.sqrt(dx * dx + dy * dy);

        double unitDx = dy / lengthAB;
        double unitDy = -dx / lengthAB;

        double xC1 = midX + height * unitDx;
        double yC1 = midY + height * unitDy;

        return new Point2D(xC1, yC1);
    }

    public Point2D getIntersectionOfLineAndCircle(double x1, double y1, double x2, double y2, double r) {
        // Параметры прямой
        double dx = x2 - x1;
        double dy = y2 - y1;

        // Коэффициенты для уравнения
        double A = dx * dx + dy * dy;
        double B = 2 * (dx * (x1 - x2) + dy * (y1 - y2));
        double C = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) - r * r;

        // Дискриминант
        double discriminant = B * B - 4 * A * C;

        // Если дискриминант отрицательный, то пересечений нет
        if (discriminant < 0) {
            System.out.println("Нет точек пересечения.");
        } else {
            // Вычисляем параметры t для двух возможных точек пересечения
            double t1 = (-B + Math.sqrt(discriminant)) / (2 * A);
            double t2 = (-B - Math.sqrt(discriminant)) / (2 * A);

            // Вычисляем координаты точек пересечения
            double xIntersect1 = x1 + t1 * dx;
            double yIntersect1 = y1 + t1 * dy;

            double xIntersect2 = x1 + t2 * dx;
            double yIntersect2 = y1 + t2 * dy;

            // Выводим результаты
            if (discriminant == 0) {
                System.out.println("Точка пересечения: (" + xIntersect1 + ", " + yIntersect1 + ")");
            } else {
                System.out.println("Точки пересечения: ");
                System.out.println("Точка 1: (" + xIntersect1 + ", " + yIntersect1 + ")");
                System.out.println("Точка 2: (" + xIntersect2 + ", " + yIntersect2 + ")");
            }
            return new Point2D(xIntersect2, yIntersect2);
        }

       return null;
    }

//    public static Polygon getArrow(double x1, double y1, double x2, double y2, double r){
//        double arrowLength = 15;
//        double arrowWidth = 5;
//
//        // Параметры прямой
//        double dx = x2 - x1;
//        double dy = y2 - y1;
//
//        // Коэффициенты для уравнения
//        double A = dx * dx + dy * dy;
//        double B = 2 * (dx * (x1 - x2) + dy * (y1 - y2));
//        double C = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) - r * r;
//
//        // Дискриминант
//        double discriminant = B * B - 4 * A * C;
//
//
//            double t2 = (-B - Math.sqrt(discriminant)) / (2 * A);
//
//
//            double xIntersect2 = x1 + t2 * dx;
//            double yIntersect2 = y1 + t2 * dy;
//
//
//
//        return null;
//
//        double angle = Math.atan2(y2 - y1, x2 - x1);
//
//        double perpendicularAngle = angle + Math.PI / 2;
//
//        double dx1 = arrowWidth * Math.cos(perpendicularAngle);
//        double dy1 = arrowWidth * Math.sin(perpendicularAngle);
//        double dx2 = -arrowWidth * Math.cos(perpendicularAngle);
//        double dy2 = -arrowWidth * Math.sin(perpendicularAngle);
//
//        // Создаем вершины стрелки
//        double[] xPoints = new double[3];
//        double[] yPoints = new double[3];
//
//        // Конец стрелки (нос)
//        xPoints[0] = xIntersect;
//        yPoints[0] = yIntersect;
//
//        // Левый край стрелки
//        xPoints[1] = xIntersect + arrowLength * Math.cos(angle) + dx1;
//        yPoints[1] = yIntersect + arrowLength * Math.sin(angle) + dy1;
//
//        // Правый край стрелки
//        xPoints[2] = xIntersect + arrowLength * Math.cos(angle) + dx2;
//        yPoints[2] = yIntersect + arrowLength * Math.sin(angle) + dy2;
//
//        // Создаем полигон для стрелки
//        return new Polygon(xPoints[0], xPoints[1], xIntersect, yIntersect, yPoints[0], yPoints[1]);
//    }

}
