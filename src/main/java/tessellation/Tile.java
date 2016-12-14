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

    protected LineType[] getLineTypes() {
        return lineTypes;
    }

    protected void setLineTypes(LineType[] lineTypes) {
        for (int i = 0; i < NUMBER_SIDES; i++) {
            this.lineTypes[i] = lineTypes[i];
        }
    }

    //region A.2
    //==================================================================================================================

    //A.2.1
    /**
     * Initializes a new instance of {@linkplain Tile} using a specific codification of connection lines
     * @param lineTypes The codification of the connection lines to use.
     * {@code LineTypes.length} is {@linkplain #NUMBER_SIDES}
     */
    public Tile(LineType[] lineTypes) {
        this.lineTypes = new LineType[NUMBER_SIDES];
        for (int i = 0; i < NUMBER_SIDES; i++) {
            this.lineTypes[i] = lineTypes[i];
        }
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
     * Get the {@linkplain LineType} reference at a given index of the sides codification array of the instance
     * @param index A number between {@code 0} and {@linkplain #NUMBER_SIDES}{@code -1}
     * @return The value of {@link #lineTypes} at the given index
     */
    public LineType getLineTypeAtIndex(int index) {
        return lineTypes[index];
    }

    //A.2.4
    /**
     * Counts the colors of the connection lines in the tile
     * @return A number between {@code 0} and {@code 3}
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
     * Checks if the given instance is exactly equal to a given {@linkplain Tile} instance
     * @param otherTile The {@linkplain Tile} instance to match
     * @return {@code True} if the instance has the same values as the given instance. {@code False} otherwise.
     */
    public boolean isExactlyEqualTo(Tile otherTile) {
        boolean isEqual = true;

        for (int i = 0; i < NUMBER_SIDES; i++) {
            if (this.lineTypes[i] != otherTile.getLineTypeAtIndex(i)) {
                //True: At a common index, the tiles have different values in their codification arrays
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }

    //A.2.6
    /**
     * Copies the instance values into a new {@linkplain Tile} instance.
     * @return A new {@linkplain Tile} instance with copied values of the current instance
     */
    public Tile copy() {
        return new Tile(lineTypes);
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
     * Checks if the instance has no colors.
     * A tile has no colors when the codification array of the sides
     * contains only references to {@linkplain LineType #NONE NONE}
     * @return {@code True} if the object contains no connection lines. {@code False} otherwise
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        for (int i = 0; i < NUMBER_SIDES; i++) {
            if (this.lineTypes[i].isColor()) {
                //True ; an element in the codification array is a color
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    //A.2.10
    /**
     * Checks if the current instance can be rotated to be equal to another {@linkplain Tile} instance
     * @param otherTile The objective {@linkplain Tile} instance
     * @return {@code True} if the actual instance can be converted to the objective one through rotation.
     * {@code False} otherwise.
     */
    public boolean isRotationEqualTo(Tile otherTile) {
        Tile clone = copy();
        boolean rotationIsEqualToOther = false;

        //The copy of the current instance will be rotated in a way
        //that it cannot be converted to the original state of the copy instance through rotation
        for (int i = 0; i < NUMBER_SIDES - 1; i++) {
            if (clone.isExactlyEqualTo(otherTile)) {
                //True: the current copy matches the given objective instance
                //In this case doesn't make sense to keep checking for matches of other rotation states of the copy
                rotationIsEqualToOther = true;
                break;
            } else {
                //False: it doesn't match.
                //Rotate the copy clockwise
                clone.rotateClockwise();
            }
        }
        return rotationIsEqualToOther;
    }

    //A.2.11
    /**
     * Checks if the current instance can be transformer to a given objective instance of {@linkplain Tile} 
     * through the recolor of its non {@linkplain LineType #NONE}-colored sides
     * @param otherTile The objective instance
     * @return {@code True} if the instance can be recolored into the objective instance.
     * {@code False} otherwise.
     */
    public boolean canBeRecoloredTo(Tile otherTile) {
        boolean canBeRecolored = true;

        for (int i = 0; i < NUMBER_SIDES; i++) {
            if (getLineTypeAtIndex(i).isColor() != otherTile.getLineTypeAtIndex(i).isColor()) {
                //True: At a common index, the instances have values that arent either colors or NONE at the same time
                //This means that a connection line should be added or 
                //removed to be transformer into the objective instance
                canBeRecolored = false;
                break;
            }
        }        
        return canBeRecolored;
    }

    //A.2.12
    /**
     * Checks if the current instance dominates a given instance
     * @param otherTile An instance of {@linkplain Tile}
     * @return {@code True} if the current instance dominates the given instance. {@code False} otherwise
     */
    public boolean dominates(Tile otherTile) {
        boolean dominates = true;

        if (isExactlyEqualTo(otherTile)) {
            //True: both instances are equal
            //In this case, the instance doesn't dominates the given one
            dominates = false;
        } else {
            for (int i = 0; i < NUMBER_SIDES; i++) {
                if (otherTile.getLineTypeAtIndex(i).isColor() && !getLineTypeAtIndex(i).isColor()) {
                    //True: At a common index, the given instance has a color but the current instance doesn't
                    //This hinders that the current instance could dominate the given one
                    dominates = false;
                    break;
                }
            }
        }        
        return dominates;
    }

    //A.2.13 
    /**
     * Checks if the instance has the same colors as a given {@linkplain Tile} instance.
     * @param otherTile An instance of {@linkplain Tile}
     * @return {@code True} if both instances have the same colors. {@code Falsa} otherwise.
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
     * Checks if the instance fits to a given {@linkplain Tile} instance
     * @param otherTile The {@linkplain Tile} instance to fit against
     * @param position A value between {@code 0} and {@linkplain #NUMBER_SIDES}{@code -1}
     * @return {@code True} if the instance fits to the given instance. {@code False} otherwise
     */
    public boolean fitsTo(Tile otherTile, int position) {     
        boolean fits = true;
        LineType instanceColor = lineTypes[position];
        LineType objectiveTileColor = otherTile.getLineTypeAtIndex(calculateReflectedSide(position));

        if (instanceColor.isColor() && objectiveTileColor.isColor()) {
            //true: only when to colors are being compared, they could not match
            if (instanceColor != objectiveTileColor)fits = false;
        }
        return fits;
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

    //region Extra methods
    //==================================================================================================================

    /**
     * <br>
     * Support method for {@linkplain Board #getConnectedPathColor(int[])}
     * @param sideIndex An index of the codification array of the current instance
     * @param otherTile A {@linkplain Tile} instance
     * @return A color, if the colors in the contact sides of the tiles represented by the instances match. 
     * {@linkplain LineType #NONE} otherwise.
     */
    protected LineType getSidesContactColor(int sideIndex, Tile otherTile) {
        LineType sidesContactColor = LineType.NONE;
        LineType instanceSideColor = lineTypes[sideIndex];
        LineType otherTileSideColor = otherTile.getLineTypeAtIndex(calculateReflectedSide(sideIndex));

        if (instanceSideColor.isColor() && otherTileSideColor.isColor()) {
            if (instanceSideColor == otherTileSideColor)sidesContactColor = instanceSideColor;
        }
        return sidesContactColor;
    }

    //==================================================================================================================
    //endregion Extra methods
}
