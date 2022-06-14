package online.hatsunemiku.games.idle.ui.clicker;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import online.hatsunemiku.games.idle.ui.clicker.listener.ClickerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MikuClicker extends Actor implements Disposable {


  private static final Logger log = LoggerFactory.getLogger(MikuClicker.class);
  private final Texture texture = new Texture("img/v4x.png");

  public MikuClicker(ClickerListener listener) {
    debug();
    addListener(listener);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(texture, getX(), getY(), getWidth(), getHeight());
  }

  @Override
  public void dispose() {
    texture.dispose();
  }
}
