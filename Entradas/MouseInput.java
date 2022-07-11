package Entradas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public static int X, Y;
	public static boolean MLB;

//	ESTE METODO SE LLAMA CUANDO SE OPRIME UNO DE LOS BOTONES DEL MAUSE
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			MLB = true;
		}
	}

//	ESTE METODO SE LLAMA CUANDO SE SUELTA UNO DE LOS BOTONES DEL MAUSE
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			MLB = false;
		}
	}

//	ESTE METODO SE LLAMA CUANDO SE MANTIENE OPRIMIDO Y SE MUEVE EL MAUSE
	@Override
	public void mouseDragged(MouseEvent e) {
		X= e.getX();
		Y= e.getY();
	}

//	ESTE METODO SE LLAMA CUANDO SE MUEVE EL MAUSE
	@Override
	public void mouseMoved(MouseEvent e) {
		X= e.getX();
		Y= e.getY();	
	}

}//FINAL CLASS
