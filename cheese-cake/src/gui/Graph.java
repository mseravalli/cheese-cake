package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Graph extends JComponent implements MouseMotionListener {

	private static final long serialVersionUID = 782920115881995999L;

	private double solutions[];
	private int steps;
	private int mouseX = 0;
	private int mouseY = 0;

	public Graph() {
		super();
		solutions = null;
		steps = 0;
		this.addMouseMotionListener(this);
		mouseX = -1;
		mouseY = -1;
	}

	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (solutions == null) {
			return;
		}

		g.setColor(Color.BLACK);

		int x1 = 0;
		int y1 = this.getHeight();
		int x2 = 0;
		int y2 = this.getHeight();

		for (int i = 0; i < steps; i++) {

			x2 = i * this.getWidth() / steps;
			y2 = (int) (this.getHeight() - this.getHeight()
					* (0.99 * solutions[i])) - 1;

			g.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;

		}

		g.setColor(Color.RED);
		int index = (int) (mouseX * steps / this.getWidth());
		mouseY = (int) (this.getHeight() - this.getHeight()
				* (0.99 * solutions[index])) - 1;
		;
		g.drawOval(mouseX - 5, mouseY - 5, 10, 10);

		String perc = String.format("filled: %.2f%%- traversable: %.1f%%",
				100.0 *index / steps, 100 * solutions[index]);
		
		if(mouseX < 110){
			mouseX = 110;
		}
		
		if(mouseX > getWidth() - 110){
			mouseX = getWidth() - 110;
		}
		
		if (mouseY > getWidth() / 2) {
			g.drawString(perc, mouseX - 110, mouseY - 10);
		} else {
			g.drawString(perc, mouseX - 110, mouseY + 20);
		}

	}

	public void setSolutions(int[] sol, int steps, int repPStep) {
		this.steps = steps;
		this.solutions = new double[steps];
		for (int i = 0; i < steps; i++) {
			this.solutions[i] = (double) sol[i] / (double) repPStep;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

		mouseX = arg0.getPoint().x;

		repaint();

	}

}
