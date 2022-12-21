package com.keshav.gaming.sprite;

import javax.imageio.ImageIO;

import com.keshav.gaming.Constants;

public class Ball extends Sprite implements Constants {
	int points;
	public Ball() throws Exception {
	w=20;
	h=20;
	x=BOARD_WIDTH/2-w;
	y=BOARD_HEIGHT-h-FLOOR-5;
	points=0;
	bi=ImageIO.read(Ball.class.getResource("redball.png"));
	}
	public int getPoints() {
		return points;
	}
	public void setPoints() {
		this.points++;
	}
}
