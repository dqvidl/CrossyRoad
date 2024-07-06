import java.awt.*;

public class Backgrounds {

  public void paint(Graphics g){
    // g.drawLine(x1, y1, x2, y2) draws a line from (x1, y1) to (x2, y2)
    // REMEMBER: window size (900, 600)
    for (int i = 100; i<900; i+=60){ // i is the x coord, each line spaced out by 60
      g.fillRect(i, 0, 2, 600); 
    }
  }
}

