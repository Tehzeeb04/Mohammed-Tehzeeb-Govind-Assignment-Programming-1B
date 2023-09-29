/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackapplication;

public class Card 
{
    private String suit;
    private String rank;

    public Card(String suit, String rank) 
    {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() 
    {
        return suit;
    }

    public String getRank() 
    {
        return rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    
    
    
    @Override
    public String toString() 
    {
        /*
         * This method overrides the default toString() method to provide
         * a custom string representation of a Card object.
         * It returns a string that includes the rank and suit of the card.
         * For example, "Ace of Hearts" or "King of Spades".
         */
        return rank + " of " + suit;
    }
}
