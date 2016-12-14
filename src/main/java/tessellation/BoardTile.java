package main.java.tessellation;

/**
 * Represents a {@linkplain Tile tile} in the {@linkplain Board game board}
 * in a specific {@linkplain BoardPosition position}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
public class BoardTile extends Tile {
    /**
     * The position of the tile in a board represented by an instance of {@linkplain board}
     */
    private final BoardPosition position;
    
    public BoardTile(BoardPosition position) {
        super();
        this.position = position;
    }
    
    public Tile getTile() {
        return new Tile(getLineTypes());
    }

    public void setTile(Tile tile) {
        setLineTypes(tile.getLineTypes());
    }
    
    /**
     * Getter for {@link #position}
     * @return The value of {@link #position}
     */
    public BoardPosition getPosition() {
        return position;
    }
    
    public String superToString() {
        return super.toString();
    }
    
    public String toString() {
        return position.toString().concat("\n").concat(super.toString());
    }
}
