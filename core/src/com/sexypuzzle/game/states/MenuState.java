package com.sexypuzzle.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sexypuzzle.game.SexyPuzzle;
import com.sexypuzzle.game.handler.Content;
import com.sexypuzzle.game.ui.GameButton;
/**
 * Created on 8/27/16.
 */
public class MenuState extends State {
  private GameButton newGameButton;
  private BitmapFont font;
  private String welcomeText;
  public MenuState(GSM gsm) {
    super(gsm);
    font = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    welcomeText = "Welcome to Sexy Puzzle!";
    newGameButton =
        new GameButton(0, 0, // starting at the bottom left
                          Gdx.graphics.getWidth(), Gdx.graphics.getWidth() / 4, // heght
                          "NEW GAME");
    newGameButton.create(Content.maps[2], gsm);
  }
  @Override
  public void handleInput() {
  }
  @Override
  public void update(float dt) {
  }
  @Override
  public void render(SpriteBatch sb) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    sb.setProjectionMatrix(cam.combined);
    sb.setColor(0, 0, 0, 1);
    sb.begin();
    font.setColor(100f, 100f, 100f, 1);
    font.draw(sb, welcomeText, Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.7f);
    newGameButton.render();
    sb.end();
  }
}
