import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window extends JPanel {
  //Create an instance of Game in order to access player & backgrounds
  private Game game;
  boolean drawn = false;

  public Window(Game game){
    this.game = game;
    //Setting the size of the Window
    setPreferredSize(new Dimension(800, 600));
    //Setting the background color of the Window
    setBackground(Color.BLACK);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g); 
    //Setting the color of the background
    game.offG.setColor(Color.BLACK);
    //Filling the background color
    game.offG.fillRect(0, 0, 900, 600);
    switch (game.gameState){
      case 0:
        drawStartingMenu();
        break;
      case 1:
        drawGame();
        break;
      case 2:
        drawEndingMenu();
        break;
      case 3:
        drawWinningMenu();
        break;
    }
    game.offG.setFont(new Font("Californian FB", Font.PLAIN, 20));
    game.offG.setColor(Color.WHITE);
    game.offG.fillRect(10, 10, 145, 25);
    game.offG.setColor(Color.BLACK);
    game.offG.drawString("Score: " + Items.getCoins()+"/30", 20, 30);
    
    //draws offscreen image onscreen
    g.drawImage(game.offscreen, 0, 0, this);
    //update the visual again 
    repaint();
  }

  public void drawStartingMenu(){
    game.offG.setColor(Color.GRAY);
    game.offG.fillRect(190, 120, 420, 180);
    game.offG.setColor(Color.WHITE);
    game.offG.setFont(new Font("Palatino", Font.PLAIN, 28));
    game.offG.drawString("Welcome to Crossy Roads!", 215, 200);
    
    game.offG.setFont(new Font("Californian FB", Font.PLAIN, 20));
    game.offG.drawString("Press [SPACE] to Begin", 290, 240);

    game.offG.setColor(Color.YELLOW);
    game.offG.setFont(new Font("Californian FB", Font.PLAIN, 15));
    game.offG.drawString("Use the WASD or arrow keys to move the player! Avoid the ", 180, 380);
    game.offG.drawString("blue cars and try to get 30 yellow coins/points to win!", 200, 400);
  }

  public void drawEndingMenu(){
    game.offG.setColor(Color.GRAY);
    game.offG.fillRect(190, 180, 420, 180);
    game.offG.setColor(Color.RED);
    game.offG.setFont(new Font("Palatino", Font.PLAIN, 28));
    game.offG.drawString("Game Over!", 315, 260);
    game.offG.setFont(new Font("Californian FB", Font.PLAIN, 20));
    game.offG.drawString("Press [SPACE] to Restart", 285, 300);
  }

  public void drawWinningMenu(){
    game.offG.setColor(Color.GRAY);
    game.offG.fillRect(190, 180, 420, 180);
    game.offG.setColor(Color.YELLOW);
    game.offG.setFont(new Font("Palatino", Font.PLAIN, 28));
    game.offG.drawString("You Won :D!", 310, 260);
    game.offG.setFont(new Font("Californian FB", Font.PLAIN, 20));
    game.offG.drawString("Press [SPACE] to Restart", 285, 300);
  }

  public void drawGame(){ 
    generateBackground();
    game.offG.setColor(Color.BLACK); 
    game.layout.paint(game.offG);  // draw black lines
    
    game.offG.setColor(Color.YELLOW);
    Items.drawCoins(game.offG); // drawn yellow coins
    
    game.offG.setColor(Color.WHITE);
    game.player.paint(game.offG); // Paint the player block

    game.offG.setColor(Color.BLUE);
    for (int i = 0; i<game.cars.size(); i++){
      game.cars.get(i).paint(game.offG); // Paint the blue cars
    }
  }

  private void generateBackground(){
    grass(0);
    grass(60);

    int groundIndex = 2;
    //for loop to randomly generate the rest of the ground
    for(int i = 100; i < 900; i+=60) {
      switch (game.grounds[groundIndex]) {
        case 0 -> road(i);
        case 1 -> grass(i);
      }
      groundIndex++;
    }
  }

  public void grass(int x)
  {
    game.offG.setColor(Color.GREEN);//Sets the colour
    game.offG.fillRect(x, 0, 60, 600);//Sets the position of the grass block
  }

  //Generates the road panels in the correct position
  public void road(int x)
  {
    game.offG.setColor(Color.GRAY);//Sets the colour
    game.offG.fillRect(x, 0, 60, 600);//Sets the position
  }
}