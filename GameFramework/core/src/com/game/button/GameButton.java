package com.game.button;

import java.net.MalformedURLException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.abstractClasses.UiDecorator;
import com.game.abstractClasses.UiGraphicsComponent;
import com.game.interfaces.IMenuComposite;
import com.game.manager.AIManager;
import com.game.menu.MainMenu;
import com.game.utils.ButtonSkins;

public class GameButton extends UiDecorator implements IMenuComposite{

	@Override
	public void draw(){
		ui.draw();
    	this.stage.draw();
	}
	
	@Override
	public void moveIn(){
		MoveToAction moveAction = new MoveToAction();
		moveAction.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight() - this.id*50 - 100);
        moveAction.setDuration(0.5f);
		this.newGameButton.addAction(moveAction);
		
		for(AIGameButton aiButton : aiButtons){
			aiButton.moveIn();
		}
	}
	
	@Override
	public boolean addChild(IMenuComposite arg0) {
		return false;
	}

	@Override
	public boolean removeChild(IMenuComposite arg0) {
		return false;
	}
	
	public GameButton(UiGraphicsComponent uiComponent, int id, String name){
		this.ui = uiComponent;
		this.id = id;
		this.name = name;
		this.stage = MainMenu.getInstance().getStage();
		
        /*Every GameButton will have AIGameButtons. Except when there isn't Artificial Intelligence
         * plugins in the file directory.*/
        String[] aI;
		try{
			aI = AIManager.getInstance().loadAI(name);
			 if(aI != null){
		       	aiButtons = new ArrayList<AIGameButton>();
		       	for(int i = 0; i < aI.length; ++i)
		       		aiButtons.add(new AIGameButton(id, aI[i], i, name));
			 }
		}catch (MalformedURLException e1){
			e1.printStackTrace();
		}catch (ClassNotFoundException e1){
			e1.printStackTrace();
		}catch (InstantiationException e1){
			e1.printStackTrace();
		}catch (IllegalAccessException e1){
			e1.printStackTrace();
		}
		
    	this.newGameButton = new TextButton(this.name, ButtonSkins.getInstance().createBasicSkin());
		this.newGameButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent arg0, Actor actor){
				for(AIGameButton ai : aiButtons)
						ai.show();		
			}
		});
		newGameButton.setPosition(0,0);
		this.stage.addActor(newGameButton);	
	}
	 
	 private TextButton newGameButton;
	 private Stage stage;
	 
	 private ArrayList<AIGameButton> aiButtons;
	 private String name;
	 private int id; 
}
