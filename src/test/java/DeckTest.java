import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck;

    @Before
    public void before() {
        deck = new Deck();
    }

    @Test
    public void deckStartsAtZero(){
        assertEquals(0, deck.deckCount());
    }

    @Test
    public void deckHas52Cards(){
        deck.getDeck();
        assertEquals(52, deck.deckCount());
    }

    @Test
    public void canShuffleDeck(){
        Deck testDeck = new Deck();
        testDeck.getDeck();
        deck.getDeck();
        deck.shuffleDeck();
        assertNotEquals(testDeck.dealCard(), deck.dealCard());
    }

    @Test
    public void canDealCard(){
        deck.getDeck();
        deck.shuffleDeck();
        deck.dealCard();
        assertEquals(51, deck.deckCount());
    }

}

