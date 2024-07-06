import java.awt.*;

public class Player extends Blocks{
  // inherits xPosition, yPosition variables from Blocks

  public Player(){
    super(20, 20); //creates a 20x20 square for player
    SPEED = 60; 
    // starting position (x, y):
    xPosition = 70; 
    yPosition = 300; 
  }

  public void moveUp(){ 
    xPosition += 0;
    yPosition += -SPEED;
  }

  public void moveDown(){ 
    xPosition += 0;
    yPosition += SPEED;
  }

  public void moveLeft(){ 
    xPosition += -SPEED;
    yPosition += 0;
  }

  public void moveRight(){ 
    xPosition += SPEED;
    yPosition += 0;
  }

  public void restart(){
    xPosition = 70; 
    yPosition = 300; 
  }

  public boolean isValidPos(int x, int y)
  {
    if (x < 60 || x > 840) {
      return false;
    } else if (y < 60 || y > 540) {
      return false;
    }
    return true;
  }

}