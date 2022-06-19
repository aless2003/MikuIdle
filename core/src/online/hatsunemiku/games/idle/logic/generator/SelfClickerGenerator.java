package online.hatsunemiku.games.idle.logic.generator;

import online.hatsunemiku.games.idle.logic.Player;

public class SelfClickerGenerator extends Generator {

  public SelfClickerGenerator(Player player) {
    super(1, player);
    this.player = player;
  }
}
