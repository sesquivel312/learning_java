import java.awt.*;

class HelloComponent extends JComponent {
	public void paintComponent( Graphics g) {
		g.drawString( "Hello Component Paint Method", 125, 95);
	}
}