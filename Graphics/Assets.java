package Graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class Assets {
//	BACKGROUND
	public static BufferedImage fondo;
//	LOAD BAR
	public static boolean loaded = false;// INDICA QUE TODOS LOS RECURSOS CARGARON
	public static float count = 0; // LLEVA LA CANTIDAD DE RECURSOS QUE CARGARON
	public static float MAX_COUNT = 57; //CANTIDAD TOTAL DE RECURSOS
//	PLAYER
	public static BufferedImage player;
	public static BufferedImage doubleGunPlayer;
//	EFFECTS
	public static BufferedImage speed;
	public static BufferedImage[] shieldEffect = new BufferedImage[3];
//	LASERS
	public static BufferedImage blueLaser, greenLaser, redLaser;
//	METEORS
	public static BufferedImage[] bigs=new BufferedImage[4];
	public static BufferedImage[] meds=new BufferedImage[2];
	public static BufferedImage[] smalls=new BufferedImage[2];
	public static BufferedImage[] tinies=new BufferedImage[2];
//	EXPLOSIONS
	public static BufferedImage[] exp = new BufferedImage[9];
//	UFO
	public static BufferedImage ufo;
//	NUMBERS
	public static BufferedImage[] numbers = new BufferedImage[11];
//	LIFE
	public static BufferedImage life;
//	FONTS
	public static Font fontBig;
	public static Font fontMed;
//	SOUNDS
	public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot, powerUp;
//	UI
	public static BufferedImage blueBtn;
	public static BufferedImage greyBtn;
//	POWER UPS
	public static BufferedImage orb, doubleScore, doubleGun, fastFire, shield, star;
	
	public static void init() {
//		BACKGROUND
		fondo = loadImage("/backgrounds/ej1.jpg");
//		PLAYER
		player= loadImage("/naves/player.png");
		doubleGunPlayer = loadImage("/naves/doubleGunPlayer.png");
//		EFCETOS DE PROPUSLSORES
		speed= loadImage("/effects/fire08.png");
//		COLORES DE LASERS
		blueLaser= loadImage("/lasers/laserBlue01.png");
		greenLaser= loadImage("/lasers/laserGreen11.png");
		redLaser= loadImage("/lasers/laserRed01.png");
//		UFO
		ufo = loadImage("/naves/ufo.png");
//		LIFE
		life = loadImage("/others/life.png");
//		FONTS
		fontBig= loadFont("/fonts/futureFont1.ttf", 42);
		fontMed= loadFont("/fonts/futureFont1.ttf", 20);
//		SHIELD
		for(int i = 0; i < 3; i++)
			shieldEffect[i] = loadImage("/effects/shield" + (i + 1) +".png");
//		METEOROS
		for(int i=0; i<bigs.length;i++)
			bigs[i]= loadImage("/meteors/big"+(i+1)+".png");
		
		for(int i=0; i<meds.length;i++)
			meds[i]= loadImage("/meteors/med"+(i+1)+".png");
		
		for(int i=0; i<smalls.length;i++)
			smalls[i]= loadImage("/meteors/small"+(i+1)+".png");
		
		for(int i=0; i<tinies.length;i++)
			tinies[i]= loadImage("/meteors/tiny"+(i+1)+".png");
//		EXPLOSIONS
		for(int i = 0; i < exp.length; i++)
			exp[i] = loadImage("/explosion/"+i+".png");
//		SCORE
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = loadImage("/numbers/"+i+".png");
//		SOUNDS
		backgroundMusic = loadSound("/sounds/backgroundMusic.wav");
		explosion = loadSound("/sounds/explosion.wav");
		playerLoose = loadSound("/sounds/playerLoose.wav");
		playerShoot = loadSound("/sounds/playerShoot.wav");
		ufoShoot = loadSound("/sounds/ufoShoot.wav");
		powerUp = loadSound("/sounds/powerUp.wav");
		
//		BUTTONS
		greyBtn= loadImage("/UI/grey_button.png");
		blueBtn= loadImage("/UI/blue_button.png");
		
//		POWER UP
		orb = loadImage("/powers/orb.png");
		doubleScore = loadImage("/powers/doubleScore.png");
		doubleGun = loadImage("/powers/doubleGun1.png");
		fastFire = loadImage("/powers/fastFire1.png");
		star = loadImage("/powers/star1.png");
		shield = loadImage("/powers/shield1.png");
		
	// ===========================================================
		
		loaded = true;
	}
	
//	METODO PARA CARGAR LAS IMAGENES
	public static BufferedImage loadImage (String path) {
		count++;
		return Loader.ImageLoader(path);
	}
	
//	METODO PARA CARGAR LAS FUENTES
	public static Font loadFont(String path, int size) {
		count ++;
		return Loader.loadFont(path, size);
	}
	
//	METODO PARA CARGAR LOS SONIDOS
	public static Clip loadSound(String path) {
		count ++;
		return Loader.loadSound(path);
	}
	
}//FINAL CLASS
