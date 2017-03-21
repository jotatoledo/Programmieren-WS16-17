package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Move;
import edu.kit.informatik.matchthree.framework.interfaces.MoveFactory;

/**
 * The implementation of a movement factory
 */
public class MoveFactoryImplementation implements MoveFactory {
    /**
     * Creates a new instance
     */
    public MoveFactoryImplementation() {
    }

    @Override
    public Move flipRight(final Position position) {
        return new Swap(position, position.plus(1, 0));
    }

    @Override
    public Move flipDown(final Position position) {
        return new Swap(position, position.plus(0, 1));
    }

    @Override
    public Move rotateSquareClockwise(final Position position) {
        return new RotateSquareClockwise(position);
    }

    @Override
    public Move rotateColumnDown(final int columnIndex) {
        return new RotateColumnDown(columnIndex);
    }

    @Override
    public Move rotateRowRight(final int rowIndex) {
        return new RotateRowRight(rowIndex);
    }
}
