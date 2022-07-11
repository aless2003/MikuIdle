package online.hatsunemiku.games.idle.ui.shop;

import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.CLICKER;
import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.MICROPHONE;
import static online.hatsunemiku.games.idle.logic.generator.GeneratorValues.NOTE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import online.hatsunemiku.games.idle.logic.Player;
import online.hatsunemiku.games.idle.logic.generator.GeneratorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopView extends Stage {

  private static final Logger log = LoggerFactory.getLogger(ShopView.class);
  private boolean shouldEnter = false;
  private final Player player;
  private final TextButton clickerGen;
  private final TextButton microphoneGen;
  private final TextButton noteGen;

  public ShopView(AssetManager assetManager, Player player, InputMultiplexer multiplexer, Viewport viewport) {
    super();
    setViewport(viewport);

    Skin skin = assetManager.get("skins/StandardSkin.json");
    Sound cancel = assetManager.get("sounds/cancel.mp3");

    Table table = new Table();
    table.setFillParent(true);

    TextButton clickerGen = new TextButton("Buy Clicker", skin);
    clickerGen.setWidth(table.getColumnWidth(0));
    TextButton noteGen = new TextButton("Buy Note", skin);
    TextButton microphoneGen = new TextButton("Buy Microphone", skin);
    TextButton exit = new TextButton("Exit", skin);
    Label label = new Label("", skin);
    label.setVisible(false);
    label.setFontScale(0.5f);
    label.setAlignment(Align.center);
    table.defaults().uniform().fill();

    clickerGen.setDisabled(player.getPoints() < player.getGeneratorCost(CLICKER));
    microphoneGen.setDisabled(player.getPoints() < player.getGeneratorCost(MICROPHONE));
    noteGen.setDisabled(player.getPoints() < player.getGeneratorCost(NOTE));

    clickerGen.setTransform(true);
    noteGen.setTransform(true);
    microphoneGen.setTransform(true);
    exit.setTransform(true);

    clickerGen.addListener(new GeneratorListener(player, CLICKER, assetManager, label));
    noteGen.addListener(new GeneratorListener(player, NOTE, assetManager, label));
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
    table.add(noteGen);
    table.row();
    table.add(microphoneGen);
    table.row();
    table.add(exit);
    table.row();
    table.add(label);

    //table.debug();

    ScrollPane scrollPane = new ScrollPane(table);
    scrollPane.setFillParent(true);
    addActor(scrollPane);

    this.player = player;
    this.clickerGen = clickerGen;
    this.microphoneGen = microphoneGen;
    this.noteGen = noteGen;
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

  @Override
  public void draw() {
    clickerGen.setDisabled(player.getPoints() < player.getGeneratorCost(CLICKER));
    microphoneGen.setDisabled(player.getPoints() < player.getGeneratorCost(MICROPHONE));
    noteGen.setDisabled(player.getPoints() < player.getGeneratorCost(NOTE));

    super.draw();
  }
}
