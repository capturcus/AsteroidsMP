package org.awesometeam.gamelogic;

public class ActorPair {

	private BoardActor first;
	private BoardActor second;
	
	public ActorPair(BoardActor object1, BoardActor object2) {
		setFirst(object1);
		setSecond(object2);
	}

	public BoardActor getFirst() {
		return first;
	}

	public void setFirst(BoardActor first) {
		this.first = first;
	}

	public BoardActor getSecond() {
		return second;
	}

	public void setSecond(BoardActor second) {
		this.second = second;
	}
}
