package online.hatsunemiku.games.idle.ui.clicker.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import online.hatsunemiku.games.idle.logic.Player;
import online.hatsunemiku.games.idle.ui.clicker.MikuClicker;
import online.hatsunemiku.games.idle.ui.clicker.listener.ClickerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClickerStage extends Stage {

  private static final Logger log = LoggerFactory.getLogger(ClickerStage.class);

  public ClickerStage(Viewport viewport, Player player) {
    super();

    setViewport(viewport);

    ClickerListener listener = new ClickerListener(player);
    MikuClicker clicker = new MikuClicker(listener);

    float moveX = getWidth() / 4;
    float moveY = getHeight() / 4;

    clicker.moveBy(moveX, moveY);

    Texture texture = new Texture("img/v4x.png");
    float aspect = (float) texture.getWidth() / (float) texture.getHeight();

    float standardWidth = getWidth() / 2f;
    float standardHeight = getHeight() / 2f;

    if (aspect > 1) {
      clicker.setSize(standardWidth, standardHeight / aspect);
    } else {
      clicker.setSize(standardWidth * aspect, standardHeight);
    }

    addActor(clicker);

    if (log.isDebugEnabled()) {
      log.debug("ClickerStage created");
    }
  }

  @Override
  public void dispose() {
    super.dispose();
  }

}
