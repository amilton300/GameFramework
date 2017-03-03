package com.game.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.game.controller.GameController;
import com.game.controller.GraphicController;
import com.game.interfaces.ICore;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.menu.MainMenu;

public class Core extends Game implements ICore{

	@Override
	public void create(){
		setScreen(menu);
	}
	
	public void alternateScreen(Screen screen){
		setScreen(screen);
	}
	
	public IGameController getGameController(){
		return GameController.getInstance();
	}
	
	public IGraphicController getGraphicController(){
		return GraphicController.getInstance();
	}

	public static Core getInstance(){
		if(instance == null)
			instance = new Core();
		return instance;
	}
	
	private Core(){
		this.menu = MainMenu.getInstance();
	}
	
	private static Core instance;
	private MainMenu menu;
}
