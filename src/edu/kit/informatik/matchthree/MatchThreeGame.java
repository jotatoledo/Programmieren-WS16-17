package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * FIXME add doc
 * @author JoseNote
 *
 */
public class MatchThreeGame implements Game {
    private final Board board;
    private int score;
    private Matcher matcher;
    
    /**
     * Creates a new instance
     * @param board a board for the game
     * @param matcher a matcher
     */
    public MatchThreeGame(final Board board, final Matcher matcher) {
        Objects.requireNonNull(board, "the board is null");
        Objects.requireNonNull(matcher, "the matcher is null");
        this.board = board;
        this.matcher = matcher;
        score = 0;
    }

    @Override
    public void initializeBoardAndStart() {
        final Set<Position> affected = board.moveTokensToBottom();
        board.fillWithTokens();
        handleMatches(affected, 1);
    }

    @Override
    public void acceptMove(final Move move) {
        if (!move.canBeApplied(board))
            throw new BoardDimensionException("the move cant be applied on the board");
        final Set<Position> affected = move.getAffectedPositions(board);
        handleMatches(affected, 1);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setMatcher(final Matcher matcher) {
        Objects.requireNonNull(matcher, "the matcher is null");
        this.matcher = matcher;
    }
    
    private void handleMatches(final Set<Position> positions, int factor) {
        // Get the matches from the given positions
        final Set<Set<Position>> matches = matcher.matchAll(board, positions).stream()
                .distinct()
                .filter(s -> s.size() >= 3)
                .collect(Collectors.toSet());
        final Set<Set<Position>> filteredMatches = new HashSet<Set<Position>>();
        for (Set<Position> s1 : matches) {
            Boolean isSubset = false;
            for (Set<Position> s2 : matches) {
                if (s1 == s2)
                    // true: both sets are the same, makes no sense to compare
                    continue;
                if (s2.containsAll(s1)) {
                    // true: another set "s2" contains s1, so s1 is a subset
                    isSubset = true;
                    break;
                }
            }
            if (!isSubset)
                // true: s1 isn't a subset
                filteredMatches.add(s1);
        }
        // Calculate this round score
        final int score = filteredMatches.stream()
                .mapToInt(s -> (3 + (s.size() - 3) * 2))
                .sum();
        // Add the roun score to the total score
        this.score += score * factor * filteredMatches.size();
        // Remove the matches from the board
        filteredMatches.forEach(s -> board.removeTokensAt(s));
        final Set<Position> affectedByPushBottom = board.moveTokensToBottom();
        board.fillWithTokens();
        handleMatches(affectedByPushBottom, factor + 1);
    }
}
