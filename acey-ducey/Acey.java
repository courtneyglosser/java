
import java.util.Random;
import java.io.Console;

public class Acey {

  int card1 = 0;
  int card2 = 0;
  int balance = 100;

  public static void main (String args[]) {

    Acey game = new Acey();
    game.print_welcome();
    int bet = 0;

    while (game.is_running()) {
      game.show_two_cards();

      try {
        bet = game.request_bet();
      }
      catch (NumberFormatException e) {
        System.out.println("Sorry partner, that doesn't look right.");
        bet = -1;
      }

      if (bet < 0) {
        System.out.println("You gotta bet money!");
      }
      else if (bet > game.balance) {
        System.out.println("You don't have that much money!");
      }
      else {
        game.resolve_bet(bet);
      }
    }

  }

  public void resolve_bet(int bet) {
    Boolean win = false;
    int card = this.draw_card();
    String display_card = evaluate_card(card);
    System.out.println("You drew: " + display_card);
    if (this.card1 < this.card2) {
      if (card > this.card1 && card < this.card2) {
        win = true;
      }
    }
    else {
      if (card > this.card2 && card < this.card1) {
        win = true;
      }
    }

    if (win) {
      System.out.println("That's a bingo!!");
      this.balance += bet;
    }
    else {
      System.out.println("Bummer...");
      this.balance -= bet;
    }
  }

  public Boolean is_running() {
    Boolean rtn = true;
    if (balance == 0) {
      rtn = false;
    }
    return rtn;
  }

  public void print_welcome() {
    System.out.println("Acey Ducey Card Game.");
    System.out.println("Acey-Ducy is played as follows:");
    System.out.println("The Computer will deal you two cards.");
    System.out.println("You have the option to bet or not.");
    System.out.println("Do you feel the next card will have a value");
    System.out.println("between the first two?");
    System.out.println("If so, enter an amount to bet.");
    System.out.println("If not, enter a 0 (zero).");
  }

  public int draw_card() {
    Random rand = new Random();
    int i = rand.nextInt(12) + 2;
    return i;
  }


  public void show_two_cards() {
    System.out.println("Here are your next two cards.");
    int i = this.draw_card();
    int j = this.draw_card();
    while (i == j) {
      j = this.draw_card();
    }

    String a = evaluate_card(i);
    String b = evaluate_card(j);

    this.card1 = i;
    this.card2 = j;

    System.out.println("First card is: " + a);
    System.out.println("Second card is: " + b);
  }

  public static String evaluate_card(int i) {
    String rtn = "";

    if (i > 0 && i < 11) {
      rtn = String.valueOf(i);
    }
    else {
      switch(i) {
        case 11:
          rtn = "Jack";
          break;
        case 12:
          rtn = "Queen";
          break;
        case 13:
          rtn = "King";
          break;
        case 14:
          rtn = "Ace";
          break;
        default:
          break;
      }
    }
    return rtn;
  }

  public int request_bet() {
    System.out.println("You now have " + this.balance + " dollars.");
    Console cnsl = System.console();
    String read_bet = cnsl.readLine("Bet: ");
    int bet = 0;
    try {
      bet = Integer.valueOf(read_bet);
    }
    catch (NumberFormatException e) {
      throw e;
    }
    System.out.println("Your bet is " + String.valueOf(bet));

    return bet;
  }
}

