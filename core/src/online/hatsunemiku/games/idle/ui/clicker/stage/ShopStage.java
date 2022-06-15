package online.hatsunemiku.games.idle.ui.clicker.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopStage extends Stage {

  private static final Logger log = LoggerFactory.getLogger(ShopStage.class);

  public ShopStage(Skin skin, Viewport viewport) {
    super();

    setViewport(viewport);
    Table table = new Table();
    TextButton button = new TextButton("Buy Self Clicker", skin);

    table.setSize(50, 50);
    table.moveBy((getWidth() / 4) * 3, 0);

    button.setTransform(true);
    float buttonWidth = table.getWidth() / 4;
    float buttonHeight = table.getHeight() / 5;
    button.setSize(buttonWidth, buttonHeight);

    log.info("Size {}x{}", getWidth(), getHeight());
    table.add(button);
    table.debug();
    table.center();
    addActor(table);

  }


}
