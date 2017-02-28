package com.game.concrete;

import com.game.factory.Volleyball;
import com.game.interfaces.IGameController;
import com.game.interfaces.IPrototype;
import com.game.interfaces.IRules;

public class VolleyballRules implements IRules, IPrototype{
	
	public void init() {
		this.gameController = Volleyball.core.getGameController();
	}
	
	public void verifyCollision() {
		this.playersX = this.gameController.getPlayersX();
		this.playersY = this.gameController.getPlayersY();
		
		for(int i = 0; i < playersX.length; ++i){
			int aux = this.lastCollider;
			if(i != this.lastCollider){
				if(this.gameController.getBallX() < playersX[i] + 20 && this.gameController.getBallX() > playersX[i] - 20){
					if(playersY[i] + 30 > this.gameController.getBallY()){
						this.lastCollider = i;
						this.timeCollided = 100;
						if(this.gameController.getBallX() > playersX[i]){
							this.gameController.setCollisionLeft(true);
							this.gameController.setCollisionRight(false);
							this.temp = 1;
						}
						else{
							this.gameController.setCollisionRight(true);
							this.gameController.setCollisionLeft(false);
							this.temp = 1;
						}
					}
				}
			}
			if(aux == this.lastCollider){
				if(this.timeCollided > 0)
					this.timeCollided -= this.gameController.getDeltaTime()*200;
				else
					this.lastCollider = -1;
			}
		}
		
		if(this.gameController.getBallX() < 5 || (this.gameController.getBallX() >= 310 && this.gameController.getBallX() <= 325 && this.gameController.getBallY() < 130)){
			this.gameController.setCollisionLeft(true);
			this.gameController.setCollisionRight(false);
			this.temp = 1;
		}
		else if(this.gameController.getBallX() > 580 || (this.gameController.getBallX() <= 315 && this.gameController.getBallX() >= 305 && this.gameController.getBallY() < 130)){
			this.gameController.setCollisionRight(true);
			this.gameController.setCollisionLeft(false);
			this.temp = 1;
		}
		
		if(this.temp == 0){
			this.gameController.setCollisionRight(false);
			this.gameController.setCollisionLeft(false);
		}
		
		this.temp = 0;
	}

	public void verifyPlayerPoint() {
		if((this.gameController.getBallX() >= 310 && this.gameController.getBallY() <= 30))// || this.gameController.getBallX() > 600)
			this.gameController.setPlayerScore(this.gameController.getPlayerScore() + 1);
	}
	
	public void verifyEnemyPoint() {
		if((this.gameController.getBallX() <= 310 && this.gameController.getBallY() <= 30))// || this.gameController.getBallX() < 0)
			this.gameController.setEnemyScore(this.gameController.getEnemyScore() + 1);
	}

	public void verifyGameOver() {
		if(this.gameController.getPlayerScore() > 5 || this.gameController.getEnemyScore() > 5)
			this.gameController.setGameOver(true);
	}
	
	public IPrototype pClone() {
		return new VolleyballRules();
	}
	
	public VolleyballRules(){	
	}
	
	private IGameController gameController;
	
	private float[] playersX, playersY;
	private int lastCollider = -1, timeCollided = 0, temp = 0;	
}
