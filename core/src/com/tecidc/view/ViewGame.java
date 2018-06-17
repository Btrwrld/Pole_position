package com.tecidc.view;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tecidc.controller.Controller;
import com.tecidc.entities.Player;


public class ViewGame implements ApplicationListener {

    OrthographicCamera camera;
    Player player;
    SpriteBatch batch;
    View view;

    @Override
    public void create() {
        camera = new OrthographicCamera(256, 256);
        camera.position.set(128, 128, 0);
        camera.update();

        batch = new SpriteBatch();

        Pixmap trackSprite = new Pixmap(Gdx.files.internal("track1.png"));

        view = new View(256, 256, Pixmap.Format.RGB565);
        view.camera.set(431, 345 + 16, 16);
        view.horizon = 30f;
        view.angle = (float) (-Math.PI / 2);
        view.floor = trackSprite;

        player = new Player(1);
        player.position.set(431, 345);
        view.sprites.add(player);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void input() {
        Controller.movePlayer(player, view);
    }

    @Override
    public void render() {
        input();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        view.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
