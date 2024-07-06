import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Items extends Blocks {

  private static List<Items> coins = new ArrayList<>();
  public static int numOfCoinsCollected;

  public Items(int x, int y) {
    super(10,10);
    xPosition = x;
    yPosition = y;
  }

  public static int getCoins(){
    return numOfCoinsCollected;
  }

  public static void generateCoins() {
    for (int i = 0; i < 15; i++) {
      int x = 60*(1 + (int)(Math.random() * 10));
      int y = 60*(1 + (int) (Math.random() * 10));
      coins.add(new Items(x, y));
    }
  }

  public static void drawCoins(Graphics g) {
    for (Items coin : coins) {
      g.fillRect(coin.xPosition, coin.yPosition, coin.width, coin.height);
    }
  }

  public static boolean checkCollision(Player player) {
      Iterator<Items> iterator = coins.iterator();
      while (iterator.hasNext()) {
          Items coin = iterator.next();
          if (player.collisionDetection(coin)) {
            iterator.remove(); // Remove the collected coin
            numOfCoinsCollected++;
            return true;
          }
      }
      return false;
  }

  public static void reset(boolean hardReset){
    coins.clear();
    generateCoins();
    if (hardReset){
      numOfCoinsCollected = 0;
    }
  }
}
