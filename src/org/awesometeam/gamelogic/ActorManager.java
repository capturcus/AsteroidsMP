package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
//import org.awesometeam.SharedMemoryServerReceived;

public class ActorManager {

    public static final Random randomGenerator = new Random();
    private ActorLists actorLists;
    private ArrayList<BoardActor> actorList;
    private TreeMap<Integer, Player> playerMap;
    private CollisionDetector collisionDetector;
    private ArrayList<Integer> toRemove;

    public ActorManager(int playerCount) {

        toRemove = new ArrayList<Integer>();

        collisionDetector = new CollisionDetector();
        Board board = new Board();
        Physics physics = new Physics();

        actorLists = new ActorLists();
        actorLists.setBoard(board);
        actorLists.setPhysics(physics);

        actorList = new ArrayList<BoardActor>();
        playerMap = new TreeMap<Integer, Player>();

//        for (int index = 0; index < playerCount; index++) {
//            Player player = new Player(index);
//            playerList.add(player);
//            Spaceship spaceship = new Spaceship(player);
//            actorLists.addSpaceship(spaceship);
//        }
        int asteroidCount = 5;// 2 * playerCount;

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
        for (int i : playersKeyPresses.navigableKeySet()) {
            //System.out.println("GameLogic                         Debug" + playersKeyPresses.get(i));

            Spaceship spaceship = null;
            if (!playerMap.containsKey(i)) {
                Player player = new Player(i);
                playerMap.put(i, player);
                //spaceship = new Spaceship(player);
                //spaceship.setPosition(actorLists.getBoard().randomPosition()); //new Point2D(300 + 70 * Board.players,300 + 70 * Board.players));
                //player.waitForPosition(0.1);//(Player.DEFAULT_TIME_TO_RESURRECTION);
                //Board.players ++;
            }

            if (playerMap.get(i).getState() == Player.State.WAITING_FOR_RESURRECTION) {
                playerMap.get(i).waitForPosition(timeInterval);
                if (playerMap.get(i).getState() == Player.State.WAITING_FOR_POSITION) {
                    spaceship = new Spaceship(playerMap.get(i));
                    spaceship.setPosition(actorLists.getBoard().randomPosition()); //new Point2D(300 + 70 * Board.players,300 + 70 * Board.players));
                }
            }

            if (playerMap.get(i).getState() == Player.State.WAITING_FOR_POSITION) {
                /*boolean clearPosition = true;
                for (BoardActor actor : actorLists.getActorList()) {
                    if (collisionDetector.areToClose(actor, spaceship, 30)) {
                        clearPosition = false;
                    }
                }*/
                //if (clearPosition) {
                    actorLists.addSpaceship(spaceship);
                    spaceship.start();
                //}

            }

            if (playersKeyPresses.get(i) != null) {
                playerMap.get(i).setKeyPresses(playersKeyPresses.get(i));
            }
            //System.out.println(i+" "+playerList.get(i).getKeyPresses());
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

        toRemove.clear();
        for (int i : playerMap.navigableKeySet()) {
            if (playerMap.get(i).getState() == Player.State.DEAD)
                playerMap.get(i).startWaiting();
        }
//        }
//        for (int i : toRemove) {
//            playerMap.remove(i);
//        }

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
