package com.sexypuzzle.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sexypuzzle.game.handler.Content;
import com.sexypuzzle.game.ui.GameButton;
public class MenuState extends State {
  private GameButton newGameButton;
  private BitmapFont welcomeFont;
  private BitmapFont pressNewGameFont;
  private String welcomeText;
  private String pressNewGame;
  public MenuState(GSM gsm) {
    super(gsm);
    welcomeFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    welcomeFont.getData().setScale(3, 3);
    pressNewGameFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    pressNewGameFont.getData().setScale(2, 2); // little smaller
    welcomeText = "Welcome to Sexy Puzzle!";
    pressNewGame = "Press New game to start playing";
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
    newGameButton.render();
    sb.begin();
    welcomeFont.draw(sb, welcomeText, 50, Gdx.graphics.getHeight() - 400);
    pressNewGameFont.draw(sb, pressNewGame, 90, Gdx.graphics.getHeight()/2);
    sb.end();
  }
}
