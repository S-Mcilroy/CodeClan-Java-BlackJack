import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void getDeck() {
        for (SuitType suit: SuitType.values()){
            for (RankType rank: RankType.values()){
                Card card = new Card(suit, rank);
                deck.add(card);
            }
        }
    }

    public int deckCount(){
        return deck.size();
    }


    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card dealCard(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }




}
