package com.tecidc.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tecidc.entities.Player;
import com.tecidc.view.View;

public class Controller {

    public static void movePlayer(Player player, View view){

        Long start = 1000000000*System.currentTimeMillis();
        Float delta = Gdx.graphics.getDeltaTime();
        Float x = (float)Gdx.input.getX();
        Float y = (float)Gdx.input.getY();
        Float w = (float)Gdx.graphics.getWidth();
        Float h = (float)Gdx.graphics.getHeight();

        if (Gdx.input.isTouched()) {
            Float dx = w / 2 - x;
            Float dy = h / 2 - y;
            Double rot = Math.atan2(dy, dx);
            /*System.out.printf("dx %f ", dx);
            System.out.printf("dy %f \n", dy);*/

            view.angle -= 1f * (float) Math.cos(rot) * delta;
            view.horizon += 20f * (float) Math.sin(rot) * delta;
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            view.camera.y += 18f * (float) Math.sin(view.angle) * delta;
        }


        if( Gdx.input.isKeyPressed(Input.Keys.S)){
            player.move(3);
            player.turnTimer += 100000000*System.currentTimeMillis() - start;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.move(4);
            player.turnTimer += 100000000*System.currentTimeMillis() - start;
        }
        if( Gdx.input.isKeyPressed(Input.Keys.D)){
            player.move(1);
            player.turnTimer = 0l;
        }
        else if( Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move(2);
            player.turnTimer = 0l;
        }



        if(player.turnTimer >= 0.000000000000000000000000000001 ){
            player.resetSprite();
        }

    }

}
