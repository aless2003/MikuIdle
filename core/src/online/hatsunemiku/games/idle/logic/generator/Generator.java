package online.hatsunemiku.games.idle.logic.generator;

import online.hatsunemiku.games.idle.logic.Player;

public abstract class Generator {

  protected float addValue;
  protected long multiplier = 1;

  protected Player player;
  protected Generator(float addValue, Player player) {
    this.addValue = addValue;
    this.player = player;
  }

  public void generate(float delta) {
    float add = addValue * delta;
    add *= multiplier;
    player.addPoints(add);
  }

  public void addMultiplier(int i) {
    this.multiplier += i;
  }
}
