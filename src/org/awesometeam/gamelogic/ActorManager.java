package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import org.awesometeam.OptionsState;
//import org.awesometeam.SharedMemoryServerReceived;

public class ActorManager {

    public static final Random randomGenerator = new Random();
    private ActorLists actorLists;
    private ArrayList<BoardActor> actorList;
    private TreeMap<Integer, Player> playerMap;
    private CollisionDetector collisionDetector;
    private ArrayList<Integer> toRemove;
    private Asteroid asteroidToCreate;
    ArrayList<Spaceship> waitingSpaceships;

    public ActorManager(int playerCount) {

        asteroidToCreate = null;
        toRemove = new ArrayList<Integer>();
        waitingSpaceships = new ArrayList<Spaceship>();

        collisionDetector = new CollisionDetector();
        Board board = new Board();
        Physics physics = new Physics();

        actorLists = new ActorLists();
        actorLists.setBoard(board);
        actorLists.setPhysics(physics);

        actorList = new ArrayList<BoardActor>();
        playerMap = new TreeMap<Integer, Player>();

        int asteroidCount = 0;
        if (OptionsState.asteroidDensity >= 0 && OptionsState.asteroidDensity <= 25)
            asteroidCount = OptionsState.asteroidDensity;

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

    public void update(TreeMap<Integer, KeyPresses> playersKeyPresses, double timeInterval) {

        for (int i : playersKeyPresses.navigableKeySet()) {
            Spaceship spaceship = null;
            if (!playerMap.containsKey(i)) {
                Player player = new Player(i);
                playerMap.put(i, player);
            }

            if (playerMap.get(i).getState() == Player.State.WAITING_FOR_RESURRECTION) {
                playerMap.get(i).waitForPosition(timeInterval);
                if (playerMap.get(i).getState() == Player.State.WAITING_FOR_POSITION) {
                    spaceship = new Spaceship(playerMap.get(i));
                    spaceship.setPosition(actorLists.getBoard().randomPosition());
                    waitingSpaceships.add(spaceship);
                }
            }

            if (playersKeyPresses.get(i) != null) {
                playerMap.get(i).setKeyPresses(playersKeyPresses.get(i));
            }
        }

        ArrayList<Spaceship> spaceshipsToRemove = new ArrayList<Spaceship>();
        for (int i = 0; i < waitingSpaceships.size(); i++) {
            Spaceship spaceship = waitingSpaceships.get(i);
            boolean clearPosition = true;
            for (BoardActor actor : actorLists.getActorList()) {

                if (collisionDetector.areToClose(actor, spaceship, Spaceship.START_INTERSPACE)) {
                    clearPosition = false;
                }
            }
            if (clearPosition) {
                actorLists.addSpaceship(spaceship);
                spaceship.start();
                spaceshipsToRemove.add(spaceship);
            }
        }

        waitingSpaceships.removeAll(spaceshipsToRemove);

        for (int i = actorLists.getSpaceshipList().size() - 1; i >= 0; i--) {
            Spaceship spaceship = actorLists.getSpaceshipList().get(i);
            if (!playersKeyPresses.containsKey(spaceship.getID())) {
                playerMap.remove(spaceship.getID());
                spaceship.die();
            }
        }

        moveActors(timeInterval);

        attack();

        detectCollisions();

        tryToCreateNewAsteroid();
    }

    private void tryToCreateNewAsteroid() {
        if (asteroidToCreate == null) {
            if (randomGenerator.nextInt(400) == 0) {
                asteroidToCreate = new Asteroid();
                asteroidToCreate.setPosition(actorLists.getBoard().randomPosition());
            }
        } else {
            boolean clearPosition = true;
            for (BoardActor actor : actorLists.getActorList()) {
                if (collisionDetector.areToClose(actor, asteroidToCreate, Asteroid.START_INTERSPACE)) {
                    clearPosition = false;
                }
            }
            if (clearPosition) {
                actorLists.addAsteroid(asteroidToCreate);
                asteroidToCreate = null;
            }
        }

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

        for (int i : playerMap.navigableKeySet()) {
            if (playerMap.get(i).getState() == Player.State.DEAD) {
                playerMap.get(i).startWaiting();
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
