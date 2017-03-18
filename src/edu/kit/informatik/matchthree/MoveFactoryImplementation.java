package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Move;
import edu.kit.informatik.matchthree.framework.interfaces.MoveFactory;

/**
 *
 */
public class MoveFactoryImplementation implements MoveFactory {
    /**
     * 
     */
    public MoveFactoryImplementation() {
    	// FIXME implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Move flipRight(final Position position) {
        return new FlipRight(position);
    }

    @Override
    public Move flipDown(final Position position) {
    	return new FlipDown(position);
    }

    @Override
    public Move rotateSquareClockwise(final Position position) {
    	return new RotateSquareClockwise(position);
    }

    @Override
    public Move rotateColumnDown(int columnIndex) {
    	// FIXME implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Move rotateRowRight(int rowIndex) {
    	// FIXME implement
        throw new UnsupportedOperationException();
    }

}
