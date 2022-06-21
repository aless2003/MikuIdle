package online.hatsunemiku.games.idle.logic.generator;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.NOTE;

import online.hatsunemiku.games.idle.logic.Player;

public class NoteGenerator extends Generator {

  public NoteGenerator(Player player) {
    super(NOTE.addValue, player);
  }
}
