package tests;
import card_class.Card;

//this is not a unit test
public class test {
    public static void main(String[] args) {
        Card card = new Card("STONE WALL");
        System.out.println("Name: " + card.getCard_name() +
                "\nType: " + card.getType() +
                "\nDescription:\n   " + card.getDescription());
    }
}
