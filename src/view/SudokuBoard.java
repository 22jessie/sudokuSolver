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
	
	private JButton[] buttons=new JButton[Constants.DIMENSION*Constants.DIMENSION];
	
	
	SudokuBoard(){
		int i,j;
		setLayout(new GridLayout(Constants.DIMENSION,Constants.DIMENSION));
		
		for(i=0,j=Constants.DIMENSION*Constants.DIMENSION; i<j; i++) {
			add(buttons[i]=new Button());
		}
	}


	public void disableButtons() {
		Arrays.asList(buttons).forEach(b->{
			b.setEnabled(false);
		});	
		
		getSudokuBoardRepresentation();
	}
	
	public List<Byte> getSudokuBoardRepresentation(){
		return Arrays.asList(buttons).stream().map(b->{
			String s;
			Byte zero=0;
			s=b.getText();
			if(!s.isEmpty()) {
				return Byte.parseByte(s);
			}else {
				return zero;
			}
		}).collect(Collectors.toList());
		
	}

}
