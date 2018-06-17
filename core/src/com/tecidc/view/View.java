package com.tecidc.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tecidc.entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class View extends Pixmap {

	public Pixmap floor;
	public Texture texture;

	public Float horizon;
	public Float angle;
	public Vector3 camera;
	public Vector2 scale;
	public Vector2 objScale;
	public List<GameObject> sprites;

	public View(int width, int height, Format format) {
		super(width, height, format);

		setFilter(Filter.NearestNeighbour);

		camera = new Vector3(0, 0, 1);
		scale = new Vector2(100, 100);
		objScale = new Vector2(0.1f, 0.1f);
		sprites = new ArrayList<GameObject>();
	}

	public void render(SpriteBatch batch) {
		setColor(Color.rgb565(135, 206, 235));
		fill();

		int width = getWidth();
		int height = getHeight();

		double sin = Math.sin(angle);
		double cos = Math.cos(angle);

		if (floor != null) {
			for (int y = (int) horizon.floatValue(); y < height; y++) {
				float distance = (camera.z * scale.y) / (y - horizon);
				float ratio = distance / scale.x;

				double dx = -sin * ratio;
				double dy = cos * ratio;

				double sx = camera.x + distance * cos - width / 2 * dx;
				double sy = camera.y + distance * sin - width / 2 * dy;

				for (int x = 0; x < width; x++) {
					int cx = (int) Math.abs(sx % floor.getWidth());
					int cy = (int) Math.abs(sy % floor.getHeight());
					int color = floor.getPixel(cx, cy);

					setColor(color);
					drawPixel(x, y);

					sx += dx;
					sy += dy;
				}
			}
		}

		List<GameObject> visible = new ArrayList<GameObject>();

		for (GameObject sprite : sprites) {
			Float dx = sprite.position.x - camera.x;
			Float  dy = sprite.position.y - camera.y;

			Double sx = dx * cos + dy * sin;
			Double sy = dy * cos - dx * sin;

			Integer sw = sprite.getPixmap().getWidth();
			Integer sh = sprite.getPixmap().getHeight();
			Integer w = (int) (sw * scale.x / sx * objScale.x);
			Integer h = (int) (sh * scale.y / sx * objScale.y);

			// Negative height, sprite is behind camera
			if (h < 1) {
				continue;
			}

			Integer x = (int) (scale.x / sx * sy) + width / 2;
			Integer y = (int) ((camera.z * scale.y) / sx + horizon);

			// Align sprite center-bottom
			sprite.screen.x = x - w / 2;
			sprite.screen.y = y - h;
			sprite.size.x = w;
			sprite.size.y = h;
			sprite.sort = y;

			visible.add(sprite);
		}

		Collections.sort(visible);

		for (GameObject sprite : visible) {
			Integer sw = sprite.getPixmap().getWidth();
			Integer sh = sprite.getPixmap().getHeight();
			Integer x = (int) sprite.screen.x;
			Integer y = (int) sprite.screen.y;
			Integer w = (int) sprite.size.x;
			Integer h = (int) sprite.size.y;

			drawPixmap(sprite.getPixmap(), 0, 0, sw, sh, x, y, w, h);
		}

		texture = new Texture(this, getFormat(), true);

		batch.draw(texture, 0, 0);
	}
}