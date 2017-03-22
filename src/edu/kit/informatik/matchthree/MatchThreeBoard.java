package edu.kit.informatik.matchthree;

import java.util.Arrays;
import java.util.HashSet;
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
    private final int columnCount;
    private final int rowCount;
    private final Set<Token> tokens;
    private final Token[][] board;
    private FillingStrategy strategy;
    // FIXME check valid function parameters (require not null)

    /**
     * FIXME add doc
     * @param tokens FIXME add doc
     * @param columnCount FIXME add doc
     * @param rowCount FIXME add doc
     * @throws BoardDimensionException if the number of rows or columns is < 2
     * @throws IllegalArgumentException if the number of tokens is < 2
     */
    public MatchThreeBoard(final Set<Token> tokens, final int columnCount, final int rowCount) {
        if (rowCount < 2) throw new BoardDimensionException("invalid row count");
        if (columnCount < 2) throw new BoardDimensionException("invalid column count");
        if (tokens.size() < 2) throw new IllegalArgumentException("invalid amount of tokens");
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.tokens = tokens;
        this.board = new Token[rowCount][columnCount];
        this.strategy = null;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                this.board[row][col] = null;
            }
        }
    }

    /**
     * FIXME add doc
     * @param tokens FIXME add doc
     * @param tokenString FIXME add doc
     * @throws BoardDimensionException if the number of rows or columns is < 2
     * @throws IllegalArgumentException if the number of tokens is < 2
     * @throws TokenStringParseException if the given token string have a different amount of columns per row
     * or one of the tokens in it is invalid
     */
    public MatchThreeBoard(final Set<Token> tokens, final String tokenString) {
        // FIXME add regex check?
        // Check valid number of tokens
        if (tokens.size() < 2)
            throw new IllegalArgumentException("invalid amount of tokens");
        this.tokens = tokens;
        // Split tokenString by ;
        final String[] split = tokenString.split(";");
        // check that the splited rows have the same length
        if (Arrays.stream(split).mapToInt(x->x.length()).distinct().count() != 1)
            throw new TokenStringParseException("different number of elements per row");
        // matrix creation
        if (split.length < 2) throw new BoardDimensionException("invalid row count");
        if (split[0].length() < 2) throw new BoardDimensionException("invalid column count");
        this.rowCount = split.length;
        this.columnCount = split[0].length();
        this.board = new Token[rowCount][columnCount];
        // Iterate the tokenString
        for (int rowIndex = 0; rowIndex < split.length; rowIndex++) {
            String rowString = split[rowIndex];
            for (int columnIndex = 0; columnIndex < rowString.length(); columnIndex++) {
                char c = rowString.charAt(columnIndex);
                if (c == ' ')// the token is empty
                    this.board[rowIndex][columnIndex] = null;
                else {
                    Token token = new Token(c);
                    // Check if the token exist in th accepted token set
                    if (!tokens.contains(token))
                        throw new TokenStringParseException("unknown token in the token string");
                    this.board[rowIndex][columnIndex] = token;
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
        return this.columnCount;
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
    public void setTokenAt(final Position position, final Token newToken)
            throws BoardDimensionException, IllegalTokenException {
        // check if the position is inside the board boundaries
        checkValidPosition(position);
        if (newToken == null)
            // true: the given token is null -> set null in given position
            this.board[position.y][position.x] = null;
        else {
            if (!this.tokens.contains(newToken))
                // true: the token doesn't belong to the set of accepted tokens
                throw new IllegalTokenException("invalid token");
            // set the new token
            this.board[position.y][position.x] = newToken;
        }
    }

    @Override
    public boolean containsPosition(final Position position) {
        Objects.requireNonNull(position, "null position");
        return position.x >= 0 && position.x <= this.columnCount - 1 
                && position.y >= 0 && position.y <= this.rowCount - 1;
    }

    @Override
    public Set<Position> moveTokensToBottom() {
        Position p;
        Position overP;
        
        final MatchThreeBoard copy = copy();
        // start from last row
        for (int counter = 0; counter < this.rowCount; counter++) {
            for (int rowIndex = this.rowCount - 1; rowIndex > counter; rowIndex--) {
                for (int columnIndex = 0; columnIndex < this.columnCount; columnIndex++) {
                    p = Position.at(columnIndex, rowIndex);
                    if (this.getTokenAt(p) == null) {
                        // true: a position is free
                        overP = p.plus(0, -1);
                        if (this.getTokenAt(overP) != null) {
                            // true: there is a token above an empty position
                            this.swapTokens(p, overP);
                        } 
                    }
                }
            }
        }
        return changed(copy);
    }

    private Set<Position> changed(final MatchThreeBoard copy) {
        final Set<Position> affected = new HashSet<Position>();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Position p = Position.at(col, row);
                if (getTokenAt(p) != copy.getTokenAt(p))
                    affected.add(p);
            }
        }
        return affected;
    }

    
    private MatchThreeBoard copy() {
        final MatchThreeBoard copy = new MatchThreeBoard(getAllValidTokens(), getColumnCount(), getRowCount());
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Position p = Position.at(col, row);
                copy.setTokenAt(p, getTokenAt(p));
            }
        }
        return copy;
    }

    @Override
    public void swapTokens(final Position positionA, final Position positionB) throws BoardDimensionException {
        // FIXME remove double chekc
        checkValidPosition(positionA);
        checkValidPosition(positionB);
        if (!positionA.equals(positionB)) {
            Token buff = this.getTokenAt(positionA);
            this.setTokenAt(positionA, this.getTokenAt(positionB));
            this.setTokenAt(positionB, buff);
        }
    }

    @Override
    public void removeTokensAt(final Set<Position> positions) throws BoardDimensionException {
        // Check that all the positions are valid before making changes
        positions.forEach(p -> this.checkValidPosition(p));
        for (Position p : positions) {
            this.setTokenAt(p, null);
        }
    }

    @Override
    public void setFillingStrategy(final FillingStrategy strategy) {
        Objects.requireNonNull(strategy, "setFillingStrategy -> the filling strategy cant be null");
        this.strategy = strategy;
    }

    @Override
    public void fillWithTokens() throws NoFillingStrategyException {
        if (this.strategy == null)
            throw new NoFillingStrategyException();
        this.strategy.fill(this);
    }

    @Override
    public String toTokenString() {
        // FIXME check
        return Arrays.stream(this.board)
                .map(x -> Arrays.stream(x).map(t -> t == null ? " " : t.toString()).collect(Collectors.joining()))
                .collect(Collectors.joining(";"));
    }

    /**
     * FIXME add doc
     * @param p FIXME add doc
     * @throws BoardDimensionException if the position isn't valid in the board (the coordinates are out of bounds)
     */
    private void checkValidPosition(final Position p) throws BoardDimensionException {
        Objects.requireNonNull(p, "null position");
        if (p.x < 0 || p.x > this.columnCount - 1)
            throw new BoardDimensionException("the position has an invalid x coordinate");
        if (p.y < 0 || p.y > this.rowCount - 1)
            throw new BoardDimensionException("the position has an invalid y coordinate");
    }
}
