package com.tecidc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class Life extends GameObject {
    public Life(){
        super();
        this.pixmap = new Pixmap(Gdx.files.internal("statusModifiers/1up.png"));
    }

}
