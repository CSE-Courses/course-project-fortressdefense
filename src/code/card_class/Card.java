package code.card_class;
import java.util.Map;

/**
 * card = new card("Name of the card(all upper case)")
 * card.getCard_name() return the name of card
 * card.getDamage() return the damage of the card, can be + and -, depends on the type
 * card.getType() return the type of the card ("DEFENSE", "ATTACK", etc))
 * card.getDescription() return description (not enough details)
 * error handling: not done yet
 */
public class Card {
    private ICardEnum card_name;

    private CardType type;

    private int damage;

    private String description;
    
    
    public Boolean PlayAtStart = false;
    public Card(ICardEnum name, CardType type, int damage) {
        this.card_name=name;
        this.type=type;
        this.damage=damage;
        
        if (this.type.equals(CardType.Attack)){
            this.description = "Deals " + this.damage + " damage to an opponent.";
        }
        else if (this.type.equals(CardType.Defense)){
            if (this.damage > 0){
                this.description = "Recover HP by " + this.damage + ".";
            }
            else{
                this.description = "Reduce HP by " + (-this.damage) + ".";
            }
        }
    }

    public ICardEnum getCard_name(){
        return card_name;
    }

    public String getDescription(){
        return description;
    }

    public int getDamage(){
        return this.damage;
    }

    public CardType getType(){
        return this.type;
    }
    
    public void setName(ICardEnum name) {
    	card_name = name;
    }
    
    public void setType(CardType type) {
    	this.type = type;
    }
}
