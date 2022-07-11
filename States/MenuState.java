package States;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GameObjects.Constants;
import Graphics.Assets;
import Graphics.Text;
import Math.Vector2D;
import UI.Action;
import UI.Button;

public class MenuState extends State {

	private ArrayList<Button> buttons;
	
	public MenuState() {
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constants.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constants.HEIGHT / 2 - Assets.greyBtn.getHeight()*2,
				Constants.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameState());
					}
				}
				));
		
		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constants.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constants.HEIGHT / 2 + Assets.greyBtn.getHeight()*2 ,
				Constants.EXIT,
				new Action() {
					@Override
					public void doAction() {
						System.exit(0);
					}
				}
				));
		
		buttons.add(new Button(
				Assets.greyBtn,
				Assets.blueBtn,
				Constants.WIDTH / 2 - Assets.greyBtn.getWidth()/2,
				Constants.HEIGHT / 2,
				Constants.HIGH_SCORES,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new ScoreState());
					}
				}
				));
	}
	
	@Override
	public void update (float dt) {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		Vector2D tittlePos = new Vector2D(
				Constants.WIDTH / 2,
				Constants.HEIGHT / 2 - 150
				);
		
		Text.drawText(g, Constants.TITTLE, tittlePos, true, Color.WHITE, Assets.fontBig);
		for(Button b: buttons) {
			b.draw(g);
		}
	}

}//FINAL CLASS
