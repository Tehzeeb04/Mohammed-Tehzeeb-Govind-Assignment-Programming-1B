
package blackjackapplication;

import java.util.ArrayList;
import java.util.List;

public class Player 
{
    private String name;
    private List<Card> hand;

    public Player(String name) 
    {
        /*
         * This constructor creates a Player object with a specified name.
         * It initializes the player's hand as an empty list.
         */
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() 
    {
        /*
         * This method returns the name of the player.
         */
        return name;
    }

    public void addCard(Card card) 
    {
        /*
         * This method adds a card to the player's hand.
         */
        hand.add(card);
    }

    public List<Card> getHand()
    {
        /*
         * This method returns the list of cards in the player's hand.
         */
        return hand;
    }

    public void setName(String name) 
    {
        /*
         * This method sets the name of the player.
         */
        this.name = name;
    }

    public void setHand(List<Card> hand) 
    {
        /*
         * This method sets the player's hand to a given list of cards.
         */
        this.hand = hand;
    }

    public int calculateHandValue()
    {
        /*
         * This method calculates the total value of the cards in the player's hand.
         * It accounts for the special value of Aces, which can be 1 or 11.
         * Face cards (King, Queen, Jack) are valued at 10, and numeric cards are valued at their face value.
         * If the hand value exceeds 21 but there are Aces, it reduces the value of Aces to 1 to avoid busting.
         * The calculated hand value is returned.
         */
        int value = 0;
        int numAces = 0;

        for (Card card : hand) 
        {
            String rank = card.getRank();
            if (rank.equals("Ace")) 
            {
                value += 11;
                numAces++;
            } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) 
            {
                value += 10;
            } else 
            {
                value += Integer.parseInt(rank);
            }
        }

        while (value > 21 && numAces > 0) 
        {
            value -= 10;
            numAces--;
        }

        return value;
    }
}
