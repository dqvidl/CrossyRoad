import java.awt.*;

public class Cars extends Blocks{
  // inherits xPosition, yPosition variables from Blocks
  int direction;

  public Cars(int num, int[] length){ //new
    // generate a 20xrandom-lengthed car
    super(20, length[(int)(Math.random() * 5)]); 
    if((int)(Math.random()*10)%2 == 0){
      direction = 0;
    }
    else {
      direction = 1;
    }
    // min + (int)(Math.random() * ((max - min) + 1)
    if(direction == 0) {
      SPEED = 3 + (int)(Math.random() * 13);
      xPosition = (num*60)+130; 
      yPosition = 600; 
    }
    else {
      SPEED = -3 - (int)(Math.random() * 13);
      xPosition = (num*60)+130; 
      yPosition = 0; 
    }
    // starting position (x, y)
  }

  public void updatePosition(){ //new
    yPosition += -SPEED;
    if (yPosition < -20) {
      yPosition = 600;
    }
    else if (yPosition > 620) {
      yPosition = 0;
    }
    super.updatePosition();
  }

}