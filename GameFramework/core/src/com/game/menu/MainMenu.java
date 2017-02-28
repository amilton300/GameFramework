package com.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.abstractClasses.UiGraphicsComponent;
import com.game.button.ButtonsComposite;
import com.game.button.GameButton;
import com.game.interfaces.IMenuComposite;
import com.game.manager.GamesManager;
import com.game.utils.Background;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainMenu implements Screen{
	    
    @Override
    public void show(){ 
       this.stage = new Stage();
       this.batch = new SpriteBatch();
       this.font = new BitmapFont();
       IMenuComposite composite = new ButtonsComposite();
       
            try{
                this.games = GamesManager.getInstance().loadGames();
                
                this.currentUiComponent = new Background();
                if(this.games != null)
                	for(int i = 0; i < this.games.length; ++i){
                		currentUiComponent = new GameButton(this.currentUiComponent, i, this.games[i]);
                		composite.addChild((IMenuComposite) currentUiComponent);
                	}
            }catch (MalformedURLException ex){
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }catch (ClassNotFoundException ex){
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }catch (InstantiationException ex){
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IllegalAccessException ex){
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            composite.moveIn();
            Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float f){
    	Gdx.gl.glClearColor(0, 0, 0, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
    	currentUiComponent.draw();
    	stage.act();
    	
    	this.batch.begin();
    	this.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    	this.font.draw(batch, "Arrows - Movement\nSpaceBar - Special Power ", 50, 70);
    	this.batch.end();
    }

    @Override
    public void resize(int i, int i1){
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void hide(){    
    }

    @Override
    public void dispose(){
    	this.stage.dispose();
    }

    public Stage getStage(){
    	return this.stage;
    }
    
    public static MainMenu getInstance(){
    	if(instance == null)
    		instance = new MainMenu();
    	return instance;
    } 
    
    private MainMenu(){
    }
    
    private static MainMenu instance;
    
    private UiGraphicsComponent currentUiComponent;
    
    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batch;
    
    private String []games;
}
