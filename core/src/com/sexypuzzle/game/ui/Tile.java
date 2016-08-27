package com.sexypuzzle.game.ui;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Tile extends Box {
  public TextureRegion textureRegion;
  public boolean selected;
  public int tilePosition;
  private float totalWidth;
  private float totalHeight;
  private float timer;
  private float xdest;
  private float ydest;
  private float dx;
  private float dy;
  public Tile(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.totalWidth = width - 8;
    this.totalHeight = height - 8;
    tilePosition++;
  }
  public Tile(float width, float height) {
    this.totalWidth = width - 8;
    this.totalHeight = height - 8;
  }
  public void setTileCoords(float x, float y) {
    this.x = x;
    this.y = y;
  }
  public void setDestination(float xdest, float ydest) {
    this.xdest = xdest;
    this.ydest = ydest;
    dx = xdest - x;
    dy = ydest - y;
    float dist = (float) Math.sqrt(dx * dx + dy * dy);
    dx /= dist;
    dy /= dist;
  }
  public int checkOrder() {
    return tilePosition;
  }
  public void setTilePosition(int newPos) {
    tilePosition = newPos;
  }
  public void setSelected(boolean b) {
    selected = b;
  }
  public void setTimer(float t) {
    timer = t;
  }
  public void setTextureRegion(TextureRegion textureRegion) {
    this.textureRegion = textureRegion;
  }
  public TextureRegion getTextureRegion() {
    return textureRegion;
  }
  public void update(float dt) {
    float maxTime = 0.5f;
    if (timer < 0) {
      width = height = 0;
    }
    if (width < totalHeight && height < totalHeight) {
      timer += dt;
      width = (timer / maxTime) * totalWidth;
      height = (timer / maxTime) * totalHeight;
      if (width < 0) width = 0;
      if (height < 0) height = 0;
      if (width > totalWidth) {
        width = totalWidth;
      }
      if (height > totalHeight) {
        height = totalHeight;
      }
    }
  }
  public void updateAnimation(float dt) {
    float speed = 900;
    x += dx * speed * dt;
    y += dy * speed * dt;
    if ((dx < 0 && x <= xdest) || (dx > 0 && x >= xdest)) {
      dx = 0;
      x = xdest;
    }
    if ((dy < 0 && y <= ydest) || (dy > 0 && y >= ydest)) {
      dy = 0;
      y = ydest;
    }
  }
  public void render(SpriteBatch sb) {
    sb.draw(getTextureRegion(), x - width / 2, y - height / 2, width, height);
  }
  public boolean isSelected() {
    return selected;
  }
  public void printRegion() {
    System.out.println("sx= " + Float.toString(x)
                           + " ex = " + Float.toString(x + totalWidth));
  }
}
