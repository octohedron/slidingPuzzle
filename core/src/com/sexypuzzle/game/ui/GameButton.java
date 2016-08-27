package com.sexypuzzle.game.ui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.sexypuzzle.game.states.PlayState;
public class GameButton extends Box {
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
  public Texture btnTexture;
  public String name;
  public BitmapFont font;
  private Stage stage;
  ButtonBonds bonds;
  //  private Table table;
  private TextButton newGameBtn;
  public GameButton(float x, float y, float width, float height, String name) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.name = name;
    this.font = new BitmapFont(Gdx.files.internal("Purisa.fnt"));
    this.bonds = new ButtonBonds(x, y, width, height);
  }
  public void create(String map, final GSM gsm) {
    stage = new Stage();
    btnTextureRegion = SexyPuzzle.res.getAtlas(map).findRegion("buttons");
    TextButtonStyle textButtonStyle = new TextButtonStyle();
    Skin skin = new Skin(SexyPuzzle.res.getAtlas(map));
    textButtonStyle.up = skin.getDrawable("btn-green-up");
    textButtonStyle.font = font;
    font.getData().setScale(5, 5);
    TextButton newGameBtn = new TextButton(name, textButtonStyle);
    float btnPadding = 15f;
    newGameBtn.setBounds(bonds.x, bonds.y,
        bonds.width + btnPadding,
        bonds.height + btnPadding);
    newGameBtn.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Clicked");
        PlayState state = new PlayState(gsm, 1);
        gsm.set(state);
      }
    });
    stage.addActor(newGameBtn);
    Gdx.input.setInputProcessor(stage);
  }
  public void addListener(final GSM gsm) {
  }
  public void render() {
    stage.draw();
  }
}

