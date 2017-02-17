import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HelloComponent2 extends JComponent implements MouseMotionListener {
  String theMessage;
  int messageX = 125; int messageY = 95;

  public HelloComponent2(String m){  //constructor
    theMessage = m;
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

}

public class HelloJava2 {
  public static void main (String[] args){
    JFrame frame = new JFrame("FrameText");
    frame.add( new HelloComponent2(args[0]));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,300);
    frame.setVisible(true);
  }
}