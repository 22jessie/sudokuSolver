package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.SudokuSolver;
import model.Constants;

public class Window extends JFrame{

	/**
	 * Jessica Marin 
	 * 15 Jul 22
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final int WIDTH_PX = 500;
	private final int HEIGHT_PX = 500;
	
	private JButton solveButton;
	
	private SudokuBoard sudokuBoard;
	
	
	
	public Window(SudokuSolver sudokuSolver) {
		setSize(WIDTH_PX, HEIGHT_PX);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Constants.APP_TITLE);
		setLayout(new BorderLayout());
		solveButton=new JButton();
		solveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sudokuBoard.disableButtons();
				sudokuSolver.solve();
			}
		});
		solveButton.setText("Solve Sudoku");
		add(BorderLayout.SOUTH,solveButton);
		add(BorderLayout.CENTER,sudokuBoard=new SudokuBoard());
	}
	

}
