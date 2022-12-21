package com.keshav.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.keshav.gaming.sprite.Ball;
import com.keshav.gaming.sprite.Egg;
import com.keshav.gaming.sprite.Sprite;

public class Board extends JPanel implements Constants{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Ball ball;
	Egg eggs[];
	Sound sound;
	String gameMsg="Start";
	String pointDisplay = "0/16";
	BufferedImage bi;
	BufferedImage subImage;
	int cameraX;
	int cameraY;
	int force;
	int count;
	public Board() throws Exception  {
		cameraX = 0;
		cameraY = 100;
		count=0;
		force = 0;
		setSize(BOARD_WIDTH,BOARD_HEIGHT);
		bi=ImageIO.read(Board.class.getResource("bounce-bg.png"));
		subImage=bi.getSubimage(cameraX, cameraY,  BOARD_WIDTH, BOARD_HEIGHT);
		ball = new Ball();
		eggs = new Egg[MAX_EGG];
		sound = new Sound();
		loadEggs();
		setFocusable(true);
		bindEvents();
		gameLoop();	
		}
	void loadEggs() throws Exception {
		// TODO Auto-generated method stub
		int x = 250;
		int y=200;
		final int GAP = 200;
		for (int i = 0 ; i<eggs.length; i++) {
			if(i==8) {
				x=350;
				y=220;
			}
			eggs[i] = new Egg(x-cameraX, y-cameraY);
			x = x + GAP;
		}
	}
	
	void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(cameraX<1920-BOARD_WIDTH) {
					cameraX = cameraX+10;
					for(Egg e1 : eggs) {
						e1.move(-10);
					}
					subImage = bi.getSubimage(cameraX, cameraY, BOARD_WIDTH, BOARD_HEIGHT);
					}
					else if(ball.getPoints()==MAX_EGG){
						sound.setChecksound("win");
						sound.play();
						gameMsg="Win";
						timer.stop();
					}
				}
				else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
					if(cameraX>0) {
					cameraX = cameraX-10;
					for(Egg e1 : eggs) {
						e1.move(10);
					}
					subImage = bi.getSubimage(cameraX, cameraY, BOARD_WIDTH, BOARD_HEIGHT);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					if(cameraY==100) {
					jump();
					}
				}
			}
		});
	}

	Timer timer;
	
	void gameLoop() {
     timer = new Timer(100,(ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				checkCollision();
				screenfall();
				isGameWin();
				count++;
				if(count==10) {
					gameMsg="";
				}
			}
		});
		timer.start();
	}
	
	void isGameWin() {
		if(ball.getPoints()==MAX_EGG && cameraX>=1920-BOARD_WIDTH) {
			gameMsg = "Game Win";
			sound.setChecksound("win");
			sound.play();
			timer.stop();
		}
	}
	void jump() {
		// TODO Auto-generated method stub
		force=-15;
		cameraY = cameraY+force;
		subImage = bi.getSubimage(cameraX, cameraY, BOARD_WIDTH, BOARD_HEIGHT);
		for (int i = 0 ; i<eggs.length; i++) {
			eggs[i].setY(force);
		}
	}
	
	void screenfall() {
		if(cameraY<100) {
			force=force+GRAVITY;
			cameraY=cameraY+force;
			subImage = bi.getSubimage(cameraX, cameraY, BOARD_WIDTH, BOARD_HEIGHT);
			if(cameraY>100) {
				cameraY=100;
				subImage = bi.getSubimage(cameraX, cameraY, BOARD_WIDTH, BOARD_HEIGHT);
			}
			for (int i = 0 ; i<eggs.length; i++) {
				eggs[i].setY(force);
			}
		}
	}
	
	boolean isCollide(Sprite one, Sprite two){
		int xDistance = Math.abs(one.getX() - two.getX());
		int yDistance = Math.abs(one.getY() - two.getY());
		int w = Math.min(one.getW(), two.getW());
		int h = Math.min(one.getH(), two.getH());
		return xDistance<=(w) && yDistance<=h;	
	}
	
	void checkCollision() {
		// Check ball is Collide with egg or not
		gameMsg="";
		for(int e=0;e<eggs.length;e++) {
			if(!eggs[e].isCollect() && isCollide(ball,eggs[e])) {
				eggs[e].setCollect(true);
				ball.setPoints();
				gameMsg="+1";
				int temp=ball.getPoints();
				pointDisplay=String.valueOf(temp)+"/16";
			}
		}
		if(gameMsg.length()>0) {
		sound.setChecksound("eggcollide");
		sound.play();
		}
	}
	
	void printEggs(Graphics pen) throws Exception {
		for(Egg e : eggs) {
			if(!e.isCollect()) {
			e.draw(pen);
			}
		}
	}
	
	void printPoints(Graphics pen) {
		pen.setColor(Color.black);
		pen.setFont(new Font("times", Font.BOLD, BOARD_WIDTH/20));
		pen.drawString(pointDisplay, 20, 20);
	}
	
	void printMsg(Graphics pen) {
		// TODO Auto-generated method stub
		pen.setColor(Color.RED);
		pen.setFont(new Font("times", Font.BOLD, BOARD_WIDTH/25));
		pen.drawString(gameMsg, BOARD_WIDTH/3, BOARD_HEIGHT/2);
	}
	
	@Override
	public void paintComponent(Graphics pen) {
		pen.drawImage(subImage,0,0,BOARD_WIDTH, BOARD_HEIGHT, null);
		ball.draw(pen);
		printPoints(pen);
		try {
			printEggs(pen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(gameMsg.length()>0){
			printMsg(pen);
			}
	}
}
