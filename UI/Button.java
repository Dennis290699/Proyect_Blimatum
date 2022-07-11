package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entradas.MouseInput;
import Graphics.Assets;
import Graphics.Text;
import Math.Vector2D;

public class Button {

	private BufferedImage mouseOutImg; //CUANDO EL MAUSE NO ESTE SOBRE EL BOTON
	private BufferedImage mouseInImg; //CUANDO EL MAUSE ESTE SOBRE EL BOTON
	private boolean mouseIn; //PARA SABER SI EL MAUSE ESTE DENTRO DEL BOTON
	private Rectangle boundingBox; //COLISION ENTRE LA CAJA Y EL MAUSE
	private Action action;
	private String text;
	
	public Button(BufferedImage mauseOutImg, BufferedImage mauseInImg, int x, int y, String text, Action action) {
		this.mouseInImg = mauseInImg;
		this.mouseOutImg = mauseOutImg;
		this.text = text;
		boundingBox = new Rectangle(x, y, mauseInImg.getWidth(), mauseInImg.getHeight());
		this.action = action;
	}
	
	public void update() {
		if(boundingBox.contains(MouseInput.X, MouseInput.Y)) {
			mouseIn = true;
		} else {
			mouseIn = false;
		}
		
		if(mouseIn && MouseInput.MLB) {
			action.doAction();
		}		
	}
	
	public void draw(Graphics g) {
		if(mouseIn) {
			g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
		}else {
			g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
		}
		
		Text.drawText(
				g,
				text,
				new Vector2D(
						boundingBox.getX() + boundingBox.getWidth() / 2,
						boundingBox.getY() + boundingBox.getHeight()),
				true,
				Color.BLACK,
				Assets.fontMed);
	}
	
}//FINAL CLASS
