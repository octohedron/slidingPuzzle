package com.sexypuzzle.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sexypuzzle.game.SexyPuzzle;
import com.sexypuzzle.game.states.GSM;
import com.sexypuzzle.game.states.MenuState;
import com.sexypuzzle.game.states.PlayState;
public class GameButtonStage extends Box {
  public static class ButtonBonds {
    float x;
    float y;
    float width;
    float height;
    public ButtonBonds(float x, float y, float width, float height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }
  }
  public TextureRegion btnTextureRegion;
  public String buttonText;
  public BitmapFont font;
  private Stage stage;
  private String map;
  private int type;
  ButtonBonds bonds;
  /**
   * Use this constructor to set the button location and size
   */
  public GameButtonStage(String map) {
    stage = new Stage();
    this.map = map;
  }
  public void addButton(float x, float y, float width, float height, String buttonText, final int type, final GSM gsm) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.buttonText = buttonText;
    this.type = type;
    this.font = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    this.bonds = new ButtonBonds(x, y, width, height);
    btnTextureRegion = SexyPuzzle.res.getAtlas(map).findRegion("buttons");
    TextButtonStyle textButtonStyle = new TextButtonStyle();
    Skin skin = new Skin(SexyPuzzle.res.getAtlas(map));
    switch (type) {
      case 0:
        textButtonStyle.up = skin.getDrawable("btn-green-up");
        break;
      case 1:
        textButtonStyle.up = skin.getDrawable("btn-red-up");
        break;
      case 2:
        textButtonStyle.up = skin.getDrawable("btn-pink-up");
        break;
      default:
        textButtonStyle.up = skin.getDrawable("btn-green-up");
    }
    font.getData().setScale(3, 3);
    textButtonStyle.font = font;
    TextButton textButton = new TextButton(buttonText, textButtonStyle);
    float btnPadding = 15f;
    textButton.setBounds(bonds.x, bonds.y,
        bonds.width + btnPadding,
        bonds.height + btnPadding);
    textButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        switch (type) {
          case 0:
            gsm.set(new PlayState(gsm, type));
            break;
          case 1:
            gsm.set(new PlayState(gsm, type));
            break;
          case 2:
            gsm.set(new MenuState(gsm));
            break;
        }
      }
    });
    stage.addActor(textButton);
    Gdx.input.setInputProcessor(stage);
  }
  public void render() {
    stage.draw();
  }
}

