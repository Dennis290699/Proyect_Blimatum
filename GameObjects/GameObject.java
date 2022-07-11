package GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Math.Vector2D;

public abstract class GameObject {
	protected BufferedImage texture;
	protected Vector2D position;

	public GameObject(Vector2D position, BufferedImage texture) {
		this.position = position;
		this.texture = texture;
	}

	public abstract void update(float dt);

	public abstract void draw(Graphics g);

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

}// FINAL CLASS
