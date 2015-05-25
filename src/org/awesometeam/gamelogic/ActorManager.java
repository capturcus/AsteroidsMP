package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import java.util.ArrayList;
import java.util.TreeMap;
import org.awesometeam.SharedMemoryServerReceived;

public class ActorManager {

    private ActorLists actorLists;
    private ArrayList<BoardActor> actorList;
    private ArrayList<Player> playerList;
    private CollisionDetector collisionDetector;

    public ActorManager(int playerCount) {

        collisionDetector = new CollisionDetector();
        Board board = new Board();
        Physics physics = new Physics();

        actorLists = new ActorLists();
        actorLists.setBoard(board);
        actorLists.setPhysics(physics);
        
        actorList = new ArrayList<BoardActor>();
        playerList = new ArrayList<Player>();

        for (int index = 0; index < playerCount; index++) {
            Player player = new Player(index);
            playerList.add(player);
            Spaceship spaceship = new Spaceship(player);
            actorLists.addSpaceship(spaceship);
        }

        int asteroidCount = 1;// 2 * playerCount;

        createObstacles(asteroidCount);
        Point2D[] positions = board.randomPositions(actorLists.getActorList().size());
        int i = 0;
        for (BoardActor actor : actorLists.getActorList()) {
            actor.setPosition(positions[i]);
            i++;
        }
    }

    public ActorLists getActorLists() {
        return actorLists;
    }

    private ArrayList<BoardActor> createObstacles(int asteroidCount) {
        ArrayList<BoardActor> obstacleList = new ArrayList<BoardActor>();
        for (int i = 0; i < asteroidCount; i++) {
            Asteroid asteroid = new Asteroid();
            actorLists.addAsteroid(asteroid);
        }
        return obstacleList;
    }

    public void update(TreeMap<Integer, KeyPresses> playersKeyPresses, double timeInterval)
            throws IncorrectListLengthAsteroidsMPGLException {

        if (false) { //TODO playersKeyPresses.size() != playerList.size()
            throw new IncorrectListLengthAsteroidsMPGLException();
        }
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setKeyPresses(playersKeyPresses.get(/*i*/0));
        }

        moveActors(timeInterval);
        attack();
        detectCollisions();
    }

    private void attack() {
        for (int i = 0; i < actorLists.getActorList().size(); i++) {
            if (actorLists.getActorList().get(i).isAttacking()) {
                actorLists.getActorList().get(i).attack();
            }
        }
    }

    private void detectCollisions() {
        ArrayList<ActorPair> collisionList = collisionDetector
                .getCollisions(actorLists.getActorList());
        for (ActorPair pair : collisionList) {
            collision(pair.getFirst(), pair.getSecond());
        }
        for (int i = actorLists.getActorList().size() - 1; i >= 0; i--) {
            if (actorLists.getActorList().get(i).getState() == BoardActor.State.DYING) {
                actorLists.getActorList().get(i).die();
            }
        }
        for (int i = playerList.size() - 1; i >= 0; i--) {
            if (playerList.get(i).getState() == Player.State.DEAD) {
                playerList.remove(i);
            }
        }
    }

    private void moveActors(double timeInterval) {
        for (BoardActor actor : actorLists.getActorList()) {
            actor.move(timeInterval);
        }
    }

    private void collision(BoardActor actor1, BoardActor actor2) {
        actor1.collision(actor2);
        actor2.collision(actor1);
    }
}
