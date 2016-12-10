package edu.kit.informatik.kachelung;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Board {
   
    public static final int NUMBER_ELEMENTS_IN_COLUMN = 3;
    public static final int NUMBER_ELEMENTS_IN_ROW = 4;
    /**
     * Represents the number of tiles in the game board.
     */
    public static final int TABLE_ELEMENTS = NUMBER_ELEMENTS_IN_ROW * NUMBER_ELEMENTS_IN_COLUMN;
    private Tile[] table;

    //region A.4
    //==================================================================================================================

    //A.4.1
    public Board() {
        table = new Tile[TABLE_ELEMENTS];
        for (int index = 0; index < TABLE_ELEMENTS; index++) {
            table[index] = new Tile();
        }
    }

    //A.4.2
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @return
     */
    public Tile getTile(int position) {
        return table[position];
    }

    //A.4.3
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     * @param newTile An instance of {@linkplain Tile}
     */
    public void setTile(int position, Tile newTile) {
        table[position] = newTile;
    }

    //A.4.4
    /**
     * 
     * @param position A value between {@code 0} and {@linkplain #TABLE_ELEMENTS}{@code -1}
     */
    public void removeTile(int position) {
        table[position] = new Tile();
    }

    //A.4.5
    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (Tile element : table) {
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

        for (Tile element : table) {
            numberColors = element.getNumberOfColors() > numberColors ? element.getNumberOfColors() : numberColors;
            if (numberColors == 3)break;
        }
        return numberColors;
    }

    //A.4.9 
    public boolean isValid() {
        throw new NotImplementedException();
    }
    
    //A.4.10
    public LineType getConnectedPathColor(int[] positions) {
        throw new NotImplementedException();
    }
    
    //A.4.11
    /**
     * 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < TABLE_ELEMENTS; index++) {
            if (index != 0 && index % NUMBER_ELEMENTS_IN_COLUMN ==  0) {
                builder.append('\n');
            }
            builder.append(table[index].toString()).append(';');
        }
        return builder.toString();
    }

    //==================================================================================================================
    //endregion A.4
    
    //region Extra methods
    //==================================================================================================================
    
    public Board(Tile[] table) {
        this.table = table;
    }
    
    public Board copy() {
        Tile[] elements = new Tile[TABLE_ELEMENTS];
        
        for (int i = 0; i < TABLE_ELEMENTS; i++) {
           elements[i] = table[i].copy(); 
        }        
        return new Board(elements);
    }
    
    //==================================================================================================================
    //endregion Extra methods
}
