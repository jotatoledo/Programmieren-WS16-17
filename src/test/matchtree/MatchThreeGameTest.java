package test.matchtree;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.DeterministicStrategy;
import edu.kit.informatik.matchthree.framework.FillingStrategy;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;

public class MatchThreeGameTest {
    private final Board boardE22;
    private final FillingStrategy strategyE22;
    private final Matcher matcherE22;
    private final Game gameE22;
    
    public MatchThreeGameTest(){
        boardE22 = new MatchThreeBoard(Token.set("AXO*"), "O*O;***;O*O;O*O");
        strategyE22 = new DeterministicStrategy(Token.iterator("AOA**"), 
                Token.iterator("AXAXA"), 
                Token.iterator("A**A*"));
        boardE22.setFillingStrategy(strategyE22);
        matcherE22 = new MaximumDeltaMatcher(new HashSet<Delta>(Arrays.asList(Delta.dxy(1, 0))));
        gameE22 = new MatchThreeGame(boardE22, matcherE22);
    }
    
    @Test
    public void testMatchThreeGame() {
        assertTrue(gameE22 != null);
    }

    @Test
    public void testInitializeBoardAndStart() {
        gameE22.initializeBoardAndStart();
    }

    @Test
    public void testAcceptMove() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetScore() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetMatcher() {
        fail("Not yet implemented");
    }

}
