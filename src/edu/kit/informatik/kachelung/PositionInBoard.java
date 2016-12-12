package edu.kit.informatik.kachelung;

/**
 * Represents a position of a tile in the game board represented by the class {@linkplain Board}
 * @author JoseNote
 * @version 1.00
 */
public enum PositionInBoard {
    UPPER_RIGHT_CORNER("UPPER_RIGHT_CORNER", 3, 4, 5),
    DOWN_RIGH_CORNER("DOWN_RIGH_CORNER", 0, 4, 5),
    UPPER_LEFT_CORNER("UPPER_LEFT_CORNER", 2, 3),
    DOWN_LEFT_CORNER("DOWN_LEFT_CORNER", 0, 1, 2),
    INTERNAL("INTERNAL", 0, 1, 2, 3, 4, 5),
    UPPER_SIDE("UPPER_SIDE", 2, 3, 4, 5),
    DOWN_SIDE("DOWN_SIDE", 0, 1, 2, 5),
    LEFT_SIDE("LEFT_SIDE", 0, 1, 2, 3),
    RIGHT_SIDE("RIGHT_SIDE", 0, 3, 4, 5);  
    
    /**
     * The value of the index in the codification list in {@linkplain Board}
     * related to the tile located in the upper left corner of the game board
     */
    public static final int UPPER_LEFT_CORNER_VALUE = 0;
    /**
     * The value of the index in the codification list in {@linkplain Board}
     * related to the tile located in the down left corner of the game board
     */
    public static final int DOWN_LEFT_CORNER_VALUE = Board.ELEMENTS_IN_COLUMN - 1;
    /**
     * The value of the index in the codification list in {@linkplain Board}
     * related to the tile located in the upper left corner of the game board
     */
    public static final int UPPER_RIGHT_CORNER_VALUE = (Board.ELEMENTS_IN_ROW - 1) * Board.ELEMENTS_IN_COLUMN;
    /**
     * The value of the index in the codification list in {@linkplain Board}
     * related to the tile located in the down right corner of the game board
     */
    public static final int DOWN_RIGHT_CORNER_VALUE = (Board.ELEMENTS_IN_ROW * Board.ELEMENTS_IN_COLUMN) - 1;
    
    /**
     * Represents the indexes of the codification list in {@linkplain Tile}
     * that have contact to other {@linkplain Tile} instances
     */
    private final int[] positions;
    /**
     * A {@linkplain String} representation of every element in this enumeration
     */
    private final String representation;
    
    PositionInBoard(String representation, int... positions) {
        this.positions = new int[positions.length];    
        for (int i = 0; i < positions.length; i++) {
            this.positions[i] = positions[i];
        }
        this.representation = representation;
    }
    
    /**
     * Getter method for {@link #representation}
     * @return The value of {@link #representation}
     */
    public String getRepresentation() {
        return representation;
    }
    
    /**
     * Getter method for {@link #positions}
     * @return The value of {@link #positions}
     */
    public int[] getPositions() {
        return positions;
    }
    
    /**
     * Calculates the {@linkplain PositionInBoard} 
     * associated to a given index of the codification list in [{@linkplain Board}
     * @param position A index of the codification list in [{@linkplain Board}
     * @return The value associated to the index
     */
    public static PositionInBoard calculateBoardPosition(int position) {
        PositionInBoard boardPositon = null;
        
        switch(position) {
             case(UPPER_LEFT_CORNER_VALUE):
                 boardPositon = PositionInBoard.UPPER_LEFT_CORNER;
                 break;
             case(DOWN_LEFT_CORNER_VALUE):
                 boardPositon = PositionInBoard.DOWN_LEFT_CORNER;
                 break;
             case(UPPER_RIGHT_CORNER_VALUE):
                 boardPositon = PositionInBoard.UPPER_RIGHT_CORNER;
                 break;
             case(DOWN_RIGHT_CORNER_VALUE):
                 boardPositon = PositionInBoard.DOWN_RIGH_CORNER;
                 break;
             default:
                 if (position % Board.ELEMENTS_IN_COLUMN == 0) {
                     boardPositon = PositionInBoard.UPPER_SIDE;
                 } else if (position % Board.ELEMENTS_IN_COLUMN == DOWN_LEFT_CORNER_VALUE) {
                     boardPositon = PositionInBoard.DOWN_SIDE;
                 } else if (position > UPPER_RIGHT_CORNER_VALUE) {
                     boardPositon = PositionInBoard.RIGHT_SIDE;
                 } else if ( position < DOWN_LEFT_CORNER_VALUE) {
                     boardPositon = PositionInBoard.LEFT_SIDE;
                 } else {
                     boardPositon = PositionInBoard.INTERNAL;
                 }
                 break;
         }
         return boardPositon;
    }
    
    public String toString() {
        return representation;
    }
}
