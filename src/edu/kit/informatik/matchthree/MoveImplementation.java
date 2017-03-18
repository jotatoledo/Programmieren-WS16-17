package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class MoveImplementation implements Move {
	protected final Position startPosition;
	
	protected MoveImplementation(final Position startPosition){
		this.startPosition = startPosition;
	}
}
