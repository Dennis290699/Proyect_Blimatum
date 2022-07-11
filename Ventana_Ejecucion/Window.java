package Ventana_Ejecucion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Entradas.Comands;
import Entradas.MouseInput;
import GameObjects.Constants;
import Graphics.Assets;
import States.LoadingState;
import States.State;

public class Window extends JFrame implements Runnable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	VENTANA
	private Canvas canvas;//LIENZO
	private Thread thread;//HILO 
	private boolean running=false;
//	ESPACIO DE DIBUJO
	private BufferStrategy bs;
	private Graphics g;
//	CONTADOR DEMFOTOGRAMAS POR SEGUNDOS(FPS)
	private final int FPS=60;
	private double TARGET_TIME=1_000_000_000/FPS;
	private double delta=0;//ALMACENA EL TIEMPO TRANSCURRIDO
	private int AVERAGE_FPS=FPS;//PERMITE SABER A CUANTO CORRE EL JUEGO
	
//	ENTRADAS POR TECLADO
	private Comands keyBoard;
	
//	MOUSE
	private MouseInput mouseImput;
	
	public Window(){
		setTitle("SPACE ROCKET by DENNIS TRUJILLO");
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//PARA PODER CERRAR LA VENTANA
		setResizable(false);//BLOQUEO DE DIMENSION PANTALLA 
		setLocationRelativeTo(null);//CENTRA LA PANTALLA
		
		
		canvas=new Canvas();
		keyBoard=new Comands();
		mouseImput = new MouseInput();
		
		canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
		canvas.setFocusable(true);//RECIBE LAS ACCIONES POR TECLADO
		
		add(canvas);
		canvas.addKeyListener(keyBoard);
		canvas.addMouseListener(mouseImput);
		canvas.addMouseMotionListener(mouseImput);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Window().start();

	}//FINAL MAIN
	
//	METODOS DE ACTUALIZAR Y DIBUJAR
	private void update(float dt) {
		keyBoard.update();
		State.getCurrentState().update(dt);
	}
	
	private void draw() {
		bs= canvas.getBufferStrategy();
		
		if(bs==null) {
			canvas.createBufferStrategy(3);//VALOR CONVENIENTE QUE DEVUELVE EL CANVAS
			return;
		}
//		PARA PODER DIBUJAR
		g=bs.getDrawGraphics();
		//---------------------------
//		HACER UN FONDO NEGRO BASICO
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
//		FONDO CON IMAGEN 
		g.drawImage(Assets.fondo, 0, 0, Constants.WIDTH, Constants.HEIGHT,null);
		State.getCurrentState().draw(g);
		g.setColor(Color.WHITE);
		g.drawString("FPS: "+ AVERAGE_FPS, 10,20);
		//--------------------------
		g.dispose();
		bs.show();
	}
	
//	METODO PARA CARGAR LOS ASSETS
	private void init() {
		
		Thread loadingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Assets.init();
			}
		});

		State.changeState(new LoadingState(loadingThread));
	}
	
	@Override
	public void run() {
		
		long now=0; //REGISTRO DE TIEMPO TRANSCURRIDO
		long lastTime = System.nanoTime(); //LA HORA ACTUAL DEL SISTEMA EN NANOSEGUNDOS
		//
		int frames=0;
		long time=0;
		
		init();
		
		while(running) {
			now = System.nanoTime();
			delta+=(now - lastTime)/TARGET_TIME;
			time+=(now - lastTime);
			lastTime=now;
			
			if(delta>=1) {
			update((float) (delta * TARGET_TIME * 0.000001f));
			draw();
			delta--;
			frames++;
			}
			if(time>=1_000_000_000) {
				AVERAGE_FPS= frames;
				frames=0;
				time=0;
			}
		}
		stop();
	}
	
//	INICIALIZA EL HILO 
	private void start() {
		thread =new Thread(this);
		thread.start();
		running=true;
	}
	
// 	FINALIZA EL HILO
	private void stop() {
		try {
			thread.join();
			running=false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}// FINAL CLASS
