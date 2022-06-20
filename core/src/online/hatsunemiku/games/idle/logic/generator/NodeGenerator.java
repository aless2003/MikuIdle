package online.hatsunemiku.games.idle.logic.generator;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.NODE;

import online.hatsunemiku.games.idle.logic.Player;

public class NodeGenerator extends Generator {

  public NodeGenerator(Player player) {
    super(NODE.addValue, player);
  }
}
