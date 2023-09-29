
package blackjackapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck 
{
    private List<Card> cards;

    public Deck() 
    {
        /*
         * This constructor creates a Deck object.
         * It initializes the deck with a list of 52 playing cards,
         * then shuffles the deck to ensure randomness.
         */
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() 
    {
        /*
         * This method initializes the deck with 52 playing cards.
         * It iterates through four suits (Hearts, Diamonds, Clubs, Spades)
         * and thirteen ranks (2 through Ace) to create each card.
         */
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits)
        {
            for (String rank : ranks) 
            {
                cards.add(new Card(suit, rank));
            }
        }
    }

    private void shuffleDeck()
    {
        /*
         * This method shuffles the deck to randomize the order of cards.
         * It uses the Collections.shuffle() method to achieve this.
         */
        Collections.shuffle(cards);
    }

    public Card drawCard() 
    {
        /*
         * This method simulates drawing a card from the deck.
         * It removes and returns the top card from the deck.
         * If the deck is empty, it returns null, indicating no more cards are available.
         */
        if (cards.isEmpty()) 
        {
            return null;
        }
        return cards.remove(0);
    }
}
