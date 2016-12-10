package edu.kit.informatik.kachelung;

public class BoardTile {
    private Tile tile;
    private PositionInBoard position;
    
    public BoardTile(Tile tile, PositionInBoard position) {
        this.tile = tile;
        this.position = position;
    }
}
