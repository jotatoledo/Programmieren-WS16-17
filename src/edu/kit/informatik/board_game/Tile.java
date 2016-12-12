package edu.kit.informatik.board_game;

public class Tile {
    /**
     * Represents the size of the codification array for the tiles used in the game.<br>
     * <strong>Can not be changed</strong>
     */
    public static final int NUMBER_ELEMENTS = 6;
    /**
     * Represents the codification of the connection lines in the tile
     */
    private LineType[] lineTypes;

    //region A.2
    //==================================================================================================================

    //A.2.1
    /**
     * Initializes a new instance of {@linkplain Tile} using a specific codification of connection lines
     * @param lineTypes The codification of the connection lines to use.
     * {@code LineTypes.length} is {@linkplain #NUMBER_ELEMENTS}
     */
    public Tile(LineType[] lineTypes) {
        this.lineTypes = lineTypes;
    }

    //A.2.2
    /**
     * Initializes a new instance of {@linkplain Tile} that doesn't contains any connection lines
     */
    public Tile() {
        lineTypes = new LineType[NUMBER_ELEMENTS];
        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            lineTypes[i] = LineType.NONE;
        }
    }

    /**
     * 
     * @param index A number between {@code 0} and {@linkplain #NUMBER_ELEMENTS}{@code -1}
     * @return The value of {@link #lineTypes} at the given index
     */
    public LineType getLineTypeAtIndex(int index) {
        return lineTypes[index];
    }

    //A.2.4
    /**
     * Counts the colors of the connection lines in the tile
     * @return A number between 0 and 3
     */
    public int getNumberOfColors() {
        int countColors = 0;
        for (LineType element : lineTypes) {
            if (element.isColor())countColors++;
        }
        return countColors / 2;
    }

    //A.2.5
    /**
     * 
     * @param otherTile
     * @return
     */
    public boolean isExactlyEqualTo(Tile otherTile) {
        boolean isEqual = true;

        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            if (this.lineTypes[i] != otherTile.getLineTypeAtIndex(i)) {
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }

    //A.2.6
    /**
     * 
     * @return
     */
    public Tile copy() {
        LineType[] copyArray = new LineType[NUMBER_ELEMENTS];
        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            copyArray[i] = lineTypes[i];
        }
        return new Tile(copyArray);
    }

    //A.2.7 
    /**
     * 
     */
    public void rotateClockwise() {
        LineType lastElement = lineTypes[NUMBER_ELEMENTS - 1];

        for (int i = NUMBER_ELEMENTS - 1; i > 0; i--) {
            //copy the content in the "i"-cell to the previous one
            lineTypes[i] = lineTypes[i - 1];
        }
        lineTypes[0] = lastElement;
    }

    //A.2.8
    /**
     * 
     */
    public void rotateCounterClockwise() {
        LineType firstElement = lineTypes[0];
        for (int i = 0; i <= NUMBER_ELEMENTS - 2; i++) {
            lineTypes[i] = lineTypes[i + 1];
        }
        lineTypes[NUMBER_ELEMENTS - 1] = firstElement;
    }

    //A.2.9
    /**
     * 
     * @return {@code True} if the object contains no connection lines. {@code False} otherwise
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            if (this.lineTypes[i] != LineType.NONE) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    //A.2.10
    /**
     * 
     * @param otherTile
     * @return
     */
    public boolean isRotationEqualTo(Tile otherTile) {
        Tile clone = this.copy();
        boolean rotationIsEqualToOther = false;

        for (int i = 0; i < NUMBER_ELEMENTS - 1; i++) {
            if (clone.isExactlyEqualTo(otherTile)) {
                rotationIsEqualToOther = true;
                break;
            } else {
                clone.rotateClockwise();
            }
        }
        return rotationIsEqualToOther;
    }

    //A.2.11 
    public boolean canBeRecoloredTo(Tile otherTile) {
        boolean canBeRecolored = true;
        
        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            if (getLineTypeAtIndex(i).isColor() != otherTile.getLineTypeAtIndex(i).isColor()) {
                canBeRecolored = false;
                break;
            }
        }        
        return canBeRecolored;
    }

    //A.2.12 
    public boolean dominates(Tile otherTile) {
        boolean dominates = true;
        
        if (isExactlyEqualTo(otherTile)) {
            dominates = false;
        } else {
            for (int i = 0; i < NUMBER_ELEMENTS; i++) {
                if (otherTile.getLineTypeAtIndex(i).isColor()
                        && !getLineTypeAtIndex(i).isColor()) {
                    dominates = false;
                    break;
                }
            }
        }        
        return dominates;
    }

    //A.2.13 
    public boolean hasSameColorsAs(Tile otherTile) {
       return hasGreen() == otherTile.hasGreen() 
               && hasRed() == otherTile.hasRed()
               && hasYellow() == otherTile.hasYellow();
    }

    /**
     * 
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return
     */
    private boolean hasRed() {
        boolean hasRed = false;
        
        for (LineType element : lineTypes) {
            if (element == LineType.RED) {
                hasRed = true;
                break;
            }
        }
        return hasRed;
    }
    
    /**
     * 
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return
     */
    private boolean hasGreen() {
        boolean hasGreen = false;
        
        for (LineType element : lineTypes) {
            if (element == LineType.GREEN) {
                hasGreen = true;
                break;
            }
        }
        return hasGreen;
    }
    
    /**
     * 
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return
     */
    private boolean hasYellow() {
        boolean hasYellow = false;
        
        for (LineType element : lineTypes) {
            if (element == LineType.YELLOW) {
                hasYellow = true;
                break;
            }
        }
        return hasYellow;
    }
    
    //A.2.14 
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (LineType element : lineTypes) {
            builder.append(element.getAbbreviation());
        }
        return builder.toString();
    }

    //==================================================================================================================
    //endregion A.2

    //region A.3

    //A.3.1
    /**
     * 
     * @param otherTile 
     * @param position A value between {@code 0} and {@linkplain #NUMBER_ELEMENTS}{@code -1}
     * @return
     */
    public boolean fitsTo(Tile otherTile, int position) {
        int reflectedPosition = calculateReflectPosition(position);

        return lineTypes[position].fitsTo(otherTile.getLineTypeAtIndex(reflectedPosition));
    }

    /**
     * Calculates a mirrored position inside a tile.
     * Support method for {@linkplain Tile#fitsTo(Tile, int)}.
     * @param position A value between {@code 0} and {@linkplain #NUMBER_ELEMENTS}{@code -1}
     * @return The mirrored position in relation to the given value
     */
    private int calculateReflectPosition(int position) {
        return (position + NUMBER_ELEMENTS / 2 ) % NUMBER_ELEMENTS;
    }

    //==================================================================================================================
    //endregion A.3
    
    public LineType getConnectedColor(int index, Tile otherTile) {
        int reflectedIndex = calculateReflectPosition(index);
        LineType color = getLineTypeAtIndex(index) == otherTile.getLineTypeAtIndex(reflectedIndex) 
                ? getLineTypeAtIndex(index) : LineType.NONE;
        return color;
    }
}