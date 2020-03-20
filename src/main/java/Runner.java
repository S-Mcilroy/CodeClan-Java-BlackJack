import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        ArrayList<Player> playerList = new ArrayList<Player>();
        Deck deck = new Deck();
        deck.getDeck();
        deck.shuffleDeck();

        Runner.GetPlayers(playerList, deck);
        Runner.Game(playerList, deck);
        Runner.Winner(playerList);

    }

    public static void GetPlayers(ArrayList<Player> playerList, Deck deck) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        int numPlayers = in.nextInt();
        while (numPlayers < 1){
            System.out.println("Please enter a number greater than 0");
            Scanner otherIn = new Scanner(System.in);
            numPlayers = otherIn.nextInt();
        }
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Please enter the player name:");
            Scanner player = new Scanner(System.in);
            String name = player.nextLine();
            Player newplayer = new Player(name);
            newplayer.takeCard(deck.dealCard());
            newplayer.takeCard(deck.dealCard());
            playerList.add(newplayer);
        }
        Player dealer = new Player("Dealer");
        dealer.takeCard(deck.dealCard());
        dealer.takeCard(deck.dealCard());
        playerList.add(dealer);
        System.out.println("One of the Dealer's cards are" + dealer.getFirstCard());
    }

    public static void Game(ArrayList<Player> playerList, Deck deck){
        for (Player player : playerList){
            if (player.getCardValue() < 21 || player.checkForAce()){
                if (player.getName().equals("Dealer") && player.getCardValue() < 16){
                    System.out.println(player.getName() + " you currently have cards" + player.heldHand() + "(" +player.getCardValue() + ")");
                    System.out.println("As the Dealer has less than 16 automatic Twist!");
                    player.takeCard(deck.dealCard());
                    if (player.getCardValue() > 21){
                        System.out.println(player.getName() + " your current hand is greater than 21, your bust! Your hand is now" + player.heldHand() + "(" +player.getCardValue() + ")");
                        break;
                    }
                }
                System.out.println(player.getName() + " you currently have cards" + player.heldHand() + "(" +player.getCardValue() + ")");
                System.out.println("Do you wish to Twist or Stick");
                Scanner decision = new Scanner(System.in);
                String option = decision.nextLine();
                while (!option.equals("Twist") && !option.equals("Stick")){
                    System.out.println("You entered an invalid option please enter 'Twist' or 'Stick'.");
                    Scanner secondDecision = new Scanner(System.in);
                    option = secondDecision.nextLine();
                }
                while (option.equals("Twist")){
                    player.takeCard(deck.dealCard());
                    if (player.getCardValue() > 21 && !player.checkForAce()){
                        System.out.println(player.getName() + " your current hand is greater than 21, your bust! Your hand is now" + player.heldHand() + "(" +player.getCardValue() + ")");
                        break;
                    } else if (player.getCardValue()-10 < 21 && player.checkForAce()){
                        System.out.println("Your Ace has been changed to a value of One");
                    }
                    System.out.println(player.getName() + " you currently have cards" + player.heldHand() + "(" +player.getCardValue() + ")");
                    System.out.println("Do you wish to Twist or Stick");
                    Scanner newDecision = new Scanner(System.in);
                    option = newDecision.nextLine();

                }
            }
        }
    }

    public static void Winner(ArrayList<Player> playerList) {
        Player highestPlayer = new Player("null");
        for (Player player : playerList) {
            if (player.getCardValue() > highestPlayer.getCardValue() && player.getCardValue() <= 21){
                highestPlayer = player;
            }
        }
        playerList.remove(highestPlayer);
        System.out.println("The winner is " + highestPlayer.getName() + " with cards" + highestPlayer.heldHand() + "(" + highestPlayer.getCardValue() + ")");
        System.out.println("All other players had the following cards:");

        for (Player player : playerList) {
            if (player.checkForAce()){
                int cardValue = player.getCardValue() - 10;
                System.out.println(player.getName() + " had cards" + player.heldHand() + "(" + cardValue + ")");
            } else {
                System.out.println(player.getName() + " had cards" + player.heldHand() + "(" + player.getCardValue() + ")");
            }
        }
    }
}
