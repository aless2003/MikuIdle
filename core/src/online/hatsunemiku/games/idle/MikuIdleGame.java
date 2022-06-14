package online.hatsunemiku.games.idle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import online.hatsunemiku.games.idle.logic.Player;
import online.hatsunemiku.games.idle.ui.clicker.ClickerStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MikuIdleGame extends ApplicationAdapter {

  private static final Logger log = LoggerFactory.getLogger(MikuIdleGame.class);
  private SpriteBatch batch;
  private Texture img;
  private Viewport viewport;
  private ClickerStage stage;

  private BitmapFont font;

  private Player player;

  @Override
  public void create() {
    batch = new SpriteBatch();
    img = new Texture("img/v4x.png");
    this.viewport = new FitViewport(500, 500);

    player = new Player(0);
    this.stage = new ClickerStage(viewport, player);
    Gdx.input.setInputProcessor(stage);

    Texture fontTexture = new Texture("fonts/mikufont.png");
    FileHandle fontFile = Gdx.files.internal("fonts/mikufont.fnt");
    font = new BitmapFont(fontFile, new TextureRegion(fontTexture), false);
    float fontScale = 0.128f * (viewport.getWorldWidth() / 100f);
    font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    font.getData().setScale(fontScale);

  }

  @Override
  public void render() {
    ScreenUtils.clear(Color.BLACK);
    viewport.apply();
    batch.setProjectionMatrix(viewport.getCamera().combined);
    stage.draw();

    batch.begin();

    float pointOffset = viewport.getWorldWidth() / 10f;
    float pointY = viewport.getWorldHeight() - pointOffset;
    float pointTargetWidth = viewport.getWorldWidth() / 2.5f;

    float scoreX = pointOffset + pointTargetWidth + (viewport.getWorldWidth() / 100f);
    float scoreTargetWidth = viewport.getWorldWidth() / 2f;

    font.draw(batch, "Points: ", pointOffset, pointY, pointTargetWidth, 1, false);
    font.draw(batch, String.valueOf(player.getPoints()), scoreX, pointY, scoreTargetWidth, 1, false);
    batch.end();
  }

  @Override
  public void dispose() {
    batch.dispose();
    img.dispose();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }
}
