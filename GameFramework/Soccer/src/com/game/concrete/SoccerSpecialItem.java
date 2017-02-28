package com.game.concrete;

import com.game.factory.Soccer;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;
import com.game.interfaces.ISpecialItem;

import java.util.Random;

public class SoccerSpecialItem implements ISpecialItem, IPrototype {

	public void draw() {
		timer += this.gameController.getDeltaTime()*2;
		if(timer > 4){
			this.init();
			this.initialized = true;
			timer = 0;
		}

		if(initialized){
			this.y -= this.gameController.getDeltaTime()*200;
			this.gameController.setSpecialItemX(this.x);
			this.gameController.setSpecialItemY(this.y);
			this.graphicController.drawSpecialItem(this.x, this.y, 25, 25);

			if(this.y < 0 || this.gameController.getSpecialItemCollided()){
				this.init();
				this.initialized = false;
			}
		}	
	}
	
	public IPrototype pClone() {
		return new SoccerSpecialItem();
	}

	public SoccerSpecialItem(){
		this.graphicController = Soccer.core.getGraphicController();
		this.gameController = Soccer.core.getGameController();
		this.random = new Random();
	}

	private void init(){
		if(random.nextInt(2) == 0)
			this.x = 200;
		else
			this.x = 400;

		this.y = 400;
		this.gameController.setSpecialItemX(this.x);
		this.gameController.setSpecialItemY(this.y);
	}

	private IGraphicController graphicController;
	private IGameController gameController;

	private Random random;

	private float x, y, timer = 0;
	private boolean initialized = false;
	
}
