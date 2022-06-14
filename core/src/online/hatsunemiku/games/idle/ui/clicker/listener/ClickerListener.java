package online.hatsunemiku.games.idle.ui.clicker.listener;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import online.hatsunemiku.games.idle.logic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClickerListener extends ClickListener {

  private static final Logger log = LoggerFactory.getLogger(ClickerListener.class);

  private Player player;

  public ClickerListener(Player player) {
    super();
    this.player = player;
  }

  @Override
  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    if (button == Buttons.LEFT) {
      log.debug("Clicked on Miku");
      player.addPoints(1);
    }
    return true;
  }

}
