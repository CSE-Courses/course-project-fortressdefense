package tests;

import card_class.card;

//this is not a unit test
public class manualTest_ED {
    public static void main(String[] args) {
        card card = new card_class.card("STONE WALL");
        System.out.println("Name: " + card.getCard_name() +
                "\nType: " + card.getType() +
                "\nDescription:\n   " + card.getDescription());
    }
}
