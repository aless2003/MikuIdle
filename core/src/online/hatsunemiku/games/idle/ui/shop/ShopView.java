package online.hatsunemiku.games.idle.ui.shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import online.hatsunemiku.games.idle.logic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopView extends Stage {

  private static final Logger log = LoggerFactory.getLogger(ShopView.class);
  private boolean shouldEnter = false;

  public ShopView(AssetManager assetManager, Player player, InputMultiplexer multiplexer, Viewport viewport) {
    super();
    setViewport(viewport);

    Skin skin = assetManager.get("skins/StandardSkin.json");
    Sound confirm = assetManager.get("sounds/buy.mp3");
    Sound cancel = assetManager.get("sounds/cancel.mp3");

    Table table = new Table();
    table.setFillParent(true);

    TextButton clickerGen = new TextButton("Buy Clicker", skin);
    TextButton exit = new TextButton("Exit", skin);
    Label label = new Label("", skin);
    label.setVisible(false);
    label.setFontScale(0.5f);

    clickerGen.setTransform(true);
    exit.setTransform(true);

    clickerGen.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (player.getPoints() >= 1) {
          confirm.play();
          player.addPoints(-1);
          player.addClicker();
          exitShop(multiplexer, label);
        } else {
          cancel.play();
          label.setText("You don't have enough points!");
          label.setVisible(true);
        }
        return true;
      }
    });

    exit.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        cancel.play();
        exitShop(multiplexer, label);
        return true;
      }
    });

    table.add(clickerGen);
    table.row();
    table.add(exit);
    table.row();
    table.add(label);

    table.debug();
    addActor(table);
    /*addActor(clickerGen);
    addActor(exit);*/
  }

  private void exitShop(InputMultiplexer multiplexer, Label label) {
    Gdx.input.setInputProcessor(multiplexer);
    label.setVisible(false);
    shouldEnter = false;
  }

  public boolean shouldEnter() {
    return shouldEnter;
  }

  public void enter() {
    log.info("Entering shop");
    Gdx.input.setInputProcessor(this);
    shouldEnter = true;
  }
}
