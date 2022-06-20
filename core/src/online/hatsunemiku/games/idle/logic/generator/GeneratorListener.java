package online.hatsunemiku.games.idle.logic.generator;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import online.hatsunemiku.games.idle.logic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorListener extends ClickListener {

  private static final Logger log = LoggerFactory.getLogger(GeneratorListener.class);
  private final Player player;
  private final GeneratorValues costs;
  private final AssetManager manager;
  private final Label errorLabel;
  @Override
  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    if (button == Buttons.LEFT) {
      log.debug("Trying to buy " + costs.name());
      if (player.getPoints() >= costs.cost) {
        player.addPoints(-costs.cost);
        Sound buy = manager.get("sounds/buy.mp3");
        buy.play();
        errorLabel.setText("");
        errorLabel.setVisible(false);
        switch (costs) {
          case CLICKER -> player.addClicker();
          case NODE -> player.addNode();
          case MICROPHONE -> player.addMicrophone();
          default -> throw new IllegalStateException("Unexpected value: " + costs);
        }
      } else {
        Sound error = manager.get("sounds/cancel.mp3");
        error.play();
        errorLabel.setText("Not enough points!");
        errorLabel.setVisible(true);
      }
    }
    return true;
  }

  public GeneratorListener(
      Player player,
      GeneratorValues costs,
      AssetManager manager,
      Label errorLabel
  ) {
    this.player = player;
    this.costs = costs;
    this.manager = manager;
    this.errorLabel = errorLabel;
  }
}
