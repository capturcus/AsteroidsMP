package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Board {

	private double width;
	private double height;
        public static int players = 0;
        
	public Board() {
		width = 1024 + 100;
		height = 768 + 100;
	}

	public Point2D getNewPosition(Point2D beginPosition, Vector2D moveVector) {
		double x = (beginPosition.getX() + moveVector.getX()) % width;
		if (x < 0)
			x += width;
		double y = (beginPosition.getY() + moveVector.getY()) % height;
		if (y < 0)
			y += height;
		return new Point2D(x, y);
	}

	public Point2D[] randomPositions(int actorCount) {
		Point2D[] positions = new Point2D[actorCount];
		for (int i = 0; i < actorCount; i++)
			positions[i] = new Point2D(width / actorCount * i, height
					/ actorCount * i);
		return positions;
	}

}
