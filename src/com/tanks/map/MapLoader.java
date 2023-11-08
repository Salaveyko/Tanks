package com.tanks.map;

import java.io.*;

public class MapLoader {

    public static GameMap loadDefaultMap() {
        MapEditor mapEditor = new MapEditor();
        return mapEditor.mapFromArray();
    }

    public static GameMap loadGameMap(String filePath) {
        GameMap gm;
        try (ObjectInputStream oIn = new ObjectInputStream(
                new FileInputStream(filePath)
        )) {
            gm = (GameMap) oIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            gm = loadDefaultMap();
        }

        return gm;
    }

    public static void saveGameMap(GameMap gameMap, String filePath) {
        try (ObjectOutputStream oOut = new ObjectOutputStream(
                new FileOutputStream(filePath))
        ) {
            oOut.writeObject(gameMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
