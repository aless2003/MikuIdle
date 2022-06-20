package online.hatsunemiku.games.idle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;
import online.hatsunemiku.games.idle.logic.Player;
import online.hatsunemiku.games.idle.ui.clicker.stage.ClickerStage;
import online.hatsunemiku.games.idle.ui.clicker.stage.ShopStage;
import online.hatsunemiku.games.idle.ui.shop.ShopView;

public class MikuIdleGame extends ApplicationAdapter {

  private SpriteBatch batch;
  private Viewport viewport;
  private ClickerStage stage;
  private ShopStage shopStage;

  private BitmapFont font;

  private Player player;
  private ShopView shopView;

  Texture bg;
  @Override
  public void create() {

    AssetManager assetManager = new AssetManager();
    batch = new SpriteBatch();
    assetManager.load("fonts/mikufont.png", Texture.class);
    assetManager.load("img/bg.png", Texture.class);
    assetManager.load("skins/StandardSkin.json", Skin.class);
    assetManager.load("sounds/buy.mp3", Sound.class);
    assetManager.load("sounds/cancel.mp3", Sound.class);

    assetManager.finishLoading();

    Texture fontTexture = assetManager.get("fonts/mikufont.png");
    Skin standardSkin = assetManager.get("skins/StandardSkin.json");

    float viewportWidth = 1000;
    float viewportHeight = 1000;

    bg = assetManager.get("img/bg.png");
    this.viewport = new FitViewport(viewportWidth, viewportHeight);
    player = new Player(100_000);
    this.stage = new ClickerStage(viewport, player);

    InputMultiplexer multiplexer = new InputMultiplexer();

    this.shopView = new ShopView(assetManager, player, multiplexer, viewport);
    this.shopStage = new ShopStage(standardSkin, viewport, shopView);

    multiplexer.addProcessor(shopStage);
    multiplexer.addProcessor(stage);
    Gdx.input.setInputProcessor(multiplexer);

    FileHandle fontFile = Gdx.files.internal("fonts/mikufont.fnt");

    font = new BitmapFont(fontFile, new TextureRegion(fontTexture), false);
    float fontScale = 0.128f * (viewport.getWorldWidth() / 100f);
    font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    font.getData().setScale(fontScale);


  }

  @Override
  public void render() {
    ScreenUtils.clear(Color.BLACK);

    if (shopView.shouldEnter()) {
      shopView.draw();
    } else {
      renderMainView();
    }

    player.generate(Gdx.graphics.getDeltaTime());
  }

  private void renderMainView() {
    viewport.apply();
    batch.setProjectionMatrix(viewport.getCamera().combined);
    batch.begin();
    batch.draw(bg, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
    batch.end();
    stage.draw();
    shopStage.draw();

    batch.begin();
    renderPoints();
    batch.end();
  }

  private void renderPoints() {
    float pointOffset = viewport.getWorldWidth() / 10f;
    float pointY = viewport.getWorldHeight() - pointOffset;
    float pointTargetWidth = viewport.getWorldWidth() / 2.5f;

    NumberFormat numberFormat = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, Style.SHORT);
    numberFormat.setMaximumFractionDigits(1);
    float scoreX = pointOffset + (pointOffset / 2f) + pointTargetWidth + (viewport.getWorldWidth() / 100f);
    float scoreTargetWidth = viewport.getWorldWidth() / 2.5f;

    font.draw(batch, "Points: ", pointOffset, pointY, pointTargetWidth, 1, false);
    String pointString = numberFormat.format(player.getPoints());
    float scaleX = font.getScaleX();
    float scaleY = font.getScaleY();
    float tempX = scaleX * (1 - (pointString.length() / 20f));
    font.getData().setScale(tempX, scaleY);
    font.draw(batch, pointString, scoreX, pointY, scoreTargetWidth, 1, false);
    font.getData().setScale(scaleX, scaleY);
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }
}
