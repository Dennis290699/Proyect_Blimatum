package Entradas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Comands implements KeyListener {
 
	private boolean [] keys = new boolean [256];
	public static boolean UP, LEFT, RIGHT, SHOOT;
	
	public Comands() {
		UP= false;
		LEFT=false;
		RIGHT=false;
		SHOOT=false;
	}
	
	public void update() {
		UP= keys[KeyEvent.VK_UP];
		LEFT=keys[KeyEvent.VK_LEFT];
		RIGHT=keys[KeyEvent.VK_RIGHT];
		SHOOT=keys[KeyEvent.VK_SPACE];
	}
	
	/*SIEMPRE LOS TRES YA QUE SON METODOS ABSTRACTOS
	 *keyPressed CADA VEZ QUE SE OPRIMA UNA TECLA true
	 *keyReleased CADA VEZ QUE SE SUELTE UNA TECLA false IMPORTANTE SI EN ESTE METODO ESTA true EL OBJETO SE MOVERA AUTOMATICAMENTE NO PARARA
	 *Y LA INFOMARCION DE QUE TECLAS FUERON OPRIMIDAS EN UN KeyCode */

	@Override
	public void keyPressed(KeyEvent e) {
//		LA SIGUIENTE LINEA VERIFICA LA ACCION DE CADA TECLA
//		System.out.println(e.getKeyCode());
		
		keys[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
			
	}
	
}//FINAL CLASS
