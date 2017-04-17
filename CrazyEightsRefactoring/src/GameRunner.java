import java.util.ArrayList;
import java.util.List;

import play.Computer;
import play.Player;
import play.players;
/**
 * The Class GameRunner is the main for testing or
 * running the project as a console application.
 */
public class GameRunner {
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/**
		 * The ArrayList contains the collection of players object.
		 * The players objects could be user defined, either Computer
		 * or Human players or both
		 */
		List<players> turns = new ArrayList<>();
		players human = new Player("Shaan");
		players comp1 = new Computer("AI-1");
		players comp2 = new Computer("AI-2");
		players comp3 = new Computer("AI-3");

		turns.add(comp1);
		turns.add(comp2);
		turns.add(comp3);
		turns.add(human);

		/**
		 * Creating an instance of GameController class to run the loop.
		 */
		GameController session1 = new GameController(turns);
	}

}
