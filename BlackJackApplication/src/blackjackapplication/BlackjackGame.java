/*
 * This is the main class that represents the Blackjack game.
 */
package blackjackapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame 
{
    private Deck deck;
    private List<Player> players;
    private Dealer dealer;
    private Scanner scanner;

    public BlackjackGame() 
    {
        // Initialize the game with a deck, players, dealer, and scanner.
        deck = new Deck();
        players = new ArrayList<>();
        dealer = new Dealer();
        scanner = new Scanner(System.in);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    

    public void start() 
    {
        /*
         * This method starts the Blackjack game, displaying a menu
         * and allowing the player to choose between starting a new game,
         * viewing game instructions, or exiting the game.
         */
        int choice;
        while (true) 
        {
            displayMenu();
            if (scanner.hasNextInt()) 
            {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) 
                {
                    break; // Valid input, exit the loop
                } else 
                {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } else 
            {
                System.out.println("Invalid input! Please enter a valid number." + "\n-------------------------------------------");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character

        switch (choice) 
        {
            case 1:
                initializePlayers();
                playGame();
                break;
            case 2:
                displayInstructions();
                break;
            case 3:
                System.out.println("Exiting the game. Goodbye!");
                break;
        }
    }

    private void displayMenu() 
    {
        /*
         * Display the game menu with options to start a new game,
         * view instructions, or exit.
         */
        System.out.println("================================== Blackjack game Menu ==========================================");
        System.out.println("1. Start a New Game");
        System.out.println("2. View Game Instructions");
        System.out.println("3. Exit");
        System.out.println("=================================================================================================");
        System.out.print("Enter your choice: ");
    }

    public void initializePlayers() 
    {
        /*
         * Prompt the user to enter the number of players and their names.
         * Initialize player objects and add them to the players list.
         */
        int numPlayers;
        while (true) 
        {
            System.out.print("Enter the number of players: ");
            if (scanner.hasNextInt()) 
            {
                numPlayers = scanner.nextInt();
                if (numPlayers > 0)
                {
                    scanner.nextLine(); // Consume the newline character
                    break; // Valid input, exit the loop
                } else 
                {
                    System.out.println("Please enter a number greater than 0.");
                }
            } else 
            {
                System.out.println("Invalid input! Please enter a valid number." + "\n-------------------------------------------");
                scanner.next(); // Consume the invalid input
            }
        }

        for (int i = 0; i < numPlayers; i++) 
        {
            System.out.print("Enter player " + (i + 1) + "'s name: ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }
    }

    public void dealInitialCards()
    {
        /*
         * Deal initial cards to players and the dealer.
         */
        for (Player player : players) 
        {
            player.addCard(deck.drawCard());
        }
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }
    
    private void playGame() 
    {
        /*
         * Main game loop where players take turns and the dealer plays.
         * Determine the winner and return to the main menu after the game ends.
         */
        dealInitialCards();

        for (Player player : players)
        {
            System.out.println(player.getName() + "'s hand: " + player.getHand());
            System.out.println(player.getName() + "'s total: " + player.calculateHandValue());
        }

        System.out.println("Dealer's hand: [" + dealer.getFirstCard() + ", ***]");

        for (Player player : players) 
        {
            while (true)
            {
                String choice = getPlayerChoice(player);

                if (choice.equals("h")) 
                {
                    player.addCard(deck.drawCard());
                    System.out.println(player.getName() + "'s hand: " + player.getHand());
                    System.out.println(player.getName() + "'s total: " + player.calculateHandValue());
                    if (player.calculateHandValue() > 21) {
                        System.out.println(player.getName() + " busts!");
                        break;
                    }
                } else if (choice.equals("s"))
                {
                    break;
                }
            }
        }

        playDealerTurn();

        determineWinners();
        start(); // After the game ends, return to the main menu
    }

    String getPlayerChoice(Player player) 
    {
        /*
         * Prompt the player for their choice (hit or stand) and validate input.
         */
        String choice;
        while (true) 
        {
            System.out.print(player.getName() + ", do you want to hit or stand? (h/s): ");
            choice = scanner.nextLine().toLowerCase();
            if (choice.equals("h") || choice.equals("s")) 
            {
                break; // Valid input, exit the loop
            } else 
            {
                System.out.println("Invalid input! Please enter 'h' for hit or 's' for stand." + "\n" + "---------------------------------------------------------");
            }
        }
        return choice;
    }

    void playDealerTurn() 
    {
        /*
         * Simulate the dealer's turn, where they draw cards until their hand value is at least 17.
         */
        while (dealer.calculateHandValue() < 17)
        {
            dealer.addCard(deck.drawCard());
        }
        System.out.println("Dealer's hand: " + dealer.getHand());
    }

    void determineWinners() 
    {
        /*
         * Determine the winners based on player and dealer hand values.
         */
        int dealerValue = dealer.calculateHandValue();

        for (Player player : players) 
        {
            int playerValue = player.calculateHandValue();

            if (playerValue > 21) 
            {
                System.out.println(player.getName() + " busts! You lose.");
            } else if (dealerValue > 21 || playerValue > dealerValue) 
            {
                System.out.println(player.getName() + " wins!");
            } else if (playerValue < dealerValue) 
            {
                System.out.println(player.getName() + " loses.");
            } else 
            {
                System.out.println(player.getName() + " ties with the dealer.");
            }
        }
    }

    private void displayInstructions() 
    {
        /*
         * Display game instructions to the user and return to the main menu.
         */
        System.out.println("================================== Game Instructions ==========================================");
        System.out.println("The rules of blackjack are simple:");
        System.out.println("1. You are dealt cards, which have the face value shown on them.");
        System.out.println("2. Your goal is to get a hand with a score as close to 21 as possible without going over it.");
        System.out.println("3. If your hand goes over 21, you lose.");
        System.out.println("4. You can 'hit' to draw additional cards or 'stand' to keep your current hand.");
        System.out.println("5. The dealer will also play and must draw cards until their hand value is at least 17.");
        System.out.println("6. The player with the hand value closest to 21 without exceeding it wins.");
        System.out.println("===============================================================================================");
        start(); // Return to the main menu after displaying instructions
    }
}
