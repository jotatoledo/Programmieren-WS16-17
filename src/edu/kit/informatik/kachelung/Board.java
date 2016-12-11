package edu.kit.informatik.kachelung;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Board {
   
    public static final int ELEMENTS_IN_COLUMN = 3;
    public static final int ELEMENTS_IN_ROW = 4;
    /**
     * Represents the number of tiles in the game board.
     */
    public static final int TABLE_ELEMENTS = ELEMENTS_IN_ROW * ELEMENTS_IN_COLUMN;
    private BoardTile[] table;

    //region A.4
    //==================================================================================================================

    //A.4.1
    public Board() {
        table = new BoardTile[TABLE_ELEMENTS];
        for (int index = 0; index < TABLE_ELEMENTS; index++) {
            table[index] = new BoardTile(new Tile(), PositionInBoard.calculateTablePosition(index));
        }
    }

    //A.4.2
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @return
     */
    public Tile getTile(int position) {
        return table[position].getTile();
    }

    //A.4.3
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @param newTile An instance of {@linkplain Tile}
     */
    public void setTile(int position, Tile newTile) {
        table[position].setTile(newTile);
    }

    //A.4.4
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     */
    public void removeTile(int position) {
        table[position].setTile(new Tile());
    }

    //A.4.5
    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (BoardTile element : table) {
            if (!element.isEmpty()) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    //A.4.6
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     */
    public void rotateTileClockwise(int position) {
        table[position].rotateClockwise();
    }

    //A.4.7
    /**
     * 
     * @param position
     */
    public void rotateTileCounterClockwise(int position) {
        table[position].rotateCounterClockwise();
    }

    //A.4.8
    /**
     * 
     * @return
     */
    public int getNumberOfColors() {
        int numberColors = 0;

        for (BoardTile element : table) {
            numberColors = element.getNumberOfColors() > numberColors ? element.getNumberOfColors() : numberColors;
            if (numberColors == 3)break;
        }
        return numberColors;
    }

    //A.4.9 
    public boolean isValid() {
        boolean isValid = true;
        
        for (int i = 0; i < Board.TABLE_ELEMENTS; i++) {
            PositionInBoard tablePosition = table[i].getPosition();
            int[] positions = tablePosition.getPositions();
            for (int position : positions) {
                Tile otherTile = getTile(calculateIndex(i, position));
                if (!table[i].getTile().fitsTo(otherTile, position)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid)break;
        }
        return isValid;
    }
    
    /**
     * 
     * Support function for {@linkplain #isValid()}.
     * @param index A position of the codification array of the board
     * @param position A position of the codification array of a tile
     * @return
     */
    private int calculateIndex(int index, int position) {
        int calculatedIndex = 0;
        
        switch(position) {
            case(0):
                calculatedIndex = index - 1;
                break;
            case(1):
                calculatedIndex = index + Board.ELEMENTS_IN_COLUMN - 1;
                break;
            case(2):
                calculatedIndex = index + Board.ELEMENTS_IN_COLUMN;
                break;
            case(3):
                calculatedIndex = index + 1;
                break;
            case(4):
                calculatedIndex = index - Board.ELEMENTS_IN_COLUMN + 1;
                break;
            case(5):
                calculatedIndex = index - Board.ELEMENTS_IN_COLUMN;
                break;
        }
        return calculatedIndex;
    }
    
    //A.4.10
    public LineType getConnectedPathColor(int[] positions) {
        throw new NotImplementedException();
    }
    
    private boolean areConnected(int indexIni, int indexEnd) {
        throw new NotImplementedException();
    }
    
    //A.4.11
    /**
     * 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < TABLE_ELEMENTS; index++) {
            if (index != 0 && index % ELEMENTS_IN_COLUMN ==  0) {
                builder.append('\n');
            }
            builder.append(table[index].tileToString()).append(';');
        }
        return builder.toString();
    }

    //==================================================================================================================
    //endregion A.4
}
