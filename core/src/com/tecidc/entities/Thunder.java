package com.tecidc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class Thunder extends GameObject{

    public Thunder(){
        super();
        this.pixmap = new Pixmap(Gdx.files.internal("statusModifiers/thunder.png"));
    }

}
