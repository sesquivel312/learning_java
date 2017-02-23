import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HelloComponent3 
      extends JComponent 
      implements MouseMotionListener, ActionListener {

  String theMessage;
  JButton theButton;  //class variable set by constructor; from java.swing.
  int messageX = 125; int messageY = 95;
  int colorIndex; // index into someColors (array?)
  static Color[] someColors = { Color.black, Color.red, Color.green, 
                                Color.blue, Color.magenta};  // Color from java.awt; the Color.x are Class variables, not instance vars

  public HelloComponent3(String m){  //constructor
    theMessage = m;
    theButton = new JButton("Change Color");
    setLayout(new FlowLayout());  //FlowLayout is a layout manager - controlling layout of contained components
    add(theButton);  //method inherited from Container class (in awt &/| swing), adds to list of components contained w/in HelloComponent3
    theButton.addActionListener(this);
    addMouseMotionListener(this);
  }

  public void paintComponent(Graphics g){  // this will get called by java when repaint() called
    g.drawString(theMessage, messageX, messageY);
  }

  public void mouseDragged(MouseEvent e){  // required by MouseMotionListener interface
      messageX = e.getX();
      messageY = e.getY();
      repaint();
  }

  public void mouseMoved(MouseEvent e){}  // required by MML interface

  synchronized private void changeColor(){
    /* synced b/c this method and currentColor() could cause an out of bound
     problem if run in the wrong order or simultaneously there are better
     ways to deal with this but we're doing it this way to introduce and 
     illustrate the synchronized methods */
    if (++colorIndex == someColors.length)  // at end of array?
      colorIndex = 0; // then go back to start
    setForeground(currentColor());
    repaint();
  }

  synchronized private Color currentColor(){
    return someColors[colorIndex];
  }

  public void actionPerformed(ActionEvent e){
    if (e.getSource() == theButton)
      changeColor();
  }
}

public class HelloJava3 {
  public static void main (String[] args){
    JFrame frame = new JFrame("FrameText");
    frame.add( new HelloComponent3(args[0]));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    frame.setVisible(true);
  }
}