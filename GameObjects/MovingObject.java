package GameObjects;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graphics.Assets;
import Graphics.Sound;
import Math.Vector2D;
import States.GameState;

public abstract class MovingObject extends GameObject {

	protected Vector2D velocity; // TODOS LOS OBJETOS MOVILES TIENEN VELOCIDAD
	protected AffineTransform at;// NOS AYUDA A ROTAR EL OBJETO
	protected double angle;// HACIA DONDE MIRA EL OBJETO
	protected double maxVel;
	protected int width;
	protected int height;
	protected GameState gameState;
	private Sound explosion;
	protected boolean Dead;

	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
			GameState gameState) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.gameState = gameState;
		width = texture.getWidth();
		height = texture.getHeight();
		angle = 0;
		explosion = new Sound(Assets.explosion);
		Dead = false;
	}

//	COLLISION
	protected void collidesWith() {

		ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();

		for (int i = 0; i < movingObjects.size(); i++) {

			MovingObject m = movingObjects.get(i);

			if (m.equals(this))
				continue;

			double distance = m.getCenter().subtract(getCenter()).getMagnitude();

			if (distance < m.width / 2 + width / 2 && movingObjects.contains(this) && !m.Dead && !Dead) {
				objectCollision(this, m);
			}
		}
	}

//	EN EL CASO DE QUE DOS OBJETOS COLISIONEN 
	private void objectCollision(MovingObject a, MovingObject b) {

		Player p = null;

		if (a instanceof Player)
			p = (Player) a;
		else if (b instanceof Player)
			p = (Player) b;

		if (p != null && p.isSpawning())
			return;

		if (a instanceof Meteor && b instanceof Meteor)
			return;

		if (!(a instanceof PowerUp || b instanceof PowerUp)) {
			a.Destroy();
			b.Destroy();
			return;
		}

		if (p != null) {
			if (a instanceof Player) {
				((PowerUp) b).executeAction();
				b.Destroy();
			} else if (b instanceof Player) {
				((PowerUp) a).executeAction();
				a.Destroy();
			}
		}

	}

	protected void Destroy() {
		Dead = true;
		if (!(this instanceof Laser) && !(this instanceof PowerUp))
			explosion.play();
	}

	protected Vector2D getCenter() {
		return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
	}

	public boolean isDead() {
		return Dead;
	}

}// FINAL CLASS
