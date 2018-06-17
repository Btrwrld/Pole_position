package com.tecidc.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Shell extends GameObject {

    public Float speedX = 0f;
    public Float speedY = 0f;

    public Shell(Vector2 position){
        super(new Pixmap(Gdx.files.internal("statusModifiers/shell.png")), new Vector2(10f,10f), position);
        this.player = 5;
    }

    public void move(){
        this.position.x += speedX;
        this.position.y += speedY;
    }
}
