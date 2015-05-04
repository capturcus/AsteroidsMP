package org.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import java.util.ArrayList;

public class ActorManager {

	private ArrayList<BoardActor> actorList;
	private ArrayList<Player> playerList;
	private CollisionDetector collisionDetector;
	private Board board;
	private Physics physics;

	public ActorManager(int playerCount) {

		collisionDetector = new CollisionDetector();
		board = new Board();
		physics = new Physics();

		actorList = new ArrayList<BoardActor>();
		playerList = new ArrayList<Player>();

		for (int index = 0; index < playerCount; index++) {
			Player player = new Player(index);
			playerList.add(player);
			actorList.add(new Spaceship(player));
		}

		int asteroidCount = 2 * playerCount;

		actorList.addAll(createObstacles(asteroidCount));
		Point2D[] positions = board.randomPositions(actorList.size());
		int i = 0;
		for (BoardActor actor : actorList) {
			actor.setPosition(positions[i]);
			i++;
		}
	}

	public ArrayList<BoardActor> getActorList() {
		return actorList;
	}

	private ArrayList<BoardActor> createObstacles(int asteroidCount) {
		ArrayList<BoardActor> obstacleList = new ArrayList<BoardActor>();
		for (int i = 0; i < asteroidCount; i++) {
			obstacleList.add(new Asteroid());
		}
		return obstacleList;
	}

	public void update(ArrayList<KeyPresses> playersKeyPresses, int timeInterval)
			throws IncorrectListLengthAsteroidsMPGLException {
		
		if (playersKeyPresses.size() != playerList.size())
			throw new IncorrectListLengthAsteroidsMPGLException();
		for (int i = 0; i < playerList.size(); i++)
			playerList.get(i).setKeyPresses(playersKeyPresses.get(i));
		
		moveActors(timeInterval);
		detectCollisions();
	}

	private void detectCollisions() {
		ArrayList<ActorPair> collisionList = collisionDetector
				.getCollisions(actorList);
		for (ActorPair pair : collisionList) {
			collision(pair.getFirst(), pair.getSecond());
		}
		for (int i = actorList.size() - 1; i >= 0; i--) {
			if (actorList.get(i).getState() == BoardActor.State.DYING) {
				actorList.get(i).die();
				if (actorList.get(i).getState() == BoardActor.State.DEAD)
					actorList.remove(i);
			}
		}
		for (int i = playerList.size() - 1; i >= 0; i--) {
			if (playerList.get(i).getState() == Player.State.DEAD)
				playerList.remove(i);
		}
	}
	

	private void moveActors(int timeInterval) {
		for (BoardActor actor : actorList) {
			actor.move(physics, board, timeInterval);
		}
	}

	private void collision(BoardActor actor1, BoardActor actor2) {
		actor1.collision(actor2);
		actor2.collision(actor1);
	}
}
