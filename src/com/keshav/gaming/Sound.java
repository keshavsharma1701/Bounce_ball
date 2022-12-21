package com.keshav.gaming;

import java.io.File;

import jaco.mp3.player.MP3Player;

public class Sound {
	MP3Player mp1,mp2;
	String checksound;
	public void setChecksound(String checksound) {
		this.checksound = checksound;
	}
	public Sound() {
		mp1 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\garden.mp3"));
		mp1.play();
	}
	public void play() {
		if(checksound=="eggcollide") {
			mp2 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\beep.mp3"));
		}
		else if(checksound=="win") {
			mp2 = new MP3Player(new File("src\\com\\keshav\\gaming\\sprite\\arcade-win.mp3"));
		}
		mp2.play();
	}
}
