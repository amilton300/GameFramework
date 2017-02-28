package com.game.play;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.core.Core;
import com.game.utils.Background;
import com.game.utils.ButtonSkins;

public class GameOver implements Screen{

	@Override
	public void show(){
		this.stage.clear();
        Gdx.input.setInputProcessor(this.stage);
        
		this.batch = new SpriteBatch();
	    this.font = new BitmapFont();
	    
	    if(won)
	    	this.crabTexture = new Texture(Gdx.files.internal("happycrab.png"));
	    else
	    	this.crabTexture = new Texture(Gdx.files.internal("sadcrab.png"));
	   
	    this.background = new Background();
	    
	    TextButton newGameButton = new TextButton("Back to menu", ButtonSkins.getInstance().createBasicSkin());
        newGameButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor){
                Core.getInstance().create();
                dispose();
            }
        });
        
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/3);
        this.stage.addActor(newGameButton);		
	}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 0);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    	this.background.draw();
    	batch.begin();
    	
    	this.batch.draw(this.crabTexture, 300, 40);
    	font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    	if(won)
    		font.draw(batch, "YOU WON!", 250, 250);
    	else
    		font.draw(batch, "YOU LOST!", 250, 250);
    	
    	
    	font.draw(batch, String.valueOf(this.playerScore), 200, 200);
    	font.draw(batch, " : ", 300, 200);
    	font.draw(batch, String.valueOf(this.enemyScore), 400, 200);
    	batch.end();
    	
    	this.stage.act();
    	this.stage.draw();	
	}

	@Override
	public void resize(int width, int height){	
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
		this.batch.dispose();
		this.font.dispose();	
	}
	
	public void setScore(int playerScore, int enemyScore){
		this.playerScore = playerScore;
		this.enemyScore = enemyScore;
		this.stage = new Stage();
		
		if(this.playerScore > this.enemyScore)
			this.won = true;
		else
			this.won = false;
	}
	
	public static GameOver getInstance(){
		if(instance == null)
			instance = new GameOver();
		return instance;
	}
	
	private GameOver(){
	}
	
	private static GameOver instance;
	
	private Background background;
	
	private BitmapFont font;
    private SpriteBatch batch;
    private Stage stage;
    private Texture crabTexture;
	 
    private int playerScore, enemyScore;
    private boolean won;
}
