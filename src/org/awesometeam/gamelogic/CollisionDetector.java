package org.awesometeam.gamelogic;

import java.util.ArrayList;
import math.geom2d.Vector2D;

public class CollisionDetector {

    public CollisionDetector() {
    }

    public ArrayList<ActorPair> getCollisions(ArrayList<BoardActor> actorList) {
        ArrayList<ActorPair> collisions = new ArrayList<ActorPair>();
        for (int i = 0; i < actorList.size(); i++) {
            for (int j = i + 1; j < actorList.size(); j++) {
                if (isCollision(actorList.get(i), actorList.get(j)))
                    collisions.add(new ActorPair(actorList.get(i), actorList.get(j)));
            }
        }
        //System.out.println(collisions);
        return collisions;
    }
    
    public boolean isCollision(BoardActor actor1, BoardActor actor2) {
        Vector2D vec = new Vector2D(actor1.getPosition(), actor2.getPosition());
        return vec.norm() <= actor1.getRadius() + actor2.getRadius();
    }
}
