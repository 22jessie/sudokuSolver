package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.Constants;

public class Button extends JButton{
	
	private static final long serialVersionUID = 1L;
	private byte count;
	private boolean enabled;
	
	
	Button(){
		setFocusable(true);
		enabled=true;
		setForeground(Constants.USER_NUMBER_COLOR);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {				
				if(enabled) {
					if(++count==Constants.DIMENSION+1) {
						count=1;
					}
					setText(Integer.toString(count));
				}
			}
		});
	}
	
	@Override
	public void setEnabled(boolean b) {
		enabled=b;
	}
	
}
