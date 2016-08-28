package com.sexypuzzle.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sexypuzzle.game.SexyPuzzle;
import com.sexypuzzle.game.handler.Content;
import com.sexypuzzle.game.ui.GameButton;
import com.sexypuzzle.game.ui.Grid;
import com.sexypuzzle.game.ui.Tile;

import java.util.ArrayList;
public class PlayState extends State {
  private float sx;
  private float sy;
  private float ctx;
  private float cty;
  private int clickedRow;
  private int posOfClickedTile;
  private int clickedCol;
  private Tile clickedTile;
  private BitmapFont font;
  private String playingText;
  private Grid mainGrid;
  private GameButton buttonStage;
  public static int level;
  public PlayState(GSM gsm) { // default constructor
    super(gsm);
    /*font = new BitmapFont();
    font.setColor(Color.WHITE);
    newGameButton = new GameButton(0, Gdx.graphics.getHeight() - 260, // starting at the top left
                                      Gdx.graphics.getWidth(), Gdx.graphics.getWidth() / 6,
                                      "NEW GAME");
    newGameButton.create(Content.maps[2], gsm);
    mainGrid = new Grid(3, 3, Content.maps[1]);*/
  }
  public PlayState(GSM gsm, int level) {
    super(gsm);
    PlayState.level = level;
    font = new BitmapFont();
    font.setColor(Color.WHITE);
    font.getData().setScale(2, 2);
    playingText = "All puzzles can be solved!";
    buttonStage = new GameButton("buttons");
    switch (level) {
      case 0: // easy game
        mainGrid = new Grid(3, 3, Content.maps[1]);
        buttonStage.addButton(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6,
            "New easy game", 0, gsm);
        break;
      case 1: // hard game
        mainGrid = new Grid(6, 6, Content.maps[0]);
        buttonStage.addButton(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6,
            "New hard game", 1, gsm);
        break;
      default: // default is medium
        mainGrid = new Grid(6, 6, Content.maps[0]);
        buttonStage.addButton(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6,
            "New easy game", 0, gsm);
    }
  }
  @Override
  public void handleInput() {
    if (Gdx.input.justTouched()) {
      mouse.x = Gdx.input.getX();
      mouse.y = Gdx.input.getY();
      cam.unproject(mouse);
      sx = mouse.x;
      sy = mouse.y;
      clickedRow = (int) ((mouse.y - Grid.BOARD_OFFSET) / Grid.TILE_SIZE);
      clickedCol = (int) (mouse.x / Grid.TILE_SIZE);
      if (clickedCol >= 0 && clickedCol < mainGrid.tileGrid.length
              && clickedRow >= 0 && clickedRow < mainGrid.tileGrid[0].length) {
        clickedTile = mainGrid.tileGrid[clickedRow][clickedCol];
        posOfClickedTile = clickedTile.tilePosition;
        ctx = clickedTile.x;
        cty = clickedTile.y;
      }
      if (mouse.y > Grid.BOARD_OFFSET &&
              mouse.y < Grid.BOARD_OFFSET + Grid.BOARD_HEIGHT &&
              mouse.x > 0 && mouse.x < SexyPuzzle.WIDTH) {
        ArrayList<int[]> aM = mainGrid.allowedTileMoves(clickedRow, clickedCol);
        for (int i = 0; i < aM.size(); i++) {
          if (mainGrid.tileGrid[clickedRow + aM.get(i)[0]][clickedCol + aM.get(i)[1]].tilePosition == 0) {
            mainGrid.swapTile(clickedRow, clickedCol, aM.get(i)[1], aM.get(i)[0]);
          }
        }
      }
    }
  }
  public boolean isBorder() {
    return !(clickedCol > 0 ||
                 clickedCol < mainGrid.tileGrid.length ||
                 clickedRow > 0 ||
                 clickedRow < mainGrid.tileGrid[0].length);
  }
  @Override
  public void update(float dt) {
    handleInput();
    for (int row = 0; row < mainGrid.tileGrid.length; row++) {
      for (int col = 0; col < mainGrid.tileGrid[0].length; col++) {
        mainGrid.tileGrid[row][col].update(dt);
        mainGrid.tileGrid[row][col].updateAnimation(dt);
      }
    }
  }
  @Override
  public void render(SpriteBatch sb) {
    sb.setProjectionMatrix(cam.combined);
    sb.begin();
    for (int row = 0; row < mainGrid.tileGrid.length; row++) {
      for (int col = 0; col < mainGrid.tileGrid[0].length; col++) {
        mainGrid.tileGrid[row][col].render(sb);
      }
    }
    sb.end();
    sb.begin();
    buttonStage.render();
    sb.end();
    sb.begin();
    font.draw(sb, playingText, 90, Grid.BOARD_HEIGHT * 1.5f);
    sb.end();
  }
}
