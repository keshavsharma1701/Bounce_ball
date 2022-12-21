package com.keshav.gaming.sprite;

import javax.imageio.ImageIO;

import com.keshav.gaming.Constants;

public class Egg extends Sprite implements Constants{
	boolean isCollect;
	public Egg(int x, int y) throws Exception {
		this.x=x;
		this.y=y;
		w=15;
		h=18;
		isCollect = false;
		bi = ImageIO.read(Egg.class.getResource("egg.png"));
	}
	public void move(int speed) {
		x=x+speed;
	}
	public boolean isCollect() {
		return isCollect;
	}
	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	@Override
	public void setY(int y) {
		this.y=this.y-y;
	}
}
