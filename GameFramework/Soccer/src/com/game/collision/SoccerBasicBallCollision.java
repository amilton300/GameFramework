package com.game.collision;

import com.game.factory.Soccer;
import com.game.interfaces.ICollision;
import com.game.interfaces.IGameController;

public class SoccerBasicBallCollision implements ICollision{
	
	public void verifyCollision(){
		this.playersX = this.gameController.getPlayersX();
		this.playersY = this.gameController.getPlayersY();
		
		for(int i = 0; i < playersX.length; ++i){
			int aux = this.lastCollider;
			if(i != this.lastCollider){
				if(this.gameController.getBallX() < playersX[i] + 20 && this.gameController.getBallX() > playersX[i] - 20){
					if(playersY[i] + 40 > this.gameController.getBallY() && playersY[i] - 30 < this.gameController.getBallY()){
						this.lastCollider = i;
						this.timeCollided = 100;
						if(this.gameController.getBallX() > playersX[i]){
							this.gameController.setCollisionLeft(true);
							this.gameController.setCollisionRight(false);
							temp = 1;
						}
						else{
							this.gameController.setCollisionRight(true);
							this.gameController.setCollisionLeft(false);
							temp = 1;
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
		
		if(this.gameController.getBallX() > 580){
			this.gameController.setCollisionLeft(true);
			this.gameController.setCollisionRight(false);
			temp = 1;	
		}
		else if(this.gameController.getBallX() < 10){
			this.gameController.setCollisionRight(true);
			this.gameController.setCollisionLeft(false);
			temp = 1;
		}
		if(temp == 0){
			this.gameController.setCollisionRight(false);
			this.gameController.setCollisionLeft(false);
		}
		
		temp = 0;	
	}
	
	public SoccerBasicBallCollision(){
		this.gameController = Soccer.core.getGameController();
	}
	
	private IGameController gameController;
	
	private float[] playersX, playersY;
	private int lastCollider = -1, timeCollided = 0, temp = 0;
}
