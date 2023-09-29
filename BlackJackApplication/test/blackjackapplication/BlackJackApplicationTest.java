package blackjackapplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlackJackApplicationTest {

    private BlackjackGame game;

    public BlackJackApplicationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
       
        System.out.println("Testing has begun...");
    }

    @AfterClass
    public static void tearDownClass() {
       
        System.out.println("...Testing has ended.");
    }

    @Before
    public void setUp() {
        
        game = new BlackjackGame();
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void testPlayDealerTurn() {
        // Test the playDealerTurn() method
        // Manually create a deck and set it in the game
        Deck deck = new Deck();
        game.setDeck(deck);

        game.dealInitialCards();
        game.playDealerTurn();
        assertTrue(game.getDealer().calculateHandValue() >= 17);
    }

    @Test
    public void testDetermineWinners() {
        // Test the determineWinners() method
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Dealer dealer = game.getDealer();
        player1.addCard(new Card("Hearts", "Ace"));
        player1.addCard(new Card("Spades", "King"));
        player2.addCard(new Card("Clubs", "10"));
        dealer.addCard(new Card("Diamonds", "7"));
        dealer.addCard(new Card("Hearts", "5"));

        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        game.determineWinners();
    }

    @Test
    public void testPlayDealerTurn_DealerHitsUntil17() {
        // Test the playDealerTurn() method when dealer hits until 17
        // Manually create a deck and set it in the game
        Deck deck = new Deck();
        game.setDeck(deck);

        // Ensure that the dealer's hand value is less than 17 initially
        game.dealInitialCards();
        Dealer dealer = game.getDealer();
        dealer.addCard(new Card("Spades", "5"));
        game.playDealerTurn();

        assertTrue(dealer.calculateHandValue() >= 17);
    }

    @Test
    public void testPlayDealerTurn_DealerStandsAt17() {
        // Test the playDealerTurn() method when dealer stands at 17
        // Manually create a deck and set it in the game
        Deck deck = new Deck();
        game.setDeck(deck);

        // Ensure that the dealer's hand value is 17 initially
        game.dealInitialCards();
        Dealer dealer = game.getDealer();
        dealer.addCard(new Card("Spades", "Ace"));
        dealer.addCard(new Card("Hearts", "6"));
        game.playDealerTurn();

        assertTrue(dealer.calculateHandValue() >= 17);
    }

    @Test
    public void testDetermineWinners_PlayerWins() {
        // Test the determineWinners() method when the player wins
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Dealer dealer = game.getDealer();
        player1.addCard(new Card("Hearts", "Ace"));
        player1.addCard(new Card("Spades", "King"));
        player2.addCard(new Card("Clubs", "10"));
        dealer.addCard(new Card("Diamonds", "7"));
        dealer.addCard(new Card("Hearts", "5"));

        game.getPlayers().add(player1);
        game.getPlayers().add(player2);

        game.determineWinners();
    }

    @Test
    public void testDetermineWinners_DealerBusts() {
        // Test the determineWinners() method when the dealer busts
        Player player = new Player("Player1");
        Dealer dealer = game.getDealer();
        player.addCard(new Card("Hearts", "Ace"));
        player.addCard(new Card("Spades", "King"));
        dealer.addCard(new Card("Diamonds", "7"));
        dealer.addCard(new Card("Hearts", "5"));
        dealer.addCard(new Card("Clubs", "10")); // Dealer's hand value exceeds 21

        game.getPlayers().add(player);

        game.determineWinners();
    }

    @Test
    public void testDetermineWinners_PlayerLoses() {
        // Test the determineWinners() method when the player loses
        Player player = new Player("Player1");
        Dealer dealer = game.getDealer();
        player.addCard(new Card("Clubs", "9"));
        dealer.addCard(new Card("Diamonds", "Ace"));
        dealer.addCard(new Card("Hearts", "King"));

        game.getPlayers().add(player);

        game.determineWinners();
    }

    @Test
    public void testDetermineWinners_PlayerTiesWithDealer() {
        // Test the determineWinners() method when the player ties with the dealer
        Player player = new Player("Player1");
        Dealer dealer = game.getDealer();
        player.addCard(new Card("Clubs", "9"));
        dealer.addCard(new Card("Diamonds", "9"));
        dealer.addCard(new Card("Hearts", "9"));

        game.getPlayers().add(player);

        game.determineWinners();
    }
}
