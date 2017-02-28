package com.game.collision;

import com.game.factory.Soccer;
import com.game.interfaces.ICollision;
import com.game.interfaces.IGameController;

public class SoccerBasicSpecialItemCollision implements ICollision {

	public void verifyCollision() {
		/*For now, only the first player can get the item.*/
		this.playerX = this.gameController.getPlayersX()[0];
		this.playerY = this.gameController.getPlayersY()[0];
		
		if(this.gameController.getSpecialItemX() < playerX + 20	&& this.gameController.getSpecialItemX() > playerX - 20){
				if(playerY + 40 > this.gameController.getSpecialItemY() && playerY - 30 < this.gameController.getSpecialItemY()){
					this.gameController.setSpecialItemCollided(true);
					//this.gameController.setSpecialItemPlayerCollidedID(i);
				}
		}
	}
	
	public SoccerBasicSpecialItemCollision(){
		this.gameController = Soccer.core.getGameController();
	}
	
	private IGameController gameController;
	
	private float playerX, playerY;
}
