package online.hatsunemiku.games.idle.logic.generator;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.CLICKER;

import online.hatsunemiku.games.idle.logic.Player;

public class SelfClickerGenerator extends Generator {

  public SelfClickerGenerator(Player player) {
    super(CLICKER.addValue, player);
    this.player = player;
  }
}
