package com.h0uss.floyd_algorithm.logic;

import javafx.geometry.Point2D;

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
}
