package com.tecidc.entities;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;


public abstract class GameObject implements Comparable<GameObject> {


    protected Pixmap pixmap;

    public int sort;
    public Vector2 screen;
    public Vector2 size;
    public Vector2 position;

    public GameObject(){}

    public GameObject(Pixmap pixmap, Vector2 position, Vector2 size) {
        this.pixmap = pixmap;

        this.screen = new Vector2();
        this.size = position;
        this.position = size;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }


    @Override
    public int compareTo(GameObject other) {
        return 0;
    }

}

