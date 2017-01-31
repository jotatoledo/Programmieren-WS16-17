package edu.kit.informatik.tessellation;

/**
 * An extension of {@linkplain Tile}<br>
 * Represents a {@linkplain Tile tile} in the {@linkplain Board game board}
 * in a specific {@linkplain BoardPosition position}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public class BoardTile extends Tile {
    /**
     * The position of the tile in a board surface represented by {@linkplain Board}
     */
    private final BoardPosition position;
    
    /**
     * Creates a new empty tile associated to a given {@linkplain BoardPosition}
     * @param position The {@linkplain BoardPosition} associated to the new object
     */
    public BoardTile(BoardPosition position) {
        super();
        this.position = position;
    }
    
    /**
     * Generates a new {@linkplain Tile} instance from the current object
     * @return A new {@linkplain Tile} instance with copies values of the current object
     */
    public Tile getTile() {
        return new Tile(getLineTypes());
    }
    
    @Override
    public void setLineTypes(LineType[] lineTypes) {
        super.setLineTypes(lineTypes);
    }
    
    /**
     * Getter for {@link #position}
     * @return The value of {@link #position}
     */
    public BoardPosition getPosition() {        
        return position;
    }
    
    /**
     * A forwarding method for {@linkplain Tile#toString()}
     * @return The result of {@linkplain Tile#toString()}
     */
    public String superToString() {
        return super.toString();
    }
    
    @Override
    public String toString() {
        return position.toString().concat("\n").concat(super.toString());
    }
}
