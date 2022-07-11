package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graphics.Assets;
import Graphics.Sound;
import Math.Vector2D;
import States.GameState;

public class Ufo extends MovingObject{

	private ArrayList<Vector2D> path; //CONTIENE LOS NODOS QUE CONFORMA EL CAMINO
	private Vector2D currentNode; //REFERENCIA AL NODO ACTUAL QUE SIGUE EL OBJETO
	private int index; //REPRESENTA EL INDICE EN EL ARREGLO DE NODOS "EL CAMINO"
	private boolean following; // SERA FALSO CUANDO EL OBJETO HAY TERMINADO EL RECORRIDO
	private long fireRate;
	private Sound shoot;
	
	public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
			ArrayList<Vector2D> path, GameState gameState) {
		super(position, velocity, maxVel, texture, gameState);
		this.path = path;
		index = 0;
		following = true;
		fireRate = 0;
		shoot = new Sound(Assets.ufoShoot);
	}
//	GESTION DE CAMINO
	private Vector2D pathFollowing() {
		currentNode = path.get(index);
		double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
		
		if(distanceToNode < Constants.NODE_RADIUS) {
			index++;
			if(index >= path.size()) {
				following = false;
			}
		}
		return seekForce(currentNode);
	}
	
	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.subtract(getCenter());
		desiredVelocity = desiredVelocity.normalize().scale(maxVel);
		return desiredVelocity.subtract(velocity);
	}

	@Override
	public void update(float dt) {
		fireRate += dt;
		
		Vector2D pathFollowing;
		
		if(following)
			pathFollowing= pathFollowing();
		else
			pathFollowing= new Vector2D();
		
		pathFollowing=pathFollowing.scale(1/Constants.UFO_MASS);
		
		velocity =velocity.add(pathFollowing);
		velocity= velocity.limit(maxVel);
		position = position.add(velocity);
		
		if(position.getX() > Constants.WIDTH || position.getY() > Constants.HEIGHT
				|| position.getX() < -width || position.getY() < -height)
			Destroy();
		
//		SHOOTS
		if(fireRate > Constants.UFO_FIRE_RATE) {
			
			Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());
			
			toPlayer = toPlayer.normalize();
			
			double currentAngle = toPlayer.getAngle();
			
			currentAngle += Math.random()*Constants.UFO_ANGLE_RANGE - Constants.UFO_ANGLE_RANGE / 2;
			
			if(toPlayer.getX() < 0)
				currentAngle = -currentAngle + Math.PI;
			
			toPlayer = toPlayer.setDirection(currentAngle);
			
			Laser laser = new Laser(
					getCenter().add(toPlayer.scale(width)),
					toPlayer,
					Constants.LASER_VEL,
					currentAngle + Math.PI/2,
					Assets.redLaser,
					gameState
					);
			
			gameState.getMovingObjects().add(0, laser); 
			
			fireRate = 0;
			
			shoot.play();
			
		}
		
		if(shoot.getFramePosition() > 8500) {
			shoot.stop();
		}
		
		angle += 0.05;
		
//		collidesWith();
	}
	
	@Override
	public void Destroy() {
		gameState.addScore(Constants.UFO_SCORE, position);
		gameState.playExplosion(position);
		super.Destroy();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, width/2, height/2);
		g2d.drawImage(texture, at, null);
	}

}//FINAL CLASS
