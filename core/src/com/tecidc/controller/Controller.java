package com.tecidc.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.tecidc.entities.Player;
import com.tecidc.entities.Shell;
import com.tecidc.view.View;

import java.util.List;

public class Controller {

    private long turnTimer = 0l;
    private long shotTimer = 0l;

    public void movePlayer(Player player, View view){

        Long start = 1000000000*System.currentTimeMillis();
        Float delta = Gdx.graphics.getDeltaTime();
        Float x = (float)Gdx.input.getX();
        Float y = (float)Gdx.input.getY();
        Float w = (float)Gdx.graphics.getWidth();
        Float h = (float)Gdx.graphics.getHeight();

        Float dx;
        Float dy;
        Double rot;

        if( Gdx.input.isTouched()){

            dx = w / 2 - x;
            dy = h / 2 - y;
            rot = Math.atan2(dy, dx);

            view.angle -= 1f * (float) Math.cos(rot) * delta;
            view.horizon += 20f * (float) Math.sin(rot) * delta;
            /*view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            view.camera.y += 18f * (float) Math.sin(view.angle) * delta;*/
        }

        if( Gdx.input.isKeyPressed(Input.Keys.E)){
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            view.camera.y += 18f * (float) Math.sin(view.angle) * delta;
        }
        else if( Gdx.input.isKeyPressed(Input.Keys.Q)){
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            view.camera.y -= 18f * (float) Math.sin(view.angle) * delta;
        }


        if( Gdx.input.isKeyPressed(Input.Keys.S)){
            player.move(3);
            this.turnTimer += 100000000*System.currentTimeMillis() - start;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){

            player.move(4);
            this.turnTimer += 100000000*System.currentTimeMillis() - start;

            dx = 0f;
            dy = 0f;
            rot = Math.atan2(dy, dx);

            /*view.angle -= 1f * (float) Math.cos(rot) * delta;
            view.horizon += 20f * (float) Math.sin(rot) * delta;*/
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            view.camera.y += 18f * (float) Math.sin(view.angle) * delta;
        }
        if( Gdx.input.isKeyPressed(Input.Keys.D)){

            player.move(1);
            this.turnTimer = 0l;

            dx = w / 2 - player.position.x;
            dy = h / 2 - y;
            rot = Math.atan2(dy, dx);

            view.angle -= 0.5f * (float) Math.cos(rot) * delta;
            view.horizon += 20f * (float) Math.sin(rot) * delta;
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            //view.camera.y += 18f * (float) Math.sin(view.angle) * delta;

        }
        else if( Gdx.input.isKeyPressed(Input.Keys.A)){
            player.move(2);
            this.turnTimer = 0l;

            dx = w / 2 - player.position.x;
            dy = h / 2 - y;
            rot = Math.atan2(dy, dx);

            view.angle += 0.5f * (float) Math.cos(rot) * delta;
            view.horizon += 20f * (float) Math.sin(rot) * delta;
            view.camera.x += 18f * (float) Math.cos(view.angle) * delta;
            //view.camera.y += 18f * (float) Math.sin(view.angle) * delta;
        }



        if(this.turnTimer >= 0.000000000000000000000000000001 ){
            player.resetSprite();
        }

    }

    public void shot(List<Shell> shells, Player player){

        for(Shell shell : shells){
            shell.move();
        }

        if((Gdx.input.isKeyPressed(Input.Keys.SPACE)) && (System.currentTimeMillis() - this.shotTimer >= 500)){

            Shell shell = new Shell(new Vector2(player.position.x, player.position.y - 1));
            this.shotTimer = System.currentTimeMillis();


            if( Gdx.input.isKeyPressed(Input.Keys.W)){
               shell.speedY = -0.8f;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                shell.speedY = 0.8f;
            }
            else if( Gdx.input.isKeyPressed(Input.Keys.D)){
                shell.speedX = 0.8f;
            }
            else if( Gdx.input.isKeyPressed(Input.Keys.A)){
                shell.speedX = -0.8f;
            }
            else{
                shell.speedY = -0.8f;
            }

            shells.add(shell);
        }

    }

}
