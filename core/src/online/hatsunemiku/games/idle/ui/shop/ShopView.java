package online.hatsunemiku.games.idle.ui.shop;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.CLICKER;
import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.MICROPHONE;
import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.NODE;

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
import online.hatsunemiku.games.idle.logic.generator.GeneratorListener;
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
    TextButton nodeGen = new TextButton("Buy Node", skin);
    TextButton microphoneGen = new TextButton("Buy Microphone", skin);
    TextButton exit = new TextButton("Exit", skin);
    Label label = new Label("", skin);
    label.setVisible(false);
    label.setFontScale(0.5f);

    clickerGen.setTransform(true);
    nodeGen.setTransform(true);
    microphoneGen.setTransform(true);
    exit.setTransform(true);

    clickerGen.addListener(new GeneratorListener(player, CLICKER, assetManager, label));
    nodeGen.addListener(new GeneratorListener(player, NODE, assetManager, label));
    microphoneGen.addListener(new GeneratorListener(player, MICROPHONE, assetManager, label));
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
    table.add(nodeGen);
    table.row();
    table.add(microphoneGen);
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
