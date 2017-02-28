package com.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonSkins{
	
	public Skin createBasicSkin(){
    	BitmapFont font = new BitmapFont();
    	this.skin = new Skin();
    	this.skin.add("default", font);
 
    
    	Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
    	pixmap.setColor(Color.WHITE);
    	pixmap.fill();
    	this.skin.add("background",new Texture(pixmap));
 

    	TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    	textButtonStyle.up = this.skin.newDrawable("background", Color.GRAY);
    	textButtonStyle.down = this.skin.newDrawable("background", Color.DARK_GRAY);
    	textButtonStyle.checked = this.skin.newDrawable("background", Color.GRAY);
    	textButtonStyle.over = this.skin.newDrawable("background", Color.LIGHT_GRAY);
    	textButtonStyle.font = this.skin.getFont("default");
    	this.skin.add("default", textButtonStyle);
    	
    	return this.skin;
    }
	
	public static ButtonSkins getInstance(){
		if(instance == null)
			instance = new ButtonSkins();
		return instance;
	}
	
	private ButtonSkins(){
	}
	
	private static ButtonSkins instance;
	
	private Skin skin;
}
