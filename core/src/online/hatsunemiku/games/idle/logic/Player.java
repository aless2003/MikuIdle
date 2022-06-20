package online.hatsunemiku.games.idle.logic;

import online.hatsunemiku.games.idle.logic.generator.MicrophoneGenerator;
import online.hatsunemiku.games.idle.logic.generator.NodeGenerator;
import online.hatsunemiku.games.idle.logic.generator.SelfClickerGenerator;

public final class Player {

  private float points;
  private SelfClickerGenerator clickerGen;

  private NodeGenerator nodeGen;
  private MicrophoneGenerator microphoneGen;

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

  public void addNode() {
    if (nodeGen == null) {
      nodeGen = new NodeGenerator(this);
    } else {
      nodeGen.addMultiplier(1);
    }
  }

  public void generate(float delta) {
    if (clickerGen != null) {
      clickerGen.generate(delta);
    }

    if (nodeGen != null) {
      nodeGen.generate(delta);
    }

    if (microphoneGen != null) {
      microphoneGen.generate(delta);
    }
  }

  public void addMicrophone() {
    if (microphoneGen == null) {
      microphoneGen = new MicrophoneGenerator(this);
    } else {
      microphoneGen.addMultiplier(1);
    }
  }
}
