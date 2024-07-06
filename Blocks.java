import java.awt.*;

public class Blocks{
  Polygon shape, drawShape;

  int xPosition;
  int yPosition;
  int SPEED;
  int width;
  int height;

  public Blocks(int w, int h){
    this.width = w*2;
    this.height = h*2;
    //Declare variable shape as a new polygon and give value to its points
    shape = new Polygon();
    shape.addPoint (w, -h);
    shape.addPoint (w, h);
    shape.addPoint (-w, h);
    shape.addPoint (-w, -h);

    //Make an identical polygon that will actually be drawn onto the screen
    drawShape = new Polygon();

    drawShape.addPoint (w, -h);
    drawShape.addPoint (w, h);
    drawShape.addPoint (-w, h);
    drawShape.addPoint (-w, -h);
  }

  public void paint(Graphics g){
    //Drawing the visual of Polygon shape
    g.drawPolygon(drawShape);
    g.fillPolygon(drawShape);
  }

  //This method updates the movement of shape
  public void updatePosition() {

    for (int i = 0; i < shape.npoints; i++) {
      drawShape.xpoints[i] = (int) Math.round(shape.xpoints[i]);
      drawShape.ypoints[i] = (int) Math.round(shape.ypoints[i]);
    }

    //Ensure drawShape's lines stay in shape while rotating
    drawShape.translate((int) Math.round(xPosition), (int) Math.round(yPosition));
  }

  //Detect collision between 2 blocks
  public boolean collisionDetection(Blocks b) {
    int x1 = this.xPosition;
    int y1 = this.yPosition;
    int w1 = this.width;
    int h1 = this.height; 
    int x2 = b.xPosition;
    int y2 = b.yPosition;
    int w2 = b.width;
    int h2 = b.height;
    
    if(w1 == 0 || h1 == 0 || w2 == 0 || h2 == 0) {
      return false;
    }
    
    w1 += x1;
    h1 += y1; 
    w2 += x2; 
    h2 += y2;

    if ((y2 <= h1 && h2 >= y1) && (x2 >= x1 && x2 <= w1)){ // front/back of block touches player
      return true;
    } else if ((x2 <= w1 && w2 >= x1) && (y2 >= y1 && y2 <= h1)){ // left/right side of block touches player
      return true;
    }
    return false;
  }

}
