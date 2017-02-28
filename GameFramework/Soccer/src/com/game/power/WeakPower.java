package com.game.power;

import com.game.factory.Soccer;

public class WeakPower implements IPowerStrategy {

	@Override
	public void executeSpecialAbility(int id) {
		this.playersX = Soccer.core.getGameController().getPlayersX();
		this.playersY = Soccer.core.getGameController().getPlayersY();
		
		for(int i = 1; i < playersX.length; ++i){
			if(i != id)
				this.playersX[i] += Soccer.core.getGameController().getDeltaTime()*100;
				this.playersY[i] += Soccer.core.getGameController().getDeltaTime()*100;
		}
	}
	
	@Override
	public float []getPlayersX() {
		return this.playersX;
	}

	@Override
	public float []getPlayersY() {
		return this.playersY;
	}
	
	private float []playersX, playersY;	
}
