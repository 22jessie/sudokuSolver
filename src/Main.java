import controller.SudokuSolver;
import view.Window;

public class Main {

	public static void main(String[] args) {
		new Window(new SudokuSolver());
	}

}
