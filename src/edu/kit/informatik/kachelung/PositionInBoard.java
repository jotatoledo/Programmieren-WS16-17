package edu.kit.informatik.kachelung;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum PositionInBoard {
    UPPER_RIGHT_CORNER(3, 4, 5),
    DOWN_RIGH_CORNER(0, 4, 5),
    UPPER_LEFT_CORNER(2, 3),
    DOWN_LEFT_CORNER(0, 1, 2),
    INTERNAL(0, 1, 2, 3, 4, 5),
    UPPER_SIDE(2, 3, 4, 5),
    DOWN_SIDE(0, 1, 2, 5),
    LEFT_SIDE(0, 1, 2, 3),
    RIGHT_SIDE(0, 3, 4, 5);  
    
    public static final int UPPER_LEFT_CORNER_VALUE = 0;
    public static final int DOWN_LEFT_CORNER_VALUE = Board.ELEMENTS_IN_COLUMN - 1;
    public static final int UPPER_RIGHT_CORNER_VALUE = (Board.ELEMENTS_IN_ROW - 1) * Board.ELEMENTS_IN_COLUMN;
    public static final int DOWN_RIGHT_CORNER_VALUE = (Board.ELEMENTS_IN_ROW * Board.ELEMENTS_IN_COLUMN) - 1;
    
    private final int[] positions;
    
    PositionInBoard(int... positions) {
        this.positions = new int[positions.length];        
    }
    
    public int[] getPositions() {
        return positions;
    }
    
    public PositionInBoard calculatePosition(int position) {
        PositionInBoard boardPositon = null;
        
        switch(position) {
             case(UPPER_LEFT_CORNER_VALUE):
                 boardPositon = PositionInBoard.UPPER_LEFT_CORNER;
                 break;
             case(DOWN_LEFT_CORNER_VALUE):
                 boardPositon = PositionInBoard.DOWN_LEFT_CORNER;
                 break;
             case(UPPER_RIGHT_CORNER_VALUE):
                 boardPositon = PositionInBoard.UPPER_RIGHT_CORNER;
                 break;
             case(DOWN_RIGHT_CORNER_VALUE):
                 boardPositon = PositionInBoard.DOWN_RIGH_CORNER;
                 break;
             default:
                 if ( position % DOWN_LEFT_CORNER_VALUE != 0) {
                     boardPositon = PositionInBoard.LEFT_SIDE;
                 } else {
                    if ( position % DOWN_RIGHT_CORNER_VALUE != 0) {
                        boardPositon = PositionInBoard.RIGHT_SIDE;
                    } else {
                        if (position % Board.ELEMENTS_IN_COLUMN == 0) {
                            boardPositon = PositionInBoard.UPPER_SIDE;
                        } else {
                            if (position % Board.ELEMENTS_IN_COLUMN == DOWN_LEFT_CORNER_VALUE) {
                                boardPositon = PositionInBoard.DOWN_SIDE;
                            } else {
                                boardPositon = PositionInBoard.INTERNAL;
                            }
                        }
                    }
                 }
         }
         return boardPositon;
    }
}
