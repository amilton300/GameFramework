package com.game.controller;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.game.abstractClasses.SubjectTemplateMethod;
import com.game.interfaces.IGraphicController;

public class GraphicController implements IGraphicController{

	@Override
	public void drawCircle(float x, float y, float radius) {
		this.batch.begin();
		this.shape.begin();
		this.shape.set(ShapeType.Filled);
		this.shape.setColor(Color.WHITE);
		this.shape.circle(x, y, radius);
		this.shape.end();	
		this.batch.end();
	}

	@Override
	public void drawRect(float x, float y, float width, float height) {
		this.batch.begin();
		this.shape.begin(ShapeType.Filled);
		this.shape.setColor(Color.WHITE);
		this.shape.rect(x, y, width, height);
		this.shape.end();
		this.batch.end();
	}
	
	@Override
	public void drawPlayer(float x, float y, float width, float height, boolean specialEnabled) {  
		this.stateWalkTime += Gdx.graphics.getDeltaTime();
		
		if(!specialEnabled)		
			this.currentWalkFrame = walkAnimation.getKeyFrame(this.stateWalkTime, true);		
		else { 
			this.currentWalkFrame = powerWalkAnimation.getKeyFrame(this.stateWalkTime, true);
		}
        this.batch.begin();
        this.batch.draw(this.currentWalkFrame, x, y, width, height);
        this.batch.end();
	}
	
	@Override
	public void drawField(float x, float y, float width, float height) {
		this.batch.begin();
		this.batch.draw(this.fieldTexture, x, y, width, height);
		this.batch.end();
	}
	
	@Override
	public void drawBackgroundEffects(float x, float y) {
		this.stateEffectsTime += Gdx.graphics.getDeltaTime();
        this.currentEffectsFrame = effectsAnimation.getKeyFrame(this.stateEffectsTime, true);
        this.batch.begin();
        this.batch.draw(this.currentEffectsFrame, x, y);
        this.batch.end();
	}
	
	@Override
	public void drawSpecialItem(float x, float y, float width, float height) {
		this.batch.begin();
		this.batch.draw(this.specialItemTexture, x, y, width, height);
		this.batch.end();
	}
	
	@Override
	public void drawPlayerEnergyBar(float x, float y, float width, float height, String color) {
		this.batch.begin();
		this.shape.begin(ShapeType.Filled);
		
		if(color.equalsIgnoreCase("RED"))
			this.shape.setColor(Color.RED);
		else if(color.equalsIgnoreCase("GREEN"))
			this.shape.setColor(Color.GREEN);
		else
			this.shape.setColor(Color.WHITE);
		this.shape.rect(x, y, width, height);
		this.shape.end();
		this.batch.end();
	}
	
	public static GraphicController getInstance() {
		if(instance == null)
			instance = new GraphicController();
		return instance;
	}
	
	private GraphicController() {
		this.shape = new ShapeRenderer();
		this.batch = new SpriteBatch();
		this.shape.setAutoShapeType(true);
		
		/*PLAYER ANIMATIONS*/
		this.walkSheet = new Texture(Gdx.files.internal("crab.png"));
        TextureRegion[][] tmpWalk = TextureRegion.split(this.walkSheet, this.walkSheet.getWidth()/PLAYERFRAME_COLS, this.walkSheet.getHeight()/PLAYERFRAME_ROWS);
        this.walkFrames = new TextureRegion[PLAYERFRAME_COLS * PLAYERFRAME_ROWS];
        int index = 0;
        for (int i = 0; i < PLAYERFRAME_ROWS; ++i){
            for (int j = 0; j < PLAYERFRAME_COLS; ++j) {
            	this. walkFrames[index++] = tmpWalk[i][j];
            }
        }
        index = 0;
        this.walkAnimation = new Animation(0.025f, this.walkFrames);
        this.stateWalkTime = 0f;
        
        this.powerWalkSheet = new Texture(Gdx.files.internal("crabpowerful.png"));
        TextureRegion[][] powertmpWalk = TextureRegion.split(this.powerWalkSheet, this.powerWalkSheet.getWidth()/PLAYERFRAME_COLS, this.powerWalkSheet.getHeight()/PLAYERFRAME_ROWS);
        this.powerWalkFrames = new TextureRegion[PLAYERFRAME_COLS * PLAYERFRAME_ROWS];
        
        for (int i = 0; i < PLAYERFRAME_ROWS; ++i){
            for (int j = 0; j < PLAYERFRAME_COLS; ++j) {
            	this. powerWalkFrames[index++] = powertmpWalk[i][j];
            }
        }
        index = 0;
        this.powerWalkAnimation = new Animation(0.025f, this.powerWalkFrames);
        
        /*EFFECTS ANIMATIONS*/
        this.effectsTexture = new Texture(Gdx.files.internal("flamesprite.png"));
        TextureRegion[][] tmpEffects = TextureRegion.split(this.effectsTexture, this.effectsTexture.getWidth()/EFFECTSFRAME_COLS, this.effectsTexture.getHeight());
        this.effectsFrames = new TextureRegion[EFFECTSFRAME_COLS];
   
        	for (int i = 0; i < EFFECTSFRAME_COLS; ++i){
        		this.effectsFrames[index++] = tmpEffects[0][i];
    
        }
        this.effectsAnimation = new Animation(0.025f, this.effectsFrames);
        this.stateEffectsTime = 0f;
        
        /*FIELD*/
        this.fieldTexture = new Texture(Gdx.files.internal("sandtexture.jpg"));
        
        /*BALL
        this.ballTexture = new TextureRegion(new Texture(Gdx.files.internal("lightningball.png")));*/  
        
        /*Special Item*/
        this.specialItemTexture = new Texture(Gdx.files.internal("star.png"));
	}
	
	private static final int PLAYERFRAME_COLS = 6, PLAYERFRAME_ROWS = 4, EFFECTSFRAME_COLS = 6;
	
	private static GraphicController instance;

    private Animation walkAnimation, effectsAnimation, powerWalkAnimation;
    private Texture walkSheet, fieldTexture, effectsTexture, specialItemTexture,
    				powerWalkSheet; 
    private TextureRegion[] walkFrames, effectsFrames, powerWalkFrames;
    private TextureRegion currentWalkFrame, currentEffectsFrame;
    private ShapeRenderer shape;
	private SpriteBatch batch;

    private float stateWalkTime, stateEffectsTime;  
}
