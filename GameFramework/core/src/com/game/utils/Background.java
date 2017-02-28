package com.game.utils;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.abstractClasses.UiGraphicsComponent;
import com.game.abstractClasses.SubjectTemplateMethod;

public class Background extends UiGraphicsComponent implements Observer{
	
	@Override
	public void update(Observable o, Object arg) {
		this.playerEnergy = ((SubjectTemplateMethod) o).getEnergy();	
	}
	
	@Override
	public void draw(){
		spriteBatch.begin();
		
		if(this.playerEnergy > 50)
			specialPowerBackgroundSprite.draw(spriteBatch);
		else
			backgroundSprite.draw(spriteBatch);
		spriteBatch.end();
	}
	
	public Background(){
		int i = 1;
		
		if(DateTime.getInstance().getHour() > 17 || DateTime.getInstance().getHour() < 6)
			i = 2;
		
		spriteBatch = new SpriteBatch();
		
		normalBackgroundTexture = new Texture(Gdx.files.internal(i + ".jpg"));
		backgroundSprite = new Sprite(normalBackgroundTexture);
		
		specialPowerBackgroundTexture = new Texture(Gdx.files.internal(i + "dark.jpg"));
		specialPowerBackgroundSprite = new Sprite(specialPowerBackgroundTexture);
	}
	
	private Texture normalBackgroundTexture, specialPowerBackgroundTexture;
	private Sprite backgroundSprite, specialPowerBackgroundSprite;
	private SpriteBatch spriteBatch;
	
	private int playerEnergy = 0;
}
