import java.util.ArrayList;
import java.util.List;


public class GameRunner {
	public static void main(String[] args) {
		List<players> turns = new ArrayList<players>();
		players human = new Player("Shaan");
		players comp1 = new Computer("AI-1");
		players comp2 = new Computer("AI-2");
		players comp3 = new Computer("AI-3");

		turns.add(comp1);
		turns.add(comp2);
		turns.add(comp3);
		turns.add(human);


		GameController session1 = new GameController(turns);
		session1.execGameLoop();

	}

}
