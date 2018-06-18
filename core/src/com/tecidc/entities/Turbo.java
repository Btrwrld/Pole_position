package com.tecidc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class Turbo extends GameObject {

    public Turbo(){
        super();
        this.pixmap = new Pixmap(Gdx.files.internal("statusModifiers/turbo.png"));
        this.player = 7;
    }
}
