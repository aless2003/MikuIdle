package online.hatsunemiku.games.idle.logic.generator;

import online.hatsunemiku.games.idle.logic.Player;

public abstract class Generator {

  protected long addValue;
  protected long multiplier = 1;
  protected Generator(long addValue) {
    this.addValue = addValue;
  }

  public abstract void generate(Player player);

}
