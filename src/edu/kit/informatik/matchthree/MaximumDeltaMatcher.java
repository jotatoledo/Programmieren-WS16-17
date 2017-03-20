package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
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
     * @param deltas
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
        final Set<Position> matches = new HashSet<Position>();
        matches.add(initial);
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Set<Position>> matchAll(Board board, Set<Position> initial) throws BoardDimensionException {
        throw new UnsupportedOperationException();
    }
}
