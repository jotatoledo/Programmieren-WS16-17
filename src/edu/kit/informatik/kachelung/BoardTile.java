package edu.kit.informatik.kachelung;

public class BoardTile {
    private Tile tile;
    private PositionInBoard position;
    
    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public PositionInBoard getPosition() {
        return position;
    }

    public void setPosition(PositionInBoard position) {
        this.position = position;
    }
    
    public BoardTile(Tile tile, PositionInBoard position) {
        this.tile = tile;
        this.position = position;
    }
    
    public boolean isEmpty() {
        return tile.isEmpty();
    }
    
    public void rotateClockwise() {
        tile.rotateClockwise();
    }
    
    public void rotateCounterClockwise() {
        tile.rotateCounterClockwise();
    }
    
    public int getNumberOfColors() {
        return tile.getNumberOfColors();
    }
}
