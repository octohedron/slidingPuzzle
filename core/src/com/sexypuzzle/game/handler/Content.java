package com.sexypuzzle.game.handler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;
public class Content {
  private HashMap<String, TextureAtlas> atlases;
  public static String[] maps = new String[3];
  public Content() {
    atlases = new HashMap<String, TextureAtlas>();
  }
  public void loadAtlas(String path, String key) {
    atlases.put(key, new TextureAtlas(Gdx.files.internal(path)));
  }
  public TextureAtlas getAtlas(String key) {
    return atlases.get(key);
  }
  public static void init(){
    setMaps();
  }
  public static void setMaps(){
    maps[0] = "kate6x6";
    maps[1] = "kate3x3";
    maps[2] = "buttons";
  }
}
