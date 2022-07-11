package online.hatsunemiku.games.idle.ui.clicker.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import online.hatsunemiku.games.idle.ui.shop.ShopView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopStage extends Stage {

  private static final Logger log = LoggerFactory.getLogger(ShopStage.class);

  public ShopStage(Skin skin, Viewport viewport, ShopView shopView) {
    super();

    setViewport(viewport);

    TextButton button = new TextButton("Shop", skin);

    float buttonPositionY = getHeight() / 2;
    float buttonPositionX = getWidth() / 10 * 7;

    float buttonScale = 0.1f;

    button.setTransform(true);
    button.setPosition(buttonPositionX, buttonPositionY);
    button.getLabel().setFontScale(buttonScale * 50);
    button.setScale(buttonScale);
    button.setWidth(getWidth() * 2);
    button.setHeight(getHeight());
    button.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        TextButton btn = (TextButton) event.getListenerActor();
        btn.setChecked(true);
        shopView.enter();
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        TextButton btn = (TextButton) event.getListenerActor();
        btn.setChecked(false);
      }
    });
    addActor(button);
  }


}
