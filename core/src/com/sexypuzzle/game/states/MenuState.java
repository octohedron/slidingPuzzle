package com.sexypuzzle.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sexypuzzle.game.handler.Content;
import com.sexypuzzle.game.ui.GameButton;
public class MenuState extends State {
  private GameButton buttonStage;
  private GameButton easyButton;
  private BitmapFont welcomeFont;
  private BitmapFont difficultyMessageFont;
  private String welcomeText;
  private String difficultyMessage;
  public MenuState(GSM gsm) {
    super(gsm);
    welcomeFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    welcomeFont.getData().setScale(3, 3);
    difficultyMessageFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    difficultyMessageFont.getData().setScale(2, 2); // little smaller
    welcomeText = "Welcome to Sexy Puzzle!";
    difficultyMessage = "Select the game difficulty";
    buttonStage = new GameButton("buttons");
    buttonStage.addButton(0, Gdx.graphics.getHeight() / 2, // starting at the middle left
        Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8,
        "Easy", 0, gsm);
    buttonStage.addButton(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2,
                          Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8,
                          "Hard", 1, gsm);
  }
  @Override
  public void handleInput() {
  }
  @Override
  public void update(float dt) {
  }
  @Override
  public void render(SpriteBatch sb) {
    buttonStage.render();
    sb.begin();
    welcomeFont.draw(sb, welcomeText, 50, Gdx.graphics.getHeight() - 400);
    difficultyMessageFont.draw(sb, difficultyMessage, 200, Gdx.graphics.getHeight() * 0.7f);
    sb.end();
  }
}
