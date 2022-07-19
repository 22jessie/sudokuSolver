package view;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Constants;

public class SudokuBoard extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JButton[] buttons=new JButton[Constants.DIMENSION*Constants.DIMENSION];
	
	private final JPanel[] buttonsPanels=new JPanel[Constants.DIMENSION];
	
	
	SudokuBoard(){
		int i,j;
		int aux;
		int n=(int) Math.sqrt(Constants.DIMENSION);
		setLayout(new GridLayout(n,n,10,10));
		for(i=0;i<buttonsPanels.length;i++) {
			buttonsPanels[i]=new JPanel();
			buttonsPanels[i].setLayout(new GridLayout(n,n));
		}
		for(i=0,j=buttons.length; i<j; i++) {
			aux=(i%Constants.DIMENSION)/n;
			buttonsPanels[((i/Constants.DIMENSION)/n)*n+aux].add(buttons[i]=new Button());
			
		}
		Arrays.asList(buttonsPanels).forEach(p->add(p));
	}


	public void disableButtons() {
		Arrays.asList(buttons).forEach(b->{
			b.setEnabled(false);
		});	
		
		getSudokuBoardRepresentation();
	}
	
	public void setBoardSolution(List<Byte> sol) {
		int i,j;
		for(i=0,j=sol.size(); i < j; i++) {
			buttons[i].setText(Byte.toString(sol.get(i)));
		}
	}
	
	public List<Byte> getSudokuBoardRepresentation(){
		return Arrays.asList(buttons).stream().map(b->{
			String s;
			s=b.getText();
			if(!s.isEmpty()) {
				return Byte.parseByte(s);
			}else {
				return Constants.DEFAULT_VALUE;
			}
		}).collect(Collectors.toList());
		
	}

}
