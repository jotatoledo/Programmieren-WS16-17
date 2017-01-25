package main.java.tessellation;

/**
 * Represents a position of a {@linkplain Tile tile} in the {@linkplain Board game board}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public enum BoardPosition {
    /**
     * UPPER_RIGHT_CORNER
     */
    UPPER_RIGHT_CORNER("UPPER_RIGHT_CORNER", 3, 4, 5),
    /**
     * DOWN_RIGH_CORNER
     */
    DOWN_RIGH_CORNER("DOWN_RIGH_CORNER", 0, 4, 5),
    /**
     * UPPER_LEFT_CORNER
     */
    UPPER_LEFT_CORNER("UPPER_LEFT_CORNER", 2, 3),
    /**
     * DOWN_LEFT_CORNER
     */
    DOWN_LEFT_CORNER("DOWN_LEFT_CORNER", 0, 1, 2),
    /**
     * INTERNAL
     */
    INTERNAL("INTERNAL", 0, 1, 2, 3, 4, 5),
    /**
     * UPPER_SIDE
     */
    UPPER_SIDE("UPPER_SIDE", 2, 3, 4, 5),
    /**
     * DOWN_SIDE
     */
    DOWN_SIDE("DOWN_SIDE", 0, 1, 2, 5),
    /**
     * LEFT_SIDE
     */
    LEFT_SIDE("LEFT_SIDE", 0, 1, 2, 3),
    /**
     * RIGHT_SIDE
     */
    RIGHT_SIDE("RIGHT_SIDE", 0, 3, 4, 5);  
    
    
    
    /**
     * Represents the indexes of the codification list in {@linkplain Tile}
     * that have contact to other {@linkplain Tile} instances
     */
    private final int[] tileSidesInContact;
    /**
     * A {@linkplain String} representation of every element in this enumeration
     */
    private final String representation;
    
    /**
     * Creates a new instance
     * @param representation the string representation
     * @param tileSides the sides of a tile in contact to other tiles
     */
    BoardPosition(String representation, int... tileSides) {
        this.tileSidesInContact = new int[tileSides.length];    
        for (int i = 0; i < tileSides.length; i++) {
            this.tileSidesInContact[i] = tileSides[i];
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
     * Getter method for {@link #tileSidesInContact}
     * @return The value of {@link #tileSidesInContact}
     */
    public int[] getTileSidesInContact() {
        return tileSidesInContact;
    }
    
    /**
     * Calculates the {@linkplain BoardPosition} 
     * associated to a given index of the codification list in [{@linkplain Board}
     * @param index A index of the codification list in [{@linkplain Board}
     * @param elementsCol number element in column
     * @param elementsRow number elements in row
     * @return The value associated to the index
     */
    public static BoardPosition calculatePosition(int index, final int elementsCol, final int elementsRow) {
        /**
         * The value of the index in the codification list in {@linkplain Board}
         * related to the tile located in the upper left corner of the game board
         */
        final int upperLeftCornerValue = 0;
        /**
         * The value of the index in the codification list in {@linkplain Board}
         * related to the tile located in the down left corner of the game board
         */
        final int downLeftCornerValue = elementsCol - 1;
        /**
         * The value of the index in the codification list in {@linkplain Board}
         * related to the tile located in the upper left corner of the game board
         */
        final int upperRightCornerValue = (elementsRow - 1) * elementsCol;
        /**
         * The value of the index in the codification list in {@linkplain Board}
         * related to the tile located in the down right corner of the game board
         */
        final int downRightCornerValue = (elementsRow * elementsCol) - 1;
        
        BoardPosition boardPositon = null;
        
        if (index == (upperLeftCornerValue)) {
            boardPositon = BoardPosition.UPPER_LEFT_CORNER;
        } else if (index == (downLeftCornerValue)) {
            boardPositon = BoardPosition.DOWN_LEFT_CORNER;
        } else if (index == (upperRightCornerValue)) {
            boardPositon = BoardPosition.UPPER_RIGHT_CORNER;
        } else if (index == (downRightCornerValue)) {
            boardPositon = BoardPosition.DOWN_RIGH_CORNER;
        } else if (index % Board.ELEMENTS_COLUMN == 0) {
             boardPositon = BoardPosition.UPPER_SIDE;
         } else if (index % Board.ELEMENTS_COLUMN == downLeftCornerValue) {
             boardPositon = BoardPosition.DOWN_SIDE;
         } else if (index > upperRightCornerValue) {
             boardPositon = BoardPosition.RIGHT_SIDE;
         } else if ( index < downLeftCornerValue) {
             boardPositon = BoardPosition.LEFT_SIDE;
         } else {
             boardPositon = BoardPosition.INTERNAL;
         }        
         return boardPositon;
    }
    
    @Override
    public String toString() {
        return representation;
    }
}
