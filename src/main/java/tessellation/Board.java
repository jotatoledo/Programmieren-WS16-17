package main.java.tessellation;

/**
 * Represents a game board made of {@linkplain Tile}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public class Board {
    /**
     * Represents the number of tiles in every column of the game board.<br>
     * <strong>Can be changed.</strong><br>
     * <strong>Has to be > = 2.</strong>
     */
    public static final int ELEMENTS_COLUMN = 3;
    /**
     * Represents the number of tiles in every row of the game board.<br>
     * <strong>Can be changed.</strong><br>
     * <strong>Has to be > = 2.</strong>
     */
    public static final int ELEMENTS_ROW = 4;
    /**
     * Represents the number of tiles in the game board.
     */
    public static final int TABLE_ELEMENTS = ELEMENTS_ROW * ELEMENTS_COLUMN;

    private static final int MAIN_DIAGONAL = ELEMENTS_COLUMN;
    private static final int VERTICAL = 1;
    private static final int SECUNDARY_DIAGONAL = ELEMENTS_COLUMN - 1;

    /**
     * Represents the tiles in the board surface
     */
    private BoardTile[] table;

    //region A.4
    //==================================================================================================================

    //A.4.1
    /**
     * Creates a new empty board
     */
    public Board() {
        table = new BoardTile[TABLE_ELEMENTS];
        for (int i = 0; i < TABLE_ELEMENTS; i++) {
            //Sets every individual element of the codification array as an empty tile with an specific position
            table[i] = new BoardTile(BoardPosition.calculatePosition(i, ELEMENTS_COLUMN, ELEMENTS_ROW));
        }
    }

    //A.4.2
    /**
     * Gets the {@linkplain Tile} instance at a given index in the codification array
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @return A {@linkplain Tile} instance
     */
    public Tile getTile(int position) {
        return table[position].getTile();
    }

    //A.4.3
    /**
     * Sets a {@linkplain Tile} instance at a given position in the codification array
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @param newTile An instance of {@linkplain Tile}
     */
    public void setTile(int position, Tile newTile) {
        table[position].setLineTypes(newTile.getLineTypes());
    }

    //A.4.4
    /**
     * Replaces the {@linkplain BoardTile} instance at a given position in the codification array
     * with a new instance of an empty {@linkplain BoardTile}
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     */
    public void removeTile(int position) {
        table[position] = new BoardTile(BoardPosition.calculatePosition(position, ELEMENTS_COLUMN, ELEMENTS_ROW));
    }

    //A.4.5
    /**
     * Checks if the instance represents an empty board
     * @return {@code True} if the board is empty. {@code False} otherwise.
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (BoardTile element : table) {
            if (!element.isEmpty()) {
                //True: A tile in the board isn't empty.
                //This means that the board isn't empty.
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    //A.4.6
    /**
     * Rotates the tile at a given index in the codification array 60° clockwise
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     */
    public void rotateTileClockwise(int position) {
        table[position].rotateClockwise();
    }

    //A.4.7
    /**
     * Rotates the tile at a given index in the codification array 60° counterclockwise
     * @param position
     */
    public void rotateTileCounterClockwise(int position) {
        table[position].rotateCounterClockwise();
    }

    //A.4.8
    /**
     * Gets the number of colors in the game board
     * @return A value between {@code 0} and {@code 3}
     */
    public int getNumberOfColors() {
        int numberColors = 0;

        //To get the number of colors in the board, we search
        //for the tile with the maximal number of colors in the board
        for (BoardTile element : table) {
            //True: The number of colors in the current tile is larger than the actual maximum.
            //In that case, the current maximum will be replaced
            numberColors = element.getNumberOfColors() > numberColors ? element.getNumberOfColors() : numberColors;
            if (numberColors == 3)break;
            //True: a tile had 3 colors. 
            //This is the maximum possible amount of colors
            //There is no reason to keep looking for a bigger number, cause there isn't
        }
        return numberColors;
    }

    //A.4.9
    /**
     * Checks if the current instance represents a valid game board.
     * @return {@code True} if the instance represents a valid game board. {@code False} otherwise.
     */
    public boolean isValid() {
        boolean isValid = true;

        //Check every tile in the game board
        for (int i = 0; i < Board.TABLE_ELEMENTS; i++) {
            //Depending on the position in the board of every tile, there is a set of sides of if
            //that have contact with another tile in the board
            for (int tileSide : table[i].getPosition().getTileSidesInContact()) {
                if (!table[i].fitsTo(table[calculatePositionNeighbourTile(i, tileSide)], tileSide)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid)break;
        }
        return isValid;
    }

    /**
     * Calculates the position in the board of a tile, that is neighbor to a reference one 
     * and contacts it in a specific side of the reference one<br>
     * Support function for {@linkplain #isValid()}.
     * @param tilePosition The position of the reference tile
     * @param tileSide The side of the reference tile
     * @return The position of the neighbor tile
     */
    private int calculatePositionNeighbourTile(int tilePosition, int tileSide) {
        int calculatedBoardIndex = 0;
        
        switch(tileSide) {
            case(0):
                calculatedBoardIndex = tilePosition - 1;
                break;
            case(1):
                calculatedBoardIndex = tilePosition + Board.ELEMENTS_COLUMN - 1;
                break;
            case(2):
                calculatedBoardIndex = tilePosition + Board.ELEMENTS_COLUMN;
                break;
            case(3):
                calculatedBoardIndex = tilePosition + 1;
                break;
            case(4):
                calculatedBoardIndex = tilePosition - Board.ELEMENTS_COLUMN + 1;
                break;
            case(5):
                calculatedBoardIndex = tilePosition - Board.ELEMENTS_COLUMN;
                break;
        }
        return calculatedBoardIndex;
    }

    //A.4.10
    /**
     * Checks if there is a color path crossing multiple positions in the game board represented by this instance.
     * @param positions An array with multiple positions in the game board
     * @return {@linkplain LineType.NONE} if there is no path in the given positions.
     * Another value of the {@linkplain LineType} enumeration otherwise.
     */
    public LineType getConnectedPathColor(int[] positions) {
        LineType actualPath = null;
        LineType currentPairPath = null;

        for (int i = 0; i < positions.length - 1; i++) {
            int predecessor = positions[i];
            int sucessor = positions[i + 1];
            int calculatedTileSide = calculateContactSideOfTileToOther(predecessor, sucessor);

            currentPairPath = table[predecessor].getConnectedColor(calculatedTileSide, table[sucessor]);
            if (actualPath != null) {
                //True: currently there is a path being followed
                if (currentPairPath == LineType.NONE || currentPairPath != actualPath) {
                    //True: the connection color of the current pair is NONE or
                    //the actual path color is different to the one of the current pair
                    actualPath = LineType.NONE;
                    break;
                }
            }
            actualPath = currentPairPath;
        }
        return actualPath;
    }

    /**
     * Calculates the contact side of a tile to a neighbor one in the board.<br>
     * Support function for {@link #getConnectedPathColor(int[])}
     * @param referenceTile The position of the main tile in the board
     * @param neighborTile The position of the neighboring tile in the board 
     * @return A number between {@code 0} and {@linkplain Tile #NUMBER_SIDES n}-1
     */
    private int calculateContactSideOfTileToOther(int referenceTile, int neighborTile) {
        int diff = Math.abs(neighborTile - referenceTile);
        int calculatedContactSide = -1;

        switch(diff) {
            case(VERTICAL):
                calculatedContactSide = referenceTile > neighborTile ? 0 : 3;
                break;
            case(MAIN_DIAGONAL):
                calculatedContactSide = referenceTile > neighborTile ? 5 : 2;
                break;
            case(SECUNDARY_DIAGONAL):
                calculatedContactSide = referenceTile > neighborTile ? 4 : 1; 
                break;
        }
        return calculatedContactSide;
    }

    //A.4.11
    /**
     * The instance is printed in a format similar to a matrix.<br>
     * Every set of {@link #ELEMENTS_COLUMN n}-elements are represented as a row.<br>
     * There will be {@link #ELEMENTS_ROW m} many rows.<br>
     */
    @Override    
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < TABLE_ELEMENTS; index++) {
            if (index != 0 && index % ELEMENTS_COLUMN ==  0) {
                //true: for every set of n-elements a new line feed must be concatenated
                builder.append('\n');
            }
            builder.append(table[index].superToString()).append(';');
        }
        return builder.toString();
    }

    //==================================================================================================================
    //endregion A.4
}
