package online.hatsunemiku.games.idle.logic.generator;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.MICROPHONE;

import online.hatsunemiku.games.idle.logic.Player;

public class MicrophoneGenerator extends Generator {

  public MicrophoneGenerator(Player player) {
    super(MICROPHONE.addValue, player);
  }
}
