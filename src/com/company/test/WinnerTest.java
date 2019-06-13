package com.company.test;

import com.company.Hand;
import com.company.Turn;
import com.company.Winner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WinnerTest {

    /**
     * Test in case of Pass
     * Expected: Pass - TD JD QD KD AD = TH JH QH KH AH
     */
    @Test
    public void testCasePass() {
        Hand h1 = new Hand("TD JD QD KD AD");
        Hand h2 = new Hand("TH JH QH KH AH");
        Turn t = new Turn(h1, h2);
        assertEquals(Winner.PASS, t.getWinner());
        testCaseHighCard();
    }

    /**
     * Test in case of High Card
     * Expected: P2 Win - High card is KH
     */
    @Test
    public void testCaseHighCard(){
        Hand h1 = new Hand("7H 3S 5C QC 4C");
        Hand h2 = new Hand("6D 7S KH 9D 3C");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P2, t.getWinner());
    }

    /**
     * Test in case of One Pair
     * Expected: P2 Win - 8S 8D > 6H 6C
     */
    @Test
    public void testCasePair(){
        Hand h1 = new Hand("2S 3C 6H 6C KS");
        Hand h2 = new Hand("7H 8S JS 8D 5C");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P2, t.getWinner());
    }

    /**
     * Test in case of Two Pairs
     * Expected: P1 Win - 7S 7D 3C 3S > 8C 8S
     */
    @Test
    public void testCaseTwoPairs(){
        Hand h1 = new Hand("7S KH 7D 3C 3S");
        Hand h2 = new Hand("8C 8S 2D 4H 5C");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P1, t.getWinner());
    }

    /**
     * Test in case of Three cards of same value
     * Expected: P1 Win - 4S 4D 4H > KH KS TH TS
     */
    @Test
    public void testCaseThreeSameKind(){
        Hand h1 = new Hand("4S 4D 4H 7S 2D");
        Hand h2 = new Hand("KH KS 9C TH TS");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P1, t.getWinner());
    }

    /**
     * Test in case of Straight
     * Expected: P1 Win - 5S 6C 7H 8H 9D > 4S 4D 4H
     */
    @Test
    public void testCaseStraight(){
        Hand h1 = new Hand("5S 6C 7H 8H 9D");
        Hand h2 = new Hand("4S 4D 4H 7S 2D");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P1, t.getWinner());
    }

    /**
     * Test in case of Flush
     * Expected: P1 Win - 1D 7D 3D 4D 5D > 5S 6C 7H 8H 9D
     */
    @Test
    public void testCaseFlush(){
        Hand h1 = new Hand("1D 7D 3D 4D 5D");
        Hand h2 = new Hand("5S 6C 7H 8H 9D");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P1, t.getWinner());
    }

    /**
     * Test in case of Full House
     * Expected: P2 Win - 7S 7H 4C 4H 4S > 1D 7D 3D 4D 5D
     */
    @Test
    public void testCaseFullHouse(){
        Hand h1 = new Hand("1D 7D 3D 4D 5D");
        Hand h2 = new Hand("7S 7H 4C 4H 4S");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P2, t.getWinner());
    }

    /**
     * Test in case of Four cards of same value
     * Expected: P2 Win - 7S 7C 7H 7D 5C > 7S 7H 4C 4H 4S
     */
    @Test
    public void testCaseFourSameKind(){
        Hand h1 = new Hand("7S 7H 4C 4H 4S");
        Hand h2 = new Hand("7S 7C 7H 7D 5C");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P2, t.getWinner());
    }

    /**
     * Test in case of Straight flush
     * Expected: P2 Win - 6D 7D 8D 9D TD > 7S 7C 7H 7D 5C
     */
    @Test
    public void testCaseStraightFlush(){
        Hand h1 = new Hand("7S 7C 7H 7D 5C");
        Hand h2 = new Hand("6D 7D 8D 9D TD");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P2, t.getWinner());
    }

    /**
     * Test in case of Royal Flush
     * Expected P1 Win - TS JS QS KS AS > 6D 7D 8D 9D TD
     */
    @Test
    public void testCaseRoyalFlush(){
        Hand h1 = new Hand("TS JS QS KS AS");
        Hand h2 = new Hand("6D 7D 8D 9D TD");
        Turn t = new Turn(h1 , h2);
        assertEquals(Winner.P1, t.getWinner());
    }
}