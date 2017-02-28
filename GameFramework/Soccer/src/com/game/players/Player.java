package com.game.players;

import com.game.abstractClasses.SubjectTemplateMethod;

public class Player extends SubjectTemplateMethod {

	@Override
	public void doUpdateEnergy() {
		if(this.energy < 100)
			this.energy += 10;
	}

	@Override
	public int getEnergy() {
		return this.energy;
	}
	
	@Override
	public void doReset() {
		this.energy = 0;
	}
	
	private int energy = 0;
}
