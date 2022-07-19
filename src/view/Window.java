package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Constants;
import model.Sudoku;

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	private final int WIDTH_PX = 500;
	private final int HEIGHT_PX = 500;
	
	private final JButton solveButton;
	
	private final SudokuBoard sudokuBoard;
	
	private Sudoku sudoku;
	
	
	
	public Window() {
		setSize(WIDTH_PX, HEIGHT_PX);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(Constants.APP_TITLE);
		setLayout(new BorderLayout());
		solveButton=new JButton();
		solveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				(new SolveSudokuThread()).start();
				solveButton.setEnabled(false);
			}
		});
		solveButton.setText("Solve Sudoku");
		add(BorderLayout.SOUTH,solveButton);
		add(BorderLayout.CENTER,sudokuBoard=new SudokuBoard());
	}
	
	private void addSolutionToView(List<Byte> sol) {
		sudokuBoard.setBoardSolution(sol);
	}
	
	private class SolveSudokuThread extends Thread{

		public void run() {
			List<Byte> solution;
			sudokuBoard.disableButtons();
			sudoku=new Sudoku(sudokuBoard.getSudokuBoardRepresentation());
			sudoku.solve();
			solution=sudoku.getSolution();
			if(solution!=null) {
				addSolutionToView(solution);
			}else {
				JOptionPane.showMessageDialog(null, "This Sudoku does not have a solution :( ");
			}
			
		}
		
	}
	

}
