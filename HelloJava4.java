import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloJava4 {
    public static void main (String[] args) {
        JFrame frame = new JFrame("jframe text");
        frame.add(new HelloComponent4("Component4 init text"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 300,300 );
        frame.setVisible(true);
    }
}

class HelloComponent4 
extends JComponent
implements MouseMotionListener, ActionListener, Runnable {
    String theMessage;
    int messageX = 125, messageY = 95;
    JButton theButton;
    int ColorIndex;
    static Color[] someColors={Color.black, Color.red, Color.green, Color.blue, Color.magenta};
    boolean blinkState;  // recall, this will be initialized to false by default

    public HelloComponent4(String message) {  //constructor
        theMessage = message;
        theButton = new JButton("Change Color");
        setLayout(new FlowLayout());
        add(theButton);
        theButton.addActionListener(this);
        addMouseMotionListener(this);
        Thread t = new Thread(this);  // pass this to Thread constructor
        t.start();  // and call our run method
    }

    public void paintComponent(Graphics g){
        g.setColor(blinkState ? getBackground(): currentColor());  // set color of message text to background (i.e. invisible) or next color based on truth value of blinkState
        g.drawString(theMessage, messageX, messageY);
    }

    public void mouseDragged(MouseEvent e){
        messageX = e.getX();
        messageY = e.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent e){
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == theButton)
            changeColor();
    }

    synchronized private void changeColor(){
        if (++ColorIndex == someColors.length)
            ColorIndex = 0;
        setForeground(currentColor());
        repaint();
    }

    synchronized private Color currentColor(){
        return someColors[ColorIndex];
    }

    public void run() {  // required b/c we implemented Runnable interface
        try {
            while(true){
                blinkState = !blinkState; // toggle blinkstate
                repaint();
                Thread.sleep(300);
            }
        }
            catch (InterruptedException ie) {
                
            }
    }
}