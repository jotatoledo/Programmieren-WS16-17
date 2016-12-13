package main.java.tessellation;

/**
 * Represents a tile with {@linkplain Tile #NUMBER_SIDES n}-sides
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public class Tile {
    /**
     * Represents the number of sides of the tile and the size 
     * of the codification array for the tiles used in the game.<br>
     * <strong>Can not be changed</strong>
     */
    public static final int NUMBER_SIDES = 6;
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
     * {@code LineTypes.length} is {@linkplain #NUMBER_SIDES}
     */
    public Tile(LineType[] lineTypes) {
        this.lineTypes = lineTypes;
    }

    //A.2.2
    /**
     * Initializes a new instance of {@linkplain Tile} that doesn't contains any connection lines
     */
    public Tile() {
        lineTypes = new LineType[NUMBER_SIDES];
        for (int i = 0; i < NUMBER_SIDES; i++) {
            lineTypes[i] = LineType.NONE;
        }
    }

    /**
     * 
     * @param index A number between {@code 0} and {@linkplain #NUMBER_SIDES}{@code -1}
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

        for (int i = 0; i < NUMBER_SIDES; i++) {
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
        LineType[] copyArray = new LineType[NUMBER_SIDES];
        
        for (int i = 0; i < NUMBER_SIDES; i++) {
            copyArray[i] = lineTypes[i];
        }
        return new Tile(copyArray);
    }

    //A.2.7 
    /**
     * Rotates the instance 60° clockwise
     */
    public void rotateClockwise() {
        LineType lastElement = lineTypes[NUMBER_SIDES - 1];

        for (int i = NUMBER_SIDES - 1; i > 0; i--) {
            //copy the content in the "i"-cell to the previous one
            lineTypes[i] = lineTypes[i - 1];
        }
        lineTypes[0] = lastElement;
    }

    //A.2.8
    /**
     * Rotates the instance 60° counterclockwise
     */
    public void rotateCounterClockwise() {
        LineType firstElement = lineTypes[0];
        
        for (int i = 0; i <= NUMBER_SIDES - 2; i++) {
            lineTypes[i] = lineTypes[i + 1];
        }
        lineTypes[NUMBER_SIDES - 1] = firstElement;
    }

    //A.2.9
    /**
     * 
     * @return {@code True} if the object contains no connection lines. {@code False} otherwise
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (int i = 0; i < NUMBER_SIDES; i++) {
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

        for (int i = 0; i < NUMBER_SIDES - 1; i++) {
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
    /**
     * 
     * @param otherTile
     * @return
     */
    public boolean canBeRecoloredTo(Tile otherTile) {
        boolean canBeRecolored = true;
        
        for (int i = 0; i < NUMBER_SIDES; i++) {
            if (getLineTypeAtIndex(i).isColor() != otherTile.getLineTypeAtIndex(i).isColor()) {
                canBeRecolored = false;
                break;
            }
        }        
        return canBeRecolored;
    }

    //A.2.12
    /**
     * 
     * @param otherTile
     * @return {@code True} if the current instance dominates the given instance. {@code False} otherwise
     */
    public boolean dominates(Tile otherTile) {
        boolean dominates = true;
        
        if (isExactlyEqualTo(otherTile)) {
            dominates = false;
        } else {
            for (int i = 0; i < NUMBER_SIDES; i++) {
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
    /**
     * 
     * @param otherTile
     * @return
     */
    public boolean hasSameColorsAs(Tile otherTile) {
       return hasGreen() == otherTile.hasGreen() 
               && hasRed() == otherTile.hasRed()
               && hasYellow() == otherTile.hasYellow();
    }

    /**
     * Checks if the instance contains the {@linkplain LineType#RED} color.<br>
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return {@code True} if the tile contains the color {@linkplain LineType#RED}. {@code False} otherwise.
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
     * Checks if the instance contains the {@linkplain LineType#GREEN} color.<br>
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return {@code True} if the tile contains the color {@linkplain LineType#GREEN}. {@code False} otherwise.
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
     * Checks if the instance contains the {@linkplain LineType#YELLOW} color.<br>
     * Support function for {@link #hasSameColorsAs(Tile)}
     * @return {@code True} if the tile contains the color {@linkplain LineType#YELLOW}. {@code False} otherwise.
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
    /**
     * Returns the text representation of the tile.<br>
     * The text representations of the colors in the codification array of the sides of the tile will be concatenated
     */
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
     * @param position A value between {@code 0} and {@linkplain #NUMBER_SIDES}{@code -1}
     * @return
     */
    public boolean fitsTo(Tile otherTile, int position) {
        int reflectedPosition = calculateReflectedSide(position);

        return lineTypes[position].fitsTo(otherTile.getLineTypeAtIndex(reflectedPosition));
    }

    /**
     * Calculates the mirrored side of a tile.<br>
     * Support method for {@linkplain Tile#fitsTo(Tile, int)}.
     * @param position A value between {@code 0} and {@linkplain #NUMBER_SIDES}{@code -1}
     * @return The mirrored position in relation to the given value
     */
    private int calculateReflectedSide(int position) {
        return (position + NUMBER_SIDES / 2 ) % NUMBER_SIDES;
    }

    //==================================================================================================================
    //endregion A.3
    
    /**
     * <br>
     * Support method for {@linkplain Board#getConnectedPathColor(int[])}
     * @param index
     * @param otherTile
     * @return
     */
    public LineType getConnectedColor(int index, Tile otherTile) {
        int reflectedIndex = calculateReflectedSide(index);
        LineType color = getLineTypeAtIndex(index) == otherTile.getLineTypeAtIndex(reflectedIndex) 
                ? getLineTypeAtIndex(index) : LineType.NONE;
        return color;
    }
}
