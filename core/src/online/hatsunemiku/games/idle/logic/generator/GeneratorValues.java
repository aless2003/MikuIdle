package online.hatsunemiku.games.idle.logic.generator;

public enum GeneratorValues {
  CLICKER(10, 1),
  NODE(100, 10),
  MICROPHONE(10000, 100);

  public final float cost;
  public final float addValue;

  GeneratorValues(float cost, float addValue) {
    this.cost = cost;
    this.addValue = addValue;
  }
}
