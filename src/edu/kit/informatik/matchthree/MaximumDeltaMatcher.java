package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.MatcherInitializationException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;

/**
 *
 */
public class MaximumDeltaMatcher implements Matcher {
    private final Set<Delta> deltas;
    
    /**
     * Creates a new instance with the given deltas
     * @param deltas a set of deltas
     * @throws MatcherInitializationException if no valid delta was given
     */
    public MaximumDeltaMatcher(final Set<Delta> deltas) {
        final Set<Delta> filtered = deltas.stream()
                .filter(d -> d != null && !d.equals(Delta.dxy(0, 0)))
                .distinct()
                .collect(Collectors.toSet());
        if (filtered.size() == 0)
            throw new MatcherInitializationException("invalid delta");
        this.deltas = filtered;
    }

    @Override
    public Set<Set<Position>> match(final Board board, final Position initial) throws BoardDimensionException {
        if (!board.containsPosition(initial))
            throw new BoardDimensionException("initial not contained in the board");
        final Set<Set<Position>> result = new HashSet<Set<Position>>();
        final Set<Position> matches = new HashSet<Position>();
        final Token searchedToken = board.getTokenAt(initial);
        if (searchedToken != null)
            recursiveMatch(searchedToken, board, initial, matches);        
        result.add(matches);
        return result;
    }

    private void recursiveMatch(final Token searchedToken, final Board board,
            final Position toVisit, final Set<Position> matches) {
        if (board.containsPosition(toVisit) 
                && board.getTokenAt(toVisit) != null
                && board.getTokenAt(toVisit).equals(searchedToken) 
                && matches.add(toVisit)) {
            // true: the position to visit exist in the board, 
            // the token in it isn't null
            // contains the searched token 
            // and wasn't contained in the matches already
            for (Delta d: deltas) {
                recursiveMatch(searchedToken, board, toVisit.plus(d), matches);
                recursiveMatch(searchedToken, board, toVisit.plus(d.negate()), matches);
            }
        }
    }
    
    @Override
    public Set<Set<Position>> matchAll(final Board board, final Set<Position> initial) throws BoardDimensionException {
        final Set<Set<Position>> result = new HashSet<Set<Position>>();
        
        // check that all the given initial positions are valid
        initial.forEach(p -> {
            if (!board.containsPosition(p))
                throw new BoardDimensionException("one of the given positions isnt contained in the board");
        });
        for (Position p : initial) {
            Set<Position> matches = new HashSet<Position>();
            Token searchedToken = board.getTokenAt(p);
            if (searchedToken != null) {
                recursiveMatch(searchedToken, board, p, matches); 
                result.add(matches);
            }      
        }
        return result;
    }
}
