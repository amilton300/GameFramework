package com.game.abstractClasses;

import java.util.Observable;

public abstract class SubjectTemplateMethod extends Observable {
	public final void updateEnergy() {
		doUpdateEnergy();
		setChanged();
		notifyObservers();
	}
	
	public final void reset() {
		doReset();
		setChanged();
		notifyObservers();
	}
	
	public abstract void doUpdateEnergy();
	public abstract void doReset();
	public abstract int getEnergy();
}
