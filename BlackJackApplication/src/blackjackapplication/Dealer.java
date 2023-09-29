package blackjackapplication;

public class Dealer extends Player
{
    public Dealer()
    {
        /*
         * This constructor creates a Dealer object.
         * It calls the superclass constructor with the name "Dealer"
         * since the dealer in the game is typically referred to as "Dealer."
         */
        super("Dealer");
    }

    public Card getFirstCard()
    {
        /*
         * This method returns the first card in the dealer's hand, 
         * which is typically hidden except for the first card.
         * It is used to show the player the dealer's face-up card.
         * If the dealer's hand is empty, it returns null.
         */
        if (!getHand().isEmpty()) 
        {
            return getHand().get(0);
        }
        return null;
    }
}
