package online.hatsunemiku.games.idle.logic;

import online.hatsunemiku.games.idle.logic.generator.Generator;
import online.hatsunemiku.games.idle.logic.generator.SelfClickerGenerator;

public final class Player {

  private float points;
  private Generator clickerGen;
  public Player(long points) {
    this.points = points;
  }

  public void addPoints(float points) {
    this.points += points;
  }

  public float getPoints() {
    return points;
  }

  public void addClicker() {
    if (clickerGen == null) {
      clickerGen = new SelfClickerGenerator(this);
    } else {
      clickerGen.addMultiplier(1);
    }
  }

  public void generate(float delta) {
    if (clickerGen != null) {
      clickerGen.generate(delta);
    }
  }
}
