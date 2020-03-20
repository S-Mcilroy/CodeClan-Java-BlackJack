import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card>  heldCard;

    public Player(String name) {
        this.name = name;
        this.heldCard = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHeldCard() {
        return heldCard;
    }

    public int getCardValue(){
        int cardsValue = 0;
        for (Card card : heldCard){
           cardsValue += card.getValueFromEnum();
        }
        return cardsValue;
    }

    public void takeCard(Card card){
        this.heldCard.add(card);
    }

    public String heldHand(){
        String result = "";
        for (Card card : heldCard){
            result += ", " + card.getRank().toString() + " of " + card.getSuit().toString();
        }
        return result;
    }

    public String getFirstCard(){
        String result = "";
            result = ", " + heldCard.get(0).getRank().toString() + " of " + heldCard.get(0).getSuit().toString();
        return result;
    }

    public Boolean checkForAce(){
        for (Card card : heldCard){
            if (card.getRank().toString().equals("ACE")){
                return true;
            }
        }
        return false;
    }

}
