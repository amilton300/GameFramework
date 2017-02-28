package com.game.power;

import java.util.Random;

import com.game.factory.Soccer;

public class StrongPower implements IPowerStrategy {

	@Override
	public void executeSpecialAbility(int id) {
		this.playersX = Soccer.core.getGameController().getPlayersX();
		this.playersY = Soccer.core.getGameController().getPlayersY();

		for (int i = 1; i < playersX.length; ++i) {
			if (i != id) {
				if (random.nextInt(5) < 3) {
					this.playersX[i] += Soccer.core.getGameController()
							.getDeltaTime() * 300;
					this.playersY[i] += Soccer.core.getGameController()
							.getDeltaTime() * 300;
				} else {
					this.playersX[i] -= Soccer.core.getGameController()
							.getDeltaTime() * 200;
					this.playersY[i] += Soccer.core.getGameController()
							.getDeltaTime() * 150;
				}
			}
		}
	}

	@Override
	public float[] getPlayersX() {
		return this.playersX;
	}

	@Override
	public float[] getPlayersY() {
		return this.playersY;
	}
	
	public StrongPower(){
		this.random = new Random();		
	}

	Random random;
	
	private float[] playersX, playersY;
}
