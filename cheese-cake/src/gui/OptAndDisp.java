package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JTextField;

public abstract class OptAndDisp extends JComponent {

	private static final long serialVersionUID = -3204782823482728561L;
	
	public OptAndDisp(){
		super();
		mainContainer = new Container();
	}
	
	protected Container mainContainer;
	
	protected abstract boolean areParametersCorrect();

	protected void blackJFieldText(JTextField aField) {
		aField.setForeground(Color.BLACK);
	}
	
	protected void redJFieldText(JTextField aField) {
		aField.setForeground(Color.RED);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		this.mainContainer.setSize(this.getWidth(), this.getHeight());
	}
	
	/**
	 * The method changes the colour of the font of the JText field if the value
	 * in the text is not an integer
	 * 
	 * @param aField
	 * @return
	 */
	protected boolean containsInteger(JTextField aField) {

		boolean isInt = false;

		try {
			Integer.parseInt(aField.getText());
			blackJFieldText(aField);
			isInt = true;

		} catch (Exception e) {
			redJFieldText(aField);
		}

		return isInt;
	}

	protected boolean containsDouble(JTextField aField) {

		boolean isDouble = false;

		try {
			Double.parseDouble(aField.getText());
			blackJFieldText(aField);
			isDouble = true;

		} catch (Exception e) {
			redJFieldText(aField);
		}

		return isDouble;
	}

	protected boolean isInRange(double x, double min, double max) {
		if (x >= min && x <= max) {
			return true;
		} else {
			return false;
		}
	}
	
}
