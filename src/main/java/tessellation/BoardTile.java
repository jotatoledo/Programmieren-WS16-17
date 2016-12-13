package main.java.tessellation;

/**
 * Represents a {@linkplain Tile tile} in the {@linkplain Board game board}
 * in a specific {@linkplain PositionInBoard position}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public class BoardTile {
    /**
     * The representation of a tile in a board represented by an instance of {@linkplain board}
     */
    private Tile tile;
    /**
     * The position of the tile in a board represented by an instance of {@linkplain board}
     */
    private PositionInBoard position;
    
    public BoardTile(Tile tile, PositionInBoard position) {
        this.tile = tile;
        this.position = position;
    }
    
    /**
     * Getter for {@link #tile}
     * @return The value of {@link #tile}
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Setter for {@link tile}
     * @param tile
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Getter for {@link #position}
     * @return The value of {@link #position}
     */
    public PositionInBoard getPosition() {
        return position;
    }

    /**
     * Setter for {@link position}
     * @param position
     */
    public void setPosition(PositionInBoard position) {
        this.position = position;
    }
    
    /**
     * Checks if the {@linkplain Tile tile} has no colors.
     * A tile has no colors when the codification list of the {@linkplain Tile} component
     * contains only references to {@linkplain LineType #NONE NONE}
     * @return {@code True} if the {@linkplain Tile} component has only references to {@linkplain LineType #NONE NONE}. 
     * {@code False} otherwise.
     */
    public boolean isEmpty() {
        return tile.isEmpty();
    }
    
    /**
     * Rotates the {@linkplain Tile tile} clockwise
     */
    public void rotateClockwise() {
        tile.rotateClockwise();
    }
    
    /**
     * Rotates the {@linkplain Tile tile} counter clockwise
     */
    public void rotateCounterClockwise() {
        tile.rotateCounterClockwise();
    }
    
    /**
     * Gets the number of colors of the {@linkplain Tile} component of the instance
     * @return 
     */
    public int getNumberOfColors() {
        return tile.getNumberOfColors();
    }
    
    public LineType getConnectedColor(int index, BoardTile otherBoardTile) {
        return tile.getConnectedColor(index, otherBoardTile.getTile());
    }
    
    /**
     * Generates the {@linkplain String} representation of the {@linkplain Tile} component of the instance
     * @return The {@linkplain String} representation of the {@linkplain Tile} component of the instance
     */
    public String tileToString() {
        return tile.toString();
    }
    
    public String toString() {
        return position.toString().concat("\n").concat(tile.toString());
    }
}
