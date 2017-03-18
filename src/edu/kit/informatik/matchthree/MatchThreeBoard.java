package edu.kit.informatik.matchthree;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.FillingStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.IllegalTokenException;
import edu.kit.informatik.matchthree.framework.exceptions.NoFillingStrategyException;
import edu.kit.informatik.matchthree.framework.exceptions.TokenStringParseException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

/**
 * 
 */
public class MatchThreeBoard implements Board {
	private final int columncount;
	private final int rowCount;
	private final Set<Token> tokens;
	private final Token[][] board;
	private FillingStrategy strategy;
	// FIXME check valid function parameters (require not null)
	
    /**
     * 
     * @param tokens
     * @param columnCount
     * @param rowCount
     */
    public MatchThreeBoard(final Set<Token> tokens, final int columnCount, final int rowCount) {
        if(rowCount< 2) throw new BoardDimensionException("invalid row count");
        if(columnCount < 2) throw new BoardDimensionException("invalid column count");
        if(tokens.size() < 2) throw new IllegalArgumentException("invalid amount of tokens");
        this.rowCount = rowCount;
        this.columncount = columnCount;
        this.tokens = tokens;
        this.board = new Token[rowCount][columnCount];
        this.strategy = null;
        // FIXME set null in positions
    }

    /**
     * 
     * @param tokens
     * @param tokenString
     */
    public MatchThreeBoard(final Set<Token> tokens, final String tokenString) {
    	// Check valid number of tokens
    	if(tokens.size() < 2) throw new IllegalArgumentException("invalid amount of tokens");
    	this.tokens = tokens;
    	// Split tokenString by ;
    	String[] split = tokenString.split(";");
    	long count = Arrays.stream(split).mapToInt(x->x.length()).distinct().count();
    	// check that the splited rows have the same length
    	if(count != 1) throw new TokenStringParseException("different number of elements per row");
    	// FIXME check matrix creation
    	this.rowCount = split.length;
    	this.columncount = split[0].length();
    	this.board = new Token[rowCount][columncount];
    	// Iterate the tokenString
    	for (int i = 0; i< split.length; i++){
    		String row = split[i];
    		for(int j = 0; j < row.length(); j++){
    			char c = row.charAt(j);
    			if (c == ' ')// the token is empty
    				this.board[i][j] = null;
    			else {
    				Token t = new Token(row.charAt(j));
    				// Check if the token exist in th accepted token set
        			if(!tokens.contains(t)) throw new TokenStringParseException("unknown token in the token string");
        			this.board[i][j] = t;
    			}
    		}
    	}
    	this.strategy = null;
    }

    @Override
    public Set<Token> getAllValidTokens() {
        return this.tokens;
    }

    @Override
    public int getColumnCount() {
        return this.columncount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public Token getTokenAt(final Position position) throws BoardDimensionException {
    	checkValidPosition(position);
        return this.board[position.y][position.x];
    }

    @Override
    public void setTokenAt(final Position position, final Token newToken) throws BoardDimensionException, IllegalTokenException {
    	// check if the position is inside the board boundaries
    	checkValidPosition(position);
    	// if token = null -> remove token from board at the given position
    	if(newToken == null) this.board[position.y][position.x] = null;
    	else{
    		// the token doesnt belong to the set of accepted tokens
    		if(!this.tokens.contains(newToken)) throw new IllegalTokenException("invalid token");
    		// set the new token
    		this.board[position.y][position.x] = new Token(newToken.toString());
    	}
    }

    @Override
    public boolean containsPosition(final Position position){
    	return position.x >= 0 && position.x <= this.columncount - 1 && position.y >= 0 && position.y <= this.rowCount -1;
    }

    @Override
    public Set<Position> moveTokensToBottom() {
    	Position p, overP;
    	
    	// start from last row
        for (int row = this.rowCount - 1; row > 0; row--) {
        	for (int column = 0; column < this.columncount - 1; column++) {
        		p = new Position(row, column);
        		if (this.getTokenAt(p) == null){
        			overP = new Position(row - 1, column);
        			// if the position over p has a, swap the tokens in the positions
        			if(this.getTokenAt(overP) != null)
        				this.swapTokens(p, overP);        			
        		}
        	}
        }
        return null;
    }

    @Override
    public void swapTokens(final Position positionA, final Position positionB) throws BoardDimensionException {
    	// FIXME remove double chekc
//        checkValidPosition(positionA);
//        checkValidPosition(positionB);
        if(!positionA.equals(positionB)){
        	Token buff = this.getTokenAt(positionA);
        	this.setTokenAt(positionA, this.getTokenAt(positionB));
        	this.setTokenAt(positionB, buff);
        }
    }

    @Override
    public void removeTokensAt(final Set<Position> positions) throws BoardDimensionException {
    	// Check that all the positions are valid before making changes
    	positions.forEach(p -> this.checkValidPosition(p));
        for(Position p : positions){
        	this.setTokenAt(p, null);
        }
    }

    @Override
    public void setFillingStrategy(final FillingStrategy strategy) {
    	Objects.requireNonNull(strategy, "the filling strategy cant be null");
        this.strategy = strategy;
    }

    @Override
    public void fillWithTokens() throws NoFillingStrategyException {
        if(this.strategy == null) throw new NoFillingStrategyException();
        this.strategy.fill(this);
    }

    @Override
    public String toTokenString() {
    	// FIXME check
    	return Arrays.stream(this.board)
    			.map(x -> Arrays.stream(x).map(t -> t.toString()).collect(Collectors.joining()))
    			.collect(Collectors.joining(";"));
    }
    
    /**
     * FIXME add doc
     * @param p FIXME add doc
     * @throws BoardDimensionException if the position isnt valid in the board (the coordinates are out of bounds)
     */
    private void checkValidPosition(final Position p) throws BoardDimensionException {
    	if (p.x < 0 || p.x > this.columncount - 1) throw new BoardDimensionException("the position has an invalid x coordinate");
    	if (p.y < 0 || p.y > this.rowCount -1) throw new BoardDimensionException("the position has an invalid y coordinate");
    }
}
