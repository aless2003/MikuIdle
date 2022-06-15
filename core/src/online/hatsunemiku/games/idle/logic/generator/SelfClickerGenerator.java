package online.hatsunemiku.games.idle.logic.generator;

import online.hatsunemiku.games.idle.logic.Player;

public class SelfClickerGenerator extends Generator {

  public SelfClickerGenerator() {
    super(1);
  }

  @Override
  public void generate(Player player) {
    player.addPoints(addValue * multiplier);
  }

  public void addMultiplier(long multiplier) {
    this.multiplier += multiplier;
  }
}
