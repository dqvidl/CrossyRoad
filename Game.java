import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

//Game file hold all of the important code for getting the game to work, such as keyboard inputs and generating graphic
public class Game extends JFrame implements KeyListener, ActionListener {

  public Window panel; //Create an instance of Window called panel
  Timer timer;  //Declaring timer as a variable in the timer class
  Image offscreen;
  Graphics offG;

  Player player; 
  Backgrounds layout;
  int[] grounds;
  ArrayList<Cars> cars;

  int gameState; // 0 - starting menu, 1 - game, 2 - ending menu, 3 - winning menu

  //This method sets the initial conditions for the game
  public void init()  {
    this.setVisible(true); 
    this.setSize(900, 600); 
    this.setTitle("Crossy Roads: Block Edition"); 
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setResizable(false);
    
    this.addKeyListener(this); //So our game can check the keys we've pressed
    add(this.panel = new Window(this), BorderLayout.CENTER); //Add the new panel from Window and putting it in the center of our original window+
    offscreen = createImage(this.getWidth(), this.getHeight());
    offG = offscreen.getGraphics();
    
    player = new Player();
    layout = new Backgrounds();

    grounds = new int[18];
    cars = new ArrayList();
    generateScreen();

    gameState = 0;

    timer = new Timer(20, this); 
    timer.start();  

    pack(); //Pack() ensure that the added panel is packed and compressed within the visible portion of game window
  }

  @Override
  public void keyTyped(KeyEvent e) { // empty method from keyListener
  }

  @Override
  //Handles the actions after a key is pressed
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) { 
      if(player.isValidPos(player.xPosition, player.yPosition - 60))
      {
        player.moveUp();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) { // A Key is pressed
      if(player.isValidPos(player.xPosition - 60, player.yPosition))
      {
        player.moveLeft();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) { // D Key is pressed
      if (player.isValidPos(player.xPosition+60, player.yPosition)){
        player.moveRight();
      } else {
        generateScreen();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) { // S Key is pressed
      if(player.isValidPos(player.xPosition, player.yPosition + 60))
      {
        player.moveDown();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
      if (gameState == 0){ // starting menu
        gameState = 1; // game
      } else if (gameState == 2 || gameState == 3){ // loosing & winning menu
        gameState = 0; // starting menu
        player.restart();
        Items.reset(true);
      }
    }
    //Repaint the visual to see the changes
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) { // empty method from keyListener
  }

  @Override
  // Everything within actionPerformed is going to be looped
  public void actionPerformed(ActionEvent e) {
    if (gameState == 1){ // if game is running
      player.updatePosition();
      for (int i = 0; i<cars.size();i++){
        cars.get(i).updatePosition();
        if (player.collisionDetection(cars.get(i))){
          gameState = 2; 
        }
      }
      Items.checkCollision(player);

      if (Items.getCoins() == 30){
        gameState = 3;
      }
    }
  } 

  public void generateScreen(){
    grounds[0] = 0;
    grounds[1] = 0;
    Random random = new Random();
    for (int i = 2; i < grounds.length; i++){
      int rand = random.nextInt(2);
      grounds[i] = rand;

      player.restart();
      Items.reset(false);
    }

    cars.clear();
    int[] length = {20, 30, 40, 50, 60};
    for (int i = 2; i < grounds.length; i++){
      if (grounds[i] == 0){
        cars.add(new Cars(i-2, length));
      }
    }
  }
}