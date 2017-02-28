package com.game.concrete;

import com.game.factory.Soccer;
import com.game.interfaces.IBall;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;

public class SoccerBall implements IBall, IPrototype{
	
	public void init() {
		this.gameController = Soccer.core.getGameController();
		this.graphicController = Soccer.core.getGraphicController();
		/*BIG EYE*/
		this.x = 300;
		this.y = 150;
		this.gameController.setBallX(this.x);
		this.gameController.setBallY(this.y);
		this.bounceGround = 0;
		this.bounceLeftRigth = 0;
		this.lastCollision = 0;
	}

	public void update() {	
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
			this.x -= this.gameController.getDeltaTime()*200;
			this.y += this.gameController.getDeltaTime()*300;
		}
		else if(this.bounceLeftRigth > 0 && this.lastCollision == -1){
			this.bounceLeftRigth -= this.gameController.getDeltaTime()*100;
			this.x += this.gameController.getDeltaTime()*200;
			this.y += this.gameController.getDeltaTime()*300;
		}
		
		if(this.y < 40){
			this.bounceGround = 50;
		}
		
		if(this.bounceGround > 0){
			this.bounceGround -= this.gameController.getDeltaTime()*100;
			this.y += this.gameController.getDeltaTime()*200;
		}
		else
			this.y -= this.gameController.getDeltaTime()*200;
		
		if(this.x < 5 || this.x > 590)
			init();
		
		this.graphicController.drawCircle(this.x, this.y, 10);
		
		this.gameController.setBallX(this.x);
		this.gameController.setBallY(this.y);
	}
	
	public IPrototype pClone() {
		return new SoccerBall();
	}
	
	public SoccerBall(){
	}
	
	private IGameController gameController;
	private IGraphicController graphicController;
	
	private float x, y, bounceLeftRigth, bounceGround;
	private int lastCollision;
}
