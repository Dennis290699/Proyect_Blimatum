package Graphics;

import java.awt.image.BufferedImage;

import Math.Vector2D;

public class Animation {

	private BufferedImage [] frames; //NUMERO TOTAL DE FOTOGRAMAS
	private int velocity; // VELOCIDAD PARA QUE CAMBIE DE FOTOGRAMA EN FOTOGRAMA
	private int index; //INDICE DEL FOTOGRAMA ACTUAL QUE SE ESTA DIBUJANDO
	private boolean running; //LA ANIMACION ESTA CORRIENDO Y SERA FALSA CUANDO EL PROGRAMA TERMINE
	private Vector2D position; // POSICION EN LA QUE VOY A DIBUJAR
	private long time; // VARIABLES AUXILIARES PARA CONTROLAR TIEMPO ACTUAL
	
	public Animation(BufferedImage[] frames, int velocity, Vector2D position) {
		this.frames=frames;
		this.velocity= velocity;
		this.position= position;
		index=0;
		running= true; //INICIA CORRIENDO
	}
	
	public void update(float dt) {
		time+= dt;
		
		if(time > velocity) {
			time=0;
			index++;
			if(index >= frames.length) {
				running = false;
				index = 0;
			}
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
}//FINAL CLASS
