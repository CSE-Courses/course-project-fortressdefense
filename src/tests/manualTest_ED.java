package tests;

import code.card_class.*;

//this is not a unit test
public class manualTest_ED {
    public static void main(String[] args) {
        Card card = new Card("STONE WALL");
        System.out.println("Name: " + card.getCard_name() +
                "\nType: " + card.getType() +
                "\nDescription:\n   " + card.getDescription());
    }
}