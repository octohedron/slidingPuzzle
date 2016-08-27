package com.sexypuzzle.game.ui;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sexypuzzle.game.SexyPuzzle;

import java.util.ArrayList;
import java.util.Random;
public class Grid extends Box {
  public Tile[][] tileGrid;
  public Tile[][] gridForSwap;
  public TextureRegion[][] textureRegions;
  public static int TILE_SIZE;
  public static float BOARD_OFFSET;
  public static int BOARD_HEIGHT;
  public static int NUM_ROWS;
  public static int NUM_COLS;
  public Grid(int rows, int cols, String map) {
    NUM_ROWS = rows;
    NUM_COLS = cols;
    textureRegions = new TextureRegion[rows][cols];
    tileGrid = new Tile[rows][cols];
    gridForSwap = new Tile[rows][cols];
    TILE_SIZE = SexyPuzzle.WIDTH / cols;
    BOARD_HEIGHT = TILE_SIZE * rows;
    BOARD_OFFSET = (SexyPuzzle.HEIGHT - BOARD_HEIGHT) / 2;
    int picNumber = 0;
    System.out.println(map);
    for (int row = NUM_ROWS - 1; row >= 0; row--) {
      for (int col = 0; col <= NUM_COLS - 1; col++) {
        gridForSwap[row][col] = new Tile(col * TILE_SIZE + TILE_SIZE / 2,
                                            row * TILE_SIZE + BOARD_OFFSET + TILE_SIZE / 2,
                                            TILE_SIZE, TILE_SIZE);
        tileGrid[row][col] = new Tile(TILE_SIZE, TILE_SIZE);
        tileGrid[row][col].tilePosition = picNumber;
        tileGrid[row][col]
            .setTextureRegion(SexyPuzzle.res.getAtlas(map)
                                  .findRegion(Integer.toString(picNumber)));
        tileGrid[row][col].setTileCoords(gridForSwap[row][col].x, gridForSwap[row][col].y);
        tileGrid[row][col].setTimer((-((NUM_COLS - 1) - row) - col) * 0.05f);
        picNumber++;
      }
    }
    TextureRegion blankRegion = SexyPuzzle.res.getAtlas("kate6x6")
                                    .findRegion(Integer.toString(36)); // blank tile
    tileGrid[NUM_ROWS - 1][0].setTextureRegion(blankRegion);
    shuffle();
  }
  public void swapTile(int clickedRow, int clickedCol, int x, int y) {
    Tile temp = tileGrid[clickedRow][clickedCol];
    tileGrid[clickedRow][clickedCol] = tileGrid[clickedRow + y][clickedCol + x];
    tileGrid[clickedRow + y][clickedCol + x] = temp; // swaps them in the array
    tileGrid[clickedRow][clickedCol]
        .setDestination(tileGrid[clickedRow + y][clickedCol + x].x,
                           tileGrid[clickedRow + y][clickedCol + x].y);
    tileGrid[clickedRow + y][clickedCol + x]
        .setDestination(tileGrid[clickedRow][clickedCol].x,
                           tileGrid[clickedRow][clickedCol].y);
    checkFinished();
  }
  public int[] moveTile(int clickedRow, int clickedCol, int x, int y) {
    //set new textures, prev tile first (current)
    TextureRegion pRegion =
        new TextureRegion(tileGrid[clickedRow][clickedCol].textureRegion);
    //set next tile texture
    tileGrid[clickedRow][clickedCol].textureRegion =
        new TextureRegion(tileGrid[clickedRow + y][clickedCol + x].textureRegion);
    //set prev tile
    tileGrid[clickedRow + y][clickedCol + x].textureRegion = pRegion;
    //fix position
    int prevPos = tileGrid[clickedRow][clickedCol].tilePosition;
    tileGrid[clickedRow][clickedCol]
        .setTilePosition(tileGrid[clickedRow + y][clickedCol + x].tilePosition);
    tileGrid[clickedRow + y][clickedCol + x].setTilePosition(prevPos);
    return new int[]{clickedRow + y, clickedCol + x};
  }
  public ArrayList<int[]> allowedTileMoves(int row, int col) {
    ArrayList<int[]> moves = new ArrayList<int[]>();
    if (row < NUM_ROWS - 1) {
      // can move up
      moves.add(new int[]{1, 0});
    }
    if (col < NUM_COLS - 1) {
      // can move right
      moves.add(new int[]{0, 1});
    }
    if (row > 0) {
      // can move down
      moves.add(new int[]{-1, 0});
    }
    if (col > 0) {
      //can move left
      moves.add(new int[]{0, -1});
    }
    return moves;
  }
  public void shuffle() {
    int[] blankTile = {NUM_ROWS - 1, 0}; // starting top left, = hole
    Random ran = new Random();
    blankTile = moveTile(blankTile[0], blankTile[1], 1, 0);
    int[] prevMove = {0, 0};
    for (int i = 0; i < 100; i++) {
      // gets a list of possible moves
      ArrayList<int[]> aM = allowedTileMoves(blankTile[0], blankTile[1]);
      //gets a random possible move
      int[] tryMove = aM.get(ran.nextInt(aM.size()));
      //checks if the move chosen will swap the tile with the
      //previously swapped tile
      if (tryMove[0] == -prevMove[0] && tryMove[1] == -prevMove[1]) { // if it's there
        //remove it from the list and select a different one
        aM.remove(tryMove);
        tryMove = aM.get(ran.nextInt(aM.size()));
      }
      blankTile = moveTile(blankTile[0], blankTile[1], tryMove[1], tryMove[0]);
      prevMove = tryMove;
    }
  }
  public void checkFinished() {
    boolean solved = true;
    for (int row = NUM_ROWS - 1; row >= 0; row--) {
      for (int col = 0; col < NUM_COLS; col++) {
        if (col < NUM_COLS - 1) {
          if (tileGrid[row][col].tilePosition > tileGrid[row][col + 1].tilePosition) {
            solved = false;
          }
        } else {
          if (row > 0) {
            if (tileGrid[row][col].tilePosition > tileGrid[row - 1][0].tilePosition) {
              solved = false;
            }
          }
        }
      }
    }
    if (solved) {
//      printTilePositions();
      System.out.println("Puzzle solved");
    }
  }
  public void printTilePositions() {
    System.out.println(" -tile positions- ");
    for (int row = NUM_ROWS - 1; row >= 0; row--) {
      for (int col = 0; col < NUM_COLS; col++) {
        System.out.print(tileGrid[row][col].tilePosition + " ");
      }
      System.out.println();
    }
  }
}
