package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.Constants;

public class Button extends JButton{
	
	private static final long serialVersionUID = 1L;
	private byte count;
	
	
	Button(){
		setFocusable(true);
		setForeground(Constants.USER_NUMBER_COLOR);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {				
				if(isEnabled()) {
					if(++count==10) {
						count=1;
					}
					setText(Integer.toString(count));
				}
			}
		});
		
	}
	
}
