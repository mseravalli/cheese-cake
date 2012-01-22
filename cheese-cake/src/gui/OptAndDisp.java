package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JTextField;

public abstract class OptAndDisp extends JComponent {

	private static final long serialVersionUID = -3204782823482728561L;

	protected Container mainContainer;

	/**
	 * Initialises the container, that will be used to store all other
	 * components
	 */
	public OptAndDisp() {
		super();
		mainContainer = new Container();
	}

	/**
	 * Checks whether all paramenters are correct
	 * 
	 * @return
	 */
	protected abstract boolean areParametersCorrect();

	/**
	 * Sets the Foreground of the passed text field as BLACK
	 * 
	 * @param aField
	 */
	protected void blackJFieldText(JTextField aField) {
		aField.setForeground(Color.BLACK);
	}

	/**
	 * Sets the Foreground of the passed text field as RED
	 * 
	 * @param aField
	 */
	protected void redJFieldText(JTextField aField) {
		aField.setForeground(Color.RED);
	}

	/**
	 * Gives the size of the component to the container
	 */
	public void paint(Graphics g) {
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

	/**
	 * The method changes the colour of the font of the JText field if the value
	 * in the text is not a double
	 * 
	 * @param aField
	 * @return
	 */
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

	/**
	 * Checks if the given falls in the given range
	 * 
	 * @param x
	 * @param min
	 * @param max
	 * @return
	 */
	protected boolean isInRange(double x, double min, double max) {
		if (x >= min && x <= max) {
			return true;
		} else {
			return false;
		}
	}

}
