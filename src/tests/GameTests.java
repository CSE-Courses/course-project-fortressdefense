package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import code.*;

public class GameTests {

	@Test
	public void TestCreateGame() {
		Game game = new Game();
		assertNotNull(game.AttackDeck);
		assertNotEquals(0, game.AttackDeck.Size());
		assertNotNull(game.DefenseDeck);
		assertNotEquals(0, game.DefenseDeck.Size());
	}

}
