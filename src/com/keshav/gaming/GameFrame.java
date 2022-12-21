package com.keshav.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements Constants{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameFrame() throws Exception{
		Board board = new Board();
		this.setSize(BOARD_WIDTH,BOARD_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bounce_Ball");
		setLocationRelativeTo(null);
		setResizable(false);
		add(board);
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GameFrame obj = new GameFrame();
	}

}
