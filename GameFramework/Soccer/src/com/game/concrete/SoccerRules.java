package com.game.concrete;

import com.game.collision.*;
import com.game.factory.Soccer;
import com.game.interfaces.ICollision;
import com.game.interfaces.IGameController;
import com.game.interfaces.IPrototype;
import com.game.interfaces.IRules;

public class SoccerRules implements IRules, IPrototype{

	public void init() {
		this.gameController = Soccer.core.getGameController();
		this.soccerBallCollision = new SoccerBasicBallCollision();
		this.specialItemCollision = new SoccerBasicSpecialItemCollision();
	}
	
	public void verifyCollision() {
		this.soccerBallCollision.verifyCollision();
		this.specialItemCollision.verifyCollision();
	}

	public void verifyPlayerPoint() {
		if(this.gameController.getBallX() > 570 && this.gameController.getBallY() < 150)
			this.gameController.setPlayerScore(this.gameController.getPlayerScore() + 1);
	}
	
	public void verifyEnemyPoint() {
		if(this.gameController.getBallX() < 20 && this.gameController.getBallY() < 150)
			this.gameController.setEnemyScore(this.gameController.getEnemyScore() + 1);
	}

	public void verifyGameOver() {
		if(this.gameController.getPlayerScore() > 4 || this.gameController.getEnemyScore() > 4)
			this.gameController.setGameOver(true);
	}
	
	public IPrototype pClone() {
		return new SoccerRules();
	}
	
	private IGameController gameController;
	private ICollision soccerBallCollision, specialItemCollision;
}
