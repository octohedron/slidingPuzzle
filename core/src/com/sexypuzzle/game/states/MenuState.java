package com.sexypuzzle.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sexypuzzle.game.ui.GameButtonStage;
public class MenuState extends State {
  private GameButtonStage buttonStage;
  private GameButtonStage easyButton;
  private BitmapFont welcomeFont;
  private BitmapFont difficultyMessageFont;
  private String welcomeText;
  private String difficultyMessage;
  public MenuState(GSM gsm) {
    super(gsm);
    welcomeFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    difficultyMessageFont = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    welcomeFont.getData().setScale(2, 2);
    difficultyMessageFont.getData().setScale(2, 2);
    welcomeText = "Welcome to Sexy Puzzle!";
    difficultyMessage = "Select the game difficulty";
    buttonStage = new GameButtonStage("buttons");
    buttonStage.addButton(0, Gdx.graphics.getHeight() / 3, // starting at the middle left
        Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 8,
        "Easy", 0, gsm);
    buttonStage.addButton(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3,
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
    welcomeFont.draw(sb, welcomeText, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.7f);
    difficultyMessageFont.draw(sb, difficultyMessage, Gdx.graphics.getWidth() * 0.08f, Gdx.graphics.getHeight() * 0.6f);
    sb.end();
  }
}
