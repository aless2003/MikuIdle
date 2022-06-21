package online.hatsunemiku.games.idle.logic;

import online.hatsunemiku.games.idle.logic.generator.GeneratorValues;
import online.hatsunemiku.games.idle.logic.generator.MicrophoneGenerator;
import online.hatsunemiku.games.idle.logic.generator.NoteGenerator;
import online.hatsunemiku.games.idle.logic.generator.SelfClickerGenerator;

public final class Player {

  private float points;
  private SelfClickerGenerator clickerGen;

  private NoteGenerator noteGen;
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

  public void addNote() {
    if (noteGen == null) {
      noteGen = new NoteGenerator(this);
    } else {
      noteGen.addMultiplier(1);
    }
  }

  public void generate(float delta) {
    if (clickerGen != null) {
      clickerGen.generate(delta);
    }

    if (noteGen != null) {
      noteGen.generate(delta);
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

  public long getGeneratorLevel(GeneratorValues costs) {
    switch (costs) {
      case CLICKER -> {
        return clickerGen == null ? 0 : clickerGen.getMultiplier();
      }
      case NOTE -> {
        return noteGen == null ? 0 : noteGen.getMultiplier();
      }
      case MICROPHONE -> {
        return microphoneGen == null ? 0 : microphoneGen.getMultiplier();
      }
      default -> throw new IllegalArgumentException("Unknown generator type");
    }
  }
}
