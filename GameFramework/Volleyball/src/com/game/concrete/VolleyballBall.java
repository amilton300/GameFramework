package com.game.concrete;

import java.util.Random;

import com.game.factory.Volleyball;
import com.game.interfaces.IBall;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;

public class VolleyballBall implements IBall, IPrototype{
	
	public void init() {
		this.random = new Random();
		this.gameController = Volleyball.core.getGameController();
		this.graphicController = Volleyball.core.getGraphicController();
		
		int i = random.nextInt(2);
		
		if(i == 0)
			this.x = 200;
		else
			this.x = 400;
		
		this.y = 300;
		
		this.gameController.setBallX(this.x);
		this.gameController.setBallY(this.y);
		this.bounceLeftRigth = 0;
		this.lastCollision = 0;
	}

	public void update() {
		int auxRandom = random.nextInt(30);
		
		if(this.gameController.getCollisionLeft()){
			this.bounceLeftRigth = 100;
			this.lastCollision = -1;
		}
		else if(this.gameController.getCollisionRight()){
			this.bounceLeftRigth = 100;
			this.lastCollision = 1;
		}
		
		if(this.bounceLeftRigth > 0 && this.lastCollision == 1){
			this.bounceLeftRigth -= this.gameController.getDeltaTime()*100;
			this.x -= this.gameController.getDeltaTime()*(150 + auxRandom);
			this.y += this.gameController.getDeltaTime()*(350 + auxRandom);
		}
		else if(this.bounceLeftRigth > 0 && this.lastCollision == -1){
			this.bounceLeftRigth -= this.gameController.getDeltaTime()*100;
			this.x += this.gameController.getDeltaTime()*150;
			this.y += this.gameController.getDeltaTime()*350;
			}
		else if(this.y > 30){
			if(this.lastCollision == 1)
				this.x -= this.gameController.getDeltaTime()*(150 + auxRandom);
			else if(this.lastCollision == -1)
				this.x += this.gameController.getDeltaTime()*(150 + auxRandom);		
		}
		
		this.y -= this.gameController.getDeltaTime()*200;
		
		this.graphicController.drawCircle(x, y, 10);
		
		this.gameController.setBallX(this.x);
		this.gameController.setBallY(this.y);
		
	}
	
	public IPrototype pClone() {
		return new VolleyballBall();
	}
	
	public VolleyballBall(){
	}
	
	private IGameController gameController;
	private IGraphicController graphicController;
	
	private Random random;
	private float x, y, bounceLeftRigth;
	private int lastCollision;	
}
