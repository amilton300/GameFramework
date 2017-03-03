package com.game.button;

import java.net.MalformedURLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.abstractClasses.AICreator;
import com.game.core.Core;
import com.game.interfaces.IArtificialIntelligence;
import com.game.interfaces.IFlexibleFactory;
import com.game.manager.AIManager;
import com.game.manager.GamesManager;
import com.game.menu.MainMenu;
import com.game.play.PlayGame;
import com.game.utils.ButtonSkins;

public class AIGameButton{
	
	public void moveIn(){
		MoveToAction moveAction = new MoveToAction();
		moveAction.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight() - this.idParent*50 - 100);
        moveAction.setDuration(0.5f);
        VisibleAction visibleAction = new VisibleAction();
        visibleAction.setTarget(newAIButton);
        visibleAction.setVisible(false);
        
        this.newAIButton.addAction(new SequenceAction(moveAction, visibleAction));
	}
	
	public void moveOut(){
		MoveToAction moveAction = new MoveToAction();
		if(idParent % 2 == 0)
			moveAction.setPosition(Gdx.graphics.getWidth()/2 + Gdx.graphics.getWidth()/3 - 85, Gdx.graphics.getHeight() - this.idParent*50 - 100 + this.idButton*50);
		else
			moveAction.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/3 - 85, Gdx.graphics.getHeight() - this.idParent*50 - 100 + this.idButton*50);
		moveAction.setDuration(0.3f);
		VisibleAction visibleAction = new VisibleAction();
		visibleAction.setTarget(newAIButton);
		visibleAction.setVisible(true);
    
		this.newAIButton.addAction(new SequenceAction(visibleAction, moveAction));
	}
	
	public void show(){
		/*variable aux controls the direction of the AIButton movement.*/
		if(this.aux == 1){
			this.moveOut();
			this.aux = 0;
		}
		else{
			this.moveIn();
			this.aux = 1;
		}		
	}
	
	public AIGameButton(int idParent, String nameButton, int idButton, String nParent){
		this.idParent = idParent;
		this.idButton = idButton;
		this.nameParent = nParent;
		this.stage = MainMenu.getInstance().getStage();
		
		newAIButton = new TextButton(nameButton, ButtonSkins.getInstance().createBasicSkin());
    	newAIButton.addListener(new ChangeListener() {
		
			@Override
			public void changed(ChangeEvent arg0, Actor actor){
				try{
					TextButton a = (TextButton)actor;
					
					aICreator = AIManager.getInstance().startAI(a.getText().toString(), a.getText().toString());
					artificialIntelligence = aICreator.createAI();
					flexibleFactory = GamesManager.getInstance().startGame(nameParent);          					
					PlayGame.getInstance().init(flexibleFactory, artificialIntelligence);
					Core.getInstance().alternateScreen(PlayGame.getInstance());
					
					stage.clear();
				}catch (MalformedURLException ex){
					Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
				}catch (ClassNotFoundException ex){
					Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
				}catch (InstantiationException ex){
					Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
				}catch (IllegalAccessException ex){
					Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
		});
    	
    	Random random = new Random();
    	int temp = random.nextInt(120);
    	newAIButton.setPosition(Gdx.graphics.getWidth()/4 - idButton*temp*3, Gdx.graphics.getHeight() - idButton*temp - 100);
    	stage.addActor(newAIButton);
		
	}
		
	private AICreator aICreator;
	private IArtificialIntelligence artificialIntelligence;
	private IFlexibleFactory flexibleFactory;
	
	private TextButton newAIButton;
	private Stage stage;
	
	private String nameParent;
	private int idParent, idButton, aux = 1;
}
