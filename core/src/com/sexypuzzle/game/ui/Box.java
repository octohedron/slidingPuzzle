package com.sexypuzzle.game.ui;
/**
 * Created by andy on 2/26/16.
 */
public class Box {
  public float x;
  public float y;
  protected float width;
  protected float height;
  public boolean contains(float x, float y) {
    return x > this.x - width / 2 &&
               x < this.x + width / 2 &&
               y > this.y - height / 2 &&
               y < this.y + height / 2;
  }
}
