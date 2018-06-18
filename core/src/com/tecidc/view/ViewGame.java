package com.tecidc.view;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tecidc.Sockets.SocketCliente;
import com.tecidc.controller.Controller;
import com.tecidc.entities.Player;
import com.tecidc.entities.Shell;

import java.io.IOException;


/**
 * ViewGame: Clase encargada de coordinar la interacción entre el usuario-controlador-servidor
 */

public class ViewGame implements ApplicationListener {

    private OrthographicCamera camera;
    public Player player;
    private SpriteBatch batch;
    private View view;
    private Controller controller = new Controller();
    private SocketCliente socket = new SocketCliente();

    /**
     *
     * Realiza el set up de todos los atributos necesarios para jugar
     *
     */
    @Override
    public void create() {
        camera = new OrthographicCamera(256, 256);
        camera.position.set(128, 128, 0);
        camera.update();

        batch = new SpriteBatch();

        Pixmap trackSprite = new Pixmap(Gdx.files.internal("tracks/track.png"));



        view = new View(256, 256, Pixmap.Format.RGB565);
        view.camera.set(431, 345 + 16, 16);
        view.horizon = 30f;
        view.angle = (float) (-Math.PI / 2);
        view.floor = trackSprite;

        player = new Player(1);
        player.position.set(431, 345);
        view.players.add(player);

        socket.SocketCliente();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }


    /**
     *
     * Ordena la interacción entre el jugador-controlador y servidor aplicando el patron del Model-View-Controller
     *
     */
    @Override
    public void render() {
        controller.movePlayer(player, view);
        controller.shot(view.shells, player);

        try {
            socket.send("{"+ "Player:" + player.getPlayer().toString() + "," + "Position:" + player.position.toString() + "," + "Lives:" + player.lives.toString() + "," + "Points:" + player.points.toString() + "}");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
