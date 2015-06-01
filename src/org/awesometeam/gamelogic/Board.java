package org.awesometeam.gamelogic;

import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Collections;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Board {

    private double width;
    private double height;
    public static int players = 0;

    public Board() {
        width = 1024 + 50;
        height = 768 + 50;
    }

    public Point2D getNewPosition(Point2D beginPosition, Vector2D moveVector) {
        double x = (beginPosition.getX() + moveVector.getX()) % width;
        if (x < 0) {
            x += width;
        }
        double y = (beginPosition.getY() + moveVector.getY()) % height;
        if (y < 0) {
            y += height;
        }
        return new Point2D(x, y);
    }

    public Point2D[] randomPositions(int actorCount) {
        Point2D[] positions = new Point2D[actorCount];
        if (actorCount == 0) {
            return positions;
        }

        double w = max(width / actorCount / 2, 100); //-max object size
        double h = max(height / actorCount / 2, 100); //-max object size
        int wi = (int) (width / w);
        int hi = (int) (height / h);
        ArrayList<Point2D> allPositions = new ArrayList<Point2D>();
        for (int i = 0; i < wi; i++) {
            for (int j = 0; j < hi; j++) {
                allPositions.add(new Point2D(w / 2 + i * w, h / 2 + j * h));
            }
        }
        Collections.shuffle(allPositions);

        for (int i = 0; i < actorCount; i++) {
            positions[i] = allPositions.get(i);
        }
//			positions[i] = new Point2D(width / actorCount * i, height
//					/ actorCount * i);
        return positions;
    }

    public Point2D randomPosition() {
        return new Point2D(ActorManager.randomGenerator.nextDouble() * width, ActorManager.randomGenerator.nextDouble() * height);
    }
}
