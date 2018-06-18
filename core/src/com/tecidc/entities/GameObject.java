package com.tecidc.entities;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

/**
 * GameObject: Clase padre de todos los actores del juego
 *
 *  protected Integer player: Identificador, define si es un jugador(1-4), un disparo(5), una vida(6), un turbo(7) o un trueno(8)
 *  public Vector2 position: Define la posición en la pantalla.
 *  protected Pixmap pixmap: almacena el sprite
 *
 */
public abstract class GameObject implements Comparable<GameObject> {


    protected Pixmap pixmap;

    public int sort;
    protected Integer player;

    public Vector2 screen;
    public Vector2 size;
    public Vector2 position;

    public GameObject(){
        this.screen = new Vector2();
        this.size = new Vector2();
        this.position = new Vector2();
    }

    public GameObject(Pixmap pixmap, Vector2 position, Vector2 size) {
        this.pixmap = pixmap;

        this.screen = new Vector2();
        this.size = position;
        this.position = size;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }
    public java.lang.Integer getPlayer() {
        return player;
    }


    @Override
    public int compareTo(GameObject other) {
        return 0;
    }

}

